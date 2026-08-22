class Solution {
    public boolean checkDivisibility(int n) {
        int original = n;
        int sum = 0;
        int product = 1;

        while (n != 0) {
            int ld = n % 10;
            sum += ld;
            product *= ld;
            n /= 10;
        }

        int c = sum + product;

        return original % c == 0;
    }
}