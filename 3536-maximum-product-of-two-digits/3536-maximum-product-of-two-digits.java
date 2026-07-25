class Solution {
    public int maxProduct(int n) {
        int f = 0;
        int s = 0;
        while(n != 0){
            int x = n % 10;
            if(x > f) {
                s = f;
                f = x;
            } else if(x >s){
                s = x;
            }
            n = n/ 10;
        }
        return f * s;
    }
}