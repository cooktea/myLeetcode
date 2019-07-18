import java.util.ArrayList;
        import java.util.List;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/18
 * Info    :    恢复二叉搜索树
 */

public class Solution_99 {
    List<TreeNode> arrayTree = new ArrayList<>();   //二叉搜索树中序遍历的有序列
    TreeNode errorNode1 = null; //第一个错误节点
    TreeNode errorNode2 = null; //第二个错误节点

    public void recoverTree(TreeNode root) {
        visitTree(root);    //生成二叉搜索树的有序列
        /*
        遍历有序列，获得两个错误的节点
         */
        for (int i=0;i<arrayTree.size()-1;i++){
            /*
            如果当前节点大于下一个节点，则该节点是错误节点或错误节点的前一个节点
             */
            if (arrayTree.get(i).val>arrayTree.get(i+1).val){
                /*
                如果第一个节点还没找到
                 */
                if (errorNode1 == null){
                    errorNode1 = arrayTree.get(i);  //当前节点是错误节点
                    errorNode2 = arrayTree.get(i+1);    //防止第一个节点和第二个节点相邻
                }
                /*
                如果第一个错误节点已经找到了
                 */
                else {
                    errorNode2 = arrayTree.get(i+1);   //当前节点的下一个节点是作物节点
                }
            }
        }
        /*
        如果两个错误节点都找到了
         */
        if (errorNode1 !=null && errorNode2 != null){
            exchangeNode(errorNode1,errorNode2);    //交换两个节点的值
        }
    }

    /**
     * 交换节点
     * @param node1 节点1
     * @param node2 节点2
     */
    public void exchangeNode(TreeNode node1,TreeNode node2){
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    /**
     * 中序遍历二叉树，生成有序列
     * @param root
     */
    public void visitTree(TreeNode root){
        if (root == null){
            return;
        }
        visitTree(root.left);
        arrayTree.add(root);
        visitTree(root.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
