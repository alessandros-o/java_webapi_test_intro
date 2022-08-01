package br.com.iteris.universidade.testes.intro.controller;

import org.junit.jupiter.api.Test;
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

    @Test
    void shouldCalcularAreaCirculoThenReturnAreaValida() throws Exception {
        final var expectedAreaCirculo = "125.663,7061";
        final var expectedContentType = "text/plain;charset=UTF-8";
        final var expectedContentLength = "12";

        var mvcResult = mockMvc.perform(
                        post("/area-circulo")
                                .param("raio", "200")
                )
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andExpect(header().string("Content-Length", expectedContentLength))
                .andReturn();

        var actualResponse = mvcResult.getResponse().getContentAsString();

        assertEquals(expectedAreaCirculo, actualResponse);
    }

}
