package designpattern.iter;

import java.util.*;

/**
 * Created by Tong on 2016/2/23.
 */
public class Waitress  {

    private ArrayList<Menu> menus;

    public Waitress(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public void printMenu(){

        for (Menu menu: menus) {
            java.util.Iterator iterator = menu.createIterator();
            printMenu(iterator);
        }

        /*调用了三次，违反了开闭原则
        dinerMenu=new DinerMenuHouse();
        pancakeMenu=new PancakeMenuHouse();
        cafeMenu=new CafeMenuHouse();

        java.util.Iterator dinerIterator = dinerMenu.createIterator();
        java.util.Iterator pancakeMenuIterator = pancakeMenu.createIterator();
        java.util.Iterator cafeMenuIterator = cafeMenu.createIterator();


        printMenu(dinerIterator);
        printMenu(pancakeMenuIterator);
        printMenu(cafeMenuIterator);*/
    }

    private void printMenu(java.util.Iterator iterator){
        while(iterator.hasNext()){
            MenuItem item=(MenuItem)iterator.next();
            System.out.println("itemName = " + item.getName());
            System.out.println("itemDescription = " + item.getDescription());
            System.out.println("vegetarian = " + item.isVegetarian());
            System.out.println("private = " + item.getPrice());
        }
    }




}
