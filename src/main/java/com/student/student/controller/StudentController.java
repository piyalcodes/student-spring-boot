package com.student.student.controller;

import com.student.student.exception.ResourceNotFoundException;
import com.student.student.model.Student;
import com.student.student.repository.StudentRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    // Create student

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // get by id
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Not Found"));
        return ResponseEntity.ok(student);
    }


    // update students

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student studentDto) {
        Student updateStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Not Found"));

        updateStudent.setSurname(studentDto.getSurname());
        updateStudent.setName(studentDto.getName());
        studentRepository.save(updateStudent);
        return ResponseEntity.ok(updateStudent);

    }


    // delete student
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("{id}")
    public ResponseEntity<Student>  deleteStudent(@PathVariable long id) {
        Student deleteStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Not Found"));

        studentRepository.delete(deleteStudent);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
