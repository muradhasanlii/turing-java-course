package leetcode;

import java.util.List;

/**
 * Performance:  ms, Beats %.
 * <a href=""> .  </a>.
 **/

public class Solution {
    /**
     * Performance: 1 ms, Beats 96.85%.
     * <a href="https://leetcode.com/problems/concatenation-of-array/description/"> 1929. Concatenation of Array </a>.
     **/
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n * 2];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];
            ans[i + n] = nums[i];
        }
        return ans;
    }

    /**
     * Performance: 6 ms, Beats 77.91%.
     * <a href="https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/description/"> 1605. Find Valid Matrix Given Row And Column Sums </a>.
     **/
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int[][] ans = new int[rowSum.length][colSum.length];
        for (int i = 0; i < rowSum.length; i++) {
            for (int j = 0; j < colSum.length; j++) {
                int min = Math.min(rowSum[i], colSum[j]);
                ans[i][j] = min;
                rowSum[i] -= min;
                colSum[j] -= min;
            }
        }
        return ans;
    }

    /**
     * Performance: 1 ms, Beats 100.00%.
     * <a href="https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/description/"> 3289. The Two Sneaky Numbers Of Digitville </a>.
     **/
    public int[] getSneakyNumbers(int[] nums) {
        int[] ans = new int[2];
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    ans[k] = nums[i];
                    k++;
                }
            }
        }
        return ans;
    }

    /**
     * Performance: 6 ms, Beats 64.19%.
     * <a href="https://leetcode.com/problems/ransom-note/description/"> 383. Ransom Note </a>.
     **/
    public static boolean canConstruct(String ransomNote, String magazine) {
        char[] arrayM = magazine.toCharArray();
        char[] arrayR = ransomNote.toCharArray();
        int n = ransomNote.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < arrayM.length; j++) {
                if (arrayR[i] == arrayM[j]) {
                    arrayM[j] = 0;
                    count++;
                    if (count == n) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }

    /**
     * Performance: 0 ms, Beats 100%.
     * <a href="https://leetcode.com/problems/shuffle-string/description/"> 1528. Shuffle String </a>.
     **/
    public static String restoreString(String s, int[] indices) {
        int n = s.length();
        char[] arrayA = new char[n];
        for (int i = 0; i < n; i++) {
            arrayA[indices[i]] = s.charAt(i);
        }
        return new String(arrayA);
    }

    /**
     * Performance: 2 ms, Beats 90.71%.
     * <a href="https://leetcode.com/problems/snake-in-matrix/description/"> 3248. Snake in Matrix </a>.
     **/
    public static int finalPositionOfSnake(int n, List<String> commands) {
        int x = 0;
        for (String command : commands) {
            switch (command) {
                case "UP":
                    x -= n;
                    break;
                case "RIGHT":
                    x++;
                    break;
                case "DOWN":
                    x += n;
                    break;
                case "LEFT":
                    x--;
                    break;
            }
        }
        return x;
    }

    /**
     * Performance: 3 ms, Beats 98.26%.
     * <a href="https://leetcode.com/problems/integer-to-roman/description/"> 12. Integer to Roman </a>.
     **/
    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] chars = {"M", "CM", "D", "CD", "C", "CX", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < numbers.length; i++) {
            while (numbers[i] <= num) {
                sb.append(chars[i]);
                num -= numbers[i];
            }
        }
        return sb.toString();
    }

}
