package designpattern.iter;

import java.util.ArrayList;

/**
 * Created by Tong on 2016/2/23.
 */
public class Client {

    public static void main(String[] args) {
        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(new DinerMenuHouse());
        menus.add(new PancakeMenuHouse());
        menus.add(new CafeMenuHouse());
        Waitress waitress=new Waitress(menus);
        waitress.printMenu();
    }
}
