package com.baeldung.springbootcrudapp.application.tests;

import com.baeldung.springbootcrudapp.application.controllers.PersonController;
import com.baeldung.springbootcrudapp.application.entities.Person;
import com.baeldung.springbootcrudapp.application.repositories.PersonRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public class PersonControllerUnitTest {

    private static PersonController personController;
    private static PersonRepository mockedPersonRepository;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;

    @BeforeClass
    public static void setUpUserControllerInstance() {
        mockedPersonRepository = mock(PersonRepository.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        personController = new PersonController(mockedPersonRepository);
    }

    @Test
    public void whenCalledshowSignUpForm_thenCorrect() {
        Person person = new Person("John", "john@domain.com");

        assertThat(personController.showSignUpForm(person)).isEqualTo("add-person");
    }
    
    @Test
    public void whenCalledaddUserAndValidUser_thenCorrect() {
        Person person = new Person("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(personController.addUser(person, mockedBindingResult, mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledaddUserAndInValidUser_thenCorrect() {
        Person person = new Person("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(personController.addUser(person, mockedBindingResult, mockedModel)).isEqualTo("add-person");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalledshowUpdateForm_thenIllegalArgumentException() {
        assertThat(personController.showUpdateForm(0, mockedModel)).isEqualTo("update-user");
    }
    
    @Test
    public void whenCalledupdateUserAndValidUser_thenCorrect() {
        Person person = new Person("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(personController.updateUser(1l, person, mockedBindingResult, mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledupdateUserAndInValidUser_thenCorrect() {
        Person person = new Person("John", "john@domain.com");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(personController.updateUser(1l, person, mockedBindingResult, mockedModel)).isEqualTo("update-person");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void whenCalleddeleteUser_thenIllegalArgumentException() {
        assertThat(personController.deleteUser(1l, mockedModel)).isEqualTo("index");
    }
}
