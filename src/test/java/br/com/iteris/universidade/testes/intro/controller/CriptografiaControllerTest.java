package br.com.iteris.universidade.testes.intro.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CriptografiaController.class)
class CriptografiaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource(value = {"vxpdylY .ph;ks. \\n{frzx", "abcABC1;1FECedc"}, delimiter = ';')
    void shouldCriptografaThenReturnPalavraCriptografada(String palavra, String expected)throws Exception {
        final var expectedContentType = "text/plain;charset=UTF-8";

        var mvcResult = mockMvc.perform(
                post("/cripto")
                        .param("palavra", String.valueOf(palavra))
        )
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andReturn();

        var actualResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, actualResponse);
    }

    @ParameterizedTest
    @CsvSource({"Tex", "ao"})
    void shouldCriptografaThenReturnException(String palavra) throws Exception{
        final var expectedContentType = "application/json";
        final var expectedMessage = "entrada é nula, vazia ou muito pequena.";

        mockMvc.perform(
                post("/cripto")
                        .param("palavra", String.valueOf(palavra))
        )
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andExpect(result -> assertEquals(expectedMessage, result.getResolvedException().getMessage()))
                .andReturn();
    }
}