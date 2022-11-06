package com.emphasoft;


import com.emphasoft.exceptions.CowAlreadyExistsException;
import com.emphasoft.exceptions.CowIsDeadException;
import com.emphasoft.exceptions.CowIsNotExistsException;
import com.emphasoft.exceptions.InitialCowCannotBeDeadException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class FarmQuestion2Test {
    private AbstractFarm farm;
    private CowQuestion2 expectedRootCowInitial;
    private CowQuestion2 expectedRootCowPlusOne;
    private CowQuestion2 expectedRootCowMinusOne;

    @Before
    public void prepare() throws IOException {
        farm = new FarmQuestion2();
        var mapper = new ObjectMapper();
        expectedRootCowInitial = mapper.readValue(
                readFromResources("src/test/resources/initial.json"),
                CowQuestion2.class
        );
        expectedRootCowPlusOne = mapper.readValue(
                readFromResources("src/test/resources/plus-one-cow.json"),
                CowQuestion2.class
        );
        expectedRootCowMinusOne = mapper.readValue(
                readFromResources("src/test/resources/minus-one-cow.json"),
                CowQuestion2.class
        );
    }

    private String readFromResources(String path) {
        String result = "";
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            result = reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Test
    public void giveBirth() {
        farm.giveBirth(0, 1, "Фрося");
        farm.giveBirth(1, 2, "Машка Фроськина дочь");
        farm.giveBirth(1, 3, "Люська Фроськина");

        // Compare two equal farms
        assertEquals(farm.getRootCow(), expectedRootCowInitial);

        // Change actual farm
        farm.giveBirth(2, 4, "Суицидальная корова");

        // Compare two not equal farms
        assertNotSame(farm.getRootCow(), expectedRootCowInitial);

        // compare two equals farms when one cow added
        assertEquals(farm.getRootCow(), expectedRootCowPlusOne);
    }

    @Test
    public void endLifeSpanTest() {
        farm.giveBirth(0, 1, "Фрося");
        farm.giveBirth(1, 2, "Машка Фроськина дочь");
        farm.giveBirth(1, 3, "Люська Фроськина");
        farm.giveBirth(2, 4, "Суицидальная корова");
        farm.endLifeSpan(4);

        // compare two equals farms with one dead cow
        assertEquals(farm.getRootCow(), expectedRootCowMinusOne);
    }

    @Test(expected = CowIsNotExistsException.class)
    public void shouldFailWhenParentCowNotExist() {
        // test that cow id 1 is not exists
        farm.giveBirth(1, 1, "Фрося");
    }

    @Test(expected = CowIsDeadException.class)
    public void shouldFailIfParentCowIsDead() {
        // test that dead cow can`t give birth
        farm.giveBirth(0, 1, "Фрося");
        farm.endLifeSpan(1);
        farm.giveBirth(1, 2, "Grrrr");
    }

    @Test(expected = CowAlreadyExistsException.class)
    public void cowAlreadyExistsTest() {
        // test that cow can`t give birth to cow with existing id
        farm.giveBirth(0, 1, "Фрося");
        farm.giveBirth(0, 2, "Jane");
        farm.giveBirth(1, 2, "Grace");
    }

    @Test(expected = InitialCowCannotBeDeadException.class)
    public void shouldFailIfTryToKillInitialCow() {
        // test that initial cow can`t end lifespan
        farm.endLifeSpan(0);
    }
}