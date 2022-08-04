package br.com.iteris.universidade.testes.intro.component;

import br.com.iteris.universidade.testes.intro.model.CountryEnum;
import br.com.iteris.universidade.testes.intro.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonInformationComponent {
    public void populateInformation(final Person person){
        populateCountry(person);
    }

    private void populateCountry(final Person person) {
        final var emailSplitted = person.getEmail().split("\\.");
        var emailDomain = emailSplitted[emailSplitted.length - 1];

        switch (emailDomain){
            case "br":
                person.setCountry(CountryEnum.BRAZIL);
                break;
            case "fr":
                person.setCountry(CountryEnum.FRANCE);
                break;
            case "pt":
                person.setCountry(CountryEnum.PORTUGAL);
                break;
            default:
                throw new IllegalArgumentException("Email not supported");
        }
    }
}
