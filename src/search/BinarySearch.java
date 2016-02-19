package search;

/**
 * Created by Tong on 2016/2/17.
 */
public class BinarySearch {

    public static void main(String[] args) {

        BST bst=new BST<Integer,Integer>();
        bst.put(-1,2);
        bst.put(1,2);
        bst.put(1,2);
        bst.put(2,2);
        bst.put(3,2);
        System.out.println("bst.getSeize() = " + bst.getSeize());
        System.out.println("getKey "+bst.getKey(1));
        System.out.println("getKey "+bst.getKey(2));
        System.out.println("getKey "+bst.getKey(3));

        System.out.println("getMinKey "+bst.min());
    }



}

class BST<Key extends Comparable<Key>,Value>{
    private Node root;

    private class Node{

        Node left;
        Node right;
        Key key;
        Value value;
        int n;
        //left and right is changed while insert data to BST
        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }

    public int getSeize(){
        return size(root);
    }

    private int size(Node x) {
        if(x==null){
            return 0;
        }else{
            return x.n;
        }
    }


    public Value getKey(Key key){
        
        return get(root,key);
    }

    private Value get(Node x, Key key) {
        if(x==null){
            return null;
        }
        int compare = key.compareTo(x.key);
        if(compare<0)
            get(x.left,key);
        else if(compare>0){
            get(x.right,key);
        }
        return x.value;
    }

    public void put(Key key,Value value){
        root=put(root,key,value);
    }

    private Node put(Node x,Key key,Value value){
        if (x==null){
            return new Node(key,value,1);
        }
        //递归思想
        int compare = key.compareTo(x.key);
        if(compare<0){
            x.left=put(x.left,key,value);
        }else if (compare>0){
            x.right=put(x.right,key,value);
        }else{
            x.value=value;
        }
        x.n=size(x.left)+size(x.right)+1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x) {
        if(x.left==null){
            return x;
        }
        return min(x.left);
    }


}