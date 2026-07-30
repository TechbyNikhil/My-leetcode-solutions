class Solution {
    public int minimumPushes(String word) {
        int n  = word.length();
        int sum = 0;
        for(int i=0;i<n;i++){
            if(i<8){
                sum = sum +1;
            }else if( i <16){
                sum = sum + 2;
            }else if(i< 24){
                sum = sum + 3;
            }else {
                sum = sum + 4;
            }
        }
        return sum;
    }
}