package br.com.iteris.universidade.testes.intro.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AreaCirculoController.class)
class AreaCirculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource(value = {"2;12,5664", "100.64;31.819,3372", "150;70.685,8347"}, delimiter = ';')
    void shouldCalcularAreaCirculoThenReturnAreaValida(double raio, String expectedArea) throws Exception {

        final var expectedContentType = "text/plain;charset=UTF-8";


        var mvcResult = mockMvc.perform(
                        post("/area-circulo")
                                .param("raio", String.valueOf(raio))
                )
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andReturn();

        var actualResponse = mvcResult.getResponse().getContentAsString();

        assertEquals(expectedArea, actualResponse);
    }

}
