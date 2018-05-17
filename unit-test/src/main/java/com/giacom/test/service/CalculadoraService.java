package com.giacom.test.service;

import com.giacom.test.Calculadora;

public class CalculadoraService {

    private Repositorio repositorio;

    public CalculadoraService(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public int calcula(int valor1, int valor2, String operador) {
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.calcula(valor1, valor2, operador);
        repositorio.salvaCalculo(calculadora);
        return resultado;
    }


}
