package com.luv2code.cruddemo.dao;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAoImpl implements StudentDAO{

    // define field for entity manager
    private EntityManager entityManager;


    // inject entity manager using constructor injection
    @Autowired
    public  StudentDAoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // Create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student order by firstName desc", Student.class);

        // Return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findStudentByLastName(String theLastName) {
        // Create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student where lastName =:theData", Student.class);

        // Set query parameters
        theQuery.setParameter("theData", theLastName);

        // Return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // retrieve the student
        Student theStudent = entityManager.find(Student.class, id);

        // delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numOfRowsDeleted = entityManager.createQuery("delete from Student").executeUpdate();
        return  numOfRowsDeleted;
    }
}
