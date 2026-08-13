class Solution {
    private static class Node {
        char leftChar, rightChar;
        int prefix, suffix, max, len;
        Node(char leftChar, char rightChar, int prefix, int suffix, int max, int len) {
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.prefix = prefix;
            this.suffix = suffix;
            this.max = max;
            this.len = len;
        }
    }
    private char[] arr;
    private Node[] tree;
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        arr = s.toCharArray();
        tree = new Node[4 * n];
        build(1, 0, n - 1);
        int k = queryCharacters.length();
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].max;
        }
        return ans;
    }

    private void build(int node, int left, int right) {
        if (left == right) {
            tree[node] = new Node(arr[left], arr[left], 1, 1, 1, 1);
            return;
        }
        int mid = left + (right - left) / 2;
        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int left, int right, int index, char ch) {
        if (left == right) {
            arr[index] = ch;
            tree[node] = new Node(ch, ch, 1, 1, 1, 1);
            return;
        }
        int mid = left + (right - left) / 2;
        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node a, Node b) {
        int len = a.len + b.len;
        char leftChar = a.leftChar;
        char rightChar = b.rightChar;
        int prefix = a.prefix;
        if (a.prefix == a.len && a.rightChar == b.leftChar) {
            prefix = a.len + b.prefix;
        }
        int suffix = b.suffix;
        if (b.suffix == b.len && a.rightChar == b.leftChar) {
            suffix = b.len + a.suffix;
        }
        int max = Math.max(a.max, b.max);
        if (a.rightChar == b.leftChar) {
            max = Math.max(max, a.suffix + b.prefix);
        }
        return new Node(leftChar, rightChar, prefix, suffix, max, len);
    }
}