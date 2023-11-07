package Version2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    //This Will be the 'Model' Class

    public ArrayList<Player> playerArrayList = new ArrayList<>();
    public LinkedList<Room> roomLinkedList = new LinkedList<>();
    private ArrayList<Item> itemArrayList = new ArrayList<>();
    private ArrayList<Puzzle> puzzleArrayList = new ArrayList<>();
    public ArrayList<String> gameCommands = new ArrayList<>();
    public ArrayList<Monster> monsterArrayList = new ArrayList<>();

    public Player player = new Player("Generic","Player","Description1",100,5);
    private Room currentRoom;
    private FileInputStream inputStream;
    private Scanner fileIn;



    // GAME SET UP =================================
    protected void populateItems(File file) throws FileNotFoundException {
        //Scans file
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while (fileIn.hasNext()) {
            String[] tempArray = fileIn.nextLine().split("=");

            String itemName = tempArray[0];
            String itemDescription = tempArray[1];
            int itemRoomLocation = Integer.parseInt(tempArray[2]);
            String itemType = tempArray[3];
            int healthPoints = Integer.parseInt(tempArray[4]);
            int attackPoints = Integer.parseInt(tempArray[5]);

            this.itemArrayList.add(new Item(itemName, itemDescription,itemRoomLocation,itemType,healthPoints,attackPoints)); //Adds item to array
        }
    }  //Adds Items into an ArrayList

    protected void populateRooms(File file) throws FileNotFoundException {
        //Scans file
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while(fileIn.hasNext()){
            String[] tempArray = fileIn.nextLine().split("=");

            int roomNum = Integer.parseInt(tempArray[0]);
            String roomName = tempArray[1];
            String roomDescription = tempArray[2];
            boolean roomVisited = Boolean.parseBoolean(tempArray[3]);
            int roomNorth = Integer.parseInt(tempArray[4]);
            int roomSouth = Integer.parseInt(tempArray[5]);
            int roomEast = Integer.parseInt(tempArray[6]);
            int roomWest = Integer.parseInt(tempArray[7]);

            //Adds room to room array
            this.roomLinkedList.add(new Room(roomNum,roomName,roomDescription,roomVisited,roomNorth,roomSouth,roomEast,roomWest));
        }

    }   //Adds Rooms into an ArrayList

    protected void populatePuzzles(File file) throws FileNotFoundException {
        //Scans File
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while(fileIn.hasNext()){
            String[] tempArray = fileIn.nextLine().split("=");

            String puzzleName = tempArray[0];
            String puzzleDesc = tempArray[1];
            String puzzleAnswer = tempArray[2];
            int puzzleAttempts = Integer.parseInt(tempArray[3]);

            //Adds Puzzle to array
            this.puzzleArrayList.add(new Puzzle(puzzleName,puzzleDesc,puzzleAnswer,puzzleAttempts));
        }

    } //Adds Puzzles into an ArrayList

    protected void populateCommands(File file) throws FileNotFoundException {
        //Scans File
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while(fileIn.hasNext()){
            gameCommands.add(fileIn.nextLine()); //Adds String into array
        }
    } //Adds Commands into an ArrayList

    public void fillrooms(){
        //Goes through each room one by one
        for(int i = 0; i < roomLinkedList.size(); i++){

            //Populates Room i with any items that correlate
            for(Item item: itemArrayList){
               if(item.roomLocation == roomLinkedList.get(i).getRoom()){
                   roomLinkedList.get(i).getRoomInventory().add(item);
               }
            }

            //Populates Room i with any puzzles that correlate
            for(Puzzle puzzle: puzzleArrayList){
                if(puzzle.roomLocation == roomLinkedList.get(i).getRoom()){
                    roomLinkedList.get(i).setRoomPuzzle(puzzle);
                }
            }

            //Populates Room i with any monsters that correlate
            for(Monster monster: monsterArrayList){
                if(monster.getRoomLocation() == roomLinkedList.get(i).getRoom()){
                    roomLinkedList.get(i).setMonster(monster);
                }
            }

        }
    } //Adds items and Puzzles into designated rooms

    public void setFirstRoom(){
        currentRoom = roomLinkedList.get(0);
    }

    public void populatePlayers(File file) throws FileNotFoundException {
        //Scans File
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while(fileIn.hasNext()) {
            String[] tempArray = fileIn.nextLine().split("=");

            String firstName = tempArray[0];
            String lastname = tempArray[1];
            String description = tempArray[2];
            int healthPoints = Integer.parseInt(tempArray[3]);
            int attackPoints = Integer.parseInt(tempArray[4]);

            playerArrayList.add(new Player(firstName,lastname,description,healthPoints,attackPoints));

        }
    }

    public void populateMonsters(File file) throws FileNotFoundException  {
        //Scans File
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        //Reads file
        while(fileIn.hasNext()){
            String[] tempArray = fileIn.nextLine().split("=");

            String monsterName = tempArray[0];
            String[] monsterDescription = tempArray[1].split(">");
            int roomLocation = Integer.parseInt(tempArray[2]);
            int healthPoints = Integer.parseInt(tempArray[3]);
            int attackPoints = Integer.parseInt(tempArray[4]);
            int threshold = Integer.parseInt(tempArray[5]);

            monsterArrayList.add(new Monster(monsterName,monsterDescription,roomLocation,healthPoints,attackPoints,threshold));
        }

    }


    // GAME COMMAND LOGIC =================================
    public void move(String movement) throws ArrayIndexOutOfBoundsException {

        //processes direction;
        if (movement.equalsIgnoreCase("n") || movement.equalsIgnoreCase("north")) {
            System.out.println("\nYou head north.");
            checkMove(currentRoom.getRoomNorth());
        } else if (movement.equalsIgnoreCase("s") || movement.equalsIgnoreCase("south")) {
            System.out.println("\nYou head south.");
            checkMove(currentRoom.getRoomSouth());
        } else if (movement.equalsIgnoreCase("e") || movement.equalsIgnoreCase("east")) {
            System.out.println("\nYou head east.");
            checkMove(currentRoom.getRoomEast());
        } else if (movement.equalsIgnoreCase("w") || movement.equalsIgnoreCase("west")) {
            System.out.println("\nYou head west.");
            checkMove(currentRoom.getRoomWest());
        } else {
            System.out.println("\nNot a real direction. Try again.\n\n");
        }

    } //Processes which direction user inputted

    public void checkMove(int direction){
        if (direction == 0){
            System.out.println("Theres nothing in that direction.");
        } else{
            currentRoom.setRoomVisited(true);
            setCurrentRoom(roomLinkedList.get(direction - 1)); //This moves the player
        }
    } //Checks if Room has a connection in designated direction

    public Room getCurrentRoom(){ return currentRoom; } //GETTER
    public void setCurrentRoom(Room room){ this.currentRoom = room; } //SETTER

    public void pickupItem(String itemName){

        if (itemName.equalsIgnoreCase("all")){
            moveAllItems(currentRoom.getRoomInventory(),player.playerInventory);
            System.out.println("You pickup all the items from the room.");
            return;
        }

        //Checks if room has [item name] and adds to Player's inventory
        for (Item roomitem : currentRoom.getRoomInventory()){
            if (roomitem.getItemName().equalsIgnoreCase(itemName)){
                player.playerInventory.add(roomitem);
                currentRoom.getRoomInventory().remove(roomitem);
                System.out.println(roomitem.getItemName() + " has been picked up from the room and successfully added to the player inventory.");
                return;
            }
        }
        System.out.println("Item not in room");
    } //Picks up item from room and adds to player inventory

    public void dropItem(String itemName){

        if (itemName.equalsIgnoreCase("all")){
            moveAllItems(player.playerInventory,currentRoom.getRoomInventory());
            System.out.println("You drop all the items in your inventory.");
            return;
        }

        //Checks if item is in player's inventory and adds it to room's inventory
        for(Item i: player.playerInventory){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                currentRoom.getRoomInventory().add(i);
                player.playerInventory.remove(i);
                System.out.println(i.getItemName()
                        + " has been dropped successfully from the player inventory and placed here in the "
                        + currentRoom.getRoomName().toLowerCase() + ".");
                return;
            }
        }
        System.out.println("This item is not in your pockets.");
    } //Drops item from player inventory into Room Inventory

    public Item inspectItem(String itemName){

        //Makes sure item is in player's inventory before printing [item name]'s description
        for(Item i: player.playerInventory){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                return i;
            }
        }
        return null;

    }  //Verifies and prints description of item in player's inventory

    public Boolean inventoryCheck(){
        if(player.playerInventory.equals(itemArrayList)){return true;}
        return false;
    }

    public void equipItem(String itemName){

        if (itemName.equalsIgnoreCase("all")){
            moveAllItems(player.playerInventory,player.equippedInventory);
            moveEquipPoints("add");
            System.out.println("You equip all the items in your inventory.");
            return;
        }

        //Checks if item is in player's inventory and adds it to room's inventory
        for(Item i: player.playerInventory){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                player.equippedInventory.add(i);
                player.playerInventory.remove(i);
                //player.attackPoints += i.behaviorPoints;
                player.setAttackPoints(player.attackPoints +i.getAttackPoints());
                System.out.println(i.getItemName()+ " is equipped! (+"+ i.getAttackPoints() + "ATK)");
                return;
            }
        }
        System.out.println("This item is not in your pockets.");
    }

    public void unequipItem(String itemName){

        if (itemName.equalsIgnoreCase("all")){
            moveEquipPoints("remove");
            moveAllItems(player.equippedInventory,player.playerInventory);
            System.out.println("You unequip all your gear into your inventory.");
            return;
        }

        //Checks if item is in player's inventory and adds it to room's inventory
        for(Item i: player.equippedInventory){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                player.equippedInventory.remove(i);
                player.playerInventory.add(i);
                player.attackPoints -= i.attackPoints;
                System.out.println(i.getItemName()+ " is unequipped!(-"+ i.getAttackPoints() + "ATK)");
                return;
            }
        }
        System.out.println("You do not have this item equipped.");
    }

    public void moveAllItems(ArrayList<Item> removeFrom,ArrayList<Item> addToo){
        addToo.addAll(removeFrom);
        removeFrom.clear();
    }

    public void moveEquipPoints(String action){
        if(action.equalsIgnoreCase("add")){
            for(Item i: player.equippedInventory){
                player.setAttackPoints(player.attackPoints+i.getAttackPoints());
            }
        } else if(action.equalsIgnoreCase("remove")){
            for(Item i: player.equippedInventory){
                player.setAttackPoints(player.attackPoints-i.getAttackPoints());
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void healItem(String itemName){
        //Checks if item is in player's inventory and adds it to room's inventory
        for(Item i: player.playerInventory){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                if(i.itemType.equalsIgnoreCase("consumable")){
                    player.playerInventory.remove(i);
                    player.setHealthPoints(player.getHealthPoints() + i.getHealthPoints());
                    System.out.println("\nYou eat " + i.getItemName().toLowerCase()+ ".");
                    System.out.println("HP +" + i.getHealthPoints());
                    return;
                } else {
                    System.out.println("\nYou can't eat this " + player.getFirstName()+ ". It'll be very painful");
                    return;
                }
            }
        }
        System.out.println("This item is not in your pockets.");
    }

    public void attackMonster() {
        currentRoom.getMonster().setHealthPoints(currentRoom.getMonster().getHealthPoints()- player.getAttackPoints());
        if(currentRoom.getMonster().getHealthPoints()<=0){
            currentRoom.getMonster().monsterDefeated();
        }
    }

    public void attackPlayer() {
        player.setHealthPoints(player.healthPoints- currentRoom.getMonster().getAttackPoints());
    }

    public boolean playerCheck() {
        if (player.getHealthPoints() > 0) {return true;}
        return false;
    }

    public boolean monsterCheck() {
        if ((currentRoom.getMonster().getHealthPoints() > 0) && (!currentRoom.getMonster().getMonsterDefeat())){
            return true;
        }
        return false;
    }

    public boolean monsterThresholdCheck() {
        Random random = new Random();
        int randInt = random.nextInt(100);
        if (randInt <= currentRoom.getMonster().getThreshold()){
            currentRoom.getMonster().setAttackPoints(currentRoom.getMonster().getAttackPoints() * 2);
            return true;
        }
        return false;
    }
}
