import java.util.HashMap;
import java.util.Map;

class Solution {
    static final int MOD = 1_000_000_007;
    long[] sumSq;
    long[] sumC;
    long[] lazy;

    // Push lazy value down to children
    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    // Apply increment 'val' to a node representing range [l, r]
    private void apply(int node, int l, int r, long val) {
        long len = (r - l + 1);
        // sumSq_new = sumSq + 2 * val * sumC + val^2 * len
        sumSq[node] = (sumSq[node] + 2 * val * sumC[node] + val * val * len) % MOD;
        // sumC_new = sumC + val * len
        sumC[node] = (sumC[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    // Range update: add 'val' to range [ql, qr]
    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sumC[node] = (sumC[2 * node] + sumC[2 * node + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumSq = new long[4 * n];
        sumC = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastOccurrence = new HashMap<>();
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int x = nums[j];
            int last = lastOccurrence.getOrDefault(x, -1);
            
            // Range [last + 1, j] gets incremented by 1
            update(1, 0, n - 1, last + 1, j, 1);
            lastOccurrence.put(x, j);

            // sumSq[root] now holds the sum of squares of all subarrays ending at j
            totalSum = (totalSum + sumSq[1]) % MOD;
        }

        return (int) totalSum;
    }
}