import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
// 1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа).

        String[] strArr = {"one", "two", "three", "four"};
        Integer[] intArr = {1, 2, 3, 4};
        Double[] doubArr = {1.1, 1.2, 1.3, 1.4};

        testTask1(strArr, 0, 3);
        testTask1(intArr, 2, 3);
        testTask1(doubArr, 0, 2);

        //2. Написать метод, который преобразует массив в ArrayList;
        convertToList(doubArr);

        //3.Большая задача: Фрукты
        System.out.println("Perform task 3 \n");
        Orange orange = new Orange();
        Apple apple = new Apple();
        Box<Orange> boxOrange1 = new Box<>();
        Box<Orange> boxOrange2 = new Box<>();
        Box<Apple> boxApple = new Box<>();

        for (int i = 0; i < 3; i++) {
            boxOrange1.add(new Orange());
        }

        for (int i = 0; i < 4; i++) {
            boxOrange2.add(new Orange());
        }
        for (int i = 0; i < 6; i++) {
            boxApple.add(new Apple());
        }

        boxOrange1.info();
        boxOrange2.info();
        boxApple.info();

        float weigthBoxOrange1 = boxOrange1.getWeight();
        float WeigthBoxOrange2 = boxOrange2.getWeight();
        float WeigthBoxApple = boxApple.getWeight();
        System.out.println("Weights: \n ");
        System.out.println("Orange Box 1 weight: " + weigthBoxOrange1);
        System.out.println("Orange Box 2 weight: " + WeigthBoxOrange2);
        System.out.println("Apple Box  weight: " + WeigthBoxApple);
        System.out.println("Compartment: \n ");
        System.out.println("Compare boxOrange1 VS boxApple: " + boxOrange1.compare(boxApple));
        System.out.println("Compare boxOrange2 VS boxApple: " + boxOrange2.compare(boxApple));
        System.out.println("Compare boxOrange1 VS boxOrange2: " + boxOrange1.compare(boxOrange2));
        System.out.println("Move box to box: \n ");
        boxOrange1.moveAt(boxOrange2);
        System.out.println("Final Info: \n ");
        boxOrange1.info();
        boxOrange2.info();
        boxApple.info();

    }

    private static <T> void swapElements(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static <E> void testTask1(E[] arrToTest, int position1, int position2) {
        System.out.println("Original Array:\n" + Arrays.toString(arrToTest));
        swapElements(arrToTest, position1, position2);
        System.out.println("Changed Array:\n" + Arrays.toString(arrToTest));
    }

    private static <F> void convertToList(F[] array) {
        List<F> list = Arrays.asList(array);
        System.out.println("Original Array:\n" + array.getClass() + " : " + Arrays.toString(array) +
                "\nModified Array: \n " + list.getClass() + " : " + list);
    }

    private static void addFruitsToBox (int qtyToBox1, int qtyToBox2, int qtyToBox3) {

    }
}

