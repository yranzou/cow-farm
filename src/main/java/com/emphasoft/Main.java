package com.emphasoft;

public class Main {
    public static void main(String[] args) {

        AbstractFarm farm = new FarmQuestion2();
        farm.giveBirth(0,1,"Фрося");
        farm.giveBirth(1,2,"Машка Фроськина дочь");
        farm.giveBirth(1,3,"Люська Фроськина");
        farm.giveBirth(2,4,"Суицидальная корова");
        farm.endLifeSpan(4);
        farm.printFarmData();
    }
}

