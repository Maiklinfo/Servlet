package logic;


import java.io.Serializable;
import java.util.*;

public class PetModel implements Serializable {
    private static final PetModel instance = new PetModel();

    private final Map<Integer, Pet> model;


    public PetModel() {
        model = new HashMap<Integer, Pet>();


    }

    public static PetModel getInstance(){
        return instance;
    }

    public void add(Pet pet, int id) {
        model.put(id,pet);

    }
    public Pet getFromList(int id){
        return model.get(id);
    }

    public Map<Integer,Pet> getAll(){
        return model;
    }

    public Pet removeFromList(int id) {
        return model.remove(id);
    }

    public void update(int id, Pet updatedPet) {
        model.put(id, updatedPet);
    }
}