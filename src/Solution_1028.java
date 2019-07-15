import java.util.*;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/15
 * Info    :    从先序遍历还原二叉树
 */

public class Solution_1028 {

    String s;   //输入字符串
    List<String > strs = new ArrayList<>(); //存储字符串按-分割后的字串
    Map<Integer,TreeNode> node = new HashMap<>();   //已节点层数存储节点
    List<Map<Integer,TreeNode>> tree = new LinkedList<>();  //节点序列

    public static void main(String[] args) {
        new Solution_1028().recoverFromPreorder("1-401--349---90--88");
    }

    public TreeNode recoverFromPreorder(String S) {
        this.s = S;
        processString();
        initTree();
        return tree.get(0).get(0);  //返回根节点
    }

    /**
     * 构建二叉树
     */
    public void initTree(){
        /*
        依次遍历节点序列
         */
        for (int i=0;i<tree.size();i++){
            boolean isLinked = false;   //判断当前节点是否和其父节点相连
            Map<Integer,TreeNode> node = tree.get(i);   //获取节点
            for (Integer floor : node.keySet()){
                if (floor != 0){    //如果不是根节点
                    /*
                    从当前节点开始向前访问
                     */
                    for (int j = i-1;j>=0;j--){
                        Map<Integer,TreeNode> temp = tree.get(j);   //获取前置节点
                        for (Integer floorTemp : temp.keySet()){
                            /*
                            如果前置节点的层数仅比当前节点的层数少一层
                            即前置节点是当前节点的父节点
                             */
                            if (floor == floorTemp+1){
                                if (temp.get(floorTemp).left != null){  //如果父节点的左子树已有
                                    temp.get(floorTemp).right = node.get(floor);    //将当前节点添加至父节点的右子树
                                } else {
                                    temp.get(floorTemp).left = node.get(floor);    //将当前节点添加至父节点的左子树
                                }
                                isLinked = true;    //当前节点已找到父节点并和父节点相连
                            }
                        }
                        if (isLinked){  //如果当前节点已找到父节点并和父节点相连，则跳出该循环
                            break;
                        }
                    }

                }
            }
        }
    }

    /**
     * 处理字符串
     */
    public void processString(){
        char[] chars = s.toCharArray(); //获取字符数组
        StringBuilder temp = new StringBuilder();   //保存字串
        /*
        分割字符串
        依次遍历字符串中的每一个字符
        并将每一个字符添加至temp的末尾
        当前字符为“-”且前一个字符不为“-”时，将temp输出并保存在strs中
         */
        for (int i=0;i<chars.length;i++){
            /*
            判断是不是需要分割
             */
            if(chars[i] == '-' && chars[i-1] != '-'){
                strs.add(temp.toString());  //保存至strs
                temp = new StringBuilder(); //重置temp
            }
            temp.append(chars[i]);  //在temp末尾添加字符
        }
        strs.add(temp.toString());  //添加最后一个字串
        temp = new StringBuilder(); //重置temp
        /*
        根据的到的子串按顺序生成节点
        子串均形如---nnn
         */
        for (String s : strs){
            chars = s.toCharArray();    //获取字串的字符数组
            int floor = 0;  //该节点的层数
            int val = 0;    //该节点的值
            for (int i=0;i<chars.length;i++){
                /*
                如果字符为“-”，则层数加1
                 */
                if (chars[i] == '-'){
                    floor++;
                }
                /*
                字串前置的“-”均已被遍历，后面开始遍历数字
                从现在开始temp用来保存节点值的字符串形式
                 */
                else {
                    temp.append(chars[i]);  //将数组添加至temp的结尾
                }
            }
            val = Integer.parseInt(temp.toString());    //根据字符串输出int
            TreeNode node = new TreeNode(val);  //新建一个节点
            node.left = null;
            node.right = null;
            Map<Integer,TreeNode> map = new HashMap<>();    //新建一个保存节点和节点层数的map
            map.put(Integer.valueOf(floor),node);
            tree.add(map);  //在节点序列中添加节点
            temp = new StringBuilder(); //初始化temp
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }


}
