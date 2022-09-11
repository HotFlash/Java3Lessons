package HomeWork_Lesson5;


import java.util.concurrent.CountDownLatch;


public class HomeWork5 {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        CountDownLatch StartLine = new CountDownLatch(CARS_COUNT);
        CountDownLatch WaitForEnd = new CountDownLatch(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            int count = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cars[count] = new Car(race, 20 + (int) (Math.random() * 10));
                    StartLine.countDown();
                }
            }).start();
        }
        try {
            StartLine.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (int i = 0; i < cars.length; i++) {
            int count = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cars[count].run();
                    WaitForEnd.countDown();
                }
            }).start();
        }
        try {
            WaitForEnd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}


