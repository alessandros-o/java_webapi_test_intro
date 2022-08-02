package br.com.iteris.universidade.testes.intro.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalarioController.class)
class SalarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource(value = {"1000;1000;1150.00"}, delimiter = ';')
    void shouldComBonusComSalarioEVendasValidos(BigDecimal salario, BigDecimal vendas, String expected) throws Exception {

        final var expectedContentType = "text/plain;charset=UTF-8";

        var mvcResult = mockMvc.perform(
                post("/bonus")
                        .param("salario", String.valueOf(salario))
                        .param("vendas", String.valueOf(vendas))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andReturn();

        var actualResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, actualResponse);
    }

    @ParameterizedTest
    @CsvSource(value = {"550;0;550.00", "500;0;500.00"}, delimiter = ';')
    void shouldComBonusComSalarioValidoEVendaZerada(BigDecimal salario, BigDecimal vendas, String expected) throws Exception{
        final var expectedContentType = "text/plain;charset=UTF-8";

        var mvcResult = mockMvc.perform(
                post("/bonus")
                        .param("salario", String.valueOf(salario))
                        .param("vendas", String.valueOf(vendas))
        )
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andReturn();

        var actualResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, actualResponse);
    }

    @ParameterizedTest
    @CsvSource(value = {"450;100;"}, delimiter = ';')
    void shouldComBonusComSalarioAbaixoDe500ThenReturnException(BigDecimal salario, BigDecimal vendas) throws Exception{
        final var expectedContentType = "application/json";
        final var expectedMessage = "Salário deve ser válido, acima do piso de 500.";

        mockMvc.perform(
                post("/bonus")
                        .param("salario", String.valueOf(salario))
                        .param("vendas", String.valueOf(vendas))
        )
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andExpect(result -> assertEquals(expectedMessage, result.getResolvedException().getMessage()))
                .andReturn();
    }

    @ParameterizedTest
    @CsvSource(value = {"501;-100", "550;-1"}, delimiter = ';')
    void shouldComBonusComSalarioValidoEVendaNegativaThenReturnException(BigDecimal salario, BigDecimal vendas) throws Exception{
        final var expectedContentType = "application/json";
        final var expectedMessage = "Vendas não podem ser negativas.";

        mockMvc.perform(
                post("/bonus")
                        .param("salario", String.valueOf(salario))
                        .param("vendas", String.valueOf(vendas))
        )
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", expectedContentType))
                .andExpect(result -> assertEquals(expectedMessage, result.getResolvedException().getMessage()))
                .andReturn();
    }
}