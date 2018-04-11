//////////////////////////////////////////////////////////
// Password Cracking Program
// Name: PasswordBreaker.java
// Author: Rafael Antonio Martinez Salguero
// Date 03/20/2018
// Purpose: Use a brute-force attack to crack the passwords
//////////////////////////////////////////////////////////
import java.util.Arrays;
import java.util.Scanner;

public class PasswordBreaker {
    public static void passwordBreaker(String password) {
        char[] charToPswd = password.toCharArray(); //returns a password.length char array ex .['l','i','n','e']
        char[] comparator = new char[password.length()];
        for (int i = 0; comparator.length - 1 >= i; i++) {
            comparator[i] = ' ';
        }
        boolean searching = true;
        int itemInChar = password.length() - 1;
        char c = comparator[itemInChar]; // compare last item of comparator to see if there is a match
        Long guessCounter = 0L;
        while (searching) {
            //this if below is not checking for all answers you have to move it somewhere else
            if (Arrays.equals(comparator, charToPswd)) { // compares two arrays if they are equal
                searching = false;
                continue;
            }
            guessCounter += 1;
            char[] comparatorCopy = Arrays.copyOf(comparator, comparator.length); // copy to compare if the comparator changed
            //checks if '~' is anywhere in the char[]
            comparator = forbiddenCharChecker(comparator);
            //if comparator changed then compare it with the password AKA re start the loop
            if (!Arrays.equals(comparator, comparatorCopy)) {
                c = comparator[itemInChar];
                continue;
            }
            //if comparator did not change then just add 1 to c
            if (Arrays.equals(comparator, comparatorCopy)){
                // changes c in any case that c needs to be changed
                c = comparator[itemInChar];
                //increase char character by one using Unicode
                c++;
                //change the value of the comparator array
                comparator[itemInChar] = c;
            }
        }
        System.out.println("Amount of Guests: " + guessCounter);
    }

    public static char[] forbiddenCharChecker(char[] comparator) {
        for (int i = comparator.length - 1; i >= 0; i--) {
            if (comparator[0] == '~'){
                if (beginningForbiddenChar(comparator)) {
                    break;
                }
            }
            if (comparator[i] == '~' && comparator[i - 1] != '~') {
                comparator[i - 1] += 1;
                comparator = restartCount(comparator, i);
                break;
            }
            if (comparator[i] != '~') {
                break;
            }
            if (comparator[i] == '~' && comparator[i - 1] == '~'){
                continue;
            }
        }
        return comparator;
    }

    public static char[] restartCount(char[] comparator, int index) {
        for (int i = index; comparator.length - 1 >= i; i++) {
            comparator[i] = ' ';
        }
        return comparator;
    }

    //checks if the char array is full of forbidden characters aka '~' if it returns true
    public static boolean beginningForbiddenChar(char[] comparator){
        for(int i = 1; i <= comparator.length - 1; i++) {
            if (comparator[i] != '~'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter password: ");
        String m = s.nextLine();
        long startTime = System.nanoTime();
        passwordBreaker(m);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time Elapsed: " + elapsedTime/1000000 + " milliseconds");
        System.out.println("Password has been found");
    }
}
