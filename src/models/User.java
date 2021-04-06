package models;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private String name;
    private LocalDate birthday;
    private int creditCard;

    public User(String name, LocalDate birthday, int creditCard) {
        setName(name);
        setBirthday(birthday);
        setCreditCard(creditCard);
    }

    public int getAge(){
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public String getName() {
        return name;
    }
    /**
     * check if our name include this rules where
     * “^” introduce that starting character of the string
     * “[A-Za-z]” makes sure that the starting character is in the lowercase or uppercase.
     * check to make sure that its word which include underscore
     * it should be from 5 to 15 characters
     */

    public void setName(String name) {
        if(name.matches("^[A-Za-z]\\w{5,15}$")){
            this.name = name;
        }
        else
            throw new IllegalArgumentException("Your name should start with characters, contains lowercase or uppercase and should be written from 5-10 characters");

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
        if(creditCard > 111111111)
            this.creditCard = creditCard;
        else
            throw new IllegalArgumentException("Please enter your real credit card");
    }
}
