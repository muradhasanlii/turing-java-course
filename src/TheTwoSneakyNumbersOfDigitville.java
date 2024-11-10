public class TheTwoSneakyNumbersOfDigitville {
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

