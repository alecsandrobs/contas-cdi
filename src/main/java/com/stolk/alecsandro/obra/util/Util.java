package com.stolk.alecsandro.obra.util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    private static DateTimeFormatter en = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter br = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DecimalFormat numerico = new DecimalFormat("#,##0.00");
    public static final DecimalFormat real = new DecimalFormat("R$ #,##0.00");

    public Util() {
    }

    public static LocalDate dataBr(String data) {
        return LocalDate.parse(data, br);
    }

    public static LocalDate dataEn(String data) {
        return LocalDate.parse(data, en);
    }

    public static String dataTxtEn(LocalDate data) {
        return en.format(data);
    }

    public static String dataTxtBr(LocalDate data) {
        return br.format(data);
    }

    public static String emReal(Double valor) {
        return real.format(valor);
    }

    public static String emValor(Double valor) {
        return numerico.format(valor);
    }

    public static String completaDireita(String texto) {
        return completaDireita(texto, 15);
    }

    public static String completaDireita(String texto, int tamanho) {
        int resto = tamanho - texto.length();
        String completar = null;
        for (int i = 0; i < resto; i++) {
            if (i != 0) {
                completar += " ";
            } else {
                completar = " ";
            }
        }
        return completar + texto;
    }

    public static String completaEsquerda(String texto, int tamanho) {
        int tamanhoTexto = texto.length();
        if (tamanhoTexto > tamanho) {
            return texto.substring(0, tamanho) + " ";
        }
        while (texto.length() <= tamanho) {
            texto += " ";
        }
        return texto;
    }
}
