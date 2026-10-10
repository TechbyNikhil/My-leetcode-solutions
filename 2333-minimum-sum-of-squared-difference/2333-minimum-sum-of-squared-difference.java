import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diffs = new int[n];
        long totalDiff = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiff += diffs[i];
            maxDiff = Math.max(maxDiff, diffs[i]);
        }

        long k = (long) k1 + k2;
        if (totalDiff <= k) {
            return 0; 
        }

        
        long left = 0, right = maxDiff;
        long targetDiff = maxDiff;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long opsNeeded = 0;
            for (int d : diffs) {
                if (d > mid) {
                    opsNeeded += (d - mid);
                }
            }
            if (opsNeeded <= k) {
                targetDiff = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        
        long opsUsed = 0;
        for (int i = 0; i < n; i++) {
            if (diffs[i] > targetDiff) {
                opsUsed += (diffs[i] - targetDiff);
                diffs[i] = (int) targetDiff;
            }
        }

        long remainingOps = k - opsUsed;

        
        for (int i = 0; i < n && remainingOps > 0; i++) {
            if (diffs[i] == targetDiff && targetDiff > 0) {
                diffs[i]--;
                remainingOps--;
            }
        }

        
        long minSumSquare = 0;
        for (int d : diffs) {
            minSumSquare += (long) d * d;
        }

        return minSumSquare;
    }
}
