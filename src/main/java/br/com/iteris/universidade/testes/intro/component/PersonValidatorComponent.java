package br.com.iteris.universidade.testes.intro.component;

import br.com.iteris.universidade.testes.intro.model.Person;
import br.com.iteris.universidade.testes.intro.util.EmailUtils;
import org.springframework.stereotype.Component;

@Component
public class PersonValidatorComponent {

    public void validate(final Person person){
        validateDocument(person.getDocument());
        validateEmail(person.getEmail());
    }

    private void validateEmail(String email) {
        if (EmailUtils.isValid(email)){
            throw new IllegalArgumentException("Person email is invalid!");
        }
    }

    private void validateDocument(String document) {
        if (document == null || document.length() != 11){
            throw new IllegalArgumentException("Person document is invalid!");
        }
    }
}
