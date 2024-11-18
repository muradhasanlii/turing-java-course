import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of digits: ");
        int numberOfDigits = sc.nextInt();
        double minBound = Math.pow(10, numberOfDigits - 1);
        int randomNumber = rand.nextInt(9 * (int) minBound) + (int) minBound;
        int[] randomArray = intDigitsToArray(randomNumber, numberOfDigits);
        boolean condition = true;
        while (condition) {
            System.out.print("Enter the guessed number: ");
            int guessNumber = sc.nextInt();
            int[] guessArray = intDigitsToArray(guessNumber, numberOfDigits);
            int[] answers = checkDifference(Arrays.copyOf(randomArray, randomArray.length), Arrays.copyOf(guessArray, guessArray.length));
            if (answers[0] == numberOfDigits) {
                System.out.println("You Win!");
                condition = false;
            }
        }
    }

    public static int[] checkDifference(int[] a, int[] b) {
        int green = 0;
        int yellow = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 0 && b[i] >= 0 && a[i] == b[i]) {
                a[i] = -1;
                b[i] = -1;
                green++;
            }
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] >= 0 && b[j] >= 0 && i != j && a[i] == b[j]) {
                    a[i] = -1;
                    b[j] = -1;
                    yellow++;
                }
            }
        }
        System.out.printf("green: %d\nyellow: %d\n", green, yellow);
        return new int[]{green, yellow};
    }

    public static int[] intDigitsToArray(int a, int numberOfDigits) {
        int[] array = new int[numberOfDigits];
        for (int i = numberOfDigits - 1; i >= 0; i--) {
            int q = a % 10;
            a /= 10;
            array[i] = q;
        }
        return array;
    }
}

