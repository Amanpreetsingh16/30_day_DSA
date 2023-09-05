public class day_11 {

    /*
     * Linked list continue
     * Quick sort Linked list
     * 
     */
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

     // qiuck sort in linked list

     public ListNode getTail(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode tail=head;
        while(tail.next!=null){
            tail=tail.next;
        }
        return tail;
     }
     public int length(ListNode head){
        if(head==null ){
            return 0;
        }
        ListNode curr=head;
        int len=0;
        while(curr.next!=null){
            len++;
            curr=curr.next;
        }
        return len;
     }

     public ListNode[] segregate(ListNode head,ListNode tail, int pivotIdx){
        ListNode small=new ListNode(-1);
        ListNode large=new ListNode(-1);
        ListNode sp=small, lp=large, curr=head;
        ListNode pivotNode=head;
        while(pivotIdx-->0){
            pivotNode=pivotNode.next;
        }
        while(curr!=null){
            if(curr!=pivotNode){
            if(curr.sum<= pivotNode.sum){
                sp.next=curr;
                sp=sp.next;
            }else{
                lp.next=curr;
                lp=lp.next;
            }
         }
            curr=curr.next;
        }
        pivotNode.next=null;
        sp.next=null;
        lp.next=null;
        ListNode lh=small.next;
         ListNode  lt=lh!=null ?sp :null;
         ListNode  rh=large.next;
         ListNode rt=rh!=null ? lp : null;

        return new ListNode[]{lh, lt, pivotNode, rh, rt};

    }

    public ListNode[] mergeElements(ListNode[] left, ListNode pivotNode, ListNode[] right){
        ListNode head=null;
        ListNode tail=null;

        if(left[0]!=null && right[0]!=null){
            head=left[0];
            tail=right[1];
            left[1].next=pivotNode;
            pivotNode.next=right[0];
        }else if(left[0]!=null){
            head=left[0];
            tail=pivotNode;
            left[1].next=pivotNode;
        }else if(right[0]!=null){
            head=pivotNode;
            tail=right[1];
            pivotNode.next=right[0];
        }else{
            head=tail=pivotNode;
        }

        return new ListNode[]{head, tail};
    }
    
     public  ListNode[] quickSort(ListNode head, ListNode tail){
        if(head==null || head==tail){
            return new ListNode[]{head, tail};
        }
        int len =length(head);
        int pivotIdx=len/2;
        ListNode[] segregatedElements=segregate(head, tail, pivotIdx);
        ListNode[] left=quickSort(segregatedElements[0],segregatedElements[1]);
        ListNode[] right=quickSort(segregatedElements[3], segregatedElements[4]);
        return mergeElements(left, segregatedElements[2],right);

     }
     public  ListNode quickSort(ListNode head){
        ListNode tail=getTail(head);
        return quickSort(head, tail)[0];
     }

     // Add 2 linked list
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
     public ListNode addLists(ListNode list1, ListNode list2){
         list1=reverse(list1);
         list2=reverse(list2);
        ListNode dummy=new ListNode(-1);
        ListNode c1=list1, c2=list2 , p=dummy;
        int carry=0 ,sum;
        while(c1!=null || c2!=null || carry!=0){
             sum=carry+(c1!=null ?c1.val : 0)+(c2!=null ? c2.val : 0);
             carry=sum/10;
             sum=sum%10;
             ListNode res=new ListNode(sum);
             p.next=res;
             p=p.next;
             if(c1!=null) c1= c1.next ;
             if(c2!=null) c2= c2.next ;
        }
        ListNode head=dummy.next;
        head= reverse(head);
         list1=reverse(list1);
         list2=reverse(list2);
         return head;
     }
    public static void main(String[] args){

    }
}
