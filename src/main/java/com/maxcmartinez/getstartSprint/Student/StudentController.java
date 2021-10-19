package com.maxcmartinez.getstartSprint.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
@CrossOrigin("*")
public class StudentController {
    private final StudentService studentService;

    //injection of dependensis into controller contructor
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent(){

        return studentService.getStudent();
    }

    @PostMapping(path = "register")
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "update/{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        System.out.println("AAAAAAAAAAAAAAAAA   "+name);
        System.out.println("MMMMMMMMMMMMMMMMM  "+email);
        studentService.updateStudent(studentId, name, email);
    }
    /*@PutMapping
    public void updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
    }*/
}
