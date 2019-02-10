The basic concept behind my game is that you need to escape a dungeon by killing the final boss. To do this, you must defeat 4 other bosses that are around the map. For the game, the map looks like this:

----F----
----###--
O####-##O
----#----
O#--###--
-##-#-#--
--###-##O
----P----

Where P is the player (or starting room), # is a room, O is a boss, and F is the final boss. It is basically impossible to defeat the final boss unless you collect the items from the other bosses.

For tallying up the points, I decided to not count both getters and setters since half the time, I wouldn't even use the setters. So, I counted getters and setters in pairs. I also created a bunch of room objects, but only counted the boss rooms and player start area since I used a for loop to set the values for all the other rooms.

The total points I got doing this was 207. I commented in my code with where I think I deserved them.

To start the game, run the Main.java file. For the responses, I only accepted 1 word, so a command like "take sword" will not work but "take" will. Also, weapon names and food items are case sensitive.
