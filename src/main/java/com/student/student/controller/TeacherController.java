package com.student.student.controller;

import com.student.student.exception.ResourceNotFoundException;
import com.student.student.model.Student;
import com.student.student.model.Teacher;
import com.student.student.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public Teacher createStudent(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // get by id
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher Not Found"));
        return ResponseEntity.ok(teacher);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable long id, @RequestBody Teacher teacherDto) {
        Teacher updateTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher Not Found"));

        updateTeacher.setSurname(teacherDto.getSurname());
        updateTeacher.setName(teacherDto.getName());
        teacherRepository.save(updateTeacher);
        return ResponseEntity.ok(updateTeacher);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("{id}")
    public ResponseEntity<Teacher>  deleteTeacher(@PathVariable long id) {
        Teacher deleteTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher Not Found"));

        teacherRepository.delete(deleteTeacher);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
