package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 850;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {210, 230, 250, 200};
    public static int[] heroesDamage = {40, 20, 30, 40};
    public static String[] heroesAttackType = {"Physycal",
            "Magical", "Kinetic", "Medical"};

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); //0,1,2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void round() {
        changeBossDefence();
        bossHit();
        menTried();
        heroesHit();
        if (bossHealth >0){
            bossHit();

        }
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("_____________________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i]);
        }
        System.out.println("_____________________");
    }
    public static void menTried() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[3] < 0) {
                heroesHealth[3] = 0;
            }
            if (heroesHealth[3] > 0) {
                heroesHealth[i] = heroesHealth[i] + heroesDamage[3];
                if (heroesHealth[i] == heroesHealth[3]) {// барына бирдей берет.
                    heroesHealth[3] = heroesHealth[3] - heroesDamage[3];
                } else if (heroesHealth[i] < 0) {
                    heroesHealth[i] = heroesHealth[i];
                } else {
                    heroesHealth[i] = heroesHealth[i];
                }
            }
        }
    }

    public static void bossHit(){
        for (int  i= 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i].equals(bossDefence)) {
                    Random r = new Random();
                    int coef = r.nextInt(5) + 2; //2,3,4,5,6
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttackType[i] +
                            " hits " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }
}
