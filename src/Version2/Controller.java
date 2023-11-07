package Version2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    //This is my 'Controller' Class

    ArrayList<File> gameFiles = new ArrayList<>();
    private Game game;
    private Display display;
    private Scanner input;
    private boolean gameOver = false;
    private boolean startOver = true;

    public Controller(Game game, Display display){
        this.game = game;
        this.display = display;
        this.input = new Scanner(System.in);

    }

    public void startGame() throws FileNotFoundException {
        while(startOver) {
            selectPlayer();
            display.printSeperator();

            while (!gameOver) {

                monsterCheck(); //Checks if Room has a monster
                if (!game.playerCheck()) {
                    display.printBadEnding(game.player);
                    gameOver = !gameOver;
                    break;
                }

                //Prints room description
                display.displayRoomInfo(game.getCurrentRoom());


                puzzleCheck(); //Checks if Room has a puzzle
                //checkInventory();


                System.out.println("What would you like to do? Type 'help' for commands.");
                String userInput = input.nextLine();

                //Passes user input
                userCommand(userInput);


                //checks if player has picked up ALL items
                if(game.inventoryCheck()){
//                    display.printCompleteRobbery();
//                    gameOver = !gameOver;
//                    break;
                }
                display.printSeperator();
            }
            gameOver();
            display.printSeperator();
        }
    } //This method starts the game and loops indefinitely until user quits.


    private void selectPlayer() {

        display.characterList(game.playerArrayList);

        while (true){

            System.out.print("\nSelect a player: ");
            String userInput = input.nextLine();

            if(userInput.isEmpty()){return;}

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

    public String splitCommand(String string){
        String[] listString = string.split(" ");

        if(listString.length>1){ return listString[1]; }

        return string;
    }

    private void monsterCheck() {
        if ((game.getCurrentRoom().getMonster() != null) && (!game.getCurrentRoom().getMonster().getMonsterDefeat())){
            display.monsterIntro(game.getCurrentRoom());
            monsterMode(game.getCurrentRoom().getMonster());
        }
    }

    private void userMonsterCommand(String userInput,Boolean canIgnore) {
        userInput = userInput.toLowerCase();
        String item = splitCommand(userInput);

        if(userInput.startsWith("examine")){
            display.examineMonster(game.getCurrentRoom().getMonster(), canIgnore);
        } else if (userInput.equalsIgnoreCase("help")) {
            display.displayHelp(game.gameCommands);
        } else if(userInput.startsWith("ignore") && canIgnore){
            display.ignoreMonster(game.getCurrentRoom().getMonster());
            game.getCurrentRoom().getMonster().monsterDefeated();
        }  else if(userInput.startsWith("ignore") && !canIgnore){
            display.cannotIgnoreMonster(game.getCurrentRoom().getMonster());
        }  else if(userInput.startsWith("attack")){
            game.attackMonster();
            display.playerAttack(game.player,game.getCurrentRoom().getMonster());
            if(game.monsterCheck()){
                display.attackPlayer(game.getCurrentRoom().getMonster(), game.player);
                game.attackPlayer();
            }else {
                display.monsterDefeat(game.getCurrentRoom().getMonster());
            }
        } else if (userInput.startsWith("equip")) {
            game.equipItem(item);
        } else if (userInput.startsWith("unequip")) {
            game.unequipItem(item);
        } else if (userInput.contains("status")) {
            display.printPlayerStatus(game.player);
        } else if (userInput.contains("inventory")) {
            display.printInventory(game.player);
        } else if (userInput.startsWith("heal")) {
            game.healItem(item);
        } else if (userInput.contains("zero")) {
//            display.monsterZero(game.getCurrentRoom().getMonster());
//            game.getCurrentRoom().getMonster().setHealthPoints(0);
        } else {
            display.printInvaldInput();
        }

    }

    private void monsterMode(Monster monster) {
        display.monsterCommandPrompt(monster);
        String userInput = input.nextLine();
        userMonsterCommand(userInput,true);
        display.printSeperator();
        if (game.monsterThresholdCheck()){
            display.monsterDoubleDamage(monster);
        }
        while((game.playerCheck()) && (game.monsterCheck())){
            display.printPlayerMonsterHP(game.getCurrentRoom().getMonster(), game.player);
//            if (!game.playerCheck()){
//                gameOver = true;
//                display.printBadEnding(game.player);
//                return;
//            }
            display.monsterCommandPrompt(monster);
            userInput = input.nextLine();
            userMonsterCommand(userInput,false);
            display.printSeperator();
        }

    }

    private void gameOver() throws FileNotFoundException {
        display.printGameOverPrompt();
        String userInput = input.nextLine();
        while (true){
            if (userInput.equalsIgnoreCase("exit")){
                display.printExit();
                this.startOver = false;
                break;
            } else if (userInput.equalsIgnoreCase("restart")) {
                restart();
                break;
            }
            else {
                display.printInvaldInput();
            }
            userInput = input.nextLine();
        }
    }

    private void restart() throws FileNotFoundException {
        gameOver = !gameOver;
        game = new Game();
        setupGame();
        display.printSeperator();
    }


    public void addGameFiles(File rooms, File items, File puzzles, File commands, File players, File monsters) {
        gameFiles.add(rooms);
        gameFiles.add(items);
        gameFiles.add(puzzles);
        gameFiles.add(commands);
        gameFiles.add(players);
        gameFiles.add(monsters);
    }

    public void setupGame() throws FileNotFoundException {
        game.populateRooms(gameFiles.get(0));
        game.populateItems(gameFiles.get(1));
        game.populatePuzzles(gameFiles.get(2));
        game.populateCommands(gameFiles.get(3));
        game.populatePlayers(gameFiles.get(4));
        game.populateMonsters(gameFiles.get(5));
        game.fillrooms();
        game.setFirstRoom();
    }
}
