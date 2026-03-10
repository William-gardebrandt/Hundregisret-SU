import java.util.Comparator;

public class TailNameComparator implements Comparator<Dog> {

    public int compare(Dog a, Dog b) {

        if (a.getTailLength() < b.getTailLength()) {
            return -1;
        }
        if (a.getTailLength() > b.getTailLength()) {
            return 1;
        }
        return a.getName().compareToIgnoreCase(b.getName());
    }

}
