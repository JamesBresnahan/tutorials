package com.baeldung.springbootcrudapp.application.tests;

import com.baeldung.springbootcrudapp.application.entities.Person;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class PersonUnitTest {
    
    @Test
    public void whenCalledGetName_thenCorrect() {
        Person person = new Person("Julie", "julie@domain.com");
        
        assertThat(person.getName()).isEqualTo("Julie");
    }
    
    @Test
    public void whenCalledGetEmail_thenCorrect() {
        Person person = new Person("Julie", "julie@domain.com");
        
        assertThat(person.getEmail()).isEqualTo("julie@domain.com");
    }
    
    @Test
    public void whenCalledSetName_thenCorrect() {
        Person person = new Person("Julie", "julie@domain.com");
        
        person.setName("John");
        
        assertThat(person.getName()).isEqualTo("John");
    }
    
    @Test
    public void whenCalledSetEmail_thenCorrect() {
        Person person = new Person("Julie", "julie@domain.com");
        
        person.setEmail("john@domain.com");
        
        assertThat(person.getEmail()).isEqualTo("john@domain.com");
    }
    
    @Test
    public void whenCalledtoString_thenCorrect() {
        Person person = new Person("Julie", "julie@domain.com");
        assertThat(person.toString()).isEqualTo("Person{id=0, name=Julie, email=julie@domain.com}");
    }
}
