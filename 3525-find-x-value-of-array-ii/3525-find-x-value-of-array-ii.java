class Solution {

    static class Node {
        int prod;
        int[] cnt;
        Node(int k) {
            cnt = new int[k];
        }
    }

    int k;
    Node[] tree;
    int n;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            
            update(1, 0, n - 1, index, value);

            
            Node ans = query(1, 0, n - 1, start, n - 1);

            result[q] = ans.cnt[x];
        }

        return result;
    }

    private void build(int node, int l, int r, int[] nums) {

        if (l == r) {
            tree[node] = new Node(k);

            int rem = nums[l] % k;

            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int index, int value) {

        if (l == r) {
            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    private Node merge(Node left, Node right) {

        Node parent = new Node(k);

        
        parent.prod = (left.prod * right.prod) % k;

        
        for (int i = 0; i < k; i++) {
            parent.cnt[i] += left.cnt[i];
        }

       
        for (int i = 0; i < k; i++) {

            int newRem = (left.prod * i) % k;

            parent.cnt[newRem] += right.cnt[i];
        }

        return parent;
    }
}