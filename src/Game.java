package bullsandcows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * This class consists of methods for the Game operations.
 * It has three subclasses: EasyGame, MediumGame and HardGame
*/
public  class Game {
    //create a new object to access NumberComparer class
    NumberComparer numberComparer= new NumberComparer();

    protected int userSecretCode;
    protected int computerSecretCode;
    protected int difficultyLevel;
    protected boolean autoGuess;
    protected int[][] saveResultArray;
    public static final int NUM_OF_TURNS=7;

    //default constructor
    public Game(){}


    /*
    Gets  a valid number (Secret code/ guess) from the user
    (Prompts the user  to re-enter if number entered is invalid)
     */
    public int getNumFromUser(String typeOfNum) {
        int num=0;
        System.out.print("Enter your "+typeOfNum+ " :");
        while (num==0) {
            try {
                num=validateNumberEntry(Keyboard.readInput());
            } catch (IncorrectNumberOfDigitsException | DuplicateDigitsException e) {
                System.out.println("Error: " + e.getMessage());
            }catch (NumberFormatException e){
                System.out.println("Please enter a number!");
            }
        }
        return num;
    }


    //validates the number entered by user (using Exceptions)
    public int validateNumberEntry(String entry) throws IncorrectNumberOfDigitsException, DuplicateDigitsException{

        int number=Integer.parseInt(entry);
        if ((entry.length() != 4 || entry.charAt(0)=='0' )) {
            throw new IncorrectNumberOfDigitsException("Number must have 4 digits!");
        }
        if (!checkDuplicateDigits(entry)) {
            throw new DuplicateDigitsException("All digits in the number must be different!");
        }
        return number;
    }

    //gets the computer's secret code by random number generation from the Util class
    public int getComputerSecretCode(){
        return getRandomNumber();
    }

    //returns a random number
    public int getRandomNumber() {
        return getShuffledList().get(0);
    }

    //returns a shuffled list of numbers in a given range
    public List<Integer> getShuffledList(){
        List<Integer> computerGuessList = new ArrayList<>();
        for (int i = 1000; i < 9999; i++) {
            if (checkDuplicateDigits(String.valueOf(i))) {
                computerGuessList.add(i);
            }
        }
        Collections.shuffle(computerGuessList);
        return computerGuessList;
    }

    //Returns a boolean, whether a String (here String value of a number) has duplicate values in it
    public boolean checkDuplicateDigits(String entry) {
        List<Character> digitsArray = new ArrayList<>();
        for (int i = 0; i < entry.length(); i++) {
            digitsArray.add(entry.charAt(i));
        }
        HashSet<Character> digitSet = new HashSet<>();
        digitSet.addAll(digitsArray);
        return (digitsArray.size() == digitSet.size());
    }

    //Returns the list of automatically generated guesses printed in the file, for user to use
    public List<String> getUserAutoGuessList(boolean autoGuess) {
        List<String> autoGuessList = new ArrayList<>();

        while(autoGuess) {
            System.out.print("Enter an existing file name: ");
            String fileName = ictgradschool.Keyboard.readInput();
            // TODO Open a file for writing, using a PrintWriter.
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    autoGuessList.add(line);
                }
                autoGuess = false;
            }catch(FileNotFoundException e){
                System.out.println("Error: Cannot find a file named "+fileName+"!");
                System.out.println("Please enter an existing file name!");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return autoGuessList;
    }



}
