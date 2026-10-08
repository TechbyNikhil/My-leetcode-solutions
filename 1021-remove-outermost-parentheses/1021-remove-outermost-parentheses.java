class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int opened = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                
                if (opened > 0) {
                    sb.append(c);
                }
                opened++;
            } else {
                opened--;
                
                if (opened > 0) {
                    sb.append(c);
                }
            }
        }
        
        return sb.toString();
    }
}
