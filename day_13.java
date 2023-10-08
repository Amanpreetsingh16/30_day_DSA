import java.util.HashMap;
    /*
     * LRU CACHE
     */
public class day_13 {

    class LRUCache {
        private class Node {
            int key, value;
            Node next, prev;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.next = null;
                this.prev = null;
            }

        }

        private HashMap<Integer, Node> map = new HashMap<>();
        private Node head = null;
        private Node tail = null;
        private int capacity = 0;
        private int size = 0;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.head = null;
            this.tail = null;

        }

        private void removeNode(Node node){
            if(this.size==1){
                this.tail=this.head=null;
            }else{
                Node prev=node.prev;
                 Node fwd=node.next;
                if(prev==null){
                   this.head=fwd;
                    fwd.prev=null;
                }else if(fwd==null){
                    this.tail=prev;
                    prev.next=null;
                }else{
                    fwd.prev=prev;
                    prev.next=fwd;
                }
                node.next=node.prev=null;
            }

            this.size--;
        }

        private void addLast(Node node){
            if(this.size==0){
                this.tail=this.head=node;
            }else{
                this.tail.next=node;
                node.prev=this.tail;
                this.tail=node;
            }
            this.size++;
        }

        private void makeRecent(Node node){
            if(this.tail==node){
                return;
            }
                removeNode(node);
                addLast(node);
        }
        // key =app, output->state
        public int get(int key) {
            if(!map.containsKey(key)){

                return -1;
            }
                Node node=map.get(key);
                makeRecent(node);
           return node.value;
        }

        // key=app, value=state
        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value=value;
                makeRecent(node);
            }else{
                if(this.size==this.capacity){
                    int rKey=this.head.key;
                    removeNode(this.head);
                    map.remove(rKey);

                }
                Node node=new Node(key,value);
                addLast(node);
                map.put(key,node);
            }
        }
    }

    public static void main(String[] args) {

    }
}
