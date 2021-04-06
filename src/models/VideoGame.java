package models;

import java.util.ArrayList;

public class VideoGame {
    private String name;
    private int ageRating;
    private ArrayList<String> user;

    public VideoGame(String name, int ageRating, ArrayList<String> user) {
        this.name = name;
        this.ageRating = ageRating;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public ArrayList<String> getUser() {
        return user;
    }

    public void setUser(ArrayList<String> user) {
        this.user = user;
    }
}
