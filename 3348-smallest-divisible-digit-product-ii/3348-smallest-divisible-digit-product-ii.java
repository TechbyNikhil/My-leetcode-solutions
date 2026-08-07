class Solution {
    public String smallestNumber(String num, long t) {
        
        long remainingFactor = t;
        for (int digit = 2; digit <= 9; digit++) {
            while (remainingFactor % digit == 0) {
                remainingFactor /= digit;
            }
        }
        if (remainingFactor > 1) {
            return "-1";
        }
        int n = num.length();
        
        long[] requiredFactor = new long[n + 1];
        requiredFactor[0] = t;
        
        int firstZeroIndex = n - 1;
        char[] digits = num.toCharArray();
       
        for (int i = 0; i < n; i++) {
            if (digits[i] == '0') {
                firstZeroIndex = i;
                break;
            }
            requiredFactor[i + 1] = requiredFactor[i] / gcd(requiredFactor[i], digits[i] - '0');
        }
        
        if (requiredFactor[n] == 1) {
            return num;
        }
        for (int i = firstZeroIndex; i >= 0; i--) {
            while (++digits[i] <= '9') {
                long currentNeed =  requiredFactor[i] / gcd(requiredFactor[i], digits[i] - '0');
                int largestDigit = 9;
                // Fill remaining suffix greedily
                for (int j = n - 1; j > i; j--) {
                    while (currentNeed % largestDigit != 0) {
                        largestDigit--;
                    }
                    currentNeed /= largestDigit;
                    digits[j] = (char) ('0' + largestDigit);
                }
                if (currentNeed == 1) {
                    return new String(digits);
                }
            }
        }
        
        StringBuilder answer = new StringBuilder();
        long remaining = t;
        for (int digit = 9; digit > 1; digit--) {
            while (remaining % digit == 0) {
                answer.append((char) ('0' + digit));
                remaining /= digit;
            }
        }
        // Pad with leading 1's
        int onesNeeded = Math.max(n + 1 - answer.length(), 0);
        for (int i = 0; i < onesNeeded; i++) {
            answer.append('1');
        }
        return answer.reverse().toString();
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
            temp = b;
        }
        return a;
    }
}