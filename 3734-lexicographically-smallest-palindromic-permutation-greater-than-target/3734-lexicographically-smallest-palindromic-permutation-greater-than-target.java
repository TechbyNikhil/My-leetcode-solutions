class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        
        if (n == 1) {
            return s.compareTo(target) > 0 ? s : "";
        }
        
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        
        String middleChar = "";
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                
                if (!middleChar.isEmpty()) {
                    return "";
                }
                middleChar = String.valueOf((char) ('a' + i));
            }
            
            count[i] /= 2;
        }

        StringBuilder prefix = new StringBuilder();
        
        for (int position = 0; position < n / 2; position++) {
            boolean found = false;
            
            for (int charIndex = 0; charIndex < 26; charIndex++) {
                if (count[charIndex] == 0) {
                    continue;
                }
                char currentChar = (char) ('a' + charIndex);
                
                count[charIndex]--;
                
                if (check(prefix.toString(), currentChar, count, middleChar, target)) {
                    prefix.append(currentChar);
                    found = true;
                    break;
                } else {
                    
                    count[charIndex]++;
                }
            }
            
            if (!found) {
                return "";
            }
            
            
            if (prefix.charAt(position) > target.charAt(position)) {
                StringBuilder leftHalf = new StringBuilder(prefix);
                for (int charIndex = 0; charIndex < 26; charIndex++) {
                    for (int frequency = 0; frequency < count[charIndex]; frequency++) {
                        leftHalf.append((char) ('a' + charIndex));
                    }
                }
                return leftHalf.toString() + middleChar + new StringBuilder(leftHalf).reverse();
            }
        }

        
        return prefix.toString() + middleChar + new StringBuilder(prefix).reverse();
    }

    private boolean check( String prefix, char currentChar,int[] count, String middleChar,  String target) {
        
        StringBuilder leftHalf = new StringBuilder(prefix);
        leftHalf.append(currentChar);
        
        for (int charIndex = 25; charIndex >= 0; charIndex--) {
            for (int frequency = 0; frequency < count[charIndex]; frequency++) {
                leftHalf.append((char) ('a' + charIndex));
            }
        }
        
        String palindrome = leftHalf.toString()   + middleChar + new StringBuilder(leftHalf).reverse();
        
        return palindrome.compareTo(target) > 0;
    }
}