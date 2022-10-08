package bullsandcows;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintResultGenerator {

    public void getGameResultFile(int[][] saveResultArray) {
        String file;
        while(true) {
            System.out.println("Please enter a filename");
            System.out.println(">>");
            file= Keyboard.readInput();
            if(file.equals("")){
                System.out.println("Please enter a valid file name!");
            }else{break;}
        }
        String fileName = file + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

            writer.println("Bulls and Cows Game Results");
            System.out.println();
            writer.println("Your Code: " + saveResultArray[0][0]);
            writer.println("Computer Code: " + saveResultArray[0][1]);

            for (int i = 1; i < saveResultArray.length; i++) {
                writer.println("-----------------------------------------------");
                writer.println("Turn " + saveResultArray[i][0]);
                writer.println("You guessed " + saveResultArray[i][1] + " ,scoring " + saveResultArray[i][3] + " bulls and " + saveResultArray[i][4] + " cows.");
                writer.println("Computer guessed " + saveResultArray[i][5] + " ,scoring " + saveResultArray[i][7] + " bulls and " + saveResultArray[i][8] + " cows.");

                if (saveResultArray[i][2] == 1) {
                    writer.println("You win! :)");
                    break;
                } else if (saveResultArray[i][6] == 1) {
                    System.out.println("Computer wins!");

                    writer.println("Computer wins!");
                    break;
                } else if (i == 7 && saveResultArray[i][2] == 0 && saveResultArray[i][6] == 0) {
                    writer.println("Game ends in a draw!");
                }

            }
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

}
