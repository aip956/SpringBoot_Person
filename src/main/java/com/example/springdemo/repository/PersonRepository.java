package com.example.springdemo.repository;
import com.example.springdemo.model.Person;



import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
