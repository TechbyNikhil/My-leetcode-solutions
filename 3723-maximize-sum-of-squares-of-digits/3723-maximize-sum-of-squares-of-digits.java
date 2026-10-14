class Solution {
    public String maxSumOfSquares(int num, int sum) {
       
        if (sum > (long) num * 9) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        
        
        int nines = sum / 9;
        int remainder = sum % 9;
        
        for (int i = 0; i < nines; i++) {
            sb.append('9');
        }
        
        
        if (remainder > 0) {
            sb.append((char) ('0' + remainder));
        }
        
        
        while (sb.length() < num) {
            sb.append('0');
        }
        
        return sb.toString();
    }
}
