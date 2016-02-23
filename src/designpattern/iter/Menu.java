package designpattern.iter;

import java.util.ArrayList;

/**
 * Created by Tong on 2016/2/23.
 */
public class Menu extends MenuComponent {

    private String name;
    private String description;
    private ArrayList<MenuComponent> menuComponents = new ArrayList<>();

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return (MenuComponent) menuComponents.get(i);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void print() {
        System.out.println("Menu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}');
    }
}
