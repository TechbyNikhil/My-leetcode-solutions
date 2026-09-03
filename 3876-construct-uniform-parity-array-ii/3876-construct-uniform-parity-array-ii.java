class Solution {
    public boolean uniformArray(int[] nums1) {
     
        int minOdd = Integer.MAX_VALUE;
        
        
        for (int num : nums1) {
            if (num % 2 != 0) {
                minOdd = Math.min(minOdd, num);
            }
        }
        
        
        if (minOdd == Integer.MAX_VALUE) return true;
        
        
        for (int num : nums1) {
            if (num % 2 == 0 && num <= minOdd) {
                return false;
            }
        }
        
        return true;
    }
}