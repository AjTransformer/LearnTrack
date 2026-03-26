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
    public void displayInfo(){
        System.out.println("Student details");
        System.out.println("Student First Name: "+getFirstName());
        System.out.println("Student Last Name: "+getLastName());
        System.out.println("Student Email ID: "+getEmail());
        System.out.println("Student Batch Name: " + getBatchName());
        System.out.println("Active: "+getActiveStatus());
    }

}
