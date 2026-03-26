package entity;

public abstract class Person {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String batch;
    protected boolean active;

    Person(int id , String firstName, String lastName, String email, String batch , boolean active){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.batch = batch;
        this.active = active;
    }

    Person(int id , String firstName, String lastName, String batch , boolean active){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.batch = batch;
        this.active = active;
    }

    public abstract void displayInfo();
}
