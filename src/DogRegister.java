import java.util.ArrayList;


public class DogRegister {
    private static final String ENTER_OWNER = "Enter owner's name";
    private static final String ENTER_DOG = "Enter dog's name";
    private static final String AO = "Add owner";
    private static final String RO = "Remove Owner";
    private static final String AD = "Add dog";
    private static final String RD = "Remove dog";
    private static final String CO = "Change owner";
    private static final String LO = "List owners";
    private static final String LD = "List dogs";
    private static final String IA = "Increase age";
    private static final String EX = "Exit";
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
                case "ADD OWNER":
                    addOwner();
                    break;

                case "REMOVE OWNER":
                    removeOwner();
                    break;

                case "ADD DOG":
                    addDog();
                    break;


                case "REMOVE DOG":
                    removeDog();
                    break;


                case "CHANGE OWNER":
                    changeOwner();
                    break;


                case "LIST OWNERS":
                    listOwners();
                    break;

                case "LIST DOGS":
                    listDogs();
                    break;

                case "INCREASE AGE":
                    increaseAge();
                    break;

                case "EXIT":
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
        ArrayList<Owner> list = owners.getAllOwners();

        for (int i = 0; i < list.size(); i++) {
            Owner ownerList = list.get(i);
            System.out.print(ownerList);


        }
    }

    private void listDogs() {
        double minTailLength = input.useDouble("Enter lowest tail-length");
        boolean found = false;

        ArrayList<Dog> list = new ArrayList<>();

        for (Owner owner : owners.getAllOwners()) {
            for (Dog dog : owner.getDogs()) {
                if (dog.getTailLength() >= minTailLength) {
                    list.add(dog);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("error, no dogs with bigger tails");
            return;
        }

        list.sort(new TailNameComparator());

        for (Dog dog : list) {
            System.out.println(dog.getName() + " - " + dog.getTailLength() + "/" +
                    dog.getOwner().getName());
        }
    }

    private void increaseAge() {
        for (Owner owner : owners.getAllOwners()) {
            for (Dog dog : owner.getDogs()) {
                dog.increaseAgeOfDog();
            }

        }

    }
}
