package designpattern.iter;

import java.util.*;
import java.util.Iterator;

/**
 * Created by Tong on 2016/2/23.
 */
public class CafeMenuHouse extends Menu{

    private Hashtable<String,MenuItem> menuItem=new Hashtable();

    public CafeMenuHouse() {

        addItem("NaTie Cafe","Good",false,29.9);
        addItem("Kabujinuo Cafe","better",false,39.9);
        addItem("Black Cafe","best",false,19.9);
    }

    private void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem item=new MenuItem(name, description, vegetarian, price);
        menuItem.put(name,item);
    }


    public Iterator createIterator() {
        return menuItem.values().iterator();
    }
}
