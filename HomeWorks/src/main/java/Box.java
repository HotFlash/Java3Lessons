import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Box<T extends Fruit> {
    public List<T> getList() {
        return list;
    }

    private List<T> list;

    public Box(T... obj) {
        list = Arrays.asList(obj);
    }

    public Box() {
        list = new ArrayList<T>();
    }

    void add(T obj) {
        list.add(obj);
    }
    void moveAt(Box<T> box) {
        box.getList().addAll(list);
        list.clear();
    }

    void info() {
        if (list.isEmpty()) {
            System.out.println("Box is empty");
        } else {
            System.out.println("Box contain: " + list.get(0).toString() + " in amount of: " + list.size());
        }
    }

    float getWeight() {
        if (list.isEmpty()) {
            return 0;
        } else {
            return list.size() * list.get(0).getWeight();
        }
    }

    boolean compare(Box<? extends Fruit> box) {
        return this.getWeight() == box.getWeight();
    }

}