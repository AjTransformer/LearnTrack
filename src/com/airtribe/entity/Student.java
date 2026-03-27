package entity;

public class Student extends Person{

    public Student(int id , String firstName, String lastName, String email, String batch , boolean active){
        super(id,firstName,lastName,email,batch,active);
    }

    public Student(int id , String firstName, String lastName, String batch , boolean active){
        super(id,firstName,lastName,batch,active);
    }

    public int getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getBatchName(){
        return batch;
    }

    public boolean getActiveStatus(){
        return active;
    }

    public void setActiveStatus(boolean active){
        this.active = active;
    }

    @Override
    public void displayInfo() {
        System.out.println("\n===== Student Information =====");
        System.out.printf("ID           : %s%n", getId());
        System.out.printf("First Name   : %s%n", getFirstName());
        System.out.printf("Last Name    : %s%n", getLastName());
        System.out.printf("Email ID     : %s%n", getEmail());
        System.out.printf("Batch Name   : %s%n", getBatchName());
        System.out.printf("Status       : %s%n", getActiveStatus() ? "Active" : "Inactive");
        System.out.println("===============================\n");
    }

    public void displayNameAndID(){
        System.out.println("\n===== Student Information =====");
        System.out.printf("ID           : %s%n", getId());
        System.out.printf("First Name   : %s%n", getFirstName());
        System.out.printf("Last Name    : %s%n", getLastName());
        System.out.println("===============================\n");
    }

}
