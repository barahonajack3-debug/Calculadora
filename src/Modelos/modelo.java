/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import excepciones.manejoExcepciones.DivisionPorCeroException;
import excepciones.manejoExcepciones.ValorInvalidoException;

/**
 *
 * @author Usuario
 */
public class modelo {
    
     public double sumar(double a, double b) throws ValorInvalidoException {
        double resultado = a + b;
        validarValor(resultado);
        return resultado;  
    }
    
    public double restar(double a, double b) throws ValorInvalidoException {
        double resultado = a - b;
        validarValor(resultado);
        return resultado;
    }
    
    public double multiplicar(double a, double b) throws ValorInvalidoException {
        double resultado = a * b;
        validarValor(resultado);
        return resultado;
    }
    
    public double dividir(double a, double b) throws DivisionPorCeroException, ValorInvalidoException {
        if (b == 0) {
            throw new DivisionPorCeroException("No se puede dividir entre cero.");
        }
        double resultado = a / b;
        validarValor(resultado);
        return resultado;
    }
    
    public void validarValor(double valor) throws ValorInvalidoException {
        if (Double.isNaN(valor)) {
            throw new ValorInvalidoException("El resultado no es un número válido.");
        }
        if (Double.isInfinite(valor)) {
            throw new ValorInvalidoException("El resultado es demasiado grande para representarse.");
        }
    }
    
    public double calcular(double a, double b, String operador)
            throws DivisionPorCeroException, ValorInvalidoException {
        if (operador.equals("+")) {
            return sumar(a, b);
        } else if (operador.equals("-")) {
            return restar(a, b);
        } else if (operador.equals("*")) {
            return multiplicar(a, b);
        } else if (operador.equals("/")) {
            return dividir(a, b);
        } else {
            throw new IllegalArgumentException("Operador no reconocido: " + operador);
        }
    }
}