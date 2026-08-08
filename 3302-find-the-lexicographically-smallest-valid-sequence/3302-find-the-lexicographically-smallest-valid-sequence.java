class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        // last[i] = index in word1 where word2[i] can be matched
        // while matching word2 from right to left.
        int[] last = new int[m];
        Arrays.fill(last, -1);
        int word2Index = m - 1;
        // Find a subsequence of word2 from right to left.
        for (int word1Index = n - 1;
             word1Index >= 0 && word2Index >= 0;
             word1Index--) {
            if (word1.charAt(word1Index) == word2.charAt(word2Index)) {
                last[word2Index] = word1Index;
                word2Index--;
            }
        }
        int[] result = new int[m];
        // Whether we have already used our one allowed modification.
        boolean usedChange = false;
        word2Index = 0;
        // Greedily choose the smallest possible index.
        for (int word1Index = 0;
             word1Index < n && word2Index < m;
             word1Index++) {
            char currentChar = word1.charAt(word1Index);
            char requiredChar = word2.charAt(word2Index);

            // Case 1: Characters already match.
            boolean charactersMatch = currentChar == requiredChar;

            // Case 2: We can use our one allowed modification.
            //
            // After changing word1[word1Index], we still need
            // to match word2[word2Index + 1 ...].
            //
            // last[word2Index + 1] tells us where that remaining
            // suffix can start.
            boolean canUseChange =  !usedChange &&  (word2Index == m - 1 ||  word1Index < last[word2Index + 1]);
            if (charactersMatch || canUseChange) {
                result[word2Index] = word1Index;
                // Mark that we used our one mismatch.
                if (!charactersMatch) {
                    usedChange = true;
                }
                word2Index++;
            }
        }
        // We couldn't match all characters.
        if (word2Index < m) {
            return new int[0];
        }

        return result;
    }
}