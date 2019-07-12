package com.baeldung.springbootcrudapp.application.repositories;

import com.baeldung.springbootcrudapp.application.entities.Person;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface PersonRepository extends CrudRepository<Person, Long> {
    
    List<Person> findByName(String name);
    
}
