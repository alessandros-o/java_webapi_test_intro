package br.com.iteris.universidade.testes.intro.service;

import br.com.iteris.universidade.testes.intro.component.EmailNotificationComponent;
import br.com.iteris.universidade.testes.intro.component.PersonInformationComponent;
import br.com.iteris.universidade.testes.intro.component.PersonValidatorComponent;
import br.com.iteris.universidade.testes.intro.fixture.PersonFixture;
import br.com.iteris.universidade.testes.intro.model.CountryEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonValidatorComponent personValidatorComponent;

    @Spy
    private PersonInformationComponent personInformationComponent;

    @Mock
    private EmailNotificationComponent emailNotificationComponent;

    @InjectMocks
    private PersonService personService;

    @Test
    void shouldInsertThenReturnPersonWithId(){
        final var person = PersonFixture.oneWithEmail("teste@iteris.com.br");
        final var actualPerson = personService.insert(person);

        assertAll(
                () -> assertNotNull(actualPerson.getId()),
                () -> verify(personValidatorComponent).validate(person),
                () -> verify(emailNotificationComponent).send(person.getEmail()),
                () -> verify(personInformationComponent).populateInformation(person),
                () -> assertEquals(CountryEnum.BRAZIL, actualPerson.getCountry())
        );
    }

    @Test
    void shouldInsertThenReturnExceptionEmailNotSupported(){
        final var expectedMessage = "Email not supported";
        final var person = PersonFixture.oneWithEmail("teste@something.io");
        final var actualException = assertThrows(IllegalArgumentException.class, () -> personService.insert(person));

        assertAll(
                () -> assertEquals(expectedMessage, actualException.getMessage()),
                () -> verify(personValidatorComponent).validate(person),
                () -> verify(personInformationComponent).populateInformation(person),
                () -> verify(emailNotificationComponent, never()).send(anyString())
        );
    }
}
