package br.com.iteris.universidade.testes.intro.fixture;

import br.com.iteris.universidade.testes.intro.model.Person;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class PersonFixture {

    public Person one(){
        return Person.builder()
                .name("Jon")
                .email("jon@email.com")
                .document("12345678912")
                .build();
    }

    public static Person oneWithId(){
        return Person.builder()
                .id(UUID.randomUUID())
                .name("Jon")
                .email("jon@email.com")
                .build();
    }

    public static Person oneWithEmail(final String email) {
        return Person.builder()
                .id(UUID.randomUUID())
                .name("Jon")
                .email(email)
                .document("12345678912")
                .build();
    }

    public static Person oneWithDocument(final String document) {
        return Person.builder()
                .id(UUID.randomUUID())
                .name("Jon")
                .email("jon@email.com")
                .document(document)
                .build();
    }
}
