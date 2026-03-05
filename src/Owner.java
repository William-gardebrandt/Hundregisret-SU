import java.util.*;

public class Owner {
    private static final int MAXDOGS = 7;

    private String name;
    private Dog[] dogs = new Dog[MAXDOGS];
    private int numOfDogs;

    public Owner(String name, Dog... dogs) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.toUpperCase();
        this.dogs = new Dog[MAXDOGS];
        this.numOfDogs = 0;

        for (Dog dog : dogs) {
            addDog(dog);
        }

    }

    public String getName() {
        return name;
    }

    public String toString() {
        String object = "Owner: " + name + "\nDogs: ";

        for (int i = 0; i < numOfDogs; i++) {
            object += dogs[i].getName();
        }
        return object;
    }

    public Dog[] getDogs() {
        return getSortedDogs();
    }

    public boolean addDog(Dog newDog) {
        if (newDog == null) {
            return false;
        }

        if (numOfDogs >= MAXDOGS) {
            return false;
        }
        if (ownsDog(newDog.getName())) {
            return false;
        }
        if (newDog.getOwner() != this) {
            return newDog.setOwner(this);
        }
        if (ownsDog(newDog)) {
            return false;
        }


        for (int i = 0; i < dogs.length; i++) {
            if (dogs[i] == null) {
                dogs[i] = newDog;
                numOfDogs++;
                normalizeDogs();
                return true;
            }
        }
        return false;
    }

    public boolean removeDog(Dog dog) {
        if (dog == null) return false;

        // Är hundens ägare?
        if (dog.getOwner() == this) {
            // Anropa setOwner (ta bort ägare)
            return dog.setOwner(null);
        }

        // Äger hunden? (finns den i min lista även om owner inte pekar på mig)
        for (int i = 0; i < dogs.length; i++) {
            if (dogs[i] == dog) {
                dogs[i] = null;
                numOfDogs--;
                normalizeDogs();
                return true;
            }
        }

        return false;
    }

    public boolean removeDog(String name) {
        Dog dog = findDogByName(name);
        if (dog == null) return false;
        return removeDog(dog);
    }

    public boolean ownsAnyDog() {
        if (numOfDogs > 0) {
            return true;
        }

        return false;

    }

    public boolean ownsMaxDogs() {
        if (numOfDogs == MAXDOGS) {
            return true;
        }

        return false;
    }

    public boolean ownsDog(String name) {
        if (name == null) {
            return false;
        }
        name = name.toUpperCase();
        for (int i = 0; i < dogs.length; i++) {
            if (dogs[i] != null && dogs[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    public boolean ownsDog(Dog dog) {
        if (name == null) {
            return false;
        }
        for (int i = 0; i < dogs.length; i++) {
            if (dogs[i] == dog) {
                return true;

            }
        }
        return false;
    }

    private Dog[] getSortedDogs() {
        Dog[] dogsCopy = new Dog[numOfDogs];
        System.arraycopy(dogs, 0, dogsCopy, 0, numOfDogs);

        if (numOfDogs > 1) {
            DogSorter.sort(
                    SortingAlgorithm.INSERTION_SORT,
                    Comparator.comparing(Dog::getName),
                    dogsCopy
            );
        }
        return dogsCopy;
    }


    private void normalizeDogs() {
        int pos = 0;

        for (int i = 0; i < dogs.length; i++) {
            if (dogs[i] != null) {
                dogs[pos] = dogs[i];
                pos++;
            }
        }
        for (int j = pos; j < dogs.length; j++) {
            dogs[j] = null;
        }

    }

    private Dog findDogByName(String name) {
        if (name == null) {
            return null;
        }

        for (Dog dog : dogs) {
            if (dog != null && dog.getName().equalsIgnoreCase(name)) {
                return dog;
            }
        }
        return null;
    }

}