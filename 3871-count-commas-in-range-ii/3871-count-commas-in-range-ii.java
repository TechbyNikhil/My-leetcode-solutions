class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long factor = 1000;
        while (factor <= n) {
            ans += (n - factor + 1);
            factor *= 1000;
        }
        return ans;
    }
}
