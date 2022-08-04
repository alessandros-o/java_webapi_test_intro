package br.com.iteris.universidade.testes.intro.controller;

import br.com.iteris.universidade.testes.intro.fixture.PersonFixture;
import br.com.iteris.universidade.testes.intro.model.Person;
import br.com.iteris.universidade.testes.intro.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldInsertThenReturnPersonWithId() throws Exception{
        final var expectedContentType = "application/json";
        final var expectedPerson = PersonFixture.oneWithId();

        when(personService.insert(any(Person.class))).thenReturn(expectedPerson);

        var person = PersonFixture.one();

        var mvcResult = mockMvc.perform(
                post("/person")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andReturn();

        var actualResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Person.class);
        assertNotNull(actualResponse.getId(), "não deve ser nulo");
        assertEquals(expectedPerson, actualResponse, "persons devem ser iguais");
    }
}
