package leetcode.TreeTrie;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        this.val = x;
    }

    @Override
    public String toString() {
        return val + "";
    }
}