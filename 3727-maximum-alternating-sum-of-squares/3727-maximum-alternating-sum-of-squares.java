import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        
        
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * nums[i];
        }
        
        
        Arrays.sort(nums);
        
        long maxScore = 0;
        int minusSlotsCount = n >> 1; 
        
        
        for (int i = 0; i < n; i++) {
            if (i < minusSlotsCount) {
                maxScore -= nums[i];
            } else {
                maxScore += nums[i];
            }
        }
        
        return maxScore;
    }
}
