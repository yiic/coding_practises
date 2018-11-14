package EnhancePractices;

import BinaryTreesProblems.TreeNodeP;

import java.util.*;

public class BinaryTreePaths {
    public String[] binaryTreePaths(TreeNodeP root) {
        if (root == null) return new String[0];
        List<String> solu = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();
        btPathsHelper(root, prefix, solu);

        String[] solution = new String[solu.size()];
        for (int i = 0; i < solu.size(); i++) {
            solution[i] = solu.get(i);
        }
        return solution;
    }
    private void btPathsHelper(TreeNodeP root, StringBuilder prefix, List<String> solu) {
        if (root.left == null && root.right == null) {
            prefix.append(root.key);
            solu.add(prefix.toString());
            return;
        }
        prefix.append(root.key);
        prefix.append("->");
        int sizeBefore = prefix.length();
        if (root.left != null) {
            btPathsHelper(root.left, prefix, solu);
        }
        if (root.right != null) {
            prefix.setLength(sizeBefore);
            btPathsHelper(root.right, prefix, solu);
        }

    }

    public static class LCA {

        //1. find lca of 2 nodes in Binary tree (basic)
        public static TreeNodeC LCAof2inBT(TreeNodeC root, TreeNodeC a, TreeNodeC b) {
            //check if current node is null or a or b
            if (root == null || root == a || root == b) {
                return root;
            }

            TreeNodeC left = LCAof2inBT(root.left, a, b);
            TreeNodeC right = LCAof2inBT(root.left, a, b);

            //return c, a and b非直接隶属，current node c 为 LCA，会一直返回上去 (in upper level, this level return c, the other return null)
            if (left != null && right != null) {
                return root;
            }

            //其中一个为null，return 另外那个非null的
            //都为null, return null
            return left != null ? left : right;

        }

        //2. find lca of n nodes in Binary tree
        public static TreeNodeC LCAofNBT(TreeNodeC root, Set<TreeNodeC> input) {
            if (root == null || input.contains(root)) {
                return root;
            }
            TreeNodeC left = LCAofNBT(root.left, input);
            TreeNodeC right = LCAofNBT(root.right, input);

            if (left != null && right != null) {
                return root;
            }
            return left != null ? left : right;
        }

        //3. find lca of two nodes in k-ary tree
        public static TreeNodeC LCAof2inKT(TreeNodeC root, TreeNodeC a, TreeNodeC b) {
            if (root == null || root == a || root == b) {
                return root;
            }

            /*
                check all children of current node:
                   1. all are null --> return null
                   2. only one is not null --> return this one
                   3. exits two are not null --> return current node
            */
            TreeNodeC solu = null;
            for (TreeNodeC child : root.children) {
                TreeNodeC temp = LCAof2inKT(child, a, b);
                if (temp != null) {
                    if (solu != null) {
                        return root;
                    } else {
                        solu = temp;
                    }
                }
            }

            return solu;
        }

        //4. find lca of n nodes in k-ary tree
        public static TreeNodeC LCAofNinKT(TreeNodeC root, Set<TreeNodeC> input) {
            if (root == null || input.contains(root)) {
                return root;
            }

            /*
                check all children of current node:
                   1. all are null --> return null
                   2. only one is not null --> return this one
                   3. exits two are not null --> return current node
            */
            TreeNodeC solu = null;
            for (TreeNodeC child : root.children) {
                TreeNodeC temp = LCAofNinKT(child, input);
                if (temp != null) {
                    if (solu != null) {
                        return root;
                    } else {
                        solu = temp;
                    }
                }
            }

            return solu;
        }

    }
}
