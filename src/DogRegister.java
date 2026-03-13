import java.util.ArrayList;


public class DogRegister {
    private static final String ENTER_OWNER = "Enter owner's name";
    private static final String ENTER_DOG = "Enter dog's name";
    private static final String AO = "ADD OWNER";
    private static final String RO = "REMOVE OWNER";
    private static final String AD = "ADD DOG";
    private static final String RD = "REMOVE DOG";
    private static final String CO = "CHANGE OWNER";
    private static final String LO = "LIST OWNERS";
    private static final String LD = "LIST DOGS";
    private static final String IA = "INCREASE AGE";
    private static final String EX = "EXIT";
    private static final String MENU = AO + "\n" + RO + "\n" + AD + "\n" + RD + "\n" + CO + "\n" + LO + "\n" + LD + "\n" + IA
            + "\n" + EX + "\n";


    private OwnerCollection owners;
    private InputReader input;

    public DogRegister() {
        owners = new OwnerCollection();
        input = new InputReader();
    }

    public static void main(String[] args) {
        new DogRegister().setProgram();

    }

    private void setProgram() {
        while (true) {
            System.out.print(MENU);
            System.out.print("commando");
            String command = input.useString("");
            command = command.trim().toUpperCase();

            switch (command) {
                case AO:
                    addOwner();
                    break;
                case RO:
                    removeOwner();
                    break;
                case AD:
                    addDog();
                    break;
                case RD:
                    removeDog();
                    break;
                case CO:
                    changeOwner();
                    break;
                case LO:
                    listOwners();
                    break;
                case LD:
                    listDogs();
                    break;
                case IA:
                    increaseAge();
                    break;
                case EX:
                    System.out.print("System closed");
                    return;
                default:
                    break;
            }
        }
    }

    private void addOwner() {
        String name = input.useString(ENTER_OWNER);
        if (name == null || name.isBlank()) {
            return;
        }
        Owner owner = new Owner(name);
        owners.addOwner(owner);
    }

    private void removeOwner() {
        String ownerName = input.useString(ENTER_OWNER);
        Owner owner = owners.getOwner(ownerName);
        if (ownerName == null || ownerName.isBlank()) {
            return;
        }
        if (owner.ownsAnyDog()) {
            System.out.print("Owner still owns dogs");
            return;
        }
        owners.removeOwner(owner);
    }

    private void addDog() {
        String dogOwnerName = input.useString(ENTER_OWNER);
        Owner owner = owners.getOwner(dogOwnerName);
        if (dogOwnerName.isEmpty()) {
            return;
        }
        if (owner == null) {
            System.out.print("owner could not be found");
            return;
        }
        if (owner.ownsMaxDogs()) {
            System.out.print("error, owner has maxdogs");
            return;
        }
        String dogName = input.useString(ENTER_DOG);
        String dogBreed = input.useString("Enter dog breed");
        int dogAge = input.useInt("Enter dog age");
        int dogWeight = input.useInt("Enter dog weight");

        Dog dog = new Dog(dogName, dogBreed, dogAge, dogWeight);
        dog.setOwner(owner);
    }


    private void removeDog() {
        String ownerName = input.useString(ENTER_OWNER);
        Owner owner = owners.getOwner(ownerName);
        if (owner == null || ownerName.isBlank()) {
            return;
        }
        String dogName = input.useString(ENTER_DOG);

        if (dogName.isBlank() || dogName == null) {
            return;
        }
        owner.removeDog(dogName);
    }

    private void changeOwner() {
        if (owners.size() < 2) {
            System.out.print("error there has to be at least two owners");
            return;
        }
        String dogOwner = input.useString("Enter current dogowner's name");
        Owner owner = owners.getOwner(dogOwner);
        Dog[] dogs = owner.getDogs();
        if (owner == null || dogs == null || dogs.length == 0) {
            System.out.print("error");
            return;
        }
        String dogName = input.useString(ENTER_DOG);
        Dog dogToRemove = null;
        for (Dog dog : owner.getDogs()) {
            if (dog.getName().equalsIgnoreCase(dogName)) {
                dogToRemove = dog;
            }
        }
        if (dogToRemove == null) {
            return;
        }
        String newOwnerName = input.useString("Enter new dogowner's name");
        Owner newOwner = owners.getOwner(newOwnerName);
        dogToRemove.setOwner(newOwner);
    }

    private void listOwners() {

        if (owners.size() == 0) {
            System.out.print("error there are no owners");
            return;
        }

        for(Owner owner : owners.getAllOwners()){
            System.out.println(owner);
        }
    }

    private void listDogs() {
        double minTailLength = input.useDouble("Enter lowest tail-length");
        ArrayList<Dog> list = new ArrayList<>();
        for (Dog dog : getAllDogs()) {
            if (dog.getTailLength() >= minTailLength) {
                list.add(dog);
            }
        }
        if (list.isEmpty()) {
            System.out.println("error, no dogs with bigger tails");
            return;
        }
        list.sort(new TailNameComparator());
        for (Dog dog : list) {
            System.out.println(dog.getName() + " - " + dog.getTailLength() + "/" + dog.getOwner().getName());
        }
    }

    private ArrayList<Dog> getAllDogs() {
        ArrayList<Dog> allDogs = new ArrayList<>();
        for (Owner owner : owners.getAllOwners()) {
            for (Dog dog : owner.getDogs()) {
                allDogs.add(dog);
            }
        }
        return allDogs;
    }

    private void increaseAge() {
        for (Owner owner : owners.getAllOwners()) {
            for (Dog dog : owner.getDogs()) {
                dog.increaseAgeOfDog();
            }
        }
    }
}
