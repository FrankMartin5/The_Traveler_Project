# the_traveler_project
Text based adventure game on the console.

Team Name: Travelers

Project Name: The Traveler 

# Project concept:
Story: Main character is transported to medieval/fantasy times, and is trying to find his evil ancestors (Boss BABY) that have ruined his life.

Sensei: Reanu Keeves, “tutorial guide” an AI - future techStarting setting: castle

General Flow: main character gets hinted to find 3 distinct potions to kill the boss baby

Features:
- GUI - Player can now play the game without an IDE
- “look [direction] : View information of the next room
- “go [direction]” : north,west,south,east
- “fight [npc name]” : Enter combat with an enemy. Each combat involves 3 rounds of riddle challenges. The player has to win 2 rounds to defeat the enemy, or else the combat will be ended with the player taking 25 points of damage to health. If the player loses a round, the enemy would taunt the player with a random quote. If the player attempts to fight a friendly npc, the npc will disappear as a punishment to the player so the player cannot have a chance to take the quiz challenge for a reward or hint.
- “talk [npc name]” : Enter conversation with a friendly npc. The npc would return random statement. Player can choose to accept the quiz challenge and be rewarded.
- “get [item name]” : Player can pick up an available in the room and put it into the inventory.
- “heal [player’s name]” : Player can be healed by adding 25 points with a health potion.
- “status” : View player’s status - name, health, level, experience, inventory.
- “help” : View available commands.
- “room info” : view current room information such as description, friendly npc, enemy, direction, item(s)
- “quit game” : Terminate the game
- “map” : View map of the game


- 5 rooms:
“Description, directional (NWSE)”
“NPC in the room”
“Items in the room”

- Enemies:
Drops items on death
2 successful hits to defeat
Charges spell, player dies if still in combat for 4 turns
Certain items can affect behavior

# items
Lantern: Needed for all the rooms except Great Hall, if a player enters a room without a lantern they can not see anything and won’t be able to interact.

Keys: Crypt Room Key

- potions
Confusion potion: increases mob spell (6 turns) charge up time
Thunderbolt potion: (needed to remove bosses shield for hit chance increase)
escape potion: places you back in the great dining hall, combat or not
anti-shield potion: (needed to remove bosses shield for hit chance increase)

- Gun/bullets: damages boss and mobs, default hit chance:60% miss chance: 40%

# starting inventory
Gun, bullets (5)

# Room Diagram
[Diagram link](https://docs.google.com/drawings/d/1_ybfLzBiHIwmHWU_Nan2W2k6W9mNbvIVSj6Cn-EH_Fg/edit)
