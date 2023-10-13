import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
      //
        //SET UP ===========
        FileInputStream inputStream = new FileInputStream(new File("rooms.txt"));
        Scanner fileIn = new Scanner(inputStream);
        Scanner userIn = new Scanner(System.in);

        ArrayList<Room> rooms = new ArrayList<>();

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

            rooms.add(new Room(roomNum,roomName,roomDescription,roomVisited,roomNorth,roomSouth,roomEast,roomWest));
        }

        //Player
        Player player = new Player(rooms);

        while(true){
            player.playThrough(userIn);
        }




    }
}