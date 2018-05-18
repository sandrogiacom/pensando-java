package com.giacom.test;

public class CalculadoraSimples {

    public int calcula(int valor1, int valor2, String operador) {

        if (operador.equals("*")) {
            return valor1 * valor2;
        }

        if (operador.equals("/")) {
            return valor1 / valor2;
        }

        if (operador.equals("+")) {
            return valor1 + valor2;
        }

        if (operador.equals("-")) {
            return valor1 - valor2;
        }

        return 0;
    }


}
