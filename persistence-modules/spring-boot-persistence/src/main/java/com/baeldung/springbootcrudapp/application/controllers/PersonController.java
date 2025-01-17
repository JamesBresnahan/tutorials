package com.baeldung.springbootcrudapp.application.controllers;

import com.baeldung.springbootcrudapp.application.entities.Person;
import com.baeldung.springbootcrudapp.application.repositories.PersonRepository;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    
    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Person> people = new ArrayList<>();
        people = personRepository.findAll();
        model.addAttribute("users", people);
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Person person) {
        return "add-user";
    }
    
    @PostMapping("/adduser")
    public String addUser(@Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        personRepository.save(person);
        model.addAttribute("users", personRepository.findAll());
        return "index";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        model.addAttribute("person", person);
        return "update-user";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "update-person";
        }
        
        personRepository.save(person);
        model.addAttribute("users", personRepository.findAll());
        return "index";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        personRepository.delete(person);
        model.addAttribute("users", personRepository.findAll());
        return "index";
    }
}
