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
        
        // Thread t1 = new Thread(() -> {
        //     coffeeShop.addReference();
        //     System.out.println(coffeeShop.getCount());
        //     try {
        //         Thread.sleep(3000);
        //         coffeeShop.setSells("caramel", 500);
        //         System.out.println(coffeeShop.getSells());
        //         System.out.println(coffeeShop.sellsOf("caramel")+" T1");
        //         } catch (InterruptedException e) {
        //                 e.printStackTrace();
        //             }
        //         });
                
        //         Thread t2 = new Thread(() -> {
        //             coffeeShop.addReference();
        //             System.out.println(coffeeShop.getCount());
        //             try {
        //                 Thread.sleep(2000);
        //                 coffeeShop.setSells("caramel", 50);
        //                 System.out.println(coffeeShop.getSells());
        //                 System.out.println(coffeeShop.sellsOf("caramel")+" T2");
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // });
        // create new runable
        Runnable task = () -> {
            coffeeShop.addReference();
            System.out.println(coffeeShop.getCount());
            synchronized (coffeeShop){
            try {
                Thread.sleep(3000);
                coffeeShop.setSells("caramel", 500);
                System.out.println(coffeeShop.getSells());
                System.out.println(coffeeShop.sellsOf("caramel")+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };};
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(() -> {
            coffeeShop.addReference();
            System.out.println(coffeeShop.getCount());
            synchronized (coffeeShop){
            try {
                Thread.sleep(1000);
                coffeeShop.setSells("caramel", -200);
                System.out.println(coffeeShop.getSells());
                System.out.println(coffeeShop.sellsOf("caramel")+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };});


        t1.start();
        t2.start();
        t3.start();
        // t1.join();
        // t2.join();
    }
    
}
