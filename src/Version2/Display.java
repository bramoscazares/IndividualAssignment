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
            System.out.println("You check your pockets: ");
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
        System.out.println("Congratulations! You robbed this man blind!\n\nThank you for playing!");
    }

    public void printPlayerStatus(Player player){
        System.out.println("\n" + player.getFirstName() +" " + player.getLastname()+ ": ");
        System.out.println("---------------------------------");
        System.out.println(player.getDescription());
        System.out.println("HP  : " + player.getHealthPoints());
        System.out.println("ATK : " + player.getAttackPoints());
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
}
