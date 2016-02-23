package designpattern.iter;

/**
 * Created by Tong on 2016/2/23.
 */
public class DinerIterator implements Iterator {

    private MenuItem[] menuItems;
    private int position=0;

    public DinerIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if (position>=menuItems.length || menuItems[position]==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object next() {
        MenuItem item=menuItems[position];
        position++;
        return item;
    }
}
