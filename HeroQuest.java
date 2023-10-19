/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package supergames.heroquest;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ben Frey
 * Start: October 13th 2023
 * Finished: October 16th 2023
 * Bug fixing in progress
 * 
 * Ver. 1.1 
 *  - fully playable mostly bug free
 *  - Copeland first to end game
 *  - more features coming soon 
 * 
 * Ver. 1.2
 *  - replaced defending with healing spell
 *  - adjusted the tower accordingly
 *  - quality of life changes
 *  - UI changes
 *  - Alex play tested
 *  - Running away added
 *  - xp changes
 * 
 */
public class HeroQuest {

    public static void main(String[] args) {

        // Hero Varriables
        int sword = 1;
        int bow = 6;
        int arrows = 3;
        int armor = 3;
        int health = 10;
        int healthMax = 10;
        int gold = 0;
        int xp = 0;
        int kills = 0;
        int healSpell = 3;
        int mana = 3;
        int manaMax = 3;
        
        System.out.println("Welcome to Hero Quest! (Ver. 1.2)");
        System.out.println("This is a fantasy themed text based RPG ");
        System.out.println("What is your hero's name?");
        Scanner keyboard = new Scanner(System.in);
        String name = keyboard.next();
        System.out.println("[Oracle]: Hello " + name + " I will help you through your journey.");
        System.out.println("[Oracle]: Have your played before: [yes] or [no]");
        String yesNo = keyboard.next();
        
        if (yesNo.equalsIgnoreCase("no")){
            System.out.println();
            System.out.println("[Oracle]: Your quest is to travel to your Grandfather's house to return his amulet");
            System.out.println("=========================================================================");
            System.out.println("  You can venture further to your grandfather but might run into trouble");
            System.out.println("  You can visit towns along the way to upgrade your gear, health and sleep");
            System.out.println("  You can check your inventory to see your gear and all your stats");
            System.out.println("  Lastly you can leave when ever and you will be given a score");
            System.out.println("  I wish you luck, make your Grandfather proud. Stay strong.");
            System.out.println();
            System.out.println("[Oracle]: Combat is fairly straight forward, it goes as follows:");
            System.out.println("==========================================================");
            System.out.println("  You will fight an enemy with random health and damage depending on their type.");
            System.out.println("  You will have the choice each turn on what you want to do depending on the enemy's action.");
            System.out.println("  The enemy's next attack is revealed. Your armor subtracts damage from the enemy's attack.");
            System.out.println();
            System.out.println("  Your sword does 1 to 10 damage, plus its upgrade level which is 1 at the begining.");
            System.out.println("  Your bow does a consistant amount of damage depending on its upgrade level, which starts at level 6.");
            System.out.println("  You bow also needs arrows and without them will result in a wasted turn.");
            System.out.println("  You can cast a healing spell which heals you depending on its level, it starts at level 3.");
            System.out.println("  You are limited by your mana, but it replinishes after each fight, you start with 3.");
            System.out.println("  Lastly you can run away but your mana is not replinished.");
            System.out.println();
        } 
        System.out.println();   
        System.out.println("[Oracle]: And so your journey begins...");
        System.out.println("=====================================");
        
        // Turn choice and active boolean
        boolean active = true;
        int turn;
        
        // do while loop for your turn choice with a switch case inside
        do {
            
            int level = ((xp/100) + 1);
            
            System.out.println("[Oracle]: What would you like to do " + name + "?");
            System.out.println();
            System.out.println("  [1] Venture forward on your quest");
            System.out.println("  [2] Visit a nearby town");
            System.out.println("  [3] Check your inventory");
            System.out.println("  [4] Abandon your quest");
            System.out.println();
            turn = keyboard.nextInt();
            
            switch (turn) {
                case 1: { //venture forward
                    System.out.println("[Oracle]: You decide to continue your quest and venture into the forest");
                    System.out.println();
                    
                    // test if you have completed your quest
                    if (xp >= 500 || kills >= 20){
                        System.out.println("==========================================================================");
                        System.out.println("[Oracle]: You stumble upon a very nice house!");
                        System.out.println("[Oracle]: The sign outside says: [Elder's Flat]");
                        System.out.println("[Oracle]: Its your Grandfather's house, you've completed your quest");
                        System.out.println();
                        System.out.println("[Grandfather]: " + name + " is that you?");
                        System.out.println("[Grandfather]: Oh it is so wonderful to see you. Do you have the amulet? ");
                        System.out.println("[Grandfather]: Wow it really is just as beautiful as I had heard.");
                        System.out.println("[Grandfather]: Its actually not my amulet. It was my brother's, your Great Uncle Parish.");
                        System.out.println("[Grandfather]: I can't thank you enough for retriving this for me. Thank you " + name );
                        System.out.println("==========================================================================");
                        System.out.println("Thank you for playing Hero Quest");
                        System.out.println("I hope you have enjoyed your time playing");
                        System.exit(0);
                    }
                    
                    //Start here with main code
                    String enemy = "invalid"; 
                    String feeling = "tbd";
                    int badHealth=1, min=0, max=0, badDamage=0, weapon=1, damage=0, reward=0, encounter=1, runAway=0;
                    int event = 0;
                    
                    Random random = new Random();
                    encounter  = random.nextInt(1, 10);
                    

                    switch (encounter) {
                        case 1: // fall through to make goblin most common encounter
                        case 2:
                        case 3:
                        case 4: {
                            //Goblin Encounter Code
                            System.out.println("[Oracle]: You encounter a: GOBLIN ");
                            System.out.println("==============================================");
                            badHealth = random.nextInt((20 + sword), 32);
                            min = (armor - 3);
                            max = (7 + level);
                            enemy = "Goblin";
                            event = 0;
                            break;
                        }
                        case 5:
                        case 6:
                        case 7: {
                            //Skeleton Encounter Code
                            System.out.println("[Oracle]: You encounter a: SKELETON ");
                            System.out.println("==============================================");
                            badHealth = random.nextInt((18 + sword), 30);
                            min = 1;
                            max = 5;
                            enemy = "Skeleton";
                            event = 0;
                            break;
                        }
                        case 8:
                        case 9: {
                            //Orc Encounter Code
                            System.out.println("[Oracle]: You encounter an: ORC ");
                            System.out.println("==============================================");
                            badHealth = random.nextInt((20 + sword), 37);
                            min = (armor - 2);
                            max = (9 + level);
                            enemy = "Orc";
                            event = 0;
                            break;
                        }
                        case 10: {
                            //Story Event Code
                            System.out.println("[Oracle]: You stumble upon something...");
                            event = 1;
                            break;
                        }
                             
                    }
                    
                    reward = badHealth;
                    
                    // encounter template that will be repeated
                    if (event==0) {    
                       do {
                        badDamage = random.nextInt(min,max);
                        
                        if (enemy == "Skeleton") {
                           if (badDamage >=4) {
                             badDamage = 0;
                         } else {
                             badDamage = (3 + level);
                         }
                        } 
        
                        //health clues feeling
                        if (badHealth > 28){
                            feeling = "strong";
                        } else if (badHealth > 18){
                            feeling = "sturdy";
                        } else if (badHealth > 7){
                            feeling = "alright";
                        } else if (badHealth > 0){
                            feeling = "weak";
                        }

                        //main encounter text
                        System.out.println("[Oracle]: The " + enemy + " looks " + feeling + "...");
                        System.out.println("[Oracle]: It will be attacking you for: [" + badDamage + "] damage on its turn");
                        System.out.println();
                        System.out.println("[Health]: " + health);
                        System.out.println("[Armor]: " + armor);
                        System.out.println();
                        System.out.println("[Oracle]: What do you want to do?");
                        System.out.println();
                        System.out.println("  [1] Attack with your Sword (1|10 + " + sword + " Damage)");
                        System.out.println("  [2] Attack with your Bow (" + bow + " Damage) (" + arrows + " Arrows)");
                        System.out.println("  [3] Cast your Healing Spell (" + healSpell + " Recovery) (" + mana + " Mana)" );
                        System.out.println("  [4] Run Away");
                        System.out.println();
                        
                        weapon = keyboard.nextInt();
                        System.out.println("==============================================");
                        
                        // do while loop do avoid bugs
                        int weaponChoice; 
                        do {
                        weaponChoice = 0;     
                        //switch case for weapon choice   
                        switch (weapon) { 
                            case 1: { //sword
                                damage = random.nextInt(1, 10);
                                damage = (damage + sword);
                                System.out.println("[Oracle]: You hit the "+ enemy +" with your sword for [" + damage + " Damage]");
                                System.out.println();
                                break;
                            }
                            case 2: {//bow
                                if (arrows >=1) {
                                    damage = bow;
                                    System.out.println("[Oracle]: You hit the "+ enemy +" with an arrow for [" + damage + "] Damage");
                                    System.out.println();
                                    arrows = (arrows - 1);
                                } else {
                                    System.out.println("[Oracle]: You don't have any arrows and wasted your turn...");
                                }
                                
                                
                                break;
                            }
                            case 3: {//heal Spell
                                damage = 0;
                                if (mana >= 1) {
                                System.out.println("[Oracle]: You recover [" +  healSpell + "] health");
                                health = (health + healSpell);
                                mana = (mana - 1);
                                if (health > healthMax){
                                    health = healthMax;
                                    System.out.println("[Oracle]: You're at Max Health");
                                    System.out.println();
                                }
                                System.out.println("[Health]: " + health);
                                System.out.println("[Mana]: " + mana);
                                System.out.println();
                                
                                
                                } else {
                                    System.out.println("[Oracle]: You don't have any mana left...");
                                    System.out.println("You wasted too much time.");
                                }
                                break;
                            } 
                            case 4: { // run away
                               
                                System.out.println("[Oracle]: You try to run away...");
                                System.out.println();
                                runAway = 1;
                                break;
                            }
                            default: {
                                System.out.println("[Oracle]: That's not an option. Try again.");
                                weaponChoice = 1;
                                break;
                            }
                        }
                        } while (weaponChoice == 1);
                        
                        if (runAway == 0) { //running way if else statement
                            

                            badHealth = (badHealth - damage);
                        
                            if (badHealth >0) { 
                                if (badDamage > armor){
                                    health = (health - (badDamage - armor));
                                    System.out.println("[Oracle]: The " + enemy + " hits you. You take [" + (badDamage - armor) + "] Damage");
                                    System.out.println();
                                } else {
                                    System.out.println("[Oracle]: Your armor fully protected you. No damage taken.");
                                    System.out.println();
                                    }
                                if (health <=0){
                                System.out.println("[Oracle]: After a hard fought battle...");
                                System.out.println("[Oracle]: " + name +" has passed...");
                                System.exit(0);
                                }        
                            }
                        } else {
                            badHealth = 0; //stop the while loop
                        }
                        
                    } while (badHealth >= 1); // ending of combat
                       
                       if (runAway == 0) {
                            System.out.println("[Oracle]: The " + enemy + " has passed...");
                            System.out.println();
                            System.out.println("[Health]: " + health);
                            System.out.println("Gold: [" + gold + "] + " + reward);
                            System.out.println("XP: [" + xp + "] + " + (reward * 2));
                            System.out.println();
                            System.out.println("[Oracle]: Your mana has been replinished");
                                mana = manaMax;
                            System.out.println("[Mana]: " + mana);
                            System.out.println();
                        
                            gold = (gold + reward);
                            xp = (xp + (reward * 2));
                            kills = (kills + 1); 
                            // ending of fight
                        } else { //run away success code
                           System.out.println("[Oracle]: You get away but you're disapointed in yourself...");
                           System.out.println("[Oracle]: Your your mana is not replinished.");
                           System.out.println();
                           System.out.println("[Health]: " + health);
                           System.out.println("[Mana]: " + mana);
                           System.out.println();
                       }
                                           
                }else {
                        //event stuff
                        System.out.println();
                    
                    int eventType = random.nextInt(1,5);
                    
                    switch (eventType) {
                        case 1 : {
                            System.out.println("[Oracle]: A mysterious man walks up to you.");
                            System.out.println("=======================================");
                            System.out.println("[Steve]: Hello there, its nice to see you");
                            System.out.println("[Steve]: Let me see your sword.");
                            System.out.println("[Steve]: Alright, this should work a bit better for you.");
                            System.out.println("[Steve]: Make sure you say hello to your Grandfather for me.");
                            
                            sword = (sword + 1);
                            
                            System.out.println();
                            System.out.println("[Sword]: Level "+ sword);
                            System.out.println();
                            System.out.println("=======================================");
                            System.out.println();
                            break;
                        }
                        case 2: {
                            System.out.println("[Oracle]: A mysterious man walks up to you.");
                            System.out.println("=======================================");
                            System.out.println("[Steve]: Hey, its nice to see you");
                            System.out.println("[Steve]: Can I see your bow.");
                            System.out.println("[Steve]: Alright, this should be a bit better for you.");
                            System.out.println("[Steve]: Make sure you say hey to your Grandfather for me.");
                            
                            bow = (bow + 1);
                            
                            System.out.println();
                            System.out.println("[Bow]: Level "+ bow);
                            System.out.println();
                            System.out.println("=======================================");
                            System.out.println();
                            break;
                        }
                        case 3: {
                            System.out.println("[Oracle]: A mysterious man walks up to you.");
                            System.out.println("=======================================");
                            System.out.println("[Steve]: Whats up, its great to see you");
                            System.out.println("[Steve]: Could I check your armor.");
                            System.out.println("[Steve]: Try this on.");
                            System.out.println("[Steve]: Make sure you say hi to your Grandfather for me.");
                            
                            armor = (armor + 1);
                            
                            System.out.println();
                            System.out.println("[Armor]: Class "+ armor);
                            System.out.println();
                            System.out.println("=======================================");
                            System.out.println();
                            break;
                        }
                        case 4: {
                            System.out.println("[Oracle]: A mysterious man walks up to you.");
                            System.out.println("=======================================");
                            System.out.println("[Steve]: No way, fancy seeing you here");
                            System.out.println("[Steve]: I might actually have something for you.");
                            System.out.println("[Steve]: Here, you can have these, I have plenty more at home.");
                            System.out.println("[Steve]: Make sure to tell your Grandfather I said hi");
                            
                            arrows = (arrows + 2);
                            
                            System.out.println();
                            System.out.println("[Arrows]: " + arrows);
                            System.out.println();
                            System.out.println("=======================================");
                            System.out.println();
                            break;
                        }
                        case 5: {
                            System.out.println("[Oracle]: A mysterious man walks up to you.");
                            System.out.println("=======================================");
                            System.out.println("[Steve]: Look who the cat dragged in");
                            System.out.println("[Steve]: I got a treat for you.");
                            System.out.println("[Steve]: Here, drink this potion.");
                            System.out.println("[Steve]: say hey to your grandfather for me.");
                            
                            health = (arrows + 2);
                            if (health > healthMax) {
                                health = healthMax;
                            }
                            
                            System.out.println();
                            System.out.println("[Health]: " + health);
                            System.out.println();
                            System.out.println("=======================================");
                            System.out.println();
                            break;
                        }
                    }
                    
                    }
                    break; //adventure case break ending
                }
   
                case 2: { // visit a town
                    
                //Start here with main code    
                int town = 0;
                System.out.println("[Oracle]: You decide to find a town");
                   
                    
                do {
                    System.out.println("=======================================");
                    System.out.println();
                    System.out.println("[Oracle]: This town looks very familiar. ");
                    System.out.println("[Gold]: " + gold);
                    System.out.println();                  
                    System.out.println("[Oracle]: Where do you want to visit?");
                    System.out.println("  [1] The Local Inn ");
                    System.out.println("  [2] The Blacksmith");
                    System.out.println("  [3] The Market");
                    System.out.println("  [4] The Tower");
                    System.out.println("  [5] Leave the town");
                    System.out.println();
                    
                    int shop = keyboard.nextInt();
                    
                    System.out.println("=======================================");
                    switch (shop) {
                        
                        case 1: { //the inn

                            System.out.println("[The local Inn]: (25 Gold) Heals to max health and replinishes your mana");
                            System.out.println("[Gold]: " + gold);                            
                            System.out.println("[Craig]: Hello, welcome to the Inn!");
                            
                            if (health == healthMax){   
                                System.out.println("[Craig]: Looks like you're already at full health.");
                                System.out.println("[Craig]: Come back when you need some rest.");
                            }else {
                                System.out.println("[Craig]: Do you want to stay for the night: [yes] or [no]");
                            yesNo = keyboard.next();
        
                            if (yesNo.equalsIgnoreCase("yes")){
                                if (gold >= 25) {
                                    health = healthMax;
                                    mana = manaMax;
                                    gold = (gold - 25);
                                    System.out.println("[Oracle]: You wake up feeling great.");
                                    System.out.println("[Health]: " + health);
                                    System.out.println("[Mana]: " + mana);
                                    System.out.println("[Craig]: Come back and see me again!");
                                } else {
                                    System.out.println("[Oracle]: You can't afford that");
                                }
                            } else {
                                System.out.println("[Oracle]: You leave the Inn.");
                                System.out.println("[Craig]: Hope to see you back.");
                                }
                            }
                            break;
                        }
                        
                        case 2: { //The blacksmith

                            System.out.println("[The Blacksmith]: Upgrade your Sword and Armor");
                            System.out.println("[Gold]: " + gold);  
                            System.out.println();
                            System.out.println("[Clint]: What can I do for ya?");
                            System.out.println("  [1]: Upgrade your armor (" + (armor * 10) + " Gold)");
                            System.out.println("  [2]: Upgrade your sword (" + (sword * 10) + " Gold)");
                            System.out.println("  [3]: Leave the store");
                            int blacksmith = keyboard.nextInt();
                            
                            switch (blacksmith) {
                                case 1: {
                                   if (gold >= (armor * 10)) {
                                       gold = (gold - (armor * 10));
                                       armor = (armor + 1);
                                       System.out.println("[Clint]: That should keep you a little safer.");
                                       System.out.println("[Armor]: " + armor);
                                       System.out.println("[Clint]: Hope to see ya again.");
                                   } else {
                                    System.out.println("[Oracle]: You can't afford that.");   
                                   }
                                   
                                   break;
                                } 
                                case 2: {
                                    if (gold >= (sword * 10)) {
                                       gold = (gold - (sword * 10));
                                       sword = (sword + 1);
                                       System.out.println("[Clint]: This sword sure will hurt.");
                                       System.out.println("[Sword]: " + sword);
                                       System.out.println("[Clint]: See ya later.");
                                   } else {
                                    System.out.println("[Oracle]: You can't afford that.");   
                                   }
                                    break;
                                }  
                                case 3: {
                                    System.out.println("[Oracle]: You leave the blacksmith");
                                    System.out.println("[Clint]: See ya later.");
                                    break;
                                }
                                       
                            }
                            break;
                        }
                        
                        case 3: { //The market

                            System.out.println("[The Market]: Upgrade your Bow and buy arrows");
                            System.out.println("[Gold]: " + gold); 
                            System.out.println();
                            System.out.println("[Ethel]: Why hello dear, how can I help you?");
                            System.out.println("  [1]: Upgrade your Bow (" + (bow * 10) + " Gold)");
                            System.out.println("  [2]: Buy 3 arrows (30 Gold)");
                            System.out.println("  [3]: Leave the store");
                            int market = keyboard.nextInt();
                            
                            switch (market) {
                                case 1: {
                                   if (gold >= (bow * 10)) {
                                       gold = (gold - (bow * 10));
                                       bow = (bow + 1);
                                       arrows = (arrows + 2);
                                       System.out.println("[Ethel]: That should help you out dear.");
                                       System.out.println("[Ethel]: I threw in a couple arrows too, thank you");
                                       System.out.println("[Bow]: " + bow);
                                       System.out.println("[Arrows]: " + arrows);
                                       System.out.println("[Ethel]: Stay safe dear.");
                                   } else {
                                    System.out.println("[Oracle]: You can't afford that.");   
                                   }
                                   
                                   break;
                                } 
                                case 2: {
                                    if (gold >= 20) {
                                       gold = (gold - 20);
                                       arrows = (arrows + 3);
                                        System.out.println("[Ethel]: Made these myself.");
                                       System.out.println("[Arrows]: "+ arrows);
                                       System.out.println("[Ethel]: You stay out of trouble now.");
                                   } else {
                                    System.out.println("[Oracle]: You can't afford that.");   
                                   }
                                    break;
                                }  
                                case 3: {
                                    System.out.println("[Oracle]: You leave the market");
                                    System.out.println("[Ethel]: Look forward to seeing you again.");
                                    break;
                                }
                                       
                            }
                            break;   
                        }
                        case 4: { //the tower

                            System.out.println("[The Tower]: This is where you can upgrade your healing spell and mana");
                            System.out.println("[Gold]: " + gold);
                            System.out.println();
                            System.out.println("[Hairy]: Hey man, what's up?");
                            System.out.println("  [1]: Upgrade your spell (" + (healSpell * 20) + " Gold)");
                            System.out.println("  [2]: Increase your maximum mana (" + (manaMax * 15) + ")");
                            System.out.println("  [3]: Leave the store");
                            int tower = keyboard.nextInt();
                            
                            switch (tower) {
                                case 1: {
                                    if (gold >= (healSpell * 20)) {
                                        gold = (gold - (healSpell * 20));
                                        healSpell = (healSpell + 1); 
                                        System.out.println("[Hairy]: Your spell will be more powerful now.");
                                        System.out.println("[Healing Spell]: " + healSpell + " Recovery");
                                        System.out.println("[Hairy]: Alright my man come back and see me sometime");
                                    } else {
                                        System.out.println("[Oracle]: You can't afford that.");
                                    }
                                    break;
                                }
                                case 2: {
                                    if (gold >= (manaMax * 15)) {
                                        gold = (gold - (manaMax * 20));
                                        manaMax = (manaMax + 1); 
                                        System.out.println("[Hairy]: You can cast heal more now.");
                                        System.out.println("[Mana]: " + manaMax);
                                        System.out.println("[Hairy]: Alrighty now, I'll be talking to you later");
                                    } else {
                                        System.out.println("[Oracle]: You can't afford that.");
                                    }
                                    break;
                                }
                                case 3: {
                                 System.out.println("[Oracle]: You leave the tower.");
                                 System.out.println("[Hairy]: See you around."); 
                                 break;
                                }
                            } 
                            break;
                        }
                        case 5: { //leave
                            System.out.println("[Oracle]: You leave the town");
                            town = 1;
                            break;
                        }
                    }
                            
                    
                } while (town==0);
                    
                    
                    break;
                }
                    
                case 3:{ //check your inventory
                    System.out.println("[Oracle]: You decide to check your inventory");
                    System.out.println();
                    
                    //Start here with main code
                    System.out.println("[INVENTORY]");
                    System.out.println("================================");
                    System.out.println("[ITEMS]");
                    System.out.println("  [Armor Class]: " + armor);
                    System.out.println("  [Sword]: Level " + sword);
                    System.out.println("  [Bow]: Level " + bow);
                    System.out.println("  [Arrows]: " + arrows);
                    System.out.println("  [Healing Spell]: " + healSpell + " Recovery");
                    System.out.println("  [Mana]: " + manaMax);
                    System.out.println("  [Amulet]: You notice the number 500 etched on the front");
                    System.out.println("  and there are 20 stones around the edge.");
                    System.out.println("================================");
                    System.out.println("[STATS]");
                    System.out.println("  [Health]: " + health);
                    System.out.println("  [Gold]: " + gold);
                    System.out.println("  [XP]: " + xp);
                    System.out.println("  [Level]: " + level);
                    System.out.println("  [Kills]: " + kills);
                    System.out.println("================================");
                    System.out.println();
                    break;
                }
                    
                case 4:{ // abandon your quest
                    System.out.println("[Oracle]: You abondon your quest and return home");
                    System.out.println();
                    System.out.println("[XP]: " + xp);
                    System.out.println();
                    
                    //Start here with main code
                    System.out.println("[Oracle]: Your grandfather is disapointed...");
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("[Oracle]: That's not an option. Try again");
                    break;
                }
                    
            }   
        } while (active);
        
        // end game stuff, complete the quest
        
        System.out.println(); 
        System.out.println("Thanks for playing");
    }
}
