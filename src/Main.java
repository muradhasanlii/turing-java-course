public class Main {
    //Concatenation of Array 97.04%
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n * 2];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];
            ans[i + n] = nums[i];
        }
        return ans;
    }

    //Find Valid Matrix Given Row And Column Sums 78.44%
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

    //The Two Sneaky Numbers Of Digitville 100.00%
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
