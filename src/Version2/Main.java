package Version2;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    //Main Class that will start the game.

    public static void main(String[] args) throws FileNotFoundException {

        //File Setup
        File rooms = new File("rooms.txt");
        File items = new File("items.txt");
        File puzzles = new File("puzzles.txt");
        File commands = new File("UserManual.txt");

        //MVC Model Setup
        Game game = new Game(rooms, items,puzzles,commands);
        Display display = new Display();
        Controller controller = new Controller(game,display);

        //Game Start
        controller.startGame();
    }
}
