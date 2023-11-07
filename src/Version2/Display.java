package Version2;


import java.util.ArrayList;
import java.util.LinkedList;

public class Display {
    //This is my 'View' Class

    public void displayRoomInfo(Room room){
        System.out.println("You currently stand in the " + room.getRoomName().toLowerCase()+ ".");
        visitedRoomCheck(room);
        System.out.println(room.getRoomDescription());
    } //Displays Room Name and Description

    public void visitedRoomCheck(Room room){
        if (room.isRoomVisited() == true){
            System.out.println("Although you've visted this room before. The air feels different somehow.");
        }
    } //Checks if room Visited == true and prints message

    public void displayHelp(ArrayList<String> commands){
        System.out.println();
        for(String i: commands){
            System.out.println(i);
        }
    } //Displays the help array to user

    public void displayRooms(LinkedList<Room> roomList) {
        System.out.println("\nHere is a list of rooms to explore:");
        for (Room r: roomList){
            System.out.println(r.toString());
        }
        System.out.println();
    } //Displays list of all rooms to player

    public void exploreRoom(Room room){
        if (room.getRoomInventory().size() != 0){
            System.out.println("\nYou rummage around the room and find the following items:");
            for (Item i : room.getRoomInventory()){
                System.out.println(i.getItemName());
            }
        } else {
            System.out.println("You rummage around the room but it is empty. Nothing much is going on here.");
        }
    } //Displays list of items in a rooms Inventory

    public void printInventory(Player player){
        if (player.playerInventory.size()>0){
            System.out.println("\nYou check your pockets: ");
            for (Item item : player.playerInventory){ System.out.println(item.getItemName()); }
        } else {
            System.out.println("\nHuh? Your pockets are empty.");
            System.out.println("You didn’t pickup any items yet.");
        }
    } //Displays list of items in a player's Inventory

    public void printSeperator(){
        System.out.println("\n--------------------------------------------------------------------------------------------------------------");
    } //Personal styling for the user

    public void printCompleteRobbery(){
        System.out.println("\nCongratulations! You robbed this man blind!\nYou win!");
        printSeperator();
    }

    public void printPlayerStatus(Player player){
        System.out.println("\n" + player.getFirstName() +" " + player.getLastname()+ ": ");
        System.out.println("---------------------------------");
        System.out.println(player.getDescription());
        System.out.println("HP  : " + player.getHealthPoints());
        System.out.println("ATK : " + player.getAttackPoints());
        System.out.println("Inventory: " + player.playerInventory.size());
        System.out.println("Equipped: ");
        if(!player.equippedInventory.isEmpty()){
            for(Item i: player.equippedInventory){
                System.out.println(i.getItemName() + " (+" + i.getAttackPoints()+ ")");
            }
        }


    }

    public void characterList(ArrayList<Player> playerArrayList) {
        System.out.println("There currently " + playerArrayList.size() + " players to choose from.");
        for (Player player: playerArrayList){
            System.out.println(player.getFirstName());
        }
    }

    public void displayItemInfo(Item item) {
        if(item != null){
            System.out.println("\nYou pull out the " + item.getItemName().toLowerCase() + " from your pocket:");
            System.out.println(item.getItemDesciption());
            if (item.itemType.equalsIgnoreCase("consumable")){
                System.out.println("This item is " + item.itemType.toLowerCase() + ". (+" + item.getHealthPoints()+ ")");
            }
        }else{
            System.out.println("This item is not in your pockets.");
        }


    }

    public void printAllMonsters(ArrayList<Monster> monsterArrayList) {
        System.out.println("\nTheres are all the monsters you may encounter:");
        for(Monster m: monsterArrayList){
            System.out.println(m.getMonsterName());
        }

    }

    public void monsterIntro(Room currentRoom) {
        Monster monster = currentRoom.getMonster();
        System.out.println("[ ENEMY ENCOUNTER! ]");
        System.out.println("As you enter " + currentRoom.getRoomName().toLowerCase()
                + ", you encounter a " + monster.getMonsterName().toLowerCase() + ".");

    }

    public void monsterDefeat(Monster monster) {
        System.out.println("\nYou won the battle!\nYour eyes flash and the monster disappears...");
    }

    public void monsterZero(Monster monster) {
        System.out.println("\nYou snap your fingers and the monster disappears...\nTheir presense still lingers though.");
    }

    public void examineMonster(Monster monster, Boolean canIgnore) {
        System.out.println("\nYou take a closer look at this "
                + monster.getMonsterName().toLowerCase()
                + "...\n");
        System.out.println(monster.getMonsterName() +"\nHP: " + monster.getHealthPoints()
                + " | ATK: " + monster.getAttackPoints());
        for(String s: monster.getDescription()){
            System.out.println(s);
        }
        if(canIgnore){
            System.out.println("\nAre you going to attack or ignore?");
        }
    }

    public void ignoreMonster(Monster monster) {
        System.out.println("You decide to ignore the "
                + monster.getMonsterName().toLowerCase()
                + "...");
    }

    public void printInvaldInput() {
        System.out.println("Invalid input. Try again.");
    }

    public void playerAttack(Player player, Monster monster) {
        System.out.println();
        System.out.println(player.getFirstName() + " swings at the "
                + monster.getMonsterName().toLowerCase() + " and does -"
                + player.getAttackPoints() + " damage.");

    }

    public void cannotIgnoreMonster(Monster monster) {
        System.out.println("\nYou cannot ignore the "
                + monster.getMonsterName().toLowerCase()
                + " now. You have to be brave and fight on.");
    }

    public void monsterCommandPrompt(Monster monster) {
        System.out.println("\nThe " + monster.getMonsterName().toLowerCase()
                + " stares at you. Anticipating your next move.");
        System.out.println("What would you like to do?");
    }

    public void attackPlayer(Monster monster, Player player) {
        System.out.println("\nThe " + monster.getMonsterName().toLowerCase() + " swings at " + player.getFirstName()
                +" and deals -" + monster.getAttackPoints() + " damage.");
        if(player.getHealthPoints() < 15){
            System.out.println("\nYou should eat something! "+ player.getFirstName() + "'s HP is running low.\n Use 'heal [item]' to heal some HP.");
        }
    }

    public void printBadEnding(Player player) {
        System.out.println(player.getFirstName() + " has been defeated!");
        System.out.println("\nThe room is filled with the deafening wails of sirens, and you realize that the authorities have arrived.");
        System.out.println("Panic grips you as you're surrounded by police officers!");
        System.out.println("\nWith no way out, your criminal escapade comes to a disastrous end. \nYou're arrested, handcuffed, and led away from the house in defeat. \n\nYour dreams of a successful heist vanish into the cold, unforgiving reality of a jail cell.");
        //System.out.println();

    }

    public void printGameOverPrompt() {
        printSeperator();
        System.out.println("[ GAME OVER ] \n\nWhat would you like to do?\nYou can start a new game: 'restart' or exit program: 'exit'.");
    }

    public void printExit() {
        printSeperator();
        System.out.println("\n\nThanks for playing!!!");
    }

    public void printPlayerMonsterHP(Monster monster, Player player) {
        System.out.println("[ HP ] "+player.getFirstName() + ": " + player.getHealthPoints()
                + " | " + monster.getMonsterName() + ": " + monster.getHealthPoints() );
    }

    public void monsterDoubleDamage(Monster monster) {
        System.out.println("Watch out! This enemy powers itself up!");
        System.out.println(monster.getMonsterName() + " now does double damage.\n");
    }
}
