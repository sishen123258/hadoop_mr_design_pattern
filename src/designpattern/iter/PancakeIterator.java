package designpattern.iter;

import java.util.ArrayList;

/**
 * Created by Tong on 2016/2/23.
 */
public class PancakeIterator implements Iterator{

    private ArrayList<MenuItem> menuItems;
    private int position=0;



    public PancakeIterator(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {

        if(position<menuItems.size()){
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        MenuItem item = menuItems.get(position);
        position++;
        return item;
    }
}
