class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int min_i = 0;
        int max_i = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[min_i]) {
                min_i = i;
            }
            if (nums[i] > nums[max_i]) {
                max_i = i;
            }
        }

        int left = Math.max(min_i, max_i) + 1;
        int right = n - Math.min(min_i, max_i);
        int both = Math.min(min_i, max_i) + 1+ n - Math.max(min_i, max_i);

        return Math.min(left, Math.min(right, both));
    }
}