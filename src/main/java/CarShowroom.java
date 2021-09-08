import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarShowroom {
    private static final int THREAD_TIMEOUT = 2500;

    private List<Car> cars = new ArrayList<>(10);

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public void receiveCar() {
        while (!Thread.currentThread().isInterrupted() && cars.size() < 10) {
            try {
                lock.lock();
                Thread.sleep(THREAD_TIMEOUT);
                cars.add(new Car());
                System.out.printf("Производитель выпустил новое авто марки %s\n", Thread.currentThread().getName());
                condition.signal();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            } finally {
                lock.unlock();
            }
        }
    }

    public void sellCar() {
        try {
            lock.lock();
            System.out.printf("%s зашел в магазин\n", Thread.currentThread().getName());
            while (cars.size() == 0) {
                System.out.println("Машин нет");
                condition.await();
            }
            Thread.sleep(THREAD_TIMEOUT);
            System.out.printf("%s покупатель уехал новеньком авто\n", Thread.currentThread().getName());
            cars.remove(0);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        } finally {
            lock.unlock();
        }
    }
}