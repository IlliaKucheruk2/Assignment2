package models;

import java.time.LocalDate;

public class User {
    private String name;
    private LocalDate birthday;
    private int creditCard;

    public User(String name, LocalDate birthday, int creditCard) {
        setName(name);
        setBirthday(birthday);
        setCreditCard(creditCard);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }
}
