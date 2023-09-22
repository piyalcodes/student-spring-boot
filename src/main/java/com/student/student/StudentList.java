package com.student.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentList {

    @GetMapping("/student-list")
    public String list() {
        return "student list new ";
    }
}
