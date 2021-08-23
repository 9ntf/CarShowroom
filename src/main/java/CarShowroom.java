import java.util.ArrayList;
import java.util.List;

public class CarShowroom {
    public static final int THREAD_TIMEOUT = 2500;

    List<Car> cars = new ArrayList<>(10);

    public synchronized void receiveCar() {
        while (!Thread.currentThread().isInterrupted() && cars.size() < 10) {
            try {
                Thread.sleep(THREAD_TIMEOUT);
                cars.add(new Car());
                System.out.printf("Производитель выпустил новое авто марки %s\n", Thread.currentThread().getName());
                notify();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public synchronized void sellCar() {
        try {
            System.out.printf("%s зашел в магазин\n", Thread.currentThread().getName());
            while (cars.size() == 0) {
                System.out.println("Машин нет");
                wait();
            }
            Thread.sleep(THREAD_TIMEOUT);
            System.out.printf("%s покупатель уехал новеньком авто\n", Thread.currentThread().getName());
            cars.remove(0);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}