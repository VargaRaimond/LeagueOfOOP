
# League of OOP - First Stage
Name: Raimond Varga
Group: 325CA

## Introduction
Simple implementation of League of OOP, a MMO-style game. It consists of a board with 4 land types and 4 types of players. Every round the players move in a specific way and if two players meet, they fight each other.

Every hero has two abilities and he uses them in every fight. Also, out of the 4 land type, one is the hero's preferred type, fighting on it giving him extra damage.  The types of players, with their specific abilities and preferred terrain are:
1. Wizard - a magic caster with superior mental capacity:
	Abilities: Drain and Deflect;
	Preferred land type: Desert;
2. Knight - a soldier who is expert in body fighting:
	Abilities: Execute and Slam;
	Preferred land type: Land;
3. Rogue - an asassin who excels in sneak attacks
	Abilities: Backstab and Paralysis;
	Preferred land type: Woods
4. Pyromancer - a high-damage fire manipulator:
	Abilities: Fireblast and Ignite
	Preferred land type: Volcanic


## Input - Output Demo
The input consists of 3 main components:
	- the map - dimensions and letters that represent the land type;
	- the players - number of players and the every player with his type       and 	position;
	- the rounds - number of rounds and for every round a string of noPlayers letters which decides how every player moves;
This is an example for a 4x4 map with 7 players and 5 rounds:

![Imgur](https://i.imgur.com/rmzE1RV.png)  

The output consists of a scoreboard of all the players. This is the output for the specified input:
![Imgur](https://i.imgur.com/pWIQMDG.png)

## Packages and classes
I have 3 main packages:
1. Main - includes main function and also 3 more classes:
	- GameInput - stores input;
	- GameInputLoader - gets input;
	- GameEngine - simulates the game;
2.  Map - includes the map, map cell and also land types and a factory to generate map cells;
3. Heroes - includes an abstract class which is extended by all player types, classes for all players, a factory that generates players and another package for abilities:
	- it has all the abilities as well as a factory that generates them and stores them in a hashmap.

## Code example - Double Dispatch

Bellow is an example of Double Dispatch from my Wizard class:

![Imgur](https://i.imgur.com/fMKtIo0.png)
Even though some interactions are the same with more than one type of player, there are some differences and also, this makes it easier to add other player types or completely change only one player type, maybe for the second stage of this homework/



