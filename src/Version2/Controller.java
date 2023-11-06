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
        selectPlayer();

        while (!gameOver){

            monsterCheck(); //Checks if Room has a monster

            //Prints room description
            display.displayRoomInfo(game.getCurrentRoom());


            puzzleCheck(); //Checks if Room has a puzzle
            //checkInventory();

            System.out.println("What would you like to do? Type 'help' for commands.");
            String userInput = input.nextLine();

            //Passes user input
            userCommand(userInput);
            display.printSeperator();
        }
    } //This method starts the game and loops indefinitely until user quits.


    private void selectPlayer() {

        display.characterList(game.playerArrayList);

        while (true){

            System.out.print("\nSelect a player: ");
            String userInput = input.nextLine();

            for (Player player: game.playerArrayList){
                if (player.getFirstName().equalsIgnoreCase(userInput)){
                    game.setPlayer(player);
                    System.out.println();
                    return;
                }
            }
            System.out.println("Player does not exist. Try again.");
        }


    }

    public void userCommand(String input){
        input = input.toLowerCase();
        String item = splitCommand(input);

        if (input.equalsIgnoreCase("quit")){
            gameOver = true;
        } else if (input.contains("go")) {
            game.move(item);
        } else if (input.equalsIgnoreCase("help")) {
            display.displayHelp(game.gameCommands);
        } else if (input.equalsIgnoreCase("rooms")) {
            display.displayRooms(game.roomLinkedList);
        } else if (input.equalsIgnoreCase("explore")) {
            display.exploreRoom(game.getCurrentRoom());
        } else if (input.contains("inventory")) {
            display.printInventory(game.player);
        } else if (input.contains("pickup")) {
            game.pickupItem(item);
        } else if (input.contains("inspect")) {
            display.displayItemInfo(game.inspectItem(item));
        } else if (input.contains("drop")) {
            game.dropItem(item);
        } else if (input.startsWith("equip")) {
            game.equipItem(item);
        } else if (input.startsWith("unequip")) {
            game.unequipItem(item);
        } else if (input.contains("status")) {
            display.printPlayerStatus(game.player);
        } else if (input.startsWith("heal")) {
            game.healItem(item);
        } else if (input.contains("monsters")) {
            display.printAllMonsters(game.monsterArrayList);
        } else {
            display.printInvaldInput();
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
            System.out.print("Enter Answer: ");
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

    public void checkInventory(){
        if(game.inventoryCheck()){
            gameOver = true;
            display.printCompleteRobbery();
        }
    }

    public String splitCommand(String string){
        String[] listString = string.split(" ");

        if(listString.length>1){ return listString[1]; }

        return string;
    }

    private void monsterCheck() {
        if ((game.getCurrentRoom().getMonster() != null) && (!game.getCurrentRoom().getMonster().getMonsterDefeat())){
            display.monsterIntro(game.getCurrentRoom());
            String userInput = input.nextLine();
            while(!userInput.equals("ignore")){
                userMonsterCommand(userInput);
                userInput = input.nextLine();
            }
        }
    }

    private void userMonsterCommand(String userInput) {
        userInput = userInput.toLowerCase();
        String item = splitCommand(userInput);

        if(userInput.startsWith("examine")){
            display.examineMonster(game.getCurrentRoom().getMonster());
        } else if(userInput.startsWith("ignore")){
            display.ignoreMonster(game.getCurrentRoom().getMonster());
            game.getCurrentRoom().getMonster().monsterDefeated();
        }  else if(userInput.startsWith("attack")){
            fightMonster();
        } else if (userInput.startsWith("equip")) {
            game.equipItem(item);
        } else if (userInput.startsWith("unequip")) {
            game.unequipItem(item);
        } else if (userInput.contains("status")) {
            display.printPlayerStatus(game.player);
        }  else {
            display.printInvaldInput();
        }

    }

    private void fightMonster() {
        game.getCurrentRoom().getMonster().setHealthPoints(game.getCurrentRoom().getMonster().getHealthPoints() - game.player.getAttackPoints());
        display.playerAttack(game.player, game.getCurrentRoom().getMonster());
        while((game.player.getHealthPoints() > 0) || (game.getCurrentRoom().getMonster().getHealthPoints() > 0)){

        }

    }


}
