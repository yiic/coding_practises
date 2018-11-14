package Final;

import java.util.Queue;
import java.util.LinkedList;



public class IsCousin {
    //************************** way 1: use recursion (LCA idea)****************************
    //time: O(n) space: O(height)
    public boolean isCousin(TreeNode root, TreeNode a, TreeNode b) {
        boolean[] result = new boolean[]{false};
        helper(root, 0, a, b, result);
        return result[0];
    }

    /**
     * a good way to get multiple results each time:
     *  pass updated curDep
     *  also determine boolean result (once immediately d1 and d2 are all not -1 and equal --> find a and b )
     *  return -1(not found) or curDep(found a or b)
     */

    private int helper(TreeNode root, int curDep, TreeNode a, TreeNode b, boolean[] result) {  //Time: 一般recursion 树的时间为 O(n)
        //base case
        if (root == null) return -1;
        if (root == a || root == b) return curDep;

        int d1 = helper(root.left, curDep + 1, a, b, result);
        int d2 = helper(root.right, curDep + 1, a, b, result);
        //cur is not parent for both a and b, cur is higher(shallower)
        if (d1 == d2 && d1 != -1 && curDep + 1 < d1) {
            result[0] = true;
        }
        return Math.max(d1, d2);//return depth of the side which is not -1.
    }

    //************************** way 2: use BFS ***********************************
    //time: O(n) space: O(height)
    public boolean isCousinBFS(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) return false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            boolean flagA = false;
            boolean flagB = false;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                TreeNode left = null, right = null;
                if (cur.left != null) {
                    left = cur.left;
                    q.offer(left);
                }
                if (cur.right != null) {
                    right = cur.right;
                    q.offer(right);
                }
                //从parent层向下看，如果是相同parent， 扼杀在摇篮里
                if (left == a && right == b || left == b && right == a) {
                    return false;
                }

                flagA = flagA | (left == a || right == a); // we find a, "|" bit-wise OR
                flagB = flagB | (left == b || right == b); // we find b
                // if we not find a and b, keep doing BFS next
            }

            if(flagA && flagB) return true; // we find both a and b: at same level be different parent
            if(flagA || flagB) return false; // we only find one: a or b, then they are not in the same level

        }
        return false;
    }

}
