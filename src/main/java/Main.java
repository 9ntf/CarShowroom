

public class Main {
    public static void main(String[] args) {
        final CarShowroom showroom = new CarShowroom();

        for (int i = 0; i < 10; i++) {
            new Thread(null, showroom::sellCar, "Покупатель " + i).start();
        }

        new Thread(null, showroom::receiveCar, "Mazda").start();
    }
}