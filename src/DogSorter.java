import java.util.Comparator;

public class DogSorter {

    private DogSorter() {
    }

    public static void sort(
            SortingAlgorithm algorithm,
            Comparator<Dog> comparator,
            Dog... dogs) {

        if (dogs == null || dogs.length <= 1) {
            return;
        }

        switch (algorithm) {
            case BUBBLE_SORT:
                bubbleSort(dogs, comparator);
                break;
            case INSERTION_SORT:
                insertionSort(dogs, comparator);
                break;
            default:
                break;
        }
    }

    private static void bubbleSort(Dog[] dogs, Comparator<Dog> comp) {
        int length = dogs.length;
        boolean swapped;

        for (int i = 0; i < length - 1; i++) {

            swapped = false;
            for (int j = 0; j < length - 1 - i; j++) {
                if (comp.compare(dogs[j], dogs[j + 1]) > 0) {
                    Dog temp = dogs[j];

                    dogs[j] = dogs[j + 1];
                    dogs[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    private static void insertionSort(Dog[] dogs, Comparator<Dog> comp) {
        int length = dogs.length;

        for (int i = 1; i < length; i++) {
            Dog key = dogs[i];
            int j = i - 1;

            while (j >= 0 && comp.compare(dogs[j], key) > 0) {
                dogs[j + 1] = dogs[j];
                j--;
            }
            dogs[j + 1] = key;
        }
    }
}