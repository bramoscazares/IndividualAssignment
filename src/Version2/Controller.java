package Version2;

import java.util.Scanner;

public class Controller {
    //This is my 'Controller' Class

    private Game game;
    private Display display;
    private Scanner input;
    private boolean gameOver = false;


    public Controller(Game game, Display display){
        this.game = game;
        this.display = display;
        this.input = new Scanner(System.in);

    }

    public void startGame(){
        while (!gameOver){
            display.displayRoomInfo(game.getCurrentRoom());
            System.out.println("Where would you like to go? (N/S/E/W)");
            String userInput = input.nextLine();
            userCommand(userInput);
            System.out.println();
        }
    }

    public void userCommand(String input){
        if (input.equals("quit")){
            gameOver = true;
        } else if (input.equals("help")) {
            display.displayHelp();
        } else if (input.equals("rooms")) {
            display.displayRooms(game.roomLinkedList);
        } else if (input.equals("explore")) {
            display.printRoomInventory(game.getCurrentRoom());
        } else if (input.contains("pick up")) {
            game.pickupItem(input);
        }else {
            game.move(input);
        }

    }
}
