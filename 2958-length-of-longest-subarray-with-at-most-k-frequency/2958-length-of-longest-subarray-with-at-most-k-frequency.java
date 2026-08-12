class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        int left = 0;
        int right = 0;
        int maxLen = 0;
        
        int overCount = 0;
        
        while (right < n) {
            int currNum = nums[right];
            freqMap.put(currNum, freqMap.getOrDefault(currNum, 0) + 1);
            
            if (freqMap.get(currNum) == k + 1) {
                overCount++;
            }
            
            if (overCount > 0) {
                int leftNum = nums[left];
                freqMap.put(leftNum, freqMap.get(leftNum) - 1);
                if (freqMap.get(leftNum) == k) {
                    overCount--;
                }
                left++;
            }
            
            if (overCount == 0) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            
            right++;
        }
        
        return maxLen;
    }
}