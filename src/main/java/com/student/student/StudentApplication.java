package com.student.student;

import com.student.student.model.Student;
import com.student.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {

		Student student = new Student();
		student.setName("John");
		student.setSurname("Ven Dan");
		studentRepository.save(student);


		Student student2 = new Student();
		student2.setName("Oshi");
		student2.setSurname("Mubarak");
		studentRepository.save(student2);
	}
}
