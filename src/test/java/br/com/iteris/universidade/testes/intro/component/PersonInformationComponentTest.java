package br.com.iteris.universidade.testes.intro.component;

import br.com.iteris.universidade.testes.intro.fixture.PersonFixture;
import br.com.iteris.universidade.testes.intro.model.CountryEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonInformationComponentTest {

    @InjectMocks
    private PersonInformationComponent personInformationComponent;

    @ParameterizedTest
    @MethodSource("providePerson")
    void shouldPopulateInformationWithValidCountry(final String email, final CountryEnum expected) {
        final var person = PersonFixture.oneWithEmail(email);
        personInformationComponent.populateInformation(person);

        assertEquals(expected, person.getCountry());
    }

    @Test
    void shouldPopulateInformationThenThrowExceptionEmailNotSupported(){
        final var person = PersonFixture.oneWithEmail("something@domain.io");
        final var actualException = assertThrows(IllegalArgumentException.class,
                () -> personInformationComponent.populateInformation(person));

        assertEquals("Email not supported", actualException.getMessage());
    }

    private static Stream<Arguments> providePerson(){
        return Stream.of(
                Arguments.of("something@iteris.com.br", CountryEnum.BRAZIL),
                Arguments.of("something@iteris.com.fr", CountryEnum.FRANCE),
                Arguments.of("something@iteris.com.pt", CountryEnum.PORTUGAL)
        );
    }
}