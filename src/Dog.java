import java.util.*;

public class Dog {
    private static final double DACHSHUND_TAIL_LENGTH = 3.7;
    private static final ArrayList<String> DACHSHUND_LIST = new ArrayList<>(List.of("TAX", "DACHSHUND", "MÄYRÄKOIRA", "TECKEL"));

    private String name;
    private String breed;
    private int age;
    private int weight;
    private Owner owner;


    public Dog(String name, String breed, int age, int weight) {
        this.name = name.toUpperCase();
        this.breed = breed.toUpperCase();
        this.age = age;
        this.weight = weight;
    }

    public Dog(String name, String breed, int age, int weight, Owner owner) {
        this(name, breed, age, weight);
        this.owner = owner;
    }


    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean setOwner(Owner newOwner) {
        if (this.owner == newOwner) {
            return false;
        }
        if (newOwner == null) {

            if (this.owner == null) {
                return false;
            }
            Owner oldOwner = this.owner;
            this.owner = null;
            oldOwner.removeDog(this);
            return true;
        }
        if (newOwner.ownsMaxDogs()) {
            return false;
        }
        if (this.owner != null) {
            if (this.owner == newOwner && !newOwner.ownsDog(this)) {
                newOwner.addDog(this);
                return true;

            }
            Owner oldOwner = this.owner;
            this.owner = null;
            oldOwner.removeDog(this);
        }

        this.owner = newOwner;

        if (!newOwner.ownsDog(this)) {
            newOwner.addDog(this);
        }
        return true;

    }

    public double getTailLength() {

        if (DACHSHUND_LIST.contains(breed)) {
            return DACHSHUND_TAIL_LENGTH;
        } else {
            return (double) (age * weight) / 10;
        }
    }

    public void increaseAgeOfDog() {
        if (age < Integer.MAX_VALUE) {
            age++;
        }

    }

    public String toString() {
        return
                "Dog" + "\n-------" + "\nName: " +
                        name + "\nBreed: " +
                        breed + "\nAge: " +
                        age + "\nWeight: " +
                        weight + "\nTaillength: " + getTailLength() + "\n" +
                        owner;
    }
}