import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        final CarShowroom showroom = new CarShowroom();

        new Thread(null, showroom::sellCar, "Покупатель 1").start();
        new Thread(null, showroom::sellCar, "Покупатель 2").start();
        new Thread(null, showroom::sellCar, "Покупатель 4").start();
        new Thread(null, showroom::sellCar, "Покупатель 5").start();
        new Thread(null, showroom::sellCar, "Покупатель 6").start();
        new Thread(null, showroom::sellCar, "Покупатель 7").start();
        new Thread(null, showroom::sellCar, "Покупатель 8").start();
        new Thread(null, showroom::sellCar, "Покупатель 9").start();
        new Thread(null, showroom::sellCar, "Покупатель 10").start();

        new Thread(null, showroom::receiveCar, "Mazda").start();
    }
}
