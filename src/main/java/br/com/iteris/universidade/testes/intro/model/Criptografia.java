package br.com.iteris.universidade.testes.intro.model;

public class Criptografia {

//    public static void main(String[] args) {
//        System.out.println(criptografar("vv.xwfxo.fd"));
//    }

    public Criptografia() throws IllegalAccessException{
        throw new IllegalAccessException("Não pode instanciar essa classe.");
    }

    public static String criptografar(String entrada){
        if (entrada.isBlank() || entrada.equals(null) || entrada.length() < 4){
            throw new IllegalArgumentException("entrada é nula, vazia ou muito pequena.");
        }
        StringBuilder palavra = new StringBuilder(entrada);

        for (int i = 0; i < palavra.length(); i++){
            if ((palavra.charAt(i) >= 65 && palavra.charAt(i) <= 90) || (palavra.charAt(i) >= 97 && palavra.charAt(i) <= 122)){
                int indiceAscii = palavra.charAt(i) + 3;
                palavra.setCharAt(i, (char)indiceAscii);
            }
        }

        palavra.reverse();
        int j = palavra.length() / 2;

        for (; j < palavra.length(); j++){
            int indiceAscii = palavra.charAt(j) - 1;
            palavra.setCharAt(j, (char)indiceAscii);
        }

        String palavraCripto = palavra.toString();
        return palavraCripto;
    }
}
