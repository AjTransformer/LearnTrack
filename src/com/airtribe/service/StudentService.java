package service;

import entity.Student;
import repository.StudentRepository;
import util.IdGenerator;


public class StudentService {
    static StudentRepository studentRepository;

    public StudentService(){
        studentRepository = studentRepository.getInstance();
    }



    public boolean addStudent(String firstName , String lastName, String email, String batch){
        int id = IdGenerator.getNextStudentId();
        Student s;
        if(email.isBlank()){
            s= new Student(id,firstName,lastName,batch,true);
        }else{
            s= new Student(id,firstName,lastName,email,batch,true);
        }
        return studentRepository.addStudentToList(s);
    }

    public static Student findStudentById(int id){
        return studentRepository.findStudentById(id);
    }

    public static void viewAll(){
        studentRepository.viewAll();
    }

    public void setActive(int id,boolean status){
        Student s = findStudentById(id);
        if(s!=null){
            studentRepository.setActive(s,status);
        }
    }
}
