# snake-game
Created a snake game using Java, and Processing library.
The processing core jar should be included in the build path.

The snake moves up/down/left/right with the key buttons. 
The red squares are food and if you eat them the snake gains points. 
The snake dies if it hits any part of its tail or it goes over
the edge of the sides. 
There is a score counter at the top left where we can see how
many points the snake currently has. 1 food = 10 points.
The food the snake eats ends up on its body and follows it around.
There is also a play/pause button where if you click on it
you can pause the game. The button becomes highlighted when 
you stand over it. The button can be kind of finnicky
but for the most part if you click hard enough it seems to
go through. 

I have two classes, Snake, and Panel. Snake is the object class 
and Panel is like the main class. The Panel class uses the draw()
method to keep updating and printing the snake. 
It also is the class that contains the panel details like clicking
the button// moving the up/down/left/right keys. The up/down/L/R
keys then edit the xSpeed and ySpeed in the object mySnake which is
an instance of the Snake class. The Panel class's main function is
to go through and constantly update the snake, print the snake, 
create the food, and if food is eaten to create the new food.

The Snake class has variables x, y,xSpeed and ySpeed which control the
movement of the snake. Variables x and y give current location and xSpeed  
and ySpeed give which direction that the snake is moving.
The movement of the tail of the snake is kept in a separate array.
There are functions to check if paused and if alive.

I already incorporated a lot of things that improve the quality 
of the game, for example, it is not possible to go backwards if you
are currently going forwards, or the equivalent in all the other directions, 
because this would automatically create a game over. Also, 
it is not possible for the food to generate on any part of the snake,
head or tail. 



