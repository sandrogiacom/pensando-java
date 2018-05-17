package com.giacom.test;

public class Calculadora {

    private int valor1, valor2;
    private String operador;

    public int calcula(int valor1, int valor2, String operador) {

        this.operador = operador;
        this.valor1 = valor1;
        this.valor2 = valor2;

        if (operador.equals("*")) {
            return multiplica(valor1, valor2);
        }

        if (operador.equals("/")) {
            return divide(valor1, valor2);
        }

        if (operador.equals("+")) {
            return soma(valor1, valor2);
        }

        if (operador.equals("-")) {
            return subtrai(valor1, valor2);
        }

        return 0;
    }

    private int subtrai(int val1, int val2) {
        return val1 - val2;
    }

    private int soma(int val1, int val2) {
        return val1 + val2;
    }

    private int divide(int val1, int val2) {
        return val1 / val2;
    }

    private int multiplica(int val1, int val2) {
        return val1 * val2;
    }


    public int getValor1() {
        return valor1;
    }

    public int getValor2() {
        return valor2;
    }

    public String getOperador() {
        return operador;
    }

}
