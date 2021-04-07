package models;

import java.util.ArrayList;

public class VideoGame {
    private String name;
    private int ageRating;
    private ArrayList<User> users;

    public VideoGame(String name, int ageRating) {
        this.name = name;
        this.ageRating = ageRating;
        users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.matches("[A-Z][a-z]*"))
            this.name = name;
        else
            throw new IllegalArgumentException("Game can not contain specific characters");
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        if (ageRating<=10 && ageRating>=0)
            this.ageRating = ageRating;
        else
            throw new IllegalArgumentException("Rating must be 0 to 10");
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUser(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if (!userExist(user))
            users.add(user);
    }

    public void removeUser(User user) {
        if (userExist(user))
            users.remove(user);
    }

    private boolean userExist(User user) {
        boolean exist = false;

        for(User usr: users) {
            if(usr.equals(user)) {
                exist = true;
                break;
            }
        }
        return  exist;
    }

}
