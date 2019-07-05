# ing-sw-2019-limperio-nicotera-italia
Progetto di Ingegneria del Software A.A 2018-2019 Polimi

Group composed by:
1. Pietro L'Imperio
2. Francesco Nicotera
3. Giuseppe Italia

The project is based on the implementation of the digital version of the game board "Adrenaline".

Additional functionality: Terminator.

Communication way: socket

GUI implementation.

How to start the game (the machine where the server run has to be connected to the same network of all of the clients):

- Server side:
Click two times on the jar file or open it from command line with the instruction: "java -jar adrenalina-1.0-SNAPSHOT.jar".
It will be shown a frame where choose to be server or player. Click on the icon of server. Wait the connection of the clients.

- Client side:
Click two times on the jar file or open it from command line with the instruction: "java -jar adrenalina-1.0-SNAPSHOT.jar".
It will be shown a frame where choose to be server or player. Click on the icon of joystick. 
Insert the ip address that you can read opening the prompt of command and digiting "ipconfig". (Be sure to copy the correct ip)
Choose the nickname and color you want to play with. They could be already used, try again.
If you are the first client connected to the server you have to choose if you want frenzy turn at the end of the game, the type of map to play with and if you want terminator mode in the match.


After the connection of the third player it will starts a timer of 60 seconds after that the game will start.

How to change some configuration in the game:

- If you want to change the timer after which the game will start you can open the jar as rar/zip folder and change in timer/timerForStartOfGame.txt the duration (in milliseconds).
- If you want to change the timer for the turn of a player (set to 3 minutes by default) you can open the jar as rar/zip folder and change in timer/timerForTurn.txt the duration (in milliseconds)
- If you want to end the normal mode of the game (before frenzy) not after 8 skulls (as a default) but after a different number of removed skulls open the jar as rar/zip folder and change in textfile/numOfSkull.txt the value.

ENJOY!