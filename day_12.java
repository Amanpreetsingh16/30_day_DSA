import org.w3c.dom.Node;

public class day_12 {
     

    /*
     * Reverse in k groups leet code 25
     * Reverse in Range leetcode 92
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
     // reverse in k groups
      ListNode th=null, tt=null;
     public void addFirst(ListNode head){
        if(th==null){
            th=head;
            tt=head;  
        }else{
            head.next=th;
            th=head;
        }
     }
     public int length(ListNode head){
        int len=0;
        ListNode curr=head;
        while(curr!=null){
             len++;
             curr=curr.next;
        }
        return len;

     }

     public ListNode revreseInKGroup(ListNode head, int k){
        if(head.next==null || k==1){
            return head;
        }
        int len=length(head);
        ListNode curr=head,ph=null,pt=null;
        while(curr!=null && k<=len){
            int itr=k;
            while(itr-->0){
                ListNode fwd=curr.next;
                curr.next=null;
                addFirst(curr);
                curr=fwd;
            }
            if(ph==null){
                ph=th;
                pt=tt;
            }else{
                pt.next=th;
                pt=tt;
            }
            len-=k;
            th=tt=null;
        }
        pt.next=curr;
        return ph;
     }

     // Reverse in range 
     // leet code 92
     ListNode th=null;
     ListNode tt=null;
     public void addFirst(ListNode node){
         if(th==null){
             th=tt=node;
         }else{
             node.next=th;
             th=node;
         }
     } 
     public ListNode reverseBetween(ListNode head, int left, int right) {
         if(head.next==null || left==right){
             return head;
         }
 
         ListNode curr=head, prev=null;
         int itr=1;
         while(curr!=null){
         while(itr>=left && itr<=right){
             ListNode fwd=curr.next;
             curr.next=null;
             addFirst(curr);
             curr=fwd;
             itr++;
         }
         if(itr>right){
         if(prev==null){
             tt.next=curr;
             return th;
         }else{
             prev.next=th;
             tt.next=curr;
             return head;
          }
         }
         prev=curr;
         curr=curr.next;
         itr++;
       }
 
       return null;
     }


     // Remove duplicate from sorted list
     public ListNode revmoveDuplicate(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode curr=head.next;
        ListNode prev=head;
        while(curr!=null){
            while(curr!=null && prev.val==curr.val){
                ListNode fwd=curr.next;
                curr.next=null;
                curr=fwd;
            }
            prev.next=curr;
            prev=prev.next;
            if(curr!=null){
                curr=curr.next;
            }
        }
        return head;
     }

     // REmove all duplicates from  sorted list (i.e 11122334566778 ==> 458)
     public ListNode revmoveAllDuplicate(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode dummy=new ListNode(-1);
        ListNode curr=head.next;
        ListNode prev=dummy;
        prev.next=head;
        while(curr!=null){
           boolean isLoopRun=false;
            while(curr!=null && prev.next.val==curr.val){
                ListNode fwd=curr.next;
                curr.next=null;
                curr=fwd;
                isLoopRun=true;
            }
            if(isLoopRun){
                prev.next=curr;
            }else{
                prev=prev.next;
                prev.next=curr;
            }
            if(curr!=null){
                curr=curr.next;
            }
        }
        return dummy.next;
     }

     // deep copy a list with random pointer
     // leetcode 138
     class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
     public void copyList(Node head){
        Node curr=head;
        while(curr!=null){
            Node fwd=curr.next;
            Node newNode=new Node(curr.val);
            curr.next=newNode;
            newNode.next=fwd;
            curr=fwd;
        }

    }
    public void copyRandom(Node head){
        Node curr=head;
        while(curr!=null){
            if(curr.random!=null){
                curr.next.random=curr.random.next;
            }
            curr=curr.next.next;
        }
    }

    public Node extractList(Node head){
        Node dummy=new Node(-1);
        Node prev=dummy; 
        Node curr=head;
        while(curr!=null){
         prev.next=curr.next;
         prev=prev.next;
         curr.next=curr.next.next;
         curr=curr.next;
        }
        return dummy.next;
    }
    public Node copyRandomList(Node head) {
        copyList(head);
        copyRandom(head);
        return extractList(head);
    }
    public static void main(String[] args){
        
    }
}    

