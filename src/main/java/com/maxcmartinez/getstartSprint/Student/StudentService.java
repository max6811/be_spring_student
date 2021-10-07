package com.maxcmartinez.getstartSprint.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                ()-> new IllegalStateException("student with id " + studentId +" does not exist")
        );
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){

            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
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
