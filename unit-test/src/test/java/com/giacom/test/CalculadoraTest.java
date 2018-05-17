package com.giacom.test;

import org.junit.Assert;
import org.junit.Test;

public class CalculadoraTest {

    @Test
    public void soma() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.calcula(1, 2, "+");
        Assert.assertEquals(3, resultado);
    }

    @Test
    public void divisao() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.calcula(10, 2, "/");
        Assert.assertEquals(5, resultado);
    }

    @Test
    public void subtracao() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.calcula(5, 2, "-");
        Assert.assertEquals(3, resultado);
    }

    @Test
    public void multiplica() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.calcula(5, 2, "*");
        Assert.assertEquals(10, resultado);
    }

    @Test
    public void vazio() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.calcula(5, 2, "");
        Assert.assertEquals(0, resultado);
    }


    @Test
    public void nulo() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.calcula(5, 2, null);
        Assert.assertEquals(0, resultado);
    }


    @Test
    public void invalido() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.calcula(5, 2, "a");
        Assert.assertEquals(0, resultado);
    }

    @Test(expected = ArithmeticException.class)
    public void porZero() {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.calcula(5, 0, "/");
        Assert.fail("nao deveria passar aqui!");
    }


}
