package logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();
    private  final Map<Integer,User> model;

    public static Model getInstance(){
        return instance;
    }
    private Model() {
    model = new HashMap<>();
    model.put(1, new User("Alex","Alexov",555555));
    model.put(2, new User("Vova","Alexov",444444));
    model.put(3, new User("Valera","Alexov",333333));
    }
    public void add(User user, int id){
        model.put(id,user);
    }
    public Map<Integer,User> getFromList(){
        return model;
    }

    public void remove(int id) {
        model.remove(id);
    }


    public void update(int id, User updatedUser) {
        model.put(id, updatedUser);
    }
}

