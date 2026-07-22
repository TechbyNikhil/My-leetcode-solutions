class SegmentTree {

    private int n;
    private int[] values;
    private int[] tree;

    public SegmentTree(int[] values) {
        this.values = values;
        this.n = values.length;
        this.tree = new int[4 * n];

        build(1, 0, n - 1);
    }

    private void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = values[left];
            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }

    public int query(int queryLeft, int queryRight) {

        if (queryLeft > queryRight) {
            return 0;
        }

        return query(1, 0, n - 1, queryLeft, queryRight);
    }

    private int query(int node, int left, int right,
                      int queryLeft, int queryRight) {

        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        int mid = (left + right) / 2;
        int answer = 0;

        if (queryLeft <= mid) {
            answer = Math.max(answer,
                    query(node * 2, left, mid, queryLeft, queryRight));
        }

        if (queryRight > mid) {
            answer = Math.max(answer,
                    query(node * 2 + 1, mid + 1, right,
                            queryLeft, queryRight));
        }

        return answer;
    }
}

class Solution {

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

        int n = s.length();

        //----------------------------------------------------------
        // Count total number of '1's in the original string.
        //----------------------------------------------------------
        int totalOnes = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                totalOnes++;
            }
        }

        //----------------------------------------------------------
        // Store every consecutive block of zeros.
        //----------------------------------------------------------
        List<Integer> zeroBlockLength = new ArrayList<>();
        List<Integer> zeroBlockStart = new ArrayList<>();
        List<Integer> zeroBlockEnd = new ArrayList<>();

        int i = 0;

        while (i < n) {

            int start = i;

            while (i < n && s.charAt(i) == s.charAt(start)) {
                i++;
            }

            if (s.charAt(start) == '0') {
                zeroBlockLength.add(i - start);
                zeroBlockStart.add(start);
                zeroBlockEnd.add(i - 1);
            }
        }

        int blockCount = zeroBlockLength.size();

        //----------------------------------------------------------
        // If there are fewer than two zero blocks,
        // no profitable trade is possible.
        //----------------------------------------------------------
        if (blockCount < 2) {

            List<Integer> answer = new ArrayList<>();

            for (int[] query : queries) {
                answer.add(totalOnes);
            }

            return answer;
        }

        //----------------------------------------------------------
        // Build array:
        // pairSum[i] = length(block i) + length(block i+1)
        //----------------------------------------------------------
        int[] pairSum = new int[blockCount - 1];

        for (int j = 0; j < blockCount - 1; j++) {
            pairSum[j] =
                    zeroBlockLength.get(j)
                  + zeroBlockLength.get(j + 1);
        }

        SegmentTree segmentTree = new SegmentTree(pairSum);

        List<Integer> answer = new ArrayList<>();

        //----------------------------------------------------------
        // Process each query.
        //----------------------------------------------------------
        for (int[] query : queries) {

            int left = query[0];
            int right = query[1];

            int firstBlock =
                    lowerBound(zeroBlockEnd, left);

            int lastBlock =
                    upperBound(zeroBlockStart, right) - 1;

            //------------------------------------------------------
            // Less than two zero blocks inside substring.
            //------------------------------------------------------
            if (firstBlock > blockCount - 1 ||
                lastBlock < 0 ||
                firstBlock >= lastBlock) {

                answer.add(totalOnes);
                continue;
            }

            //------------------------------------------------------
            // Effective length of first zero block inside substring.
            //------------------------------------------------------
            int firstLength =
                    zeroBlockEnd.get(firstBlock)
                    - Math.max(zeroBlockStart.get(firstBlock), left)
                    + 1;

            //------------------------------------------------------
            // Effective length of last zero block inside substring.
            //------------------------------------------------------
            int lastLength =
                    Math.min(zeroBlockEnd.get(lastBlock), right)
                    - zeroBlockStart.get(lastBlock)
                    + 1;

            //------------------------------------------------------
            // Exactly two zero blocks.
            //------------------------------------------------------
            if (firstBlock + 1 == lastBlock) {

                int bestGain = firstLength + lastLength;

                answer.add(totalOnes + bestGain);
                continue;
            }

            //------------------------------------------------------
            // Three possible candidates:
            //
            // 1. Partial first + complete second
            // 2. Complete second-last + partial last
            // 3. Two complete adjacent blocks in middle
            //------------------------------------------------------
            int option1 =
                    firstLength
                    + zeroBlockLength.get(firstBlock + 1);

            int option2 =
                    zeroBlockLength.get(lastBlock - 1)
                    + lastLength;

            int option3 =
                    segmentTree.query(firstBlock + 1,
                                      lastBlock - 2);

            int bestGain =
                    Math.max(option1,
                    Math.max(option2, option3));

            answer.add(totalOnes + bestGain);
        }

        return answer;
    }

    //--------------------------------------------------------------
    // First index >= target
    //--------------------------------------------------------------
    private int lowerBound(List<Integer> list, int target) {

        int left = 0;
        int right = list.size();

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    //--------------------------------------------------------------
    // First index > target
    //--------------------------------------------------------------
    private int upperBound(List<Integer> list, int target) {

        int left = 0;
        int right = list.size();

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
