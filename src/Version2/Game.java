package Version2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    //This Will be the 'Model' Class

    public ArrayList<Player> playerArrayList = new ArrayList<>();
    public LinkedList<Room> roomLinkedList = new LinkedList<>();
    private ArrayList<Item> itemArrayList = new ArrayList<>();
    private ArrayList<Puzzle> puzzleArrayList = new ArrayList<>();
    public ArrayList<String> gameCommands = new ArrayList<>();

    public Player player = new Player("Generic", "1","Description1",100,5);
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
            String lastName = tempArray[1];
            String description = tempArray[2];
            int healthPoints = Integer.parseInt(tempArray[3]);
            int attackPoints = Integer.parseInt(tempArray[4]);

            playerArrayList.add(new Player(firstName,lastName,description,healthPoints,attackPoints));

        }
    }


    // GAME COMMAND LOGIC =================================
    public void move(String movement) throws ArrayIndexOutOfBoundsException {

        //processes direction;
        if (movement.equalsIgnoreCase("n") || movement.equalsIgnoreCase("north")) {
            System.out.println("You head north.");
            checkMove(currentRoom.getRoomNorth());
        } else if (movement.equalsIgnoreCase("s") || movement.equalsIgnoreCase("south")) {
            System.out.println("You head south.");
            checkMove(currentRoom.getRoomSouth());
        } else if (movement.equalsIgnoreCase("e") || movement.equalsIgnoreCase("east")) {
            System.out.println("You head east.");
            checkMove(currentRoom.getRoomEast());
        } else if (movement.equalsIgnoreCase("w") || movement.equalsIgnoreCase("west")) {
            System.out.println("You head west.");
            checkMove(currentRoom.getRoomWest());
        } else {
            System.out.println("Not a real direction. Try again.\n\n");
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

    public void inspectItem(String itemName){

        //Makes sure item is in player's inventory before printing [item name]'s description
        for(Item i: player.playerInventory){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                System.out.println("\nYou pull out the " + itemName + " from your pocket:");
                System.out.println(i.getItemDesciption());
                return;
            }
        }
        System.out.println("This item is not in your pockets.");

    }  //Verifies and prints description of item in player's inventory

    public Boolean inventoryCheck(){
        if(player.playerInventory.equals(itemArrayList)){return true;}
        return false;
    }

    public void equipItem(String itemName){

        if (itemName.equalsIgnoreCase("all")){
            moveAllItems(player.playerInventory,player.equippedInventory);
            moveEquipPoints("add");
            return;
        }

        //Checks if item is in player's inventory and adds it to room's inventory
        for(Item i: player.playerInventory){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                player.equippedInventory.add(i);
                player.playerInventory.remove(i);
                //player.attackPoints += i.behaviorPoints;
                player.setAttackPoints(player.attackPoints +i.getAttackPoints());
                System.out.println(i.getItemName()+ " is equipped!");
                return;
            }
        }
        System.out.println("This item is not in your pockets.");
    }

    public void unequipItem(String itemName){

        if (itemName.equalsIgnoreCase("all")){
            moveEquipPoints("remove");
            moveAllItems(player.equippedInventory,player.playerInventory);
            return;
        }

        //Checks if item is in player's inventory and adds it to room's inventory
        for(Item i: player.equippedInventory){
            if (i.getItemName().equalsIgnoreCase(itemName)){
                player.equippedInventory.remove(i);
                player.playerInventory.add(i);
                player.attackPoints -= i.attackPoints;
                System.out.println(i.getItemName()+ " is unequipped!");
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


    //RANDOM TEST METHOD
    public void test(){
        for (int i = 0; i< roomLinkedList.size();i++){
            roomLinkedList.get(i).getRoomInventory().add(itemArrayList.get(i));
        }
    }




}
