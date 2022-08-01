package br.com.iteris.universidade.testes.intro.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MediaController.class)
public class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource(value = {"2;2;2,0", "2;4;3,0", "2.5;5.2;3,9"}, delimiter = ';')
    void shouldCalcularMediaDoisValores(double value1, double value2, String expected) throws Exception {
        final var expectedContentType = "text/plain;charset=UTF-8";

        var mvcResult = mockMvc.perform(
                        post("/media/dois-valores")
                                .param("n1", String.valueOf(value1))
                                .param("n2", String.valueOf(value2))
                )
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andReturn();

        var actualResponse = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, actualResponse);
    }

    @ParameterizedTest
    @CsvSource(value = {"2;-2", "-2;4", "-2;-4"}, delimiter = ';')
    void shouldCalcularMediaValoresInvalidosThenThrowException(double value1, double value2) throws Exception {
        final var expectedContentType = "application/json";
        final var expectedMessage = "Os valores devem ser positivos.";

        mockMvc.perform(
                        post("/media/dois-valores")
                                .param("n1", String.valueOf(value1))
                                .param("n2", String.valueOf(value2))
                )
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andExpect(result -> assertEquals(expectedMessage, result.getResolvedException().getMessage()))
                .andReturn();
    }

    @ParameterizedTest
    @CsvSource(value = {"5;6;7;6,0", "5;10;10;8,3", "10.5;6.2;7.8;8,2"}, delimiter = ';')
    void shouldCalcularMediaTresValoresValidos(double value1, double value2, double value3, String expectedValue) throws Exception {
        final var expectedContentType = "text/plain;charset=UTF-8";

        var mvcResult = mockMvc.perform(
                        post("/media/tres-valores")
                                .param("n1", String.valueOf(value1))
                                .param("n2", String.valueOf(value2))
                                .param("n3", String.valueOf(value3))
                )
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andReturn();

        var actualResponse = mvcResult.getResponse().getContentAsString();

        assertEquals(expectedValue, actualResponse);
    }

    @ParameterizedTest
    @CsvSource(value = {"-2;2;1", "2;-4;1", "2;4;-1", "-2;-4;1", "2;-4;-1", "-2;4;-1", "-2;-4;-1"}, delimiter = ';')
    void shouldCalcularMediaTresValoresInvalidosThenThrowException(double value1, double value2, double value3) throws Exception {
        final var expectedContentType = "application/json";
        final var expectedMessage = "Os valores devem ser positivos.";

        mockMvc.perform(
                        post("/media/tres-valores")
                                .param("n1", String.valueOf(value1))
                                .param("n2", String.valueOf(value2))
                                .param("n3", String.valueOf(value3))
                )
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andExpect(result -> assertEquals(expectedMessage, result.getResolvedException().getMessage()))
                .andReturn();
    }


}
