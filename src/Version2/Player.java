package Version2;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    public ArrayList<Item> playerInventory;

    public void printInventory(){
        System.out.println("You currently have the following items in your inventory: ");
        for (Item item : playerInventory){ System.out.println(item.getItemName()); }
    }

}
