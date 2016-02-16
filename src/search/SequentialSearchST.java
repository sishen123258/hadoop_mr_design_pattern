package search;

/**
 * Created by root on 2/17/16.
 */
public class SequentialSearchST<Key,Value> {

    private Node first;

    private class Node{
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }


    }

    public Value get(Key key) {

        for (Node x = first; x!=null ; x=x.next){
            if(x.key.equals(key)){
                return x.value;
            }
        }

        return null;
    }

    public void put(Key key,Value value){

        for(Node x=first;x!=null;x=x.next){
            if (x.key.equals(key)){
                x.value=value;
                return;
            }
        }
        first=new Node(key,value,first);

    }




}
