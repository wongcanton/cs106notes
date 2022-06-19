# Lecture 3

Topics: 
- 3.1 Karel and Java, 
- 3.2 Common Errors, 
- 3.3 Comments, 
- 3.4 Pre-conditions and Post-conditions, 
- 3.5 Decomposition, 
- 3.6 The DoubleBeepers Example, 
- 3.7 Importance of Good Software Engineering, 
- 3.8 The Right Decomposition, 
- 3.9 The CleanUpKarel Example



## 3.1 Karel and Java
All Karel program files have a file extension .java
These are technically Java.
For Karel, don't use any other Java constructs for the exercises.

To stop Karel, close the program window.


## 3.2 Common Errors:
	• Infinite loop
		○ Loop that keeps going because it cannot be existed
```java
	while (frontIsClear()) {
		turnLeft();
	}
```
		○ Syntax is valid, but the program doesn't do anything useful
	• OFF BY ONE BUG (OBOB)
		○ Forgot to do something one more time than required
		○ This is the fencepost error from the reading
		○ The last anticipated action doesn't run
		○ It happens all the time in many programs

## 3.3 Comments:
	• Putting something in a program for human reading that does nothing in the program
	• In Karel/Java a multi line comment:
	/* comments
	comments
	comments */
	• For good programming practice: have a comment at the top of the file that says the file name and an explanation of the program
	• Single line comments can be 
	// comment
	• Methods should have comments as well as good practice
		○ Preconditions and postconditions are fantastic to have for method comments
			§ Do this for Karel programs at least
See handouts for example code with comments.

Decomposition
	• Breaking down a problem into smaller problems to solve

'Top down design'/Stepwise Refinement:
	• Make high level description, then break into smaller until level of detail is 'primitives' which are commands in a programming language
	• The process of breaking problems down to smaller pieces is called decomposition

Another approach is 'bottom up design':
	• Thinking of commands first and putting them together to solve the problems
	• Most programmers start with 'bottom up design' without thinking about it
	• 'Top down design' is more effective but takes practice. Get good at this.

Double beepers example:
	• Karel starts 0,0
	• Beepers at 0,1
	• Karel doubles beepers by dropping them
	• Karel goes back to 0,0

Algorithm:
	• Essentially an approach

So in the double beeper example, let's follow a top down approach:
```
public class myDoubleBeeper extends SuperKarel {
	public void run() {
		move();
		doubleBeepers(); // Idea of doing this, not that we have the function yet
		moveBackward(); // Idea of doing this
	}
}
```

So let's make those unwritten functions written:
```
private void moveBackward() {
	turnAround();
	move();
	turnAround();
}

private void doubleBeepers() {
	// This will take some thought
	// No counter in Karel…
	// Also at this point we don't know how to declare variables so I can't just make a counter variable
	// This is actually harder than I thought
	// One approach is to put all beepers in a holder place and double there
	
	while (beepersPresent()) {
		pickBeeper();
		putTwoBeepersNextDoor(); 
		// We'll write. Writing methods that don't exist is part of top down design!
	}
	movePileNextDoorBack();
}

private void putTwoBeepersNextDoor() {
	move();
	putBeeper();
	putBeeper();
	moveBackward(); // Exists!
}

private void movePileNextDoorBack() {
	move();
	while (beepersPresent()) {
		moveOneBeeperBack();
	}
	moveBackward();
}

private void moveOneBeeperBack() {
	pickBeeper();
	moveBackward();
	putBeeper();
	move();
}
```

Mehran also teaches CS103.

This decomposition was so much more maintainable than a single straight shot of commands.
Say someone wants to make it triple beepers, they just go to putTwoBeepersNextDoor and add a putBeeper(); line. If this was a just long list of commands, it would be harder to change it.
	• This is why good software engineering is important

Order of method definitions is not improtant in Karel/Java programs. So the calls to methods earlier that are written later is fine.

How much to decompose?
	• Make each method solve one problem
	• Rough guideline: methods likely 1-15 lines long
		○ One line methods are fine since they rename a command for logic in the program
	• Descriptive names for methods
	• Comments on methods to document them
