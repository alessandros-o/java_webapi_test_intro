package br.com.iteris.universidade.testes.intro.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Salario {

    private final static BigDecimal PISO_SALARIAL = new BigDecimal("500.00");
    private final static BigDecimal VENDAS_ZERADAS = new BigDecimal("0.00");

    public Salario() throws IllegalAccessException{
        throw new IllegalAccessException("Não pode instanciar essa classe");
    }

    public static String comBonus(BigDecimal salario, BigDecimal vendas) {
        if (salario.compareTo(PISO_SALARIAL) < 0 ){
            throw new IllegalArgumentException("Salário deve ser válido, acima do piso de 500.");
        }

        if (vendas.compareTo(VENDAS_ZERADAS) < 0){
            throw new IllegalArgumentException("Vendas não podem ser negativas.");
        } else if (vendas.compareTo(VENDAS_ZERADAS) == 0){
            return salario.setScale(2, RoundingMode.HALF_UP).toString();
        }

        BigDecimal bonus = calcularBonus(vendas);
        salario = salario.add(bonus);


        return salario.setScale(2, RoundingMode.HALF_UP).toString();
    }

    private static BigDecimal calcularBonus(BigDecimal valor){
        return valor.multiply(new BigDecimal("0.15"));
    }
}
