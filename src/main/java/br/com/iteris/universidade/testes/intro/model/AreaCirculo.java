package br.com.iteris.universidade.testes.intro.model;

public class AreaCirculo {

    private AreaCirculo() throws IllegalAccessException {
        throw new IllegalAccessException("Não pode instanciar essa classe.");
    }

    public static String calcula(final double raio) {
        final var area = Math.PI * Math.pow(raio, 2);
        return String.format("%,.4f", area);
    }

}
