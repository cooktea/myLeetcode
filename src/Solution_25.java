/**
 * Author  :   ChenKang
 * Time    :   2019/7/20
 * Info    :    K 个一组翻转链表
 */

public class Solution_25 {

    public static void main(String[] args) {
        Solution_25 s = new Solution_25();
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i=2;i<6;i++){
            ListNode node = new ListNode(i);
            temp.next = node;
            temp = node;
        }
        s.printList(head);
        head = s.reverseKGroup(head,2);
        s.printList(head);
    }

    int group = 0;

    /**
     * 翻转链表
     * @param head  头节点
     * @param k k个一组
     * @return  翻转后的头节点
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1){
            return head;
        }
        int length = getLength(head);   //获得链表长度
        int group = (int)length/k;  //获得需要反转的组数
        this.group = group;
        return reverse(head,k,0)[1];    //返回反转后的链首
    }

    /**
     * 按组翻转链表
     * @param head  每组的头节点
     * @param k 需要反转的节点个数
     * @param count 第count组
     * @return  一个保存了这组反转后的头节点和尾节点的节点数组
     */
    public ListNode[] reverse(ListNode head,int k,int count){
        ListNode[] results = new ListNode[2];   //results[0]:翻转后的尾节点，results[1]：翻转后的头节点
        results[0] = head;  //保存当前头节点/反转后的尾节点
        int tempK = k;  //保存kde值
        /*
        如果最后一组翻转完成
         */
        if (count == group){
            results[1] = head;  //翻转后的头节点时当前的头节点
            return results;
        }
        ListNode p1,p2,p3;
        p1 = head;
        p2 = p1.next;
        p3 = p2.next;
        /*
        翻转操作
         */
        while (k>1){
            /*
            翻转一个节点
             */
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            /*
            如果后面没有节点了，则跳出循环
             */
            if (p3 == null){
                results[1] = p2;
                break;
            }
            p3 = p3.next;
            k--;
        }
        results[1] = p1;    //保存反转后的头节点
        count++;
        ListNode temp = reverse(p2,tempK,count)[1]; //翻转后一组，获得后一组翻转后的头节点
        results[0].next = temp; //将当前组的尾节点和后一组的头节点相连。
        return results;
    }

    public void printList(ListNode head){
        ListNode temp = head;
        while (temp!=null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println(" ");
    }

    public int getLength(ListNode head){
        int len = 0;
        ListNode temp = head;
        while (head != null){
            len++;
            head = head.next;
        }
        return len;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}