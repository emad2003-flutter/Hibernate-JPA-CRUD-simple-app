package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner (StudentDAO studentDAO){
		return runner -> {
			// createStudent(studentDAO);
            // createMultipleStudent(studentDAO);
			// readStudent(studentDAO);
			// queryForStudents(studentDAO);
			// queryForStudentsByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			deleteALLStudents(studentDAO);


		};
	}

	private void deleteALLStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students...");
		int numOfDeletedStudents = studentDAO.deleteAll();
		System.out.println("The number of deleted students: " + numOfDeletedStudents);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		studentDAO.delete(studentId);
		System.out.println("Deleted student with id: " + studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id: primary key
		int studentID = 1;
		System.out.println("Getting student with id: " + studentID);
		Student myStudent = studentDAO.findById(studentID);

		// change first name to "Hommmosa"
		System.out.println("Updating student with id: " + studentID);
		myStudent.setLastName("Hommmosa");

		// update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println(myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO)
	{
		// get a list of student
		List<Student> theStudent = studentDAO.findStudentByLastName("Mahgoub");

		// display list of student
		for(Student student : theStudent)
		{
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// Get a list of students
		List<Student> theStudnts = studentDAO.findAll();

		// Display list of students
		for (Student student : theStudnts) {
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO)
	{
		// Create a student object
		System.out.println("Creating a student object...");
		Student tempStudent = new Student("Samii", "Nammi","samii@ex.com");

		// Save the student
		System.out.println("Savin a student object...");
		studentDAO.save(tempStudent);

		// Display id of the save student
		System.out.println("Display id of the save student...");
		int studentId = tempStudent.getId();
		System.out.println(studentId);

		// Retrieve student based on the id: primary key
		System.out.println("Display the student with id: " + studentId);
		Student myStudent =  studentDAO.findById(studentId);

		// Display student
		System.out.println("Student data: \nFirst name: " + myStudent.getFirstName()
				+ "\n" + "Last name: " + myStudent.getLastName() + "\n"
				+ "Email: " + myStudent.getEmail());
	}
	private void createMultipleStudent(StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating Multiple Student..");
		Student tempStudent1 = new  Student("Emad", "Mahgoub", "emad@example.com");
		Student tempStudent2 = new  Student("Ahmed", "Mohammed", "ahmed@example.com");
		Student tempStudent3= new  Student("Sami", "Nami", "sami@example.com");

		// save the student object
		System.out.println("Created Multiple Student..");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}
	private void createStudent(StudentDAO studentDAO){

		// create the student object
		System.out.println("Creating new Student object ...");
		Student tempStudent = new Student("Emad", "Mahgoub", "emad@example.com");

		// save the student object
		System.out.println("Saving Student object ...");
		studentDAO.save(tempStudent);

		// display id of the saved object
		System.out.println("Display Student ID object ...");
		System.out.println(tempStudent.getId());
	}

}
