package com.giacom.test;

import org.junit.Test;

public class CalculadoraSimplesTest {

    @Test
    public void soma() {

        CalculadoraSimples calc = new CalculadoraSimples();

        int resultado = calc.calcula(1,3,"+");

        System.out.println(resultado);

    }


}