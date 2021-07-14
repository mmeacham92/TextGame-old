package com.meacham;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

//        Graphics2D graphics2D = (Graphics2D) Graphics2D;
//        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//        graphics2D.drawString("BAELDUNG", 12, 24);

        // System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Game Variable
        String[] enemies = {"Skeleton Warrior", "Zombie Knight", "Phantom Assassin",
                "Ninja Vampire", "Demon Orc", "Undead Ape"};
        int maxEnemyHealth = 40;
        int enemyMaxHit = 22;
        int enemyHit;
        boolean isFinalFight = false;
        boolean isDefeated = false;
        boolean isFirstTurn = true;

        // Player variables
        int playerMaxHealth = 100;
        int playerHealth = playerMaxHealth;
        int playerMaxHit = 30;
        int playerHit;
        int criticalHit = (int) Math.round(playerMaxHit / 1.2);
        int currentTilePosition = 0;
        int finalTile = 25;

        // Item variables
        int startingHealthPotions = 3;
        int numHealthPotions = startingHealthPotions;
        int healthPotionValue = 35;
        int costOfHealthPotion = 150;
        int healthPotionDropChance = 20; // Percentage chance of drop
        int coinPouch = 0;

        boolean running = true;

//        System.out.println("\n\n\n\tWelcome to Meacham's Lair!\n");

        System.out.println("\n\n\t\t#     #                                           ###           #                       \n" +
                "\t\t##   ## ######   ##    ####  #    #   ##   #    # ###  ####     #         ##   # #####  \n" +
                "\t\t# # # # #       #  #  #    # #    #  #  #  ##  ##  #  #         #        #  #  # #    # \n" +
                "\t\t#  #  # #####  #    # #      ###### #    # # ## # #    ####     #       #    # # #    # \n" +
                "\t\t#     # #      ###### #      #    # ###### #    #          #    #       ###### # #####  \n" +
                "\t\t#     # #      #    # #    # #    # #    # #    #     #    #    #       #    # # #   #  \n" +
                "\t\t#     # ###### #    #  ####  #    # #    # #    #      ####     ####### #    # # #    #");

        System.out.println("\t-----------------------------------------------------------------------------------------------\n");
        GAME:
        while (running) {

            if (isFirstTurn) {
                System.out.println("\n\t\t\t\t\t\t\t\033[3m'WHOMEVER DARETH ENTER OFFERTH THY SOUL'\t\033[0m");
                System.out.println("\n\n\n\n\tEnter Meacham's Lair?: ");
                System.out.println("\t1. Yes\n\t2. No");
                String startUp = in.nextLine();
                if (startUp.equals("1")) {
                    isFirstTurn = false;
                    continue;
                } else break GAME;
            }

            System.out.println("\t-----------------------------------------------------------------------------------------------\n");

            int stepsTaken = 0;
            int maxStepsTaken = 3;
            for (int i = 1; i < maxStepsTaken; i++) {
                currentTilePosition++;
                stepsTaken++;

                if (currentTilePosition == finalTile) {
                    isFinalFight = true;
                    break;
                } else if (currentTilePosition == 1) {
                    break;
                }
                int encounterChance = 20;
                int encounterRollPerTile = rand.nextInt(100);

                if (encounterChance >= encounterRollPerTile) {
                    break;
                }
            }
            System.out.println("\t> You advance " + stepsTaken + " floors.\n\n");
            System.out.println("\tCurrent Floor No. " + currentTilePosition);
            String enemy = enemies[rand.nextInt(enemies.length)]; // create new enemy spawn
            int enemyHealth = rand.nextInt(maxEnemyHealth) + 1; // assign new enemy HP

            if (isFinalFight) {
                String finalBoss = "Meacham, the Time Lord of Destiny";
                int bossHealth = 130;
                int bossMaxHit = 40;
                enemyHealth = bossHealth;
                enemyMaxHit = bossMaxHit;
                enemy = finalBoss;
            }

            System.out.println("\n\t# " + enemy + " has appeared! #\n");

            while (enemyHealth > 0) {
                System.out.println("\n\tYour HP: " + playerHealth + "/" + playerMaxHealth);

                if (isFinalFight) {
                    System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                } else {
                    System.out.println("\tThe " + enemy + "'s HP: " + enemyHealth);
                }

                System.out.println("\n\n\tWhat would you like to do?\n");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion (+35 HP) ----- x" + numHealthPotions);
                System.out.println("\t3. Run");
//                System.out.println("\t4. Exit dungeon");
                String input = in.nextLine();

                if (input.equals("1")) {
                    playerHit = rand.nextInt(playerMaxHit);
                    enemyHit = rand.nextInt(enemyMaxHit);

                    if (playerHit >= criticalHit) {
                        System.out.println("\t> Critical Hit!\n");
                    } else if (playerHit == 0) {
                        System.out.println("\t> Attack missed! Oh no!");
                    }

                    if (enemyHealth <= playerHit) {
                        enemyHealth = 0;
                        System.out.println("\t> You hit for " + playerHit + " damage!");

                        if (isFinalFight) {
                            System.out.println("\t> " + enemy + " has been slain! Congratulations!");
                            isDefeated = true;
                        } else {
                            System.out.println("\t> The " + enemy + " was knocked out!");
                        }
                        int coinDropAmount = rand.nextInt(50);

                        if (isDefeated) {
                            coinDropAmount *= 100;
                        }
                        coinPouch += coinDropAmount;
                        System.out.println("\t> You found " + coinDropAmount + "gp! You now have " +
                                coinPouch + "gp.");
                        int dropRoll = rand.nextInt(100);

                        if (dropRoll <= healthPotionDropChance && !isFinalFight) {
                            numHealthPotions++;
                            System.out.println("\t> The " + enemy + " dropped a health potion! Nice!\n");
                        }
                        System.out.println("\t-------------------------------------------------------\n");
                    } else {
                        playerHealth -= enemyHit;
                        enemyHealth -= playerHit;
                        System.out.println("\t> You attacked for " + playerHit + " damage!");

                        if (isFinalFight) {
                            System.out.println("\t> " + enemy + " has " + enemyHealth + " HP remaining.");
                            System.out.println("\t> " + enemy + " hit for " + enemyHit + "!");
                        } else {
                            System.out.println("\t> The " + enemy + " has " + enemyHealth + " HP remaining.");
                            System.out.println("\n\t> The " + enemy + " hit for " + enemyHit + "!");
                        }

                        if (playerHealth <= 0) {
                            running = false;
                            playerHealth = 0;
                            System.out.println("\t>>> You have lost all of your HP! GAME OVER!!!!");
                            System.out.println("\n\t#######################################");
                            System.out.println("\t############## GAME OVER ##############");
                            System.out.println("\t#######################################");
                            System.out.println("\n-------------------------------------------------------\n");
                            System.out.println("\n\tWould you like to enter the dungeon again?");
                            System.out.println("\t1. Yeah baby!");
                            System.out.println("\t2. Fuck no!!!!");
                            String willContinue = in.nextLine();

                            if (willContinue.equals("1")) {
                                playerHealth = playerMaxHealth;
                                currentTilePosition = 0;
                                running = true;
                                enemyMaxHit = 22;
                                isFinalFight = false;
                                numHealthPotions = startingHealthPotions;
                                continue GAME;
                            } else {
                                break GAME;
                            }
                        } else {
                            System.out.println("\t> You have " + playerHealth + " HP remaining.");
                        }

                        System.out.println("\n-------------------------------------------------------\n");
                    }

                } else if (input.equals("2")) {

                    if (numHealthPotions > 0) {

                        if ((playerHealth + healthPotionValue) >= playerMaxHealth) {
                            int amountHealed = playerMaxHealth - playerHealth;
                            playerHealth = playerMaxHealth;
                            numHealthPotions--;
                            System.out.println("\tYou drank a health potion.");
                            System.out.println("\t> You healed " + amountHealed + " HP! Fully restored!");
                        } else {
                            playerHealth += healthPotionValue;
                            numHealthPotions--;
                            System.out.println("\t> You healed " + healthPotionValue + " HP!");
                            System.out.println("\t> You now have " + playerHealth + " HP.");
                        }
                        enemyHit = rand.nextInt(enemyMaxHit);

                        if (playerHealth <= enemyHit) {
                            playerHealth = 0;
                            running = false;
                            System.out.println("\n\t> The " + enemy + " hit for " + enemyHit + "!");
                            System.out.println("\t> You have lost all of your HP!\n");
                            System.out.println("\n\t#######################################");
                            System.out.println("\t############## GAME OVER ##############");
                            System.out.println("\t#######################################");
                            System.out.println("\n-------------------------------------------------------\n");
                            System.out.println("\n\tWould you like to enter the dungeon again?");
                            System.out.println("\t1. Yeah baby!");
                            System.out.println("\t2. Fuck no!!!!");
                            String willContinue = in.nextLine();

                            if (willContinue.equals("1")) {
                                playerHealth = playerMaxHealth;
                                currentTilePosition = 0;
                                enemyMaxHit = 22;
                                numHealthPotions = startingHealthPotions;
                                running = true;
                                isFinalFight = false;
                                continue GAME;
                            } else {
                                System.out.println("Pussy");
                                break GAME;
                            }
                        } else {
                            playerHealth -= enemyHit;
                            System.out.println("\n\t> The " + enemy + " hit for " + enemyHit + "!");
                            System.out.println("\t> You have " + playerHealth + " HP remaining.");
                            System.out.println("\n-------------------------------------------------------\n");
                        }

                    } else {
                        System.out.println("\tYou are out of health potions! Careful!\n");
                    }
                } else if (input.equals("3")) {
                    int runSuccessChance = 80;
                    int runRoll = rand.nextInt(100);
                    System.out.println("\t> You attempt to run away.");

                    if (isFinalFight) {
                        System.out.println("\t>>> Its too late to run, you insolent fool!!!");
                        enemyHit = rand.nextInt(enemyMaxHit);
                        playerHealth -= enemyHit;
                        System.out.println("\n\t> " + enemy + " hit for " + enemyHit + "!");
                    } else if (runSuccessChance >= runRoll) {
                        System.out.println("\t> You got away safely!\n");
                        continue GAME;
                    } else {
                        System.out.println("\t> The " + enemy + " blocks your path! You can't get away!\n");
                        enemyHit = rand.nextInt(enemyMaxHit);
                        playerHealth -= enemyHit;
                        System.out.println("\n\t> The " + enemy + " hit for " + enemyHit + "!");
                    }
                    if (playerHealth <= enemyHit) {
                        running = false;
                        playerHealth = 0;
                        System.out.println("\t> You have lost all of your HP!");
                        System.out.println("\n\t#######################################");
                        System.out.println("\t############## GAME OVER ##############");
                        System.out.println("\t#######################################");
                        System.out.println("\n-------------------------------------------------------\n");
                        System.out.println("\n\tWould you like to enter the dungeon again?");
                        System.out.println("\t1. Yeah baby!");
                        System.out.println("\t2. Fuck no!!!!");
                        String willContinue = in.nextLine();

                        if (willContinue.equals("1")) {
                            playerHealth = playerMaxHealth;
                            currentTilePosition = 0;
                            running = true;
                            enemyMaxHit = 22;
                            numHealthPotions = startingHealthPotions;
                            isFinalFight = false;
                            continue GAME;
                        } else {
                            break GAME;
                        }
                    } else {
                        System.out.println("\t> You have " + playerHealth + " HP remaining.");
                        System.out.println("\n-------------------------------------------------------\n");
                    }
                }
//                  else if (input.equals("4")) {
//                    System.out.println("\t\033[3mPussy\033[0m");
//                    break GAME;
//                }
                    else {
                    System.out.println("\t Stop that! Choose a valid option.");
                }
            }
            if (isDefeated) {
                System.out.println("\t##########################################");
                System.out.println("\t### YOU REACHED THE END OF THE DUNGEON ###");
                System.out.println("\t################ WINNER! #################");
                System.out.println("\t##########################################");

                System.out.println("\n-------------------------------------------------------\n");
                System.out.println("\n\n\tWould you like to enter the dungeon again?");
                System.out.println("\t1. Yeah baby!");
                System.out.println("\t2. Fuck no!!!!");
                String willContinue = in.nextLine();

                if (willContinue.equals("1")) {
                    playerHealth = playerMaxHealth;
                    currentTilePosition = 0;
                    enemyMaxHit = 22;
                    running = true;
                    numHealthPotions = startingHealthPotions;
                    isFinalFight = false;
                    isDefeated = false;
                    continue GAME;
                } else {
                    break GAME;
                }
            }

            continueMenu();
            String continueInput = in.nextLine();

            if (continueInput.equals("1")) {
                continue GAME;
            } else if (continueInput.equals("2")) {
                while (continueInput.equals("2")) {
                    if (coinPouch >= costOfHealthPotion) {
                        numHealthPotions++;
                        coinPouch -= costOfHealthPotion;
                        System.out.println("\n\t> You purchased a health potion!" +
                                "\n\t> You now have " + numHealthPotions + " health potions.");
                        System.out.println("\t> Remaining Coins: " + coinPouch);
                        continueMenu();
                        continueInput = in.nextLine();
                    } else {
                        while (continueInput.equals("2")) {
                            System.out.println("\n\t>>> Not enough gold pieces!");
                            continueMenu();
                            continueInput = in.nextLine();
                            if (continueInput.equals("3")) {
                                break GAME;
                            }
                        }
                    }
                }
            } else {
                System.out.println("\t>>>> QUITTER!!");
                break GAME;
            }
        }
    }

    public static void continueMenu() {
        System.out.println("\n\t-------------------------------------------------------\n");
        System.out.println("\tWhat would you like to do?: ");
        System.out.println("\t1. Continue through dungeon");
        System.out.println("\t2. Buy potion (150gp)");
        System.out.println("\t3. Exit dungeon");
    }

}
