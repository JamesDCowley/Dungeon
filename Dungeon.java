import java.util.Scanner;
import java.util.Random;

public class Dungeon
{
   public static void main(String[] args)
   {
        //Dungeon by James Cowley
        //Please excuse the messy code; this is my first java game.
        
        //System objects
      Scanner input = new Scanner(System.in);
      Random rand = new Random();
        
        //Enemy Variables
      String [] enemies = {"Org", "Skeleton" , "Beast", "Bat", "Zombie" , "Spider", "Slime"};
      int maxEnemyHealth = 99;
      int enemyAtk = 100;
        
        //Player Variables
      String pName = " ";
      int health = 500;
      String [] items = {"Potion", "Max Potion", "Bomb", "Big Bomb", "Death Dart", "Cursed Potion"};
      int numItems = 0;
      int atk = 50;
      int potionHealAmount = 100;
      int potionDropChance = 50; //percentage
      int level = 1;
      int runningChance = 50; //percent
      int gold = 0;
        
        //item variables
      int potions = 5;
      int maxPotions = 1;
      int bombs = 1;
      int bigBombs = 0;
      int deathDart = 0;
      int luckCharm = 0;
      int cursePotion = 0;
        
      boolean running = true;
        
      System.out.println("\n---Dungeon---");
      System.out.println("\nCoded and created by James Cowley.");
        //Ask for name
      System.out.println("\nWhat is your name brave adventurer?");
      pName = input.nextLine();
      
      //Easy Mode
      if(pName.equals("Baby"))
      {
         enemyAtk = 50;
         maxEnemyHealth  = 50;
         atk = 70;
         potionDropChance = 75;
         runningChance = 75;
         System.out.println("EASY MODE ACTIVATED");
      }   
        
      //This is the while loop that keeps the game infinite. It is named "GAME".
      GAME:
        while(running)
      {
            
         System.out.println("\nLevel: " + level);
         System.out.println("\n################################################");
         int enemyHealth = 0;
         int enHealthGold = 0;
         String enemy = "";
         
         // Check for boss fight (every 5 levels)
         if(level % 5 == 0)
         {
            enemyHealth = (100 * (level / 5));
            enHealthGold = enemyHealth;
            runningChance = 0;
            enemyAtk = level * 15;
            enemy = "Boss";
         }
         else
         {
            enemyHealth = ((rand.nextInt(maxEnemyHealth) + 1) * level);
            enHealthGold = enemyHealth;
            runningChance = 50;
            enemyAtk = level * 10;
            enemy = enemies[rand.nextInt(enemies.length)];
         }
         System.out.println("\t# " + enemy + " appeared! #\n");
            
         //Check if enemy is dead   
         while(enemyHealth > 0)
         {
            System.out.println("\t# " + enemy + "'s HP: " + enemyHealth + " #");
            System.out.println("\t# " + pName + "'s HP: " + health + " #");
                
            //Ask for input
            System.out.println("\t\n What will " + pName + " do?");
            System.out.println("\t 1. Attack");
            System.out.println("\t 2. Items");
            System.out.println("\t 3. Run");
            String in = input.nextLine();
                
            //To avoid error I use a String because if you use a int, entering a character that isn't a number will cause error.
            if(in.equals("1"))
            {
               //Check if critical hit (10% chance)
               if(rand.nextInt(9) == 1)
               {
                  System.out.println("\t " + pName + " attacked " + enemy + " with a critical hit!");
                  //Will do at least 100 damage + what the generated attack was
                  int damageDealt = ((rand.nextInt(atk) + 50) * 2);
                  int damageTaken = rand.nextInt(enemyAtk);
                  enemyHealth -= damageDealt;
                  health -= damageTaken;
                  System.out.println("\t It took " + damageDealt + " damage!");
                  System.out.println("\t Ow! You took " + damageTaken + " damage!\n");
               }
                    
                    
               else
               {
                  System.out.println("\t " + pName + " attacked " + enemy + "!");
                  //generates a random num 0-99 and adds 1 (makes it 1-100)
                  int damageDealt = rand.nextInt(atk) + 1;
                  int damageTaken = rand.nextInt(enemyAtk) + 1;
                  enemyHealth -= damageDealt;
                  health -= damageTaken;
                  System.out.println("\t It took " + damageDealt + " damage!");
                  System.out.println("\t Ow! You took " + damageTaken + " damage!\n");
               }
               //check if dead (will have to repeat for every time the player takes damage)
               if(health <= 0)
               {
                  System.out.println("\t" + pName + " has stumbled out of the dungeon.");
                  System.out.println("\t Perhaps next time " + pName + " can go farther!\n");
                  //this will break the GAME while loop and go straight to the game over
                  break;
                        
               }
                    
            }
            else if(in.equals("2"))
            {
               numItems = potions + maxPotions + bombs + bigBombs + deathDart + cursePotion;
               if (numItems > 0)
               {
                  //Shows the inventory and has the effects of all the items
                  System.out.println("\t ITEMS:");
                        
                  System.out.println("1. Potion (x" + potions + ")");
                  System.out.println("2. Max Potion (x" + maxPotions + ")");
                  System.out.println("3. Bomb (x" + bombs + ")");
                  System.out.println("4. Big Bomb (x" + bigBombs + ")");
                  System.out.println("5. Death Dart (x" + deathDart + ")");
                  System.out.println("6. Cursed Potion (x" + cursePotion + ")");
                  System.out.println("7. Back");
                  
                  //Ask player what item they would want to use      
                  String item = input.nextLine();
                        
                        //Potion
                  if(item.equals("1"))
                  {
                     if(potions > 0)
                     {
                        System.out.println("\t" + pName + " drank a potion and healed " + potionHealAmount + " HP!");
                        System.out.println("\t Only " + potions + " left!\n");
                        potions --;
                        health += potionHealAmount;
                     }
                     else
                     {
                        System.out.println("\tNo Potions!");
                     }
                  }
                        //Max Potion
                  else if(item.equals("2"))
                  {
                     if(maxPotions > 0)
                     {
                        System.out.println("\t" + pName + " drank a max potion and regained all HP!");
                        System.out.println("\t Only " + maxPotions + " left!\n");
                        maxPotions --;
                        health = 500;
                     }
                     else
                     {
                        System.out.println("\tNo Max Potions!");
                     }
                  }     //Bomb
                  else if(item.equals("3"))
                  {
                     if(bombs > 0)
                     {
                        System.out.println("\t" + pName + " threw a bomb at the enemy!");
                        System.out.println("\t It took 100 damage!\n");
                        bombs --;
                        enemyHealth -= 100;
                     }
                     else
                     {
                        System.out.println("\tNo Bombs!");
                     }
                  }     //Big Bomb
                  else if(item.equals("4"))
                  {
                     if(bigBombs > 0)
                     {
                        System.out.println("\t" + pName + " threw a big bomb at the enemy!");
                        System.out.println("\t It took 500 damage!\n");
                        bigBombs --;
                        enemyHealth -= 500;
                     }
                     else
                     {
                        System.out.println("\tNo Big Bombs!");
                     }
                  }     //Death Dart
                  else if(item.equals("5"))
                  {
                     if(deathDart > 0)
                     {
                        System.out.println("\t" + pName + " threw a dart at the enemy!");
                        if(rand.nextInt(10) < 1)
                        {
                           System.out.println("\tThe dart bounced off the enemy and hit you! You lost half your HP!\n");
                           health = health / 2;
                        }
                        else
                        {   
                           System.out.println("\t The dart kills the enemy!\n");
                           deathDart --;
                           enemyHealth = 0;
                        }   
                     }
                     else
                     {
                        System.out.println("\tNo Death Darts!");
                     }
                  }     //Curse Potion
                  else if(item.equals("6"))
                  {
                     if(cursePotion > 0)
                     {
                        if(rand.nextInt(10) <= 5)
                        {
                           System.out.println("\t" + pName + " drank a cursed potion and regained 100 HP! Phew!");
                           System.out.println("\t Only " + cursePotion + " left!\n");
                           cursePotion --;
                           health += potionHealAmount;
                        }
                        else
                        {
                           System.out.println("\t" + pName + " drank the potion, but it made you take 100 damage!");
                           cursePotion --;
                           health -= potionHealAmount;
                        }
                     }
                     else
                     {
                        System.out.println("\tNo Cursed Potions!");
                     }
                  }
                  else if(item.equals("7"))
                  {
                  }
                  else
                  {
                     System.out.println("\n\tInvalid Command!");
                  }
               }
               else
               {
                  System.out.println("No items in inventory!\n");
               }
                    
               //Check if dead (This would be easier if I made a void method that check if you are dead.)
               if(health < 1)
               {
                  System.out.println("\t" + pName + " has stumbled out of the dungeon.");
                  System.out.println("\t Perhaps next time " + pName + " can go farther!\n");
                  break;
               }
            }
            else if(in.equals("3"))
            {
               //To make it where players can't just run until the RNG gods are nice, there is a 50% chance you will take damage while running.
               //The running chance will be 0 during boss fights. DONT RUN DURING BOSS FIGHTS!
               if(rand.nextInt(100) < runningChance)
               {
                  System.out.println("You ran away unscathed!\n");
                  continue GAME;
               }
               //When running takes damage, it will do 1-50 damage.
               int runDamage = rand.nextInt(49) + 1;
               System.out.println("\t" + pName + "ran away from the enemy, but took " + runDamage + " damage!\n");
               health -= runDamage;
                    
               if(health < 1)
               {
                  System.out.println("\t" + pName + " has stumbled out of the dungeon.");
                  System.out.println("\t Perhaps next time " + pName + " can go farther!\n");
                  break;
               }
                    
               continue GAME;
            }
            else
            {
               System.out.println("\t Invalid command!");
            }
            if(health < 1)
            {
               System.out.println("\t" + pName + " has stumbled out of the dungeon.");
               System.out.println("\t Perhaps next time " + pName + " can go farther!\n");
               break;
            }
                
         }
         System.out.println("################################################");
         //Check if dead
         if(health < 1)
         {
            break;
         }
               
         System.out.println("\tYou have defeated " + enemy + " !\n");
         level ++;
         
         //Gold Drops
         int dropGold = (enHealthGold / 10 + 20);
         gold += dropGold; 
         
         System.out.println("\tYou got " + dropGold + " gold!\n");
         
         //Item Drops
         if(rand.nextInt(100) < potionDropChance)
         {
            String droppedItem = items[rand.nextInt(items.length)];
            if(droppedItem.equals("Potion"))
            {
               potions ++;
               System.out.println("\tYou picked up a potion!\n");
            }
            if(droppedItem.equals("Max Potion"))
            {
               maxPotions ++;
               System.out.println("\tYou picked up a max potion!\n");
            }
            if(droppedItem.equals("Bomb"))
            {
               bombs ++;
               System.out.println("\tYou picked up a bomb!\n");
            }
            if(droppedItem.equals("Big Bomb"))
            {
               bigBombs ++;
               System.out.println("\tYou picked up a big bomb!\n");
            }
            if(droppedItem.equals("Death Dart"))
            {
               deathDart ++;
               System.out.println("\tYou picked up a death dart!\n");
            }
            if(droppedItem.equals("Cursed Potion"))
            {
               cursePotion ++;
               System.out.println("\tYou picked up a cursed potion!\n");
            }
         }
         System.out.println("\tDo you want to continue on your adventure?\n");
         System.out.println("\t1. Continue down the dungeon.");
         System.out.println("\t2. Enter Shop");
         System.out.println("\t3. Leave Dungeon");
         String in2 = input.nextLine();
            
         while(!in2.equals("1") && !in2.equals("2") && !in2.equals("3"))
         {
            System.out.println("\tInvalid Command!\n");
            in2 = input.nextLine();
         }
                
                
         if(in2.equals("1"))
         {
            System.out.println("\tYou continue down the dungeon!\n");
         }
         //This is the shop. It shows all the items and then lets you purchase items
         
         else if(in2.equals("2"))
         {
            boolean shopActive = true;
            while(shopActive)
            {
               System.out.println("\tYou enter the shop.\n");
            
               System.out.println("\nYou have " + gold + " gold.\n");
               System.out.println("\tSHOP\n");
               System.out.println("\t1. Potion - 10G");
               System.out.println("\t2. Max Potion - 50G");
               System.out.println("\t3. Bomb - 100G");
               System.out.println("\t4. Big Bomb - 200G");
               System.out.println("\t5. Death Dart- 300G");
               System.out.println("\t6. Cursed Potion - 5G");
               System.out.println("\t7. Continue on adventure!\n1");
            
               String shopIn = input.nextLine();
            
               if(shopIn.equals("1"))
               {
                  if(gold >= 10)
                  {
                     potions ++;
                     gold -= 10;
                     shopActive = false;
                  }
                  else
                  {
                     System.out.println("\tNot enough gold!");
                  }
               } 
               else if(shopIn.equals("2"))
               {
                  if(gold >= 50)
                  {
                     maxPotions ++;
                     gold -= 50;
                     shopActive = false;
                  }
                  else
                  {
                     System.out.println("\tNot enough gold!");
                  }
               }
               else if(shopIn.equals("3"))
               {
                  if(gold >= 100)
                  {
                     bombs ++;
                     gold -= 100;
                     shopActive = false;
                  }
                  else
                  {
                     System.out.println("\tNot enough gold!");
                  }
               }
               else if(shopIn.equals("4"))
               {
                  if(gold >= 200)
                  {
                     bigBombs ++;
                     gold -= 200;
                     shopActive = false;
                  }
                  else
                  {
                     System.out.println("\tNot enough gold!");
                  }
               }
               else if(shopIn.equals("5"))
               {
                  if(gold >= 300)
                  {
                     deathDart ++;
                     gold -= 300;
                     shopActive = false;
                  }
                  else
                  {
                     System.out.println("\tNot enough gold!");
                  }
               }
               else if(shopIn.equals("6"))
               {
                  if(gold >= 5)
                  {
                     cursePotion ++;
                     gold -= 5;
                     shopActive = false;
                  }
                  else
                  {
                     System.out.println("\tNot enough gold!");
                  }
                  shopActive = false;
               }  
               else if(shopIn.equals("7"))
               {
                  continue GAME;
               }
            
            
            
            }
            
         }    
         else if (in2.equals("3"))
         {
            System.out.println("\tYou leave the dungeon.\n");
            break;
         }
                
      }
      //Game Over
      System.out.println("\t GAME OVER! \n");
      System.out.println("\t You made it " + level + " levels!\n");
      System.out.println("\t Coded and created by James Cowley");
        
   }
}