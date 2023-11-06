package Version2;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    //Main Class that will start the game.

    public static void main(String[] args) throws FileNotFoundException {

        //File Setup
        File rooms = new File("rooms.txt");
        File items = new File("testItems.txt");
        File puzzles = new File("puzzles.txt");
        File commands = new File("UserManual.txt");
        File players = new File("playerData.txt");
        File monsters = new File("monsters.txt");



        //MVC Model Setup
        Game game = new Game();
            game.populateRooms(rooms);
            game.populateItems(items);
            game.populatePuzzles(puzzles);
            game.populateCommands(commands);
            game.populatePlayers(players);
            game.populateMonsters(monsters);
            game.fillrooms();
            game.setFirstRoom();

        Display display = new Display();
        Controller controller = new Controller(game,display);



        //Game Start
        controller.startGame();
    }
}
