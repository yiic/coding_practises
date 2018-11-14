package MidII;

public class MaximunPathSumBinaryTreeI {
    public class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    public int maxPathSumBinaryTreeI(TreeNode root) {
        //Assumption: path is from one leaf node to another leaf node,
        //if there is no such path, return Integer.MIN_VALUE
        //corner case
        if (root == null) return Integer.MIN_VALUE;

        int[] glo_max = new int[1];
        maxPathHelper(root, glo_max);
        return glo_max[0];
    }
    //helper fun return max path value from current node to a leaf node
    //update glo_max as the max sum result from leaf to leaf
    private int maxPathHelper(TreeNode root, int[] glo_max) {
        //base case
        if (root == null) return 0;

        int left = maxPathHelper(root.left, glo_max);
        int right = maxPathHelper(root.right, glo_max);

        //update glo_max only root has two children
        if (root.left != null && root.right != null) {
            glo_max[0] = Math.max(glo_max[0], root.key + left + right);
            return Math.max(left, right) + root.key;
        }

        //node has only one child, return the only path's sum
        //cannot return Math.max(left, right) + root.key; because:
        //null node is not real node, but base case let it return 0, which beats
        //negative value node subpath, we cannot count this null node subpath.
        return root.left != null ? left + root.key : right + root.key;
    }
}
