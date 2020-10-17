package br.com.desafio.pontoeletronico.negocio.utils;

public final class StringUtil {

    private StringUtil() { }

    public static String padEsquerdaZeros(String valor, Integer tamanho) {
        if (valor.length() >= tamanho) {
            return valor;
        }
        var sb = new StringBuilder();
        while (sb.length() < tamanho - valor.length()) {
            sb.append('0');
        }
        sb.append(valor);
        return sb.toString();
    }

}
