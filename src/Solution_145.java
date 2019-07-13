import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/13
 * Info    :
 */

public class Solution_145 {
    List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
//        Solution_145 s = new Solution_145();
    }

    /**
     * 后序遍历二叉树
     * @param root  根节点
     * @return  后序遍历序列
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        /*
        递归解法
         */
        visitANode(root);
        /*
        迭代解法
         */
        visitTree(root);
        return list;
    }

    /**
     * 递归解法
     * @param root  节点
     */
    private void visitANode(TreeNode root){
        if(root == null){
            return;
        } else {
            visitANode(root.left);
            visitANode(root.right);
            list.add(root.val);
        }
    }

    /**
     * 迭代解法
     * @param root  二叉树根节点
     */
    private void visitTree(TreeNode root){
        TreeNode temp = root;
        Stack<TreeNode> st = new Stack<>();
        while (temp!=null || !st.empty()){
            if (temp != null){
                list.add(0,temp.val);
                st.push(temp);
                temp = temp.right;
            } else {
                TreeNode node = st.pop();
                temp = node.left;
            }
        }
    }

    /**
     * 二叉树节点
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
