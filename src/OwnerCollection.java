import java.util.ArrayList;
import java.util.Comparator;

public class OwnerCollection {
    private ArrayList<Owner> ownerList;

    public OwnerCollection() {
        this.ownerList = new ArrayList<>();
    }

    public boolean addOwner(Owner owner) {
        if (owner == null || containsOwner(owner.getName())) {
            return false;
        }
        ownerList.add(owner);
        return true;
    }

    public boolean removeOwner(String name) {
        if (name == null) {
            return false;
        }
        for (int i = 0; i < ownerList.size(); i++) {
            if (ownerList.get(i).getName().equalsIgnoreCase(name)) {
                ownerList.remove(i);
                return true;

            }
        }
        return false;

    }

    public boolean removeOwner(Owner owner) {
        return removeOwner(owner.getName());
    }

    public boolean containsOwner(String name) {
        if (name == null) {
            return false;
        }

        for (int i = 0; i < ownerList.size(); i++) {
            if (ownerList.get(i).getName().equalsIgnoreCase(name)) {
                return true;


            }
        }
        return false;
    }

    public boolean containsOwner(Owner owner) {
        return containsOwner(owner.getName());
    }

    public Owner getOwner(String name) {
        for (int i = 0; i < ownerList.size(); i++) {
            if (ownerList.get(i).getName().equalsIgnoreCase(name)) {
                return ownerList.get(i);
            }
        }
        return null;
    }


    public ArrayList<Owner> getAllOwners() {
        ArrayList<Owner> OwnerCopyList = new ArrayList<>();
        for (int i = 0; i < ownerList.size(); i++) {
            OwnerCopyList.add(ownerList.get(i));

        }
        OwnerCopyList.sort(Comparator.comparing(Owner::getName));
        return OwnerCopyList;
    }

    public int size() {
        return ownerList.size();
    }

}
