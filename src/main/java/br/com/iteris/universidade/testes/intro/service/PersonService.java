package br.com.iteris.universidade.testes.intro.service;

import br.com.iteris.universidade.testes.intro.component.EmailNotificationComponent;
import br.com.iteris.universidade.testes.intro.component.PersonInformationComponent;
import br.com.iteris.universidade.testes.intro.component.PersonValidatorComponent;
import br.com.iteris.universidade.testes.intro.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonValidatorComponent personValidatorComponent;
    private final PersonInformationComponent personInformationComponent;
    private final EmailNotificationComponent emailNotificationComponent;
    public Person insert(final Person person){
        personValidatorComponent.validate(person);
        personInformationComponent.populateInformation(person);
        person.setId(UUID.randomUUID());
        emailNotificationComponent.send(person.getEmail());
        return person;
    }
}
