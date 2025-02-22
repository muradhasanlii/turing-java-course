import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class ShipBattleGame {
    static final Random RAND = new Random();
    static final int N = 10;
    static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        game();
    }

    // This method add ship to the map and return str placements of ship
    public static String[][] addShips(int[][] map) {
        int[][] intShips = new int[6][];
        String[][] strShips = new String[6][]; // for coordinates
        int k = 0;
        for (int i = 5; i > 1; i--) { // creating ships size of 5, 4, 3, 2
            int[] coordinatesOfShip = check(map, i); // taking coordinates of 1 ship
            for (int j = 0; j < i; j++) {
                int x = coordinatesOfShip[j] / 10;
                int y = coordinatesOfShip[j] % 10;
                intShips[k] = coordinatesOfShip; // adding ship to all ships
                strShips[k] = new String[i];
                map[x][y] = 1; // adding 1 to the map which is ship particle
            }
            k++;
        }
        for (int i = 3; i > 1; i--) { //creating ships size of 3 and 2
            int[] coordinatesOfShip = check(map, i);
            for (int j = 0; j < i; j++) {
                int x = coordinatesOfShip[j] / 10;
                int y = coordinatesOfShip[j] % 10;
                intShips[k] = coordinatesOfShip;
                strShips[k] = new String[i];
                map[x][y] = 1; // adding 1 to the map which is ship particle
            }
            k++;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < intShips[i].length; j++) {
                strShips[i][j] = String.format("%02d", intShips[i][j]); // format is for coordinates which x's are zero
            }
        }
        //printArray(map);
        return strShips;
    }

    // This method determines in which direction the ship will be created.
    public static int[] destination() {
        int d = RAND.nextInt(4);
        return switch (d) {
            case 0 -> new int[]{0, 1};   // to the north
            case 1 -> new int[]{0, -1};  // to the south
            case 2 -> new int[]{1, 0};   // to the east
            default -> new int[]{-1, 0}; // to the west
        };
    }

    // This method checks where it can be created and returns the coordinates that will be a ship's particles.
    public static int[] check(int[][] map, int n) {
        while (true) {
            int[] coordinates = randomPoint();
            int x = coordinates[0]; // starting x
            int y = coordinates[1]; // starting y
            int[] d = destination();
            int i = d[0]; // x's increment
            int j = d[1]; // y's increment
            int[] willPaint = new int[n]; // ship's particles
            int partOfShip = 0;
            while (partOfShip != n) { // n is equal to the length of the ship
                // checking if x and y is right place for to be ship's particle
                if ((getValue(x, y, map) == 0) && (getValue(x + 1, y, map) != 1) && (getValue(x - 1, y, map) != 1) && (getValue(x, y + 1, map) != 1) && (getValue(x, y - 1, map) != 1)) {
                    willPaint[partOfShip] = 10 * x + y; // i don't wanna add x and y as in array so i made them 2 digit number
                    x += i;
                    y += j;
                    partOfShip++;
                    if (partOfShip == n) { // if all particles are determined then return all particles
                        return willPaint;
                    }
                } else { // if coordinates isn't right place exit ,and it will create new x and y
                    break;
                }
            }
        }
    }

    // getting value of coordinate if it's out of boundary it will give -2 else, it will give value of coordinate
    public static int getValue(int i, int j, int[][] matrix) {
        if (i < 0 || j < 0 || i > N - 1 || j > N - 1) {
            return -1;
        }
        return matrix[i][j];
    }

    // Method for printing map, it is only for checking
    public static void printArray(int[][] map) {
        for (int[] row : map) {
            for (int i : row) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }
    }

    // Method for getting all coordinates of ships, this also is only for checking
    public static void printArray(String[][] map) {
        for (String[] row : map) {
            for (String s : row) {
                System.out.print(s + "  ");
            }
            System.out.println();
        }
    }

    // This method will create random x and y values
    public static int[] randomPoint() {
        int x = RAND.nextInt(10);
        int y = RAND.nextInt(10);
        return new int[]{x, y};
    }

    // This method returns if the ship fully destroyed
    public static boolean checkIfDestroyed(int[] pointCoordinate, String[][] allShips) {
        for (int j = 0; j < allShips[pointCoordinate[0]].length; j++) {
            if (!Objects.equals(allShips[pointCoordinate[0]][j], "DD")) {
                return false;
            }
        }
        return true;
    }

    // return an int array of 2 size that contain x and y based on str number
    public static int[] findPointCoordinate(String str, String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (Objects.equals(str, array[i][j])) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};

    }

    public static void game() {
        int[][] map = new int[N][N]; //creating map
        String[][] allShips = addShips(map); // adding ships to the map
        printArray(allShips);
        int bombs = 25;
        boolean gameIsOver = false;
        int destroyedShips = 0;
        while (!gameIsOver) {
            System.out.print("Enter the coordinate: "); // must enter coordinates unit like 12, 23, 01
            String s = SC.nextLine();
            int x = Integer.parseInt(s.substring(0, 1));
            int y = Integer.parseInt(s.substring(1, 2));
            int[] pointCoordinate = findPointCoordinate(s, allShips); // find x and y
            if (map[x][y] != 1) { // 1 is there is a ship particle
                System.out.println("No damage!");
            } else {
                map[x][y] = -1; // changing value of the ship to the -1
                allShips[pointCoordinate[0]][pointCoordinate[1]] = "DD"; // changing value in all ships to "DD" means destroyed that particle
                if (checkIfDestroyed(pointCoordinate, allShips)) { // check if all particle equals "DD"
                    System.out.println("Ship destroyed!");
                    destroyedShips++;
                } else {
                    System.out.println("You hit 1 coordinate!");
                }
                if (destroyedShips == 6) {
                    System.out.println("You WIN!");
                    gameIsOver = true;
                }
            }
            if (bombs == 0) {
                System.out.println("Run out of bombs, LOSE!");
                gameIsOver = true;
            }
            bombs--;
        }
    }
}