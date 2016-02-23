package designpattern.iter;

import java.util.ArrayList;

/**
 * Created by Tong on 2016/2/23.
 */
public class PancakeMenuHouse implements Menu{

    //他是自动扩容的
    private ArrayList<MenuItem> menuItems;

    public PancakeMenuHouse() {
        menuItems=new ArrayList<>();

        addMenuItem("A chicken","dinner",true,58.8);
        addMenuItem("A egg and rice","dinner",false,8.8);
        addMenuItem("A tomato and rice","dinner",false,9.8);
    }

    /*同样这也会暴露我们的内部实现
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }*/

    public java.util.Iterator createIterator(){
        return menuItems.iterator();
    }

    public void addMenuItem(String name, String description, boolean vegetarian, double price) {
        MenuItem item=new MenuItem(name, description, vegetarian, price);
        this.menuItems.add(item);
    }
}
