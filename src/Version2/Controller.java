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
            //Prints room description
            display.displayRoomInfo(game.getCurrentRoom());

            puzzleCheck(); //Checks if Room has a puzzle

            System.out.println("What would you like to do? Type 'help' for commands.");
            String userInput = input.nextLine();

            //Passes user input
            userCommand(userInput);
            display.printSeperator();
        }
    } //This method starts the game and loops indefinitely until user quits.

    public void userCommand(String input){
        if (input.equalsIgnoreCase("quit")){
            gameOver = true;
        } else if (input.contains("go")) {
            game.move(input);
        } else if (input.equalsIgnoreCase("help")) {
            display.displayHelp(game.gameCommands);
        } else if (input.equalsIgnoreCase("rooms")) {
            display.displayRooms(game.roomLinkedList);
        } else if (input.equalsIgnoreCase("explore")) {
            display.exploreRoom(game.getCurrentRoom());
        } else if (input.contains("inventory")) {
            display.printInventory(game.player);
        } else if (input.contains("pickup")) {
            game.pickupItem(input);
        } else if (input.contains("inspect")) {
            game.inspectItem(input);
        } else if (input.contains("drop")) {
            game.dropItem(input);
        } else {
            System.out.println("Invalid Command. Try again.");
        }

    } //This method processes user input


    public void puzzleCheck(){      //If Room has a puzzle, and it's not solved yet
        if ((game.getCurrentRoom().getRoomPuzzle() != null) && (!game.getCurrentRoom().getRoomPuzzle().solved)){
            solvePuzzle(game.getCurrentRoom().getRoomPuzzle());
        }
    } //Checks for puzzle

    public void solvePuzzle(Puzzle puzzle){
        //Prints puzzle name and description
        System.out.println("\n\n"+ puzzle.getName());
        System.out.println(puzzle.getDesciption());

        //Loops till attempts run out
        for (int i = puzzle.numAttempts; i > 0; i--){
            System.out.print("Enter Answer:");
            String userAnswer = input.nextLine();

            //If answer is correct~
            if(userAnswer.equalsIgnoreCase(puzzle.getAnswer())){
                System.out.println("Sweet. Thats right!");
                puzzle.setSolved();
                return; //Exits interface
            }
            //If answer is incorrect
            System.out.println("the answer you provided is wrong, you still have "+ (i-1) + " attempts left. Try again.\n");
        }

        //After all failed attempts
        System.out.println("Failed to solve.\nDang, I'll try later.");
    }  //Main Puzzle Interface


}
