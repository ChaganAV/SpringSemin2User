package com.example.model;

public class User {
    private int id; // идентификатор
    private String firstName; // имя
    private String lastName; // фамилия

    /**
     * Конструктор пользователя
     * @param firstName имя
     * @param lastName фамилия
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User(){

    }

    /**
     * Переопределим проверку на сравнение
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id && getFirstName().equals(user.firstName) && getLastName().equals(user.lastName);
    }

    // region getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // end
}
