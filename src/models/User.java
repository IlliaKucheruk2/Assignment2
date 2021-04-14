package models;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private String name;
    private LocalDate birthday;
    private int creditCard;
    private int id;

    public User(String name, LocalDate birthday, int creditCard, int userId) {
        setName(name);
        setBirthday(birthday);
        setCreditCard(creditCard);
        setId(userId);
    }

    public int getAge(){
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    private void setId(int value) {
        id = value;
    }

    /**
     * “^” introduce that starting character of the string
     * “[A-Za-z]” makes sure that the starting character is in the lowercase or uppercase.
     * check to make sure that its word which include underscore
     * it should be from 5 to 15 characters
     */
    public void setName(String name) {
        if(name.matches("[A-Za-z]\\w{5,15}$")){
            this.name = name;
        }
        else
            throw new IllegalArgumentException("Your name should start with characters, contains underscore, should be written from 5-15 characters");

    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        if (birthday.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Please enter your real birthday");
        this.birthday = birthday;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        if(creditCard > 100000000)
            this.creditCard = creditCard;
        else
            throw new IllegalArgumentException("Please enter your real credit card number with 9 digits");
    }

    public String toString(){
        return String.format("%s Age: %d Card: %d",name, getAge(), creditCard);
    }
}
