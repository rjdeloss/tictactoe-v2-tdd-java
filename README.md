# tictactoe-tdd-java (WIP)

Implementing Tic-Tac-Toe using a Java

## Getting Started

prerequisite: [Java Runtime Environment (JRE)](https://www.java.com/en/download/manual.jsp) must be installed onto your computer.

- Install Java Open SDK 17
- Brew Install Gradle
- Disable Kotlin in IDE
- Run command > ./gradlew build
- Run tests > Run command > ./gradlew test
- Play game > IDE run src/main/java/com.e8L/App


## Instructions

Tic-Tac-Toe is a paper-and-pencil game for two players who take turns marking the spaces in a three-by-three grid with X or O. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row is the winner. It is a solved game, with a forced draw assuming best play from both players. -[Wikipedia](https://en.wikipedia.org/wiki/Tic-tac-toe)

Although there's an example on how the game should be played below, this game will run entirely through the test suite meaning it will not include a display class to render how the game is being played.


```
Welcome to TicTacToe in Java
1 | 2 | 3
---+---+---
4 | 5 | 6
---+---+---
7 | 8 | 9
Player X Enter an unoccupied space between 1-9:
3
```

Once the first player plays, the board is updated, then it's the next player's turn
```
 1 | 2 | X
---+---+---
 4 | 5 | 6
---+---+---
 7 | 8 | 9
Player O Enter an unoccupied space between 1-9:

```
The game will continue until all the spaces are filled. If there's a winner, a winner will be declared.

```
 1 | O | X
---+---+---
 O | X | 6
---+---+---
 X | 8 | 9
Congratulations player X, You've won the game!
```
If there's no winner, the game ends in a tie.
```
 X | O | X
---+---+---
 O | X | X
---+---+---
 O | X | O
Awwww it's a tie...
```

## On-going Thoughts
One of the main lessons I'm learning while building this is: "It's not about the destination but the journey getting to the destination." When I first started with this project, I was eager to get it done and continue onto the next one. The more I've sat with this project, the more I've been able to experiment with design patterns, OOP, and exploring refactoring technique. I am looking forward to see how this code evolves in the weeks to come.

## Resources Used

[Intro to Java Programming - Course for Absolute Beginners (video)](https://www.youtube.com/watch?v=GoXwIVyNvX0&t=4081s)

[IntelliJ IDEA | Full Course | 2020 (video)](https://www.youtube.com/watch?v=yefmcX57Eyg&t=1377s)

[JUnit @Before and @After Annotations (video)](https://www.youtube.com/watch?v=H0onfCEH1-c&t=17s)

[Refactoring Guru](https://refactoring.guru/)

