package designpattern.iter;

/**
 * Created by Tong on 2016/2/23.
 */
public class DinerMenu {

    static final int MAX_ITEMS=6;
    private int numberOfItem=0;
    private MenuItem [] menuItems;

    public DinerMenu() {
        this.menuItems = new MenuItem[6];
        addMenuItem("B chicken","dinner",true,58.8);
        addMenuItem("B egg and rice","dinner",false,8.8);
        addMenuItem("B tomato and rice","dinner",false,9.8);
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public MenuItem getMenuItem() {
        return this.menuItems[numberOfItem];
    }

    public void addMenuItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem=new MenuItem(name, description, vegetarian, price);
        if(numberOfItem>MAX_ITEMS){
            System.out.println("Sorry, you can not add item.");
            return;
        }
        menuItems[numberOfItem]=menuItem;
        numberOfItem++;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public void setNumberOfItem(int numberOfItem) {
        this.numberOfItem = numberOfItem;
    }
}
