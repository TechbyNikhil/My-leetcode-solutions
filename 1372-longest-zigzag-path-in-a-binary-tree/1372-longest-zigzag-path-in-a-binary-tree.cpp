/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int pathLen;
    void solve(TreeNode* node, int count, int dir)
    {
        if(!node)return ;
        pathLen=max(pathLen,count);

        if(dir==0)
        {
            solve(node->left,count+1,1);
            solve(node->right,1,0);
        }
        else
        {
            solve(node->right,count+1,0);
            solve(node->left,1,1);
        }
    }
    int longestZigZag(TreeNode* root) {
        pathLen=0;

        solve(root->left,1,1);
        solve(root->right,1,0);

        return pathLen;
    }
};