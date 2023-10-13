import java.util.ArrayList;
import java.util.Scanner;

public class Player {


    private Room currentRoom;
    private ArrayList<Room> roomArrayList;


    public Player(ArrayList<Room> roomArrayList) {
        this.roomArrayList = roomArrayList;
        this.currentRoom = roomArrayList.get(0);

    }
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    public void playThrough(Scanner in){
        System.out.println("You are currently stand in the " + currentRoom.getRoomName().toLowerCase() + ".");
        visitedRoomCheck();
        System.out.println(currentRoom.getRoomDescription());
        System.out.println("\nWhere would you like to go? (N,S,E,W)");
        move(in.nextLine());
    }
    public void move(String movement){
        //int direction;
        if (movement.equalsIgnoreCase("n")) {
            System.out.println("You head north.\n");
            checkMove(currentRoom.getRoomNorth());
        } else if (movement.equalsIgnoreCase("s")) {
            System.out.println("You head south.\n");
            checkMove(currentRoom.getRoomSouth());
        } else if (movement.equalsIgnoreCase("e")) {
            System.out.println("You head east.\n");
            checkMove(currentRoom.getRoomEast());
        } else if (movement.equalsIgnoreCase("w")) {
            System.out.println("You head west.\n");
            checkMove(currentRoom.getRoomWest());
        } else {
            System.out.println("Sorry I didn't understand. Try again.\n\n");
        }

    }

//    public void move(String movement){
//        System.out.println("You head " + movement.toLowerCase() + ".");
//        int direction;
//        switch (movement.toLowerCase()){
//            case "north":
//                checkMove(currentRoom.getRoomNorth());
//            case "south":
//                checkMove(currentRoom.getRoomSouth());
//            case "east":
//                checkMove(currentRoom.getRoomEast());
//            case "west":
//                checkMove(currentRoom.getRoomWest());
//        }
//
//    }

    public void checkMove(int direction){
        if (direction == 0){
            System.out.println("Theres nothing in that direction.");
        } else{
            //currentRoom = roomArrayList.get(direction - 1);
            roomArrayList.get(currentRoom.getRoom() - 1).setRoomVisited(true);
            setCurrentRoom(roomArrayList.get(direction - 1));

        }
    }

    public void visitedRoomCheck(){
        if (currentRoom.isRoomVisited() == true){
            System.out.println("Although you've visted this room before. The air feels different somehow.\n");
        }
    }
}
