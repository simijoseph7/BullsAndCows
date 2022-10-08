package bullsandcows;

import java.util.Arrays;
import java.util.List;

public class EasyGame extends Game{

    //constructor for EasyGame
    public EasyGame(int userSecretCode, int computerSecretCode, boolean autoGuess){
        this.userSecretCode=userSecretCode;
        this.computerSecretCode=computerSecretCode;
        this.autoGuess=autoGuess;
    }


    //Method which starts the EasyGame.
    public int[][] startGame(int userSecretCode, int computerSecretCode, boolean autoGuess){

        int numOfGameTurns=1;
        int userGuess;
        int computerGuess;
        int bulls;
        int cows;
        int playing=0;

        //Creates an Array that contains the result (play, bulls and  cows) each time Guess and secretCode are compared
        int[] compareNumResult;

        /*
        Creates an Array (of arrays) that collects all the details of the whole game which can be later printed
        in a file if user chooses to do so
         */
        int[][] saveResultArray= new int[NUM_OF_TURNS+1][];

        /*
        1. Creates an array printSecretCodes that stores value of user and computerSecretCode for this Game
        2. Assigns printSecretCodes[] array as the first array in saveResultArray[][]
         */
        int[] printSecretCodes= new int[2];
        printSecretCodes[0]=userSecretCode;
        printSecretCodes[1]=computerSecretCode;
        saveResultArray[0]=printSecretCodes;

        //Gets the list of guesses from the file guess.txt if user wants automatic guessing
        List<String> autoGuessList=getUserAutoGuessList(autoGuess);

        outerLoop:
        while(playing==0) {

            for (int i = 0; i <NUM_OF_TURNS ; i++) {

                 /*
                Creates an Array that collects ALL the details of each round of the game
                Each eachTurnResult[] becomes an element of saveResultArray
                */
                int[] eachTurnResult= new int[9];


                System.out.println("------------------------------");

                //Getting the User Guess manually or using autoGuess
                if(!autoGuess) {
                    userGuess = getNumFromUser("Guess");
                }else{
                    userGuess= Integer.parseInt(autoGuessList.get(numOfGameTurns-1));
                    System.out.println("You guess: "+userGuess);
                }

                 /*
                1. compareNumbers() method compares userGuess and computerSecretCode
                2. returns an array with values of playing, bulls, cows.
                3. Assign the returned values to compareNumResult array
                */
                compareNumResult= numberComparer.compareNumbers("Congratulations, You win! :)", true, userGuess, computerSecretCode);
                playing=compareNumResult[0];
                bulls=compareNumResult[1];
                cows=compareNumResult[2];

                //Updates the details of user's turn to the eachTurnResult[]array
                eachTurnResult[0]=numOfGameTurns;
                eachTurnResult[1]=userGuess;
                eachTurnResult[2]=playing;
                eachTurnResult[3]=bulls;
                eachTurnResult[4]=cows;

                //If user wins,break the loop
                if (playing==1){
                    break outerLoop;
                }

                System.out.println();

                //Gets Computer's Guess
                computerGuess = getRandomNumber();
                System.out.println("Computer guess: "+computerGuess);

                //Similar to above, compareNumbers() method compares computerGuess & userSecretCode
                // returns an array compareNumResult[] with values of playing, bulls, cows.
                compareNumResult= numberComparer.compareNumbers("Computer won!", true,computerGuess, userSecretCode);
                playing=compareNumResult[0];
                bulls=compareNumResult[1];
                cows=compareNumResult[2];

                //Assigns the details of user's turn to the eachTurnResult[]array
                eachTurnResult[5]=computerGuess;
                eachTurnResult[6]=playing;
                eachTurnResult[7]=bulls;
                eachTurnResult[8]=cows;

                //saves the results of this round-eachTurnResult[] to saveResultArray[]
                saveResultArray[numOfGameTurns]=eachTurnResult;
                numOfGameTurns=numOfGameTurns+1;

                if (playing==1){
                    break outerLoop;
                }
            }
            System.out.println("Game finishes in a draw, 7 attempts completed.");
            break;
        }
        return saveResultArray;
    }




}
