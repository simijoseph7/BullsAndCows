package bullsandcows;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This class consists of methods for running the Bulls and Cows game.
 * Run this class to start the game.
 */
public class GameControl {
    Game newGame;

    //create an object print to access the ResultGenerator class
    PrintResultGenerator print=new PrintResultGenerator();

    // Constructor of GameControl
        public GameControl() {
            newGame = new Game();
    }


    public void start(){
        // Prints the welcome message of the game.
        printWelcomeMessage();

        //Gets the difficulty level(Easy/Medium/Hard) from the user
        getDifficultyLevel();

        //Gets the preferred guessing method (automatic guesses from o file/ manual entry) from user
        getUserGuessMethod();

        //If user wants automatic guesses, writes the list of random guesses in a file names guess.txt
        writeGuessToFile(newGame.autoGuess);

        //Gets the user's secret code and assigns it to variable userSecretCode
        newGame.userSecretCode=newGame.getNumFromUser("Secret Code");

        /*
        Gets the computer's secret code by random number generation from the Util class
        Assigns the number to the computerSecretCode
         */
        newGame.computerSecretCode=newGame.getComputerSecretCode();

        /*
        1. Creates a new Game (EasyGame/MediumGame/HardGame) according to the difficulty level ,preference of
       guessing method of user, Use Secret Code and Computer Secret Code
        2. Starts the game play
        3. The game once completed returns an Array containing the results of the game. This result is assigned
       to  the saveResultArray variable.
    */
        newGame.saveResultArray=startNewGame(newGame.difficultyLevel, newGame.autoGuess, newGame.userSecretCode, newGame.computerSecretCode);

        /*
        Gets the preference of the user(Yes/No) to save the results of the game in a file from saveGameResults() method
        If it returns true, the results are printed in a file
         */
        if(saveGameResults()){
            print.getGameResultFile(newGame.saveResultArray);
        }

        //Prints the exit message
        printExitMessage();
    }


    // Prints the welcome message of the game.
    private static void printWelcomeMessage() {
        System.out.println("Welcome to Bulls and Cows!");
    }


    //Gets the difficulty level(Easy/Medium/Hard) from the user
    private int getDifficultyLevel() {
        while (true) {
            System.out.println("Select the game's difficulty level:");
            System.out.println("Enter 1 for easy");
            System.out.println("Enter 2 for medium");
            System.out.println("Enter 3 for hard");
            System.out.print(">>");
            try {
                newGame.difficultyLevel = Integer.parseInt(bullsandcows.Keyboard.readInput());
                if (newGame.difficultyLevel == 1 || newGame.difficultyLevel == 2 || newGame.difficultyLevel == 3) {
                    return newGame.difficultyLevel;
                }
            } catch (NumberFormatException e) {
                // If the value could not be parsed, display an error.
                System.out.println("Please enter a number!!");
            }
        }
    }

    //Gets the preferred guessing method (automatic guesses from o file/ manual entry) from user
    private boolean getUserGuessMethod(){

        System.out.println("Would you like to guess manually or automatically?");
        System.out.println("Enter \"M\" for entering guess manually");
        System.out.println("Enter \"A\" for automatic guessing");
        System.out.print(">>");

        boolean repeatLoop=true;
        while (repeatLoop) {
            String input = Keyboard.readInput().toLowerCase();
            if (input.equals("m")) {
                newGame.autoGuess = false;
                repeatLoop=false;
            } else if (input.equals("a")) {
                newGame.autoGuess = true;
                repeatLoop=false;
            } else {
                System.out.println("Please enter \"M\" or \"A\"!");
                //repeatLoop=true;
            }
        }
        return newGame.autoGuess;
    }

    //If user wants automatic guesses, writes the list of random guesses in a file names guess.txt
    public void writeGuessToFile(boolean autoGuess) {
        if (autoGuess) {
            String fileName = "guess.txt";

            // TODO Open a file for writing, using a PrintWriter.
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

                List<Integer> guessList = newGame.getShuffledList();
                for (int i = 0; i < 7; i++) {
                    writer.println(guessList.get(i));
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /*
    1. Creates a new Game (EasyGame/MediumGame/HardGame) according to the difficulty level ,preference of
       guessing method of user, Use Secret Code and Computer Secret Code
    2. Starts the game play
    3. The game once completed returns an Array containing the results of the game. This result is assigned
       to  the saveResultArray variable.
    */
    private int[][] startNewGame(int difficultyLevel, boolean autoGuess, int userSecretCode, int computerSecretCode) {
        switch (difficultyLevel) {

            case 1:
                newGame = new EasyGame(userSecretCode, computerSecretCode, autoGuess);
                newGame.saveResultArray=((EasyGame)newGame).startGame( userSecretCode, computerSecretCode,autoGuess);
                return newGame.saveResultArray;
            case 2:
                newGame = new MediumGame(userSecretCode, computerSecretCode, autoGuess);
                newGame.saveResultArray=((MediumGame)newGame).startGame( userSecretCode, computerSecretCode, autoGuess);
                return newGame.saveResultArray;
            case 3:
                newGame = new HardGame(userSecretCode, computerSecretCode, autoGuess);
                newGame.saveResultArray=((HardGame)newGame).startGame( userSecretCode, computerSecretCode, autoGuess);
                return newGame.saveResultArray;
        }
        return newGame.saveResultArray;
    }

    //Gets the preference of the user(Yes/No) for saving the results of the game in a file
    private boolean saveGameResults(){
        System.out.println("Would you like to save the results of the game to a text file?");
        System.out.println("Enter \"Y\" if you want to save results ");
        System.out.println("Enter \"N\" if you do not want to save results ");
        System.out.print(">>");
        boolean saveResult=false;
        boolean repeatLoop=true;
        while (repeatLoop) {
            String input = Keyboard.readInput().toLowerCase();
            if (input.equals("n")) {
                repeatLoop = false;
                //saveResult= false;
            } else if (input.equals("y")) {
                repeatLoop=false;
                saveResult= true;
            } else {
                System.out.println("Please enter \"Y\" or \"N\"!");
                //repeatLoop=true;
            }
        }
        return saveResult;
    }

    //Prints Exit Message
    private void printExitMessage() {
        System.out.println();
        System.out.println("Thanks for playing Bulls and cows!");

    }

    public static void main(String[] args) {
        bullsandcows.GameControl g= new bullsandcows.GameControl();
        g.start();
    }

}


