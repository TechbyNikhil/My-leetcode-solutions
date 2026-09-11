class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int digit : digits) {
            freq[digit]++;
        }
        int count = 0;
        for (int num = 100; num <= 999; num++) {
            
            if (num % 2 != 0) {
                continue;
            }
            int n = num;
            int a = n % 10;
            n /= 10;
            int b = n % 10;
            n /= 10;
            int c = n % 10;
            
            int[] need = new int[10];
            need[a]++;
            need[b]++;
            need[c]++;
            boolean possible = true;
            for (int d = 0; d <= 9; d++) {
                if (need[d] > freq[d]) {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                count++;
            }
        }
        return count;
    }
}