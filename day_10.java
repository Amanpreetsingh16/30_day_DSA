public class day_10 {

    /*
     * Linked list set
     * middle of linklist leetcode 876
     * reverse a linkedlinst
     * palindrom LinkedList leetcode 234
     * reoder linked list leetcode 143
     * unfold linkedlist
     * Merge two Sorted List (leetcode 21)
     * merge sort linkedList (leetcode 148)
     * Remove Nth from last in linked list (leetcode 19)
     * segregate odd even list ( Odd Even Linked List) (leetcode 328)
     * segregate 01's 
     * segregate 012's
     *  segregate over last of linked list
     * segregate from pivot index
     */

    // Midlle of linkedlist
    // leetcode 876
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

        }
        return slow;
    }

    // reverse a linkedlinst
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode forward = curr.next; // backup
            curr.next = prev;
            prev = curr;
            curr = forward;
        }
        return prev;
    }

    // palindrom LinkedList
    // leetcode 234
    ListNode pleft;

    public boolean isPalindrome_helper(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = isPalindrome_helper(right.next);
        if (!res) {
            return false;
        } else if (pleft.val != right.val) {
            return false;

        } else {
            pleft = pleft.next;
            return true;
        }
    }

    public boolean isPalindrome(ListNode head) {
        pleft = head;
        return isPalindrome_helper(head);
    }

    // another way of solving Pallindrom question
    public boolean isPallindrom(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = middleNode(head);
        ListNode nHead = mid.next;
        mid.next = null;
        nHead = reverse(nHead);
        ListNode c1 = head, c2 = nHead;
        boolean res = true;
        while (c2 != null) {
            if (c1.val != c2.val) {
                res = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }
        nHead = reverse(nHead);
        mid.next = nHead;
        return res;
    }

    // fold a linkedlist
    // reoder linked list leetcode 143
    public void reorder(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode nHead = mid.next;
        mid.next = null;
        nHead = reverse(nHead);
        ListNode c1 = head, c2 = nHead;

        while (c2 != null) {
            ListNode fwd1 = c1.next, fwd2 = c2.next;
            c1.next = c2;
            c2.next = fwd1;
            c1 = fwd1;
            c2 = fwd2;
        }
    }

    // unfold a linked list
    public void unfold(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode p1 = l1, p2 = l2, c1 = head, c2 = head.next;
        while (c1 != null && c2 != null) {
            p1.next = c1;
            p2.next = c2;
            p1 = p1.next;
            p2 = p2.next;
            if (c2 != null)
                c1.next = c2.next;
            if (c1 != null)
                c2.next = c1.next;
        }
        p1.next = null;
        l2.next = reverse(l2.next);
        p1.next = l2.next;

    }

    // leetcode 21 Merge two sorted List
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy, c1 = list1, c2 = list2;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                p.next = c1;
                c1 = c1.next;
            } else {
                p.next = c2;
                c2 = c2.next;
            }
            p = p.next;
        }
        p.next = c1 != null ? c1 : c2;
        return dummy.next;
    }

    // Merge K Sorted List
    // leet code 23
    // time comlexity: O(Nlogk) , space: O(logk) where N=k
    public ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si == ei) {
            return lists[si];

        }
        int mid = (si + ei) / 2;
        ListNode leftMergedList = mergeKLists(lists, si, mid);
        ListNode rightMergedList = mergeKLists(lists, mid + 1, ei);
        return mergeTwoLists(leftMergedList, rightMergedList);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    // merge sort linkedList
    // leetcode 148
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = middleNode(head);
        ListNode nhead = mid.next;
        mid.next = null;
        return mergeTwoLists(sortList(head), sortList(nhead));
    }

    // Remove Nth from last in linked list
    // leetcode 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            ListNode rnode = slow;
            head = rnode.next;
            rnode.next = null;
            return head;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode rnode = slow.next;
        slow.next = rnode.next;
        rnode.next = null;
        return head;
    }
    // segregate odd even list ( Odd Even Linked List)
    //leet code 328
    public ListNode oddEven(ListNode head){
        if(head==null || head.next==null){
            return head;
        }

        ListNode odd=new ListNode(-1);
        ListNode even=new ListNode(-1);
        ListNode op=odd, ep=even, curr=head;
        while(curr!=null){
            if(curr.val % 2 !=0){
                op.next=curr;
                op=op.next;
            }else{
                ep.next=curr;
                ep=ep.next;
            }
            curr=curr.next;
        }
        op.next=even.next;
        head=odd.next;
        ep.next=null;
        odd.next=even.next=null;
        return head;

    }
    // segregate 01's 
    public ListNode zeroOne(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode one=new ListNode(-1);
        ListNode zero=new ListNode(-1);
        ListNode op=one, zp=zero, curr=head;
        while(curr!=null){
            if(curr.val !=0){
                op.next=curr;
                op=op.next;
            }else{
                zp.next=curr;
                zp=zp.next;
            }
            curr=curr.next;
        }
        zp.next=one.next;
        op.next=null;
        head=zero.next;
        one.next=zero.next=null;
        return head;
    }
    // segregate 012's
    public ListNode zeroOneTwo(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode one=new ListNode(-1);
        ListNode zero=new ListNode(-1);
        ListNode two=new ListNode(-1);
        ListNode op=one, zp=zero,tp=two,curr=head;
        while(curr!=null){
            if(curr.val ==1){
                op.next=curr;
                op=op.next;
            }else if(curr.val==0){
                zp.next=curr;
                zp=zp.next;
            }else{
                tp.next=curr;
                tp=tp.next;
            }
            curr=curr.next;
        }

        op.next=two.next; // iss line ko phle likhne se hmara test case jisme sirf all 2's ho pass ho jayega
        zp.next=one.next;
        tp.next=null;
        head=zero.next;
        one.next=zero.next= two.next=null;
        return head;
    }
    // segregate over last of linked list

     public ListNode segregateOnLastIdxex(ListNode head){
        if(head==null || head.next==null){
            return head;
        }

        ListNode small=new ListNode(-1);
        ListNode large=new ListNode(-1);
        ListNode sp=small, lp=large, curr=head;
        ListNode pivotNode=head;
        while(pivotNode.next!=null){
            pivotNode=pivotNode.next;
        }
        while(curr!=null){
            if(curr.val<= pivotNode.val){
                sp.next=curr;
                sp=sp.next;
            }else{
                lp.next=curr;
                lp=lp.next;
            }
            curr=curr.next;
        }
        sp.next=large.next;
        head=small.next;
        lp.next=null;
        large.next=small.next=null;
        return sp;

    }
// segregate from pivot index
public ListNode segregateOnPivot(ListNode head, int pivotIdx){
        if(head==null || head.next==null){
            return head;
        }

        ListNode small=new ListNode(-1);
        ListNode large=new ListNode(-1);
        ListNode sp=small, lp=large, curr=head;
        ListNode pivotNode=head;
        while(pivotIdx-->0){
            pivotNode=pivotNode.next;
        }
        while(curr!=null){
            if(curr==pivotNode){
               
            }
           else if(curr.val<= pivotNode.val){
                sp.next=curr;
                sp=sp.next;
            }else{
                lp.next=curr;
                lp=lp.next;
            }
            curr=curr.next;
        }
        sp.next=pivotNode;
        pivotNode.next=large.next;
        head=small.next;
        lp.next=null;
        large.next=small.next=null;
        return head;

    }
    public static void main(String[] args) {

    }
}
