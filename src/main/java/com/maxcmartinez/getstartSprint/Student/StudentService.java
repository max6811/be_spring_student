package com.maxcmartinez.getstartSprint.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        //studentRepository.findById(studentId);
        Boolean exist = studentRepository.existsById(studentId);
        if (!exist){
            throw new IllegalStateException("student with id " + studentId +" does not exist");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        System.out.println("NAME NAME NAME "+name);
        System.out.println("EMAIL EMAIL EMAIL "+email);
        Student student = studentRepository.findById(studentId).orElseThrow(
                ()-> new IllegalStateException("student with id " + studentId +" does not exist")
        );
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            System.out.println("LLLLLLLLLLLLLLLLL"+student);
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){

            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            System.out.println("OOOOOOOOOOOOOOOOO"+student);
            student.setEmail(email);
        }
        System.out.println("EEEEEEEEEEstudent"+student);
        //studentRepository.save(student);
    }

    /*@Transactional
    public void updateStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if(studentOptional.isPresent()){
            studentOptional.get().setName(student.getName());
            studentOptional.get().setEmail(student.getEmail());
            studentRepository.save(studentOptional.get());
        }else{
            throw new IllegalStateException("Student does not exist");
        }
    }*/
}
