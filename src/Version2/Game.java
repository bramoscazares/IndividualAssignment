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


    //public ArrayList<Room> roomArrayList = new ArrayList<>();
    public LinkedList<Room> roomLinkedList = new LinkedList<>();
    private ArrayList<Item> itemArrayList = new ArrayList<>();

    Player player = new Player();
    private Room currentRoom;
    FileInputStream inputStream;
    Scanner fileIn;



    public Game(File rooms, File items) throws FileNotFoundException {
        populateRooms(rooms);
        populateItems(items);
        currentRoom = roomLinkedList.get(0);
        test();
    }

    protected void populateItems(File file) throws FileNotFoundException {
        inputStream = new FileInputStream(file);
        fileIn = new Scanner(inputStream);

        while (fileIn.hasNext()) {
            String[] tempArray = fileIn.nextLine().split(",");

            String itemName = tempArray[0];
            String itemDescription = tempArray[1];

            this.itemArrayList.add(new Item(itemName, itemDescription));
        }
    }


    protected void populateRooms(File file1) throws FileNotFoundException {
        inputStream = new FileInputStream(file1);
        fileIn = new Scanner(inputStream);

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

            this.roomLinkedList.add(new Room(roomNum,roomName,roomDescription,roomVisited,roomNorth,roomSouth,roomEast,roomWest));
        }

    }


    public void move(String movement){
        //int direction;
        if (movement.equalsIgnoreCase("n") || movement.equalsIgnoreCase("north")) {
            checkMove(currentRoom.getRoomNorth());
        } else if (movement.equalsIgnoreCase("s") || movement.equalsIgnoreCase("south")) {
            checkMove(currentRoom.getRoomSouth());
        } else if (movement.equalsIgnoreCase("e") || movement.equalsIgnoreCase("east")) {
            checkMove(currentRoom.getRoomEast());
        } else if (movement.equalsIgnoreCase("w") || movement.equalsIgnoreCase("south")) {
            checkMove(currentRoom.getRoomWest());
        } else {
            System.out.println("Sorry I didn't understand. Try again.\n\n");
        }

    }

    public void checkMove(int direction){
        if (direction == 0){
            System.out.println("Theres nothing in that direction.");
        } else{
//            roomArrayList.get(currentRoom.getRoom() - 1).setRoomVisited(true);
//            setCurrentRoom(roomArrayList.get(direction - 1));

            currentRoom.setRoomVisited(true);
            setCurrentRoom(roomLinkedList.get(direction - 1));
        }
    }

    public Room getCurrentRoom(){ return currentRoom; }
    public void setCurrentRoom(Room room){ this.currentRoom = room; }


    public void pickupItem(String pickupString){
        String itemName = pickupString.split(" ")[2];

        for (Item roomitem : currentRoom.getRoomInventory()){
            if (roomitem.getItemName().equalsIgnoreCase(itemName)){
                player.playerInventory.add(roomitem);
                currentRoom.getRoomInventory().remove(roomitem);
                return;
            }
        }
        System.out.println("Item not in room");
    }

    public void test(){
        for (Item i : itemArrayList) {
            currentRoom.getRoomInventory().add(i);
        }
    }




}
