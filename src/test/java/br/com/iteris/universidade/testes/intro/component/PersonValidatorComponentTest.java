package br.com.iteris.universidade.testes.intro.component;

import br.com.iteris.universidade.testes.intro.fixture.PersonFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonValidatorComponentTest {

    @InjectMocks
    private PersonValidatorComponent personValidatorComponent;

    @Test
    void shouldValidateThenNotThrowException() {
        final var person = PersonFixture.one();
        assertDoesNotThrow(() -> personValidatorComponent.validate(person));
    }

    @Test
    void shouldValidateThenThrowExceptionInvalidEmail(){
        final var person = PersonFixture.oneWithEmail("invalidemail#...");
        final var actualException = assertThrows(IllegalArgumentException.class,
                () -> personValidatorComponent.validate(person));
        assertEquals("Person email is invalid!", actualException.getMessage());
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"123", "12345678910234"})
    void shouldValidateThenThrowExceptionInvalidDocument(final String document){
        final var person = PersonFixture.oneWithDocument(document);
        final var actualException = assertThrows(IllegalArgumentException.class,
                () -> personValidatorComponent.validate(person));
        assertEquals("Person document is invalid!", actualException.getMessage());
    }
}