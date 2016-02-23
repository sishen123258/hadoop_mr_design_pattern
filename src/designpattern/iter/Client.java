package designpattern.iter;

import java.util.ArrayList;

/**
 * Created by Tong on 2016/2/23.
 */
public class Client {

    public static void main(String[] args) {
        MenuComponent pancakeMenu=new Menu("Pancake menu","breakfast");
        MenuComponent dinnerMenu=new Menu("Dinner menu","dinner");
        MenuComponent cafeMenu=new Menu("Cafe menu","night");
        MenuComponent dessertMenu=new Menu("Dessert menu","dessert");
        MenuComponent allMenu=new Menu("All","All");

        allMenu.add(pancakeMenu);
        allMenu.add(cafeMenu);
        allMenu.add(dinnerMenu);
        dinnerMenu.add(dessertMenu);

        pancakeMenu.add(new MenuItem("A egg and rice","dinner",false,8.8));
        pancakeMenu.add(new MenuItem("A tomato and rice","dinner",false,9.8));
        pancakeMenu.add(new MenuItem("A chicken","dinner",true,58.8));

        cafeMenu.add(new MenuItem("NaTie Cafe","Good",false,29.9));
        cafeMenu.add(new MenuItem("Kabujinuo Cafe","better",false,39.9));
        cafeMenu.add(new MenuItem("Black Cafe","best",false,19.9));

        dinnerMenu.add(new MenuItem("B chicken","dinner",true,58.8));
        dinnerMenu.add(new MenuItem("B egg and rice","dinner",false,8.8));
        dinnerMenu.add(new MenuItem("B tomato and rice","dinner",false,9.8));

        dessertMenu.add(new MenuItem("Disert","dinner",false,8.8));

        Waitress waitress=new Waitress(allMenu);
        waitress.printMenu();
    }
}
