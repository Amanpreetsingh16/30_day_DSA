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
            if(curr.val<= pivotNode.val){
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


     // subtract two linked list

     public ListNode subtractLists(ListNode list1, ListNode list2){
         list1=reverse(list1);
         list2=reverse(list2);
        ListNode dummy=new ListNode(-1);
        ListNode c1=list1, c2=list2 , p=dummy;
        int borrow=0 ,diff;
        while(c1!=null || c2!=null){
             diff=borrow+(c1!=null ?c1.val : 0)-(c2!=null ? c2.val : 0);
             if(diff<0){
                diff+=10;
                borrow=-1;
             }else
             borrow=0;
             ListNode res=new ListNode(diff);
             p.next=res;
             p=p.next;
             if(c1!=null) c1= c1.next ;
             if(c2!=null) c2= c2.next ;
        }
        ListNode head=dummy.next;
        head= reverse(head);
        while(head!=null && head.val==0){        //  10000000-9999999 = 1 
            head=head.next;
        }
         list1=reverse(list1);
         list2=reverse(list2);
         return head;
     }

     // multiply two linked list 

    public void addTwoList(ListNode head, ListNode ansItr){
        ListNode c1=head;
        ListNode c2=ansItr;
        int carry=0,sum, digit;
        while(c1!=null || carry!=0){
            sum=carry+(c1!=null ? c1.val :0)+(c2.next!=null ? c2.val : 0);
            carry=carry/10;
            digit=sum%10;
            if(c2.next!=null)c2.next.val=digit;
            else{
                c2.next=new ListNode(digit);
            }
            if(c1!=null)c1=c1.next;
            c2=c2.next;
        }
    }
     public ListNode multiplyTwoDigits(ListNode head,int dig){
        ListNode dummy=new ListNode(-1);
        ListNode ac=dummy, curr=head;
        int carry=0 ,sum, digit;
        while(curr!=null || carry!=0){
          sum=carry+(curr!=null ? curr.val*dig : 0);
          carry=sum/10;
          digit=sum%10;
          ac.next=new ListNode(digit);
          ac=ac.next;
          if(curr!=null)curr=curr.next; 
        }
        return dummy.next;
     }
     public ListNode multiplyTwoLL(ListNode l1, ListNode l2) {
        l1=reverse(l1);
        l2=reverse(l2);
        ListNode dummy=new ListNode(-1);
        ListNode l2_Itr=l2;
        ListNode ansItr=dummy;
        while(l2_Itr!=null){
            ListNode prod=multiplyTwoDigits(l1, l2_Itr.val);
            l2_Itr=l2_Itr.next;
            addTwoList(prod,ansItr);
            ansItr=ansItr.next;

        }
        return reverse(dummy.next);
    }

     // Is cycle present in linked list

     public boolean isCyclePresent(ListNode head){
        if(head==null || head.next ==null){
            return false;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next!=null && fast.next.next !=null){
            fast=fast.next.next;
            slow =slow.next;
            if(fast==slow){
                return true;
            }
        }
        return false;
     }

     // cycle node of linked list

     public ListNode cycleNode(ListNode head){
        if(head==null || head.next ==null){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next!=null && fast.next.next !=null){
            fast=fast.next.next;
            slow =slow.next;
            if(fast==slow){
                break;
            }
        }
        if(fast!=slow){
            return null;
        }
        ListNode meetingNode=fast;
        slow=head;
        while(fast!=slow){
            slow=slow.next;
            fast=fast.next;
        }
        return slow ;
     }


     // extention of cycle node finding other parameter of equations

     public ListNode cycleNode2(ListNode head){
        if(head==null || head.next ==null){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast.next!=null && fast.next.next !=null){
            fast=fast.next.next;
            slow =slow.next;
            if(fast==slow){
                break;
            }
        }
        if(fast!=slow){
            return null;
        }
        ListNode meetingNode=fast;
        int a=1, b=0,c=0,bc=0,nDash=0,n=0;
        int count=0;
        slow=head;
        boolean isLoopRun=false;
        while(fast!=slow){
            slow=slow.next;
            fast=fast.next;
            if(nDash==0 && fast==meetingNode){
                bc=count;
            }
            if(fast==meetingNode){
                nDash++;
            }
            a++;
            count++;
            isLoopRun=true;
        }

        // edge case when there is no tail only cycle is present
        if(!isLoopRun){
            fast=fast.next;
            bc=1;
            while(fast!=slow){
                fast=fast.next;
                bc++;
            }
            a=0;
            b=bc;
            c=0;
            n=1;
            nDash=0;
        }else{

            n=nDash+1;
            c=a-bc*nDash;
            b=bc-c;
        }
        System.out.println("length of tail:"+a);
        System.out.println("length of b:"+b);
        System.out.println("length of c:"+c);
        System.out.println("length of tail:"+a);
        System.out.println("No. of rotations by fast pointer before meeting point:"+n);
        System.out.println("No. of rotations by fast pointer after meeting point:"+nDash);

        return slow ;
     }

     // intersection of two linked list using floyed cycle
     // leet code 160
     
     public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null){
            return null;
        }
        ListNode tail=headA;
        while(tail.next!=null){
            tail=tail.next;
        }
        tail.next=headB;
        ListNode ans=cycleNode(headA);
        tail.next=null;
        return ans;
     }
    public static void main(String[] args){

    }
}
