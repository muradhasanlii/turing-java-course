package classwork2202;

import java.util.*;
import java.util.stream.Collectors;

public class Exercises {
    public static void main(String[] args) {
        //Given a list of words, return the top k most frequent words in descending order of frequency.
        // If two words have the same frequency, sort them alphabetically.
        String[] arr1 = {"apple", "banana", "apple", "orange", "banana", "banana", "apple"};
        HashMap<String, Integer> frequencyMap = new HashMap<>();
        for (String word : arr1) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        int maxValue = Collections.max(frequencyMap.values());
        List<String> l1 = frequencyMap.entrySet().stream().filter(i -> i.getValue() == maxValue).map(Map.Entry::getKey).sorted().toList();
        System.out.println(l1);

        //Compute Average Score per Student
        class Student {
            private final String name;
            private final int score;

            Student(String name, int score) {
                this.name = name;
                this.score = score;
            }

            public String getName() {
                return name;
            }

            public int getScore() {
                return score;
            }
        }
        List<Student> l2 = Arrays.asList(new Student("Alice", 100), new Student("Alice", 80), new Student("Ashley", 60));
        Map<String, Double> map2 = l2.stream().collect(Collectors.groupingBy(student -> student.getName(), Collectors.averagingDouble(student -> student.getScore())));
        System.out.println(map2);

        //Find the First Non-Repeating Character in a String
        String str3 = "swiss";
        Map<Character, Integer> map3 = new LinkedHashMap<>();
        for (char c : str3.toCharArray()) {
            map3.put(c, map3.getOrDefault(c, 0) + 1);
        }
        System.out.println(map3.entrySet().stream().filter(c -> c.getValue() == 1).map(entry -> entry.getKey()).findFirst().orElse(null));
    }
}
