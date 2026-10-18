import java.util.Arrays;
import java.util.Comparator;

class Solution {
    
    // Helper function to calculate the sum of digits of a number
    private int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Store numbers along with their original indices
        Integer[][] items = new Integer[n][2];
        for (int i = 0; i < n; i++) {
            items[i][0] = nums[i];          // value
            items[i][1] = i;                // original index
        }
        
        // Sort based on digit sum first, then by numerical value
        Arrays.sort(items, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                int sumA = getDigitSum(a[0]);
                int sumB = getDigitSum(b[0]);
                if (sumA != sumB) {
                    return Integer.compare(sumA, sumB);
                }
                return Integer.compare(a[0], b[0]);
            }
        });
        
        // Find cycles to determine minimum swaps
        boolean[] visited = new boolean[n];
        int cycles = 0;
        
        for (int i = 0; i < n; i++) {
            // If the element is already visited or already in the correct place
            if (visited[i] || items[i][1] == i) {
                continue;
            }
            
            // Traverse the cycle
            int curr = i;
            int cycleSize = 0;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = items[curr][1]; // Move to the index where this element should go
                cycleSize++;
            }
            
            if (cycleSize > 1) {
                cycles += (cycleSize - 1);
            }
        }
        
        return cycles;
    }
}