# Battleboat

The Battleship-inspired board game created using `Java` and `javafx`.

## Navigation
<a name="top"></a>
1. [Game Description](#intro)
    - [Screenshots](#screen)
2. [Game Controls and Features](#feature)
3. [How to Install Battleboat](#install)
4. [Documentation](#document)
5. [Code Extension for Future Development](#extension)
5. [Authors](#Authors)
6. [Individual Contributions](#addendum)
7. [License Information](#license)

## <a name="intro"></a>Game Description
Battleboat is a turn-by-turn based game in which the objective is to destroy all the opponent’s boats before he destroys yours.


[Back to top](#top)

## <a name="screen"></a>Screenshots

![Imgur](https://i.imgur.com/eWIKpeU.png)
![Imgur](https://i.imgur.com/LmuS75W.png)

    
[Back to top](#top)

## <a name="feature"></a>Game Controls and Features

###RULES:

-Each Player has different boats to choose from, for his board.
-Each player then sets up his/her boats anywhere on the board either vertically, horizontally or diagonally.
-When the players are done with setting their respective boards, the game begins.
-Battleboat is a turn-by-turn based game.
-On one’s turn, one chooses the type of missile with which one wants to attack the opponent’s board.
-Then, one clicks a tile on the opponent’s board to try and hit a boat.
-A red tile represents that a boat has been hit, and a blue tile represents that the shot was missed.
-The game continues turn-by-turn, and the first player to destroy all the boats of the other player wins.

### Types of Boats:
-Patrol Boat: A 2-tile boat with a special Patrol Scanner Missile.
-Destroyer Boat: A 3-tile boat.
-Submarine: Another 3-tile boat.
-Battleship: A 4-tile boat to pay tribute to the original Battleship game.
-Carrier: A 5-tile big boat.

### Types of Missiles:
-Regular Missile: A normal missile which hits 1 selected tile in the opponent’s board.
-Submarine Missile: A special missile which keeps going by hitting every tile from the tile selected to a tile which is either a boat or the end of the board.
-Patrol Boat Scanner:  A special missile launched from the player’s patrol boat with a special ability to reveal a 3*3 area in the opponent’s board but at the cost of revealing the player’s patrol boat’s location to the opponent.


[Back to top](#top)

## <a name="install"></a>How to Install Battleboat

### For `Windows`

 
- To install the game, simply click the link here:

https:\\www.allnewbattleboat.com

-After entering the website, click the download button at the top of the page. After the download is finished, open the “Battleboat.zip” file with an archiver utility of your choice. We recommend 7-Zip, which can be found at https://www.7-zip.org/.

-Now select the location where you want to store the game data and unpack the files. Once the files are unzipped, you are good to go. Just double click on the executable file, “Battleboat.exe” to play.


[Back to top](#top)

## <a name="documen"></a>Documentation
### The <Coord> Class:
This class stores the information of the coordinates of the players’ boats. It interacts with the <Move> class which further updates the <Battleboat> class to send information to the <Battleboatboard> class. It has the “row” and “col” private attributes.
It has the following methods:
The constructor creates a Move object. 
-The getRow() method which returns the row of the coordinates.
-The getCol() method which returns the col of the coordinates.
-The toString() method which returns a string representation of the coordinates.
 
### The <Missile> Class:
This class stores information about the missile that is to be used by the <Player> class. It has in the “quantity” and “type” private attributes.
It has the following methods:
-The constructor creates an instance of a default missile.
-Another Constructor for a non-default missile and will be used for future development.
-The getType() method which returns the type of the missile.
-The getQuantity() method which returns the quantity of the missiles.
-The canUse() method which returns true if the player still has missiles left to be used.
-The use() method returns true only when the players use a missile.
 
### The <Move> Class:
This class uses inheritance by extending the Coord class. This class is used to make a move according to the type of missile used by the player. It has the “missileIndex” private attribute.
It has the following methods:
-The constructor which inherits from the <Coord> Class.
-The getMissileIndex() method which returns the index of a missile from the list of missiles
-The toString() method which inherits from the <Coord> class. 

### The <BattleboatBoard> Class
This class initializes the gameplay board and allows the player to modify the board by changing the size of the board. 
It has functions as below:  
-validcoordinate() function. It returns true if and only if the coordinate is valid. 
-The getHealthyBoatCount() checks the board then returns the number of boat that is still left.
-The isBoat() function takes in the row and column value then returns if the object is a boat. 
-The hasMove() checks if the is already occupied by the missile.
-The Move() changes the board with different kinds of missiles. 
-The canplaceBoat() returns true if the boat can be placed.
-The placeBoat() function returns true if the boat will be placed. 
-The get() takes in the row and column value and returns what is in that position. 
-The set() function takes a row, column, and character value, then set the position into the character.

### The <BattleboatController> Class :
This is the parent controller class .
Attributes:
-The single instance of Battleboat object is the main game model. All other
attributes are gotten through this model, for the sake of clarity in my codes.
-The following are the four Player objects: player1 , player2 , player3 , and player4 .
-The following are four BattleboatBoard objects: board1 , board2 , board3 , and
board4 .
-The player and board with the same number are related.
Methods:
-The constructor does not take any parameters. It creates a new Battleboat game model
to be referenced by any in-game interaction.
-The setup method is meant to be the phase where players take turns placing
Boat objects on their respective boards.
-The setupHelper method is made to place all Boat objects of a specific type. It is
a private helper method and not meant for access outside of the class.
-The play method is meant to be the phase where players take turns playing
moves on their opponent’s boards.
-Abstract declarations of report , reportMove , and reportFinal are meant for
implementation in the subclass BattleboatControllerVerbose .

### The <BattleboatControllerVerbose> Class:
This subclass extends BattleboatController . There are no additional attributes. However, the
purpose of this subclass is to implement console messages that the user will see.
Methods:
There is no constructor since this class is not meant to be a fully functional controller.
-The report method prints out the state of the boards in play.
-The reportMove method prints out who’s turn it is. The displayed player should
be the one to input a move when prompted.
-The reportFinal method prints out the winner of a game. This method should only
be called when the game is over.

### The <BattleboatControllerHumanVSHuman> Class :
This subclass extends BattleboatControllerVerbose . The intention is to simulate a two
human-player game . There are no additional attributes added to its parent class.
Methods:
The constructor is directly inherited from BattleboatController .
-There is one main method that constructs itself, calls setup and calls play .
-This is the java file to run to play a default one versus one game.

[Back to top](#top)

## <a name="Extension"></a>Code Extension for Future Development

The code is designed for a one versus one and two versus two environment. Although that has not been fully implemented yet all methods are created to adapt to two versus two play. For instance, the main Battleboat class file generates four players and four boards instead of two. The GUI BBGrid generate a grid for each board separately. Hence, by generating additional grids and modifying the main rule set in BattleboatController, a two versus two game is a few edits away. It is highly regrettable that we did not have enough free time to devote to this game. Therefore, we are going to keep supporting future development on this project.

[Back to top](#top)



## <a name="Authors"></a>Authors

We are computer science students at the University of Toronto Mississauga.
This project was done for credit in the course CSC290: Communication Skills for Computer Scientists.
Our members are listed below:

-    David Zhao
-    Yixuan Zhang
-    Iwan Irawan

[Back to top](#top)

## <a name="addedum"></a>Individual Contribution

### Iwan Irawan
Contribution in the code:

I worked on 3 different classes namely: <Coord>, <Missile>, and <Move>.
The players place their boats on the board to play the game. The <Coord> class stores the coordinates of each of the boats so that a boat can be hit if the players choose the correct coordinates for their move. The <Missile> class is designed such that a player has different types and a certain quantity of missiles to be used. It checks if the player still has missiles left to be used based on the quantity and which type of the missile it is based on the type. The <Move> class is used to make a move for the player by getting the coordinates from the <Coord> class. It also decides how to make that move based on the type of missile used by the player. It does so by getting the index of the missile from the list of the missiles that a player has.

Contribution in Readme:

Gameplay, Individual Contribution, Licensing, Documentation

### Yixuan Zhang
Contribution in the code:

Finished the BattleboatBoard class with all the functions that go along with the class itself, like getHealthyBoatCount(), validCoordinates() and others. The board contains all the correlation between boats and missiles, and move the missile differently according to the missile type you chose. It also checks available moves that can be made on the board and consistently making sure that the game is not over.
.
Contribution in Readme:
Installation, Individual Contribution, Documentation


### David Zhao
    Contribution in the code:
I worked on the Player class and how to implement and track each player’s resources. Out of multiple designs, I dabbled in a modified GUI system. By uniquely extending from prebuilt javafx constructs such as Button and GridPane, we can better store information and iterate through the grid.
    
    
    Contribution in Readme:
    Code Extension, Documentation, Individual Contribution

[Back to top](#top)

## <a name="license"></a>License Information

Open Software License 3.0

Copyright © 2019 Battleboat

You can find a copy of the License at https://mit-license.org/

License for them is in `Public Domain`
