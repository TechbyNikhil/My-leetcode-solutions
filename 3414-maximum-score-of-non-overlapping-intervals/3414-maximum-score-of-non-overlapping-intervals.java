class Solution {
    int[][] arr;
    int[] next;
    Node[][] memo;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); 
            arr[i][1] = intervals.get(i).get(1); 
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }
        
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);

            return Integer.compare(a[1], b[1]);
        });

        
        next = new int[n];
        for (int i = 0; i < n; i++) {
            int low = i + 1;
            int high = n;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr[mid][0] > arr[i][1])
                    high = mid;
                else
                    low = mid + 1;
            }
            next[i] = low;
        }

        memo = new Node[n][5];
        Node answer = solve(0, 4);
        Collections.sort(answer.indices);
        return answer.indices.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private Node solve(int i, int k) {

        
        if (i == arr.length || k == 0) {
            return new Node(0, new ArrayList<>());
        }

        if (memo[i][k] != null)
            return memo[i][k];

        
        Node skip = solve(i + 1, k);

        
        Node nextResult = solve(next[i], k - 1);

        List<Integer> takeIndices = new ArrayList<>();
        takeIndices.add(arr[i][3]);
        takeIndices.addAll(nextResult.indices);

        Node take = new Node(
                arr[i][2] + nextResult.score,
                takeIndices);

        // Choose better answer
        memo[i][k] = better(skip, take);

        return memo[i][k];
    }

    private Node better(Node a, Node b) {

        if (a.score != b.score)
            return a.score > b.score ? a : b;

        
        List<Integer> x = new ArrayList<>(a.indices);
        List<Integer> y = new ArrayList<>(b.indices);

        Collections.sort(x);
        Collections.sort(y);

        for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
            if (!x.get(i).equals(y.get(i)))
                return x.get(i) < y.get(i) ? a : b;
        }

        return x.size() <= y.size() ? a : b;
    }

    static class Node {

        long score;
        List<Integer> indices;

        Node(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }
}