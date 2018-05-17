package com.giacom.test.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CalculadoraServiceTest {

    private Repositorio repositorio;

    @Test
    public void calculaService() {
        CalculadoraService service = new CalculadoraService(repositorio);

        int resultado = service.calcula(1, 2, "+");

        assertThat(resultado, is(3));

    }
}

