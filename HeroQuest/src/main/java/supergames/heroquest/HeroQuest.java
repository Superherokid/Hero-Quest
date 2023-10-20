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
 * 
 * Ver. 1.3
 *  - Level up system increases health
 *  - save state password
 *  - new tutorial text
 * 
 * Ver. 1.4
 *  - Full story with boss
 *  - reward, increases your strength 
 *  - save state new game plus
 *  - New dog in the village
 *  - updated dialog
 *  - final game other than bug and balances
 */

public class HeroQuest {

    public static void main(String[] args) {

        // Hero Varriables
        int sword = 1;
        int strength = 1;
        int bow = 6;
        int arrows = 3;
        int armor = 3;
        int health = 10;
        int healthMax = 10;
        int gold = 0;
        int xp = 0;
        int level = 1;
        int levelUp = 1;
        int kills = 0;
        int healSpell = 3;
        int mana = 3;
        int manaMax = 3;
        
        
        
        System.out.println("Welcome to Hero Quest! (Ver. 1.4)");
        System.out.println("This is a fantasy themed text based RPG ");
        System.out.println("What is your hero's name?");
        Scanner keyboard = new Scanner(System.in);
        String name = keyboard.next();
        System.out.println("[Oracle]: Hello " + name + " I'm your Game Master and will help you through your journey.");
        System.out.println("[Oracle]: Have your played before: [yes] or [no]");
        String yesNo = keyboard.next();
        
        if (yesNo.equalsIgnoreCase("no")){
            System.out.println("=============================================================================");
            System.out.println();
            System.out.println("[Story]: You're quest is to return am amulet to your grandfather.");
            System.out.println();
            System.out.println("[Gameplay Loop]: You can choose what you want to do on your quest. You can: ");
            System.out.println("  Venture forward: Does a random combat event where you fight an enemy and are rewarded");
            System.out.println("  Go to the town: You can visit the town to upgrade all your gear, spells, purchase arrows, and sleep");
            System.out.println("  Check your inventory: Shows all your gear's stats and your player stats ");
            System.out.println("  Abandon your quest: This is how to save and quit the game. ");
            System.out.println();
            System.out.println("=============================================================================");
            System.out.println();
            System.out.println("[Combat]: You fight a random enemy with random health and random attacks all dependent on its type");
            System.out.println("  Goblins: Regular enemy, nothing special");
            System.out.println("  Skeletons: Use a bow so their damage is consistent during combat but they can miss");
            System.out.println("  Orcs: Stronger version of goblins");
            System.out.println();
            System.out.println("=============================================================================");
            System.out.println();
            System.out.println("[Options on your turn]: The enemies next attack is always revealed and you attack first");
            System.out.println("  Sword: 1 to 10 damage (1d10) plus your sword level ");
            System.out.println("  Bow: consistant damge every turn, no randomization but it uses arrows ");
            System.out.println("  Healing Spell: Recovers health equal to its current level, consumes Mana which is replenished at the end of each combat.");
            System.out.println("  Run away: You run away but can't replenish your mana back so your next fight will be much harder");
            System.out.println();
            
        } else {
            System.out.println("Do you have a SaveState Password? [yes] or [no]");
            String answer = keyboard.next();
            
            if (answer.equalsIgnoreCase("yes")) { //save state
                System.out.println();
                System.out.println("Please paste your SaveState Password: ");
                sword = keyboard.nextInt();
                strength = keyboard.nextInt();
                bow = keyboard.nextInt();
                arrows = keyboard.nextInt();
                armor = keyboard.nextInt();
                health = keyboard.nextInt();
                healthMax = keyboard.nextInt();
                gold = keyboard.nextInt();
                xp = keyboard.nextInt();
                level = keyboard.nextInt();
                kills = keyboard.nextInt();
                healSpell = keyboard.nextInt();
                mana = keyboard.nextInt();
                manaMax = keyboard.nextInt();
                
                //decryption
                sword = (sword / 7) ;
                strength = (strength / 7);
                bow = (bow / 7);
                arrows = (arrows / 7);
                armor = (armor / 7);
                health = (health / 7);
                healthMax = (healthMax / 7);
                gold = (gold / 7);
                xp = (xp / 7);
                level = (level / 7);
                kills = (kills / 7);
                healSpell = (healSpell / 7);
                mana = (mana / 7);
                manaMax = (manaMax / 7);
                System.out.println();
            } else {
                System.out.println();
                System.out.println("[Oracle]: No problem");
            }
        }
        System.out.println();   
        System.out.println("[Oracle]: And so your journey begins...");
        System.out.println("=====================================");
        
        // Turn choice and active boolean
        int active = 0;
        int turn;
        
        // do while loop for your turn choice with a switch case inside
        do {
            // Level up
            levelUp = level;
            level = ((xp/100) + 1);
            
            if (level > levelUp) {
                System.out.println("[Oracle]: Level up!");
                System.out.println("[Level]: "+ level);
                System.out.println("[Max Health]: "+healthMax + " + " + (level - levelUp));
                healthMax = (10 + (level - 1)); // might lead to bug
                System.out.println("[Oracle]: You feel much stronger");
                System.out.println();
            }
            
            
            System.out.println("[Oracle]: What would you like to do " + name + "?");
            System.out.println();
            System.out.println("  [1] Venture forward on your quest");
            System.out.println("  [2] Visit a nearby town");
            System.out.println("  [3] Check your inventory");
            System.out.println("  [4] Abandon your quest(Save and Quit)");
            System.out.println();
            turn = keyboard.nextInt();
            
            switch (turn) {
                case 1: { //venture forward
                    System.out.println("[Oracle]: You decide to continue your quest and venture into the forest");
                    System.out.println();

                    //Start here with main code
                    String enemy = "invalid"; 
                    String feeling = "tbd";
                    int badHealth=1, min=0, max=0, badDamage=0, weapon=1, damage=0, reward=0, encounter=1, runAway=0, badArmor=0;
                    int event = 0;
                    
                    Random random = new Random();
                    encounter  = random.nextInt(1, 10);
                    // Boss fight
                    if (kills == 10){ //stage 1 of boss fight
                        encounter = 11;   
                    }
                    if (kills == 20){ //stage 2 of boss fight
                        encounter = 12;   
                    }
                    if (kills == 30){ //stage 3 of boss fight
                        encounter = 13;   
                    }
                    
                    
                    
                    //start of a normal encounter
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
                        case 11: { //boss fight stage 1
                            System.out.println("[Oracle]: The ground shakes as you see trees colapse in the distance...");
                            System.out.println();
                            System.out.println("[Oracle]: You encounter: Hughmungus the Troll!");
                            System.out.println("==============================================");
                            badHealth = random.nextInt(40, 50); //stage 1 health
                            min = 1; //throwing rocks
                            max = 3; 
                            enemy = "Troll";
                            event = 0;
                            break;    
                        }
                        case 12: { //boss fight stage 2
                            System.out.println("[Oracle]: The ground shakes as you see trees colapse in the distance...");
                            System.out.println("[Oracle]: This time weilding a massive club...");
                            System.out.println();
                            System.out.println("[Oracle]: You encounter: Hughmungus the Troll!");
                            System.out.println("==============================================");
                            badHealth = random.nextInt(45, 55); //stage 2 health
                            min = 1; //throwing rocks or club
                            max = 6; 
                            enemy = "Troll";
                            event = 0;
                            break;    
                        }
                        case 13: { //boss fight stage 3
                            System.out.println("[Oracle]: You crest a hill and there right where you remember it, is your grandfather's house!");
                            System.out.println("[Oracle]: Before you can make it...");
                            System.out.println("");
                            System.out.println("[Oracle]: The ground shakes as you see trees colapse in the distance...");
                            System.out.println("[Oracle]: He has armor on...");
                            System.out.println();
                            System.out.println("[Oracle]: You encounter: Hughmungus the Troll!");
                            System.out.println("==============================================");
                            badHealth = random.nextInt(50,55); //stage 3 health
                            min = 1; //throwing rocks or club
                            max = 6; 
                            enemy = "Troll";
                            event = 0;
                            badArmor = 3;
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
                        } //ending of skeleton
                        
                        if (enemy == "Troll") {
                           if (badDamage == 1) {
                             badDamage = 0;
                             System.out.println("[Oracle]: Hughmungus cant find a rock...");
                             System.out.println();
                         } else  if (badDamage > 3) {
                             badDamage = (badDamage + 4); //club
                               System.out.println("[Oracle]: Hughmungus lifts up his huge club...");
                               System.out.println();
                         } else {
                             badDamage = 7; //rock
                             System.out.println("[Oracle]: Hughmungus picks up a rock...");
                             System.out.println();
                         }
                        } 
        
                        //health clues feeling
                        if (badHealth >48 ) {
                            feeling = "huge";
                       } else if (badHealth > 40){
                            feeling = "tough";
                        } else if (badHealth > 28){
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
                        System.out.println("  [1] Attack with your Sword ("+ strength +"|10 + " + sword + " Damage)");
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
                                damage = random.nextInt(strength, 10);
                                damage = (damage + sword);
                                System.out.println("[Oracle]: You hit the "+ enemy +" with your sword for [" + damage + " Damage]");
                                if (kills == 30) {
                                    System.out.println("[Oracle]: His armor blocks [3] of that damage...");
                                    damage = (damage - 3);
                                }
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
                           if (enemy == "Troll"){
                               if (kills == 10) {
                                   System.out.println("=====================================================================================================");
                                System.out.println("[Oracle]: Before you kill the troll you notice a stone on your amulet...");
                                System.out.println("[Oracle]: You look down and see its glowing bright green!");
                                System.out.println("[Oracle]: Before you can react, Hughmungus runs back into the forest, out of your sight."); 
                                System.out.println();
                                System.out.println("[Oracle]: It was a hard fought battle, but you feel much stronger");
                                strength = (strength + 1);
                                System.out.println("[Strength]: " + (strength));
                                System.out.println();
                               }
                               if (kills == 20) {
                                   System.out.println("=====================================================================================================");
                                System.out.println("[Oracle]: Before you kill the troll you notice another stone on your amulet...");
                                System.out.println("[Oracle]: You look down and see its glowing bright red!");
                                System.out.println("[Oracle]: Before you can react, Hughmungus runs back into the forest, out of your sight.");
                                System.out.println("[Oracle]: You're not quite sure what this could mean...");
                                System.out.println();
                                System.out.println("[Oracle]: It was a hard fought battle yet again, but you feel even stronger");
                                strength = (strength + 1);
                                System.out.println("[Strength]: " + (strength));
                                System.out.println();
                               }
                               if (kills == 30) {
                                System.out.println("=====================================================================================================");
                                System.out.println("[Oracle]: Before you kill the troll you notice, the last stone on the amulet...");
                                System.out.println("[Oracle]: You look down and see its glowing bright blue!");
                                System.out.println("[Oracle]: Hughmungus calms down and looks at the amulet...");
                                System.out.println();
                                System.out.println("[Oracle]: The amulet rises up out of your hand and floats toward the giant troll. ");
                                System.out.println("[Oracle]: The amulet places itself on the troll and Hughmungus slowly begins to transfer into a human...");
                                System.out.println();
                                System.out.println("[Grandfather]: I knew you could do it " + name);
                                System.out.println("[Oracle]: You turn around to find your grandfather right behind you!");
                                System.out.println("[Grandfather]: That amulet right there was the only cure for him, it was my only hope to get my brother back... ");
                                System.out.println("[Oracle]: At that moment out of the fog where Hughmungus just stood walks out your Great Uncle Parrish");
                                System.out.println("[Parrish]: Elder my brother, it is so nice to finally see you again.");
                                System.out.println("[Parrish]: And look at this guy, they really do grow up fast don't they!");
                                System.out.println("[Parrish]: "+ name + " I cannot thank you enough for freeing my from that curse.");
                                System.out.println("[Grandfather]: I'm just glad to have my brother back, can't thank you enough for your help " + name);
                                System.out.println("=====================================================================================================");

                                System.out.println("[Oracle]: It was a hard fought battle, but it was one worth fighting for");
                                strength = (strength + 1);
                                System.out.println("[Strength]: " + (strength));
                                System.out.println();
                                active = 1;
                               }
                                
                            } else {
                             System.out.println("[Oracle]: The " + enemy + " has passed...");
                             System.out.println();  
                            }
                            System.out.println("[Health]: " + health);
                            System.out.println("Gold: [" + gold + "] + " + reward);
                            System.out.println("XP: [" + xp + "] + " + (reward));
                            System.out.println();
                            System.out.println("[Oracle]: Your mana has been replinished");
                                mana = manaMax;
                            System.out.println("[Mana]: " + mana);
                            System.out.println();
                        
                            gold = (gold + reward);
                            xp = (xp + (reward));
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
                    System.out.println("  [5] Visit the Dog");
                    System.out.println("  [6] Leave the town");
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
                                       System.out.println("[Ethel]: I threw in a couple arrows too, that work real good against armor");
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
                                        System.out.println("[Ethel]: Made these myself. They can damage through armor!");
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
                        case 5: {
                            System.out.println("[The Dog]: Updates you on the story");
                            System.out.println("  [Quincy]: Hey its me Quincy, I'm the wolf that protects this town");
                            System.out.println("  [Quincy]: I heard that theres a giant scary troll, probably not as scary as me");
                            System.out.println("  [Quincy]: People are saying he really sucks, my sister Ellie really sucks, shes the worst.");
                            System.out.println();
                            System.out.println("  [Quincy]: I heard your going to see your Grandfather? He's probably my best friend. He gives me waffles");
                            System.out.println("  [Quincy]: Speaking of, if you ever have any, you know where to find me. My sister Ellie hates waffles so don't give her any");
                            System.out.println("  [Quincy]: Be careful, you seem really nice, I dont want you to get hurt. I'm a Warrior like my Mom");
                            System.out.println("  [Ellie]: Did I hear someone say Waffles, if Sir Quincy gets some then I get some...");
                            System.out.println("  [Quincy]: How about you shut up Ellie no one was talking to you");
                            System.out.println("  [Ellie]: I bet this Troll isn't as scary as Flynn");
                            System.out.println("  [Quincy]: Flynn isn't scary, I'm the scary one, you're so stupid Ellie");
                            System.out.println();
                            System.out.println("[Oracle]: The two dogs begin to bicker back and fourth");
                           break; 
                        }
                        case 6: { //leave
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
                    System.out.println("  [Amulet]: There are 10 markings around the edge with 3 stones in the middle");
                    System.out.println("================================");
                    System.out.println("[STATS]");
                    System.out.println("  [Health]: " + health);
                    System.out.println("  [Max Health]: " + healthMax);
                    System.out.println("  [Strength]: " + strength);
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
                    
                    //encryption
                    sword = (sword * 7) ;
                    strength = (strength * 7);
                    bow = (bow * 7);
                    arrows = (arrows * 7);
                    armor = (armor * 7);
                    health = (health * 7);
                    healthMax = (healthMax * 7);
                    gold = (gold * 7);
                    xp = (xp * 7);
                    level = (level * 7);
                    kills = (kills * 7);
                    healSpell = (healSpell * 7);
                    mana = (mana * 7);
                    manaMax = (manaMax * 7);
                    
                    //save state code
                    System.out.println("Here is your SaveState Password: ");
                    System.out.println();
                    System.out.println(sword +" "+ strength +" "+ bow +" "+ arrows +" "+ armor +" "+ health +" "+ healthMax +" "+ gold +" "+ xp +" "+ level +" "+ kills +" "+ healSpell +" "+ mana +" "+ manaMax);
                    System.out.println();
                    
                    //Start here with main code
                    System.out.println("[Oracle]: Your grandfather is disapointed...");
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("[Oracle]: That's not an option. Try again");
                    active = 0;
                    break;
                }
                    
            }   
        } while (active == 0);
        
        // end game stuff, complete the quest
        
        System.out.println("=======================================================");
        System.out.println(); 
        System.out.println("Thank you for playing Hero Quest Ver 1.4");
        System.out.println("I hope you have enjoyed your time playing");
        System.out.println();
        System.out.println("Feel free to start over or play new game plus with your loadout with you SaveState Code ");
        
        //encryption
                    sword = (sword * 7) ;
                    strength = (strength * 7);
                    bow = (bow * 7);
                    arrows = (arrows * 7);
                    armor = (armor * 7);
                    health = (health * 7);
                    healthMax = (healthMax * 7);
                    gold = (gold * 7);
                    xp = (xp * 7);
                    level = (level * 7);
                    kills = (kills * 7);
                    healSpell = (healSpell * 7);
                    mana = (mana * 7);
                    manaMax = (manaMax * 7);
                    
                    //save state code
                    System.out.println("Here is your SaveState Password: ");
                    System.out.println();
                    System.out.println(sword +" "+ strength +" "+ bow +" "+ arrows +" "+ armor +" "+ health +" "+ healthMax +" "+ gold +" "+ xp +" "+ level +" "+ kills +" "+ healSpell +" "+ mana +" "+ manaMax);
                    System.out.println();

    }
}
