package com.emphasoft;

import com.emphasoft.exceptions.CowAlreadyExistsException;
import com.emphasoft.exceptions.CowIsDeadException;
import com.emphasoft.exceptions.CowIsNotExistsException;
import com.emphasoft.exceptions.InitialCowCannotBeDeadException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;

import java.util.Optional;

@Getter
abstract class AbstractFarm {
    private final Cow rootCow;
    private final ObjectMapper objectMapper;
    int ROOT_COW_ID = 0;
    static final String ROOT_COW_NICKNAME = "rootCow";

    AbstractFarm() {
        this.rootCow = getNewCow(ROOT_COW_ID, ROOT_COW_NICKNAME);
        this.objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void giveBirth(int parentCowId, int childCowId, String nickName) {
        if (findCow(childCowId).isPresent()) {
            throw new CowAlreadyExistsException(childCowId);
        }
        var parent = findCow(parentCowId).orElseThrow(() -> new CowIsNotExistsException(parentCowId));
        validateCowIsNotDead(parent);
        var calf = getNewCow(childCowId, nickName);
        addChild(parent, calf);
    }

    public void endLifeSpan(int cowId) {
        validateCowIsNotInitial(cowId);
        var cow = findCow(cowId).orElseThrow(() -> new CowIsNotExistsException(cowId));
        validateCowIsNotDead(cow);
        cow.setAlive(false);
    }

    public void printFarmData() {
        try {
            System.out.println(objectMapper.writeValueAsString(rootCow));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateCowIsNotInitial(int cowId) {
        if (cowId == ROOT_COW_ID) {
            throw new InitialCowCannotBeDeadException(cowId);
        }
    }

    public void validateCowIsNotDead(Cow cow) { // todo move to interface or abstract class
        if (!cow.isAlive()) {
            throw new CowIsDeadException(cow.getCowId());
        }
    }

    public abstract Cow getNewCow(int cowId, String nickName);

    public abstract Optional<Cow> findCow(int childCowId);

    protected abstract void addChild(Cow parent, Cow calf);
}
