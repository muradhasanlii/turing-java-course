package lab1;

import java.util.Scanner;

public class TestMovie {
    final static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("""
                Press 0 to exit.
                Press 1 to input movies.
                Press 2 to display movies and ratings.
                Press 3 to input more movies.
                Press 4 to find statistics.
                Press 5 to search for a movie.
                Press 6 to update movie ratings.
                Press 7 to delete a movie.
                Press 8 to sort movies.""");
        Movie[] allMovies = new Movie[0];
        boolean appIsOn = true;
        while (appIsOn) {
            String command = SC.nextLine();
            Movie[] movies;
            int movieIndex;
            String movieName;
            switch (command) {
                case "1":
                    movies = new Movie[3];
                    for (int i = 0; i < 3; i++) {
                        String[] m = getMovieAndRatingFromUser();
                        Movie movie = new Movie(m[0], Double.parseDouble(m[1]));
                        movies[i] = movie;
                    }
                    allMovies = mergeArrays(allMovies, movies);
                    break;
                case "2":
                    printMovies(allMovies);
                    break;
                case "3":
                    System.out.print("How many movies you want to input: ");
                    int n = Integer.parseInt(SC.nextLine());
                    movies = new Movie[n];
                    for (int i = 0; i < n; i++) {
                        String[] m = getMovieAndRatingFromUser();
                        Movie movie = new Movie(m[0], Double.parseDouble(m[1]));
                        movies[i] = movie;
                    }
                    allMovies = mergeArrays(allMovies, movies);
                    break;
                case "4":
                    double averageRating = 0;
                    double minRating = allMovies[0].getRating();
                    double maxRating = allMovies[0].getRating();
                    for (Movie m : allMovies) {
                        averageRating += m.getRating();
                        if (maxRating < m.getRating()) {
                            maxRating = m.getRating();
                        }
                        if (minRating > m.getRating()) {
                            minRating = m.getRating();
                        }
                    }
                    averageRating /= allMovies.length;
                    System.out.printf("average rating: %f\nmin rating: %f\nmax rating: %f\n", averageRating, minRating, maxRating);
                    break;
                case "5":
                    System.out.print("Enter the movie you searching for: ");
                    movieName = SC.nextLine();
                    movieIndex = searchMovie(allMovies, movieName);
                    System.out.println(allMovies[movieIndex].getRating());
                    break;
                case "6":
                    System.out.print("Enter the movie you want to update: ");
                    movieName = SC.nextLine();
                    movieIndex = searchMovie(allMovies, movieName);
                    if (movieIndex != -1) {
                        System.out.print("Update the rating: ");
                        allMovies[movieIndex].setRating(Double.parseDouble(SC.nextLine()));
                    }
                    break;
                case "7":
                    System.out.print("Enter the name of the movie that you want to delete: ");
                    movieName = SC.nextLine();
                    movieIndex = searchMovie(allMovies, movieName);
                    if (movieIndex != -1) {
                        allMovies = removeMovie(allMovies, movieIndex);
                    }
                    break;
                case "8":
                    sort(allMovies);
                    break;
                case "0":
                    appIsOn = false;
                    break;
            }
        }
    }

    public static void sort(Movie[] allMovies) {
        for (int i = 0; i < allMovies.length - 1; i++) {
            for (int j = 0; j < allMovies.length - 1 - i; j++) {
                if (allMovies[j].getRating() > allMovies[j + 1].getRating()) {
                    Movie temp = allMovies[j];
                    allMovies[j] = allMovies[j + 1];
                    allMovies[j + 1] = temp;
                }
            }
        }
    }

    public static int searchMovie(Movie[] allMovies, String movie) {
        for (int i = 0; i < allMovies.length; i++) {
            if (allMovies[i].getName().equals(movie)) {
                return i;
            }
        }
        System.out.println("Movie not found");
        return -1;
    }

    public static String[] getMovieAndRatingFromUser() {
        System.out.print("Enter the movie: ");
        String movieName = SC.nextLine();
        System.out.print("Enter the rating of the movie: ");
        String movieRating = SC.nextLine();
        return new String[]{movieName, movieRating};
    }

    public static Movie[] mergeArrays(Movie[] baseArray, Movie[] newArray) {
        int size = baseArray.length + newArray.length;
        Movie[] mergedArray = new Movie[size];
        System.arraycopy(baseArray, 0, mergedArray, 0, baseArray.length);
        System.arraycopy(newArray, 0, mergedArray, baseArray.length, newArray.length);
        return mergedArray;
    }

    public static void printMovies(Movie[] allMovies) {
        for (Movie movie : allMovies) {
            System.out.println(movie);
        }
    }

    public static Movie[] removeMovie(Movie[] allMovies, int index) {
        if (allMovies == null || index < 0 || index >= allMovies.length) {
            return allMovies;
        }
        Movie[] newAllMovies = new Movie[allMovies.length - 1];
        for (int i = 0, k = 0; i < allMovies.length; i++) {
            if (i == index) {
                continue;
            }
            newAllMovies[k++] = allMovies[i];
        }
        return newAllMovies;
    }
}
