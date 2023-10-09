package referencecounter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // first 
        Sales coffeeShop = Sales.getInstance();
        coffeeShop.setSells("Spresso", 20);
        coffeeShop.setSells("Spresso", 15);
        coffeeShop.setSells("Spresso", 10);
        coffeeShop.setSells("Spresso", 3);
        coffeeShop.setSells("capocinu", 7);
    
        int spressoSales = coffeeShop.sellsOf("Spresso");
        System.out.println(spressoSales);
        System.out.println(coffeeShop.getCount());
        
        // Secound
        Sales coffeeShop2 = Sales.getInstance();
        int spressoSales2 = coffeeShop2.sellsOf("Spresso");
        System.out.println(spressoSales2); // same result
        System.out.println(coffeeShop2.getCount());
        coffeeShop.removeReference();
        coffeeShop.removeReference();
        Thread.sleep(2000);
        coffeeShop2.gc();
        System.out.println(coffeeShop.getCount());

    }
    
}
