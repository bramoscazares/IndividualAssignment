package Version2;


import java.util.LinkedList;

public class Display {
    //This is my 'View' Class

    public void displayRoomInfo(Room room){
        System.out.println("You currently stand in the " + room.getRoomName().toLowerCase()+ ".");
        visitedRoomCheck(room);
        System.out.println(room.getRoomDescription());
    }

    public void visitedRoomCheck(Room room){
        if (room.isRoomVisited() == true){
            System.out.println("Although you've visted this room before. The air feels different somehow.");
        }
    }

    public void displayHelp(){
        System.out.println("Here are the current commands for help.");
    }

//    public void displayRooms(ArrayList<Room> roomArrayList) {
//        System.out.println("\nHere is a list of rooms to explore:");
//        for (Room r: roomArrayList){
//            System.out.println(r.toString());
//        }
//        System.out.println();
//    }
    public void displayRooms(LinkedList<Room> roomList) {
        System.out.println("\nHere is a list of rooms to explore:");
        for (Room r: roomList){
            System.out.println(r.toString());
        }
        System.out.println();
    }

    public void printRoomInventory(Room room){
        if (room.getRoomInventory().size() != 0){
            for (Item i : room.getRoomInventory()){
                System.out.println(i.getItemName());
            }
        } else {
            System.out.println("Sorry. This room is empty. Nothing much is going on here.");
        }
    }

}
