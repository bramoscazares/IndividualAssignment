Hello!
Welcome to the my mini text-based adventure game called "Lost House".

SETUP: -----------------------------------------------------------------------------------------------------

This game requires a text file named "rooms.txt" that has all information for the rooms on the map.
Each line in this file follows the following format, separated by "=".

[ roomNumber=roomName=roomDescription=roomVisitedBoolean=NorthInt=SouthInt=EastInt=WestInt ]

roomNumber         - The room number, a numerical identifier.
roomName           - The name of the Room.
roomDescription    - A short description of the room.
roomVisitedBoolean - A boolean value implying if the room has been visited already.
NorthInt/SouthInt  - 4 integer connection for a north, south, east, and west direction of each room.
 EastInt/WestInt


MAP DETAILS: -----------------------------------------------------------------------------------------------------

This file already comes with a useable "room.txt" file that generates the following map:

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

            - From the Foyer [1] you can travel to the Kitchen [2] and Living Room [4].
            - From the Kitchen [2] you can travel to the Foyer [1] and Dining Room [3].
            - From the Dining Room [3] you can travel to the Living Room [4] and Kitchen [2].
            - From the Living Room [4] you can travel to the Foyer [1], Dining Room [3], and Bedroom [5].
            - From the Bedroom [5] you can travel to the Living Room [4] and Bathroom [6].
            - From the Bathroom [6] you can only travel to the Bedroom [5].

            -You cannot travel to any other room unless it is stated above.


USER CONSOLE: -----------------------------------------------------------------------------------------------------

You start in the first room, the foyer, and navigate from there.
The console will inform the player of their current location and provide a brief description.
If the user has visited room, the console will also show a message before the room's description.
Then, the console will then ask the user where they would like to go, prompting the user for their movement.

The user must enter a 'n','s','e', or 'w' for each of the respective directions.
Anything other than that, console will let the user know that it didn't understand and to try again.

            **Screenshots are provided in the 'Screenshots' folder to demonstrate a playthrough.
             User input is boxed in yellow.**


At this moment, all rooms are empty and there is no objectives. The player simply moves through rooms.



FILES/CLASSES: -----------------------------------------------------------------------------------------------------

Main:
    This is where the game is initiated. There is no special functions besides starting the game.
    This class creates the scanner to read the Text file and another scanner for user input.
    An ArrayList is also created. Room objects get added after the Scanner reads from 'rooms.txt'.
    A player object is initialized and its playThrough() method will start the game.

Player:
    This class will hold player information. Currently it only holds which room the player is in.
        > playThroough() method takes a scanner and this is where the console interacts with the user.
        > move() this methods takes the user to the room in the specified direction.
        > checkMove() checks if the player can go in the specified direction.
                - FALSE: The player is notified nothing in that direction.
                - TRUE: It moves the player by updating its currentRoom variable.
                    True will also set the Room object's Visisted boolean to true.
        > roomVisitedCheck() checks if room has been visted already.
                - FALSE: Nothing will display.
                - TRUE: The console will display a message.

Map:
    This class holds all the information for a room object, when initiated.
    This object will hold, and use as parameters:
        the room number (int)
        a room name (string)
        room description (string)
        a flag if room was already visited (boolean)
        North connection (int)
        South connection (int)
        East connection (int)
        West connection (int)
    As well as Setters and Getters for all of these variables.

rooms.txt:
    This file is essential for running the program. It contains the room data that this project will interpret.
    Each data point is seperated with "=".

mapFile.txt:
    This contains only the map in an isolated, simple format, along with the same format presented in this Read Me file.

README.txt:
    This file contains all necessary info about the game, map, files, and classes.


END OF README: -----------------------------------------------------------------------------------------------------