import java.util.Scanner;

public class WeekPlanner {
    static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        String[][] schedule = createSchedule();
        boolean appIsOn = true;
        while (appIsOn) {
            System.out.print("Please, input the day of the week: ");
            String[] commands = SC.nextLine().trim().toLowerCase().split("\\s+");
            if (commands[0].equals("exit")) {
                appIsOn = false;
            } else if (commands[0].startsWith("change") || commands[0].startsWith("reschedule")) {
                change(schedule, commands[1]);
            } else {
                showDay(commands[0], schedule);
            }
        }
    }

    public static void change(String[][] schedule, String day) {
        System.out.print("Please, input new tasks for " + day + ".");
        String newTask = SC.nextLine();
        int k = findDay(day);
        schedule[k][1] = newTask;
    }

    public static int findDay(String day) {
        return switch (day) {
            case "monday" -> 0;
            case "tuesday" -> 1;
            case "wednesday" -> 2;
            case "thursday" -> 3;
            case "friday" -> 4;
            case "saturday" -> 5;
            case "sunday" -> 6;
            default -> -1;
        };
    }

    public static void showDay(String command, String[][] schedule) {
        String output = switch (command) {
            case "monday" -> schedule[0][1];
            case "tuesday" -> schedule[1][1];
            case "wednesday" -> schedule[2][1];
            case "thursday" -> schedule[3][1];
            case "friday" -> schedule[4][1];
            case "saturday" -> schedule[5][1];
            case "sunday" -> schedule[6][1];
            default -> "Sorry, I don't understand you, please try again.";
        };
        System.out.println(output);
    }

    public static String[][] createSchedule() {
        String[][] schedule = new String[7][2];
        schedule[0][0] = "Monday";
        schedule[1][0] = "Tuesday";
        schedule[2][0] = "Wednesday";
        schedule[3][0] = "Thursday";
        schedule[4][0] = "Friday";
        schedule[5][0] = "Saturday";
        schedule[6][0] = "Sunday";
        schedule[0][1] = "go to course";
        schedule[1][1] = "hit the gym and do coursework";
        schedule[2][1] = "go to course and watch a movie";
        schedule[3][1] = "hit the gym";
        schedule[4][1] = "watch a movie";
        schedule[5][1] = "hit the gym and go to course";
        schedule[6][1] = "hit the gym and do coursework";
        return schedule;
    }
}
