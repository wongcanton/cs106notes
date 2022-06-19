# Lecture 2 - Programming with Karel, Control structures in Karel


**Topics**: 
- 1. Handout Information 
- 2. Section Sign-up 
- 3. Karel Commands,
- 4. An Algorithm vs Program 
- 5. Syntax of a Karel Program
- 6. Running a Karel Program 
- 7. Creating Methods 
- 8. SuperKarel 
- 9. A for Loop 
- 10. A While Loop 
- 11. Karel Conditions 
- 12. If Statement 
- 13. Putting it All Together


## 1. Handout Information

Four more handouts today.
	• Information about downloading eclipse
	• Using Karel and the environment
	• First assignment today
		○ Due Friday of next week in class
	• How to submit assignment
	• Handouts are on website in pdf


## 2. Section Sign-up 

Readings are due on the day of the lecture.
So today I should have read the Karel book chapters 1-3.
I did not, wasn't sure. But now I need to catch up on the reading.

## 3. Karel Commands

How to program Karel, 4 commands:
	• move
		○ Moves forward in direction it's facing
	• turnLeft
		○ Turns left
	•  pickBeeper
		○ Picks up beeper
	• putBeeper
		○ Puts down beeper

These are methods, which are instructions that can be called.
Karel responds to the method.
We invoke the methods on Karel.

For a program:
	• Import karel in elipse

A program will:
	• Have an initial state
	• Have a final state
	• Use commands to go from initial state to final state.

Example:
```
move
pickBeeper
move
turnLeft
move
turnLeft
turnLeft
turnLeft
move
putBeeper
putBeeper
```

You cannot do other turns, so just turn left as many times as needed.

The example above is an algorithm, which is a recipe of instructions.
It is not a program since it cannot run.
For an algorithm to be a program, it needs to be syntactically correct in a language.

For Karel:
	• Methods need () at the end
	• Statementd need ; at the end of lines

Also, the program needs to be encapsulated in the following:
```
public void run() {
	
}
```

The body of the program is contained in the braces.
In this case, we defined a new method 'run' for Karel.

```
public void run(){
	move();
	pickBeeper();
	move();
	…
}
```
The tabs make it more readable for human.

This is Java actually.
Karel is a Class in Java.

To make a new class:
``public class ourKarelProgram extends Karel{}``

Okay so we encapsulate the run method in a Karel class.

```
public class ourKarelProgram extends Karel{
	public void run(){
		move();
		pickBeeper();
		…
	}
}
```

But wait, where does the original Karel class come from?
Import it:
``import stanford.karel.*;``
The * means get everything.

Running the same program twice on Karel will fail because the 'initial' state of Karel is different after the first run.

The run method is what Karel will always run.

Now let's say we want to make a new method:
```
private void turnRight(){
	turnLeft();
	turnLeft();
	turnLeft();
}
```

Private are private.
For Karel, the run method must be public.

More on public/private later.

For Karel we can make any method for use except run. run is special and is what Karel uses to run in the world.

There's actually a SuperKarel class that already has turnRight and turnAround methods.

Mehran asks:
What is the downfall of the modern college student?
	• Procrastination
	• And use Snooze
Examples on using snooze.

For loops in Karel:
```
for (int I = 0; I < 3; i++){
	turnLeft();
}
```

What if you don't know how many times you want to do something?
A for loop cannot help this because for loops are used when you know how many times to iterate.
	• While loop

While loop in Karel:
```
while (condition){
	command;
}
```

Example for checking if wall not in front of Karel:
```
while (frontIsClear()){
	move();
}
```

This runs while a wall is not in front of Karel.

Now as a method to use this:
```
private void moveToWall(){
	while (frontIsClear()){
		move();
	}
}
```

Page 18 of Karel: has conditions for Karel.

For running a command when a condition is true once:
	• if operators
```
if (beeperisPresent()){
	pickBeeper();
}
```

But just 'if' is not helpful, we want to do something if it's false:
	• else

```
if (beeperIsPresent()){
	pickBeeper();
} else {
	putBeeper();
}
```

You can nest if statements:
```
if (this()){
	if (that){
		doThis;
	}
} else {
	doThat;
}
```

Steeple problem:
	• Have Karel go over hurdles

Mehran writes a series of small functions for ascending and descending, and calls those in run().
He says that the abstraction is a great way to make the program more human readable instead of just writing code to do the entire thing without function definitions.

Good software engineering is about writing software that not only works, but is human understandable.

It's good to write Karel programs that achieve a goal in a given world, but can be applied in other worlds as well.

