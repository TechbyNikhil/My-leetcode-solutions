class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openParentheses = 0;
        int i = 0;
        int n = s.length();

        while (i < n) {
            char c = s.charAt(i);

            if (c == '(') {
                openParentheses++;
                i++;
            } else { 
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    
                    i += 2;
                } else {
                    
                    insertions++;
                    i++;
                }

                
                if (openParentheses > 0) {
                    openParentheses--;
                } else {
                    
                    insertions++;
                }
            }
        }

       
        insertions += openParentheses * 2;

        return insertions;
    }
}
