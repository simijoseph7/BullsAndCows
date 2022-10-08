package bullsandcows;

import java.util.*;

public class NumberComparer {


    public int[] compareNumbers(String winMessage, boolean printResult, int num1, int num2) {
        //get the arrays of digits of the two numbers
        char[] charArray1= getDigitsArray(num1);
        char[] charArray2= getDigitsArray(num2);
        //compare the numbers to get the result of bulls and cows
        return getResult(winMessage,printResult,charArray1, charArray2);
    }

    //Convert the number to an Array
    public char[] getDigitsArray(int number){
        String numToString= Integer.toString(number);
        return numToString.toCharArray();
    }

    //compare the two arrays and return the result of Bulls and Cows
    public int[] getResult(String winMessage, boolean printResult, char[] array1, char[] array2) {
        int[] resultArray = new int[3];
        int cows=0;
        int bulls=0;
        String result;

        if (Arrays.equals(array1, array2)) {
            System.out.println(winMessage);
            resultArray[0]=1;
            resultArray[1]=4;
            return resultArray;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array1[i] == array2[j]) {
                    cows=cows+1;
                }
            }
            if (array1[i] == array2[i]) {
                bulls= bulls+1;
            }
        }
        cows =cows-bulls;
        result="Result: "+bulls+" bulls "+cows+" cows.";
        if(printResult) {
            System.out.println(result);
        }
        resultArray[1]=bulls;
        resultArray[2]=cows;
        return resultArray;
    }

}
