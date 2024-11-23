package leetcode;
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
}
