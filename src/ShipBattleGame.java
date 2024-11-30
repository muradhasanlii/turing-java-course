import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class ShipBattleGame {
    static Random RAND = new Random();
    static int N = 10;
    static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        game();
    }

    public static String[][] addShips(int[][] map) {
        int[][] intShips = new int[6][];
        String[][] strShips = new String[6][];
        int k = 0;
        for (int i = 5; i > 1; i--) {
            int[] coordinatesOfShips = check(map, i);
            for (int j = 0; j < i; j++) {
                int x = coordinatesOfShips[j] / 10;
                int y = coordinatesOfShips[j] % 10;
                intShips[k] = coordinatesOfShips;
                strShips[k] = new String[i];
                map[x][y] = 1;
            }
            k++;
        }
        for (int i = 3; i > 1; i--) {
            int[] coordinatesOfShips = check(map, i);
            for (int j = 0; j < i; j++) {
                int x = coordinatesOfShips[j] / 10;
                int y = coordinatesOfShips[j] % 10;
                intShips[k] = coordinatesOfShips;
                strShips[k] = new String[i];
                map[x][y] = 1;
            }
            k++;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < intShips[i].length; j++) {
                strShips[i][j] = String.format("%02d", intShips[i][j]);
            }
        }
        //printArray(map);
        return strShips;
    }

    public static int[] destination() {
        int d = RAND.nextInt(4);
        return switch (d) {
            case 0 -> new int[]{0, 1};
            case 1 -> new int[]{0, -1};
            case 2 -> new int[]{1, 0};
            default -> new int[]{-1, 0};
        };
    }

    public static int[] check(int[][] map, int n) {
        while (true) {
            int[] coordinates = randomPoint();
            int x = coordinates[0];
            int y = coordinates[1];
            int[] d = destination();
            int i = d[0];
            int j = d[1];
            int[] willPaint = new int[n];
            int partOfShip = 0;
            while (partOfShip != n) {
                if (getValue(x, y, map) == 0 && getValue(x + 1, y, map) != 1 && getValue(x - 1, y, map) != 1 && getValue(x, y + 1, map) != 1 && getValue(x, y - 1, map) != 1) {
                    willPaint[partOfShip] = 10 * x + y;
                    x += i;
                    y += j;
                    partOfShip++;
                    if (partOfShip == n) {
                        return willPaint;
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static int getValue(int i, int j, int[][] matrix) {
        if (i < 0 || j < 0 || i > N - 1 || j > N - 1) {
            return -2;
        }
        return matrix[i][j];
    }

    public static void printArray(int[][] map) {
        for (int[] row : map) {
            for (int i : row) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }
    }

    public static void printArray(String[][] map) {
        for (String[] row : map) {
            for (String s : row) {
                System.out.print(s + "  ");
            }
            System.out.println();
        }
    }

    public static int[] randomPoint() {
        int x = RAND.nextInt(10);
        int y = RAND.nextInt(10);
        return new int[]{x, y};
    }

    public static boolean checkIfDestroyed(int[] pointCoordinate, String[][] allShips) {
        for (int j = 0; j < allShips[pointCoordinate[0]].length; j++) {
            if (!Objects.equals(allShips[pointCoordinate[0]][j], "DD")) {
                return false;
            }
        }
        return true;
    }

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
        int[][] map = new int[N][N];
        String[][] allShips = addShips(map);
        //printArray(allShips);
        int bombs = 25;
        boolean gameIsOver = false;
        int destroyedShips = 0;
        while (!gameIsOver) {
            System.out.print("Enter the coordinate: ");
            String s = SC.nextLine();
            int x = Integer.parseInt(s.substring(0, 1));
            int y = Integer.parseInt(s.substring(1, 2));
            int[] pointCoordinate = findPointCoordinate(s, allShips);
            if (map[x][y] != 1) {
                System.out.println("No damage!");
            } else {
                map[x][y] = -1;
                allShips[pointCoordinate[0]][pointCoordinate[1]] = "DD";
                if (checkIfDestroyed(pointCoordinate, allShips)) {
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

