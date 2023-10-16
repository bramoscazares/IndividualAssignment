Hello!
Welcome to the mini text-based adventure game called "House Reaper".

SUMMARY: -----------------------------------------------------------------------------------------------------

This is a text-based adventure game called "House Reaper", where you play as a burglar stealing items while
the homeowner is away on vacation.

This homeowner is away on a business trip, and you take advantage of this opportunity to raid his home and
get yourself some treasures you can sell for cash.

SETUP: -----------------------------------------------------------------------------------------------------

This game requires the text files for a normal playthrough:

"rooms.txt":
    This file has all information for the ROOMS on the map.
    Each line in this file follows the following format, separated by "=".

    [ roomNumber=roomName=roomDescription=roomVisitedBoolean=NorthInt=SouthInt=EastInt=WestInt ]

    roomNumber          - The room number, a numerical identifier.
    roomName            - The name of the Room.
    roomDescription     - A short description of the room.
    roomVisitedBoolean  - A boolean value implying if the room has been visited already.
    NorthInt/SouthInt   - 4 integer connection for a north, south, east, and west direction of each room.
     EastInt/WestInt

"items.txt":
    This file has all information for the items that will be placed in rooms.
    Each line in this file follows the following format, separated by "=".

    [ itemName=itemDescription=roomlocation ]

    itemName            - The item name.
    itemDescription     - A short description of the item.
    roomlocation        - The item's room location.

"puzzles.txt":
    This file has all information for the puzzles that will be placed in rooms.
    Each line in this file follows the following format, separated by "=".

    [ puzzleName=PuzzleDescription=puzzleAnswer=roomlocation ]

    puzzleName          - The puzzle name.
    puzzleDescription   - The puzzle's description.
    puzzleAnswer        - The correct answer to the puzzle.
    roomlocation        - The item's room location.

MAP DETAILS: -----------------------------------------------------------------------------------------------------

This file already comes with a usable "room.txt" file that generates the following map:

        _________
       |         |                      1 - Foyer
 ______|    1    |______                2 - Kitchen
|                |  6   |               3 - Dining Room
|  2   |____ ____|__ ___|___            4 - Living Room
|____ _|         |          |           5 - Bedroom
|      |              5     |           6 - Bathroom
|  3        4    |__________|
|      |         |
|______|_________|


            Room #      Room Name                 [Direction]   Connection
            ---------------------------------------------------------------------
            1           Foyer                       [South]     Living Room
                                                    [West]      Kitchen
            ---------------------------------------------------------------------
            2           Kitchen                     [South]     Dining Room
                                                    [East]      Foyer

            ---------------------------------------------------------------------
            3           Dining Room                 [North]     Kitchen
                                                    [East]      Living Room
            ---------------------------------------------------------------------
            4           Living Room                 [North]     Foyer
                                                    [East]      Bedroom
                                                    [West]      Dining Room
            ---------------------------------------------------------------------
            5           Bedroom                     [North]     Bathroom
                                                    [West]      Living Room
            ---------------------------------------------------------------------
            6           Bathroom                    [South]     Bedroom
            ---------------------------------------------------------------------

            -You cannot travel to any other room unless it is stated above.


USER CONSOLE: -----------------------------------------------------------------------------------------------------

You start in the first room, the foyer, and navigate from there.
The console will inform the player of their current location and provide a brief description.
If the user has visited room, the console will also show a message before the room's description.
If a room has a puzzle the player will be prompted to solve the puzzle first before inputting another action.

The console will then ask the user what they would like to do, prompting the user for input.
If the player enters an incorrect command, console will let the user know that it didn't understand and to try again.

            **Screenshots are provided in the 'Screenshots' folder to demonstrate a playthrough.
             User input is boxed in yellow.**


FILES/CLASSES: -----------------------------------------------------------------------------------------------------

Main:
    This is where the game is initiated. There is no special functions besides starting the game.
    Here files for the rooms, items, puzzles, and command descriptions are initialized.
    A game, display, and controller class are initialized as well.

Game:
    This class holds all game logic such as moving through rooms and others.

Display:
    This class holds all logic printing messages to the console.

Controller:
    This class starts and ends the game. This class also handles user input and pass it to correlated methods.

Room:
    This class hold all the room information, as well as getters and setters.

Item:
    This class hold all the item information, as well as getters and setters.

Puzzle:
    This class hold all the room information, as well as getters and setters.

Player:
    This class will hold player information. Currently, it only an ArrayList for the player's inventory.

rooms.txt:
    This file is essential for running the program. It contains the room data that this project will interpret.
    Each data point is separated with "=".

items.txt:
    This file is essential for running the program. It contains the item data that this project will interpret.
    Each data point is separated with "=".

puzzles.txt:
    This file is essential for running the program. It contains the puzzle data that this project will interpret.
    Each data point is separated with "=".

mapFile.txt:
    This contains only the map in an isolated, simple format, along with the same format presented
    in this Read Me file. This file also maps out all items and puzzle locations.

UserManual.txt:
    This file lists all commands a user can input during their playthrough.

README.txt:
    This file contains all necessary info about the game, map, files, and classes.


END OF README: -----------------------------------------------------------------------------------------------------