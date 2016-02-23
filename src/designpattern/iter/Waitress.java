package designpattern.iter;

/**
 * Created by Tong on 2016/2/23.
 */
public class Waitress  {

    private DinerMenuHouse dinerMenu;
    private PancakeMenuHouse pancakeMenu;

    public void printMenu(){
        dinerMenu=new DinerMenuHouse();
        pancakeMenu=new PancakeMenuHouse();

        java.util.Iterator dinerIterator = dinerMenu.createIterator();
        java.util.Iterator pancakeMenuIterator = pancakeMenu.createIterator();

        printMenu(dinerIterator);
        printMenu(pancakeMenuIterator);
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
