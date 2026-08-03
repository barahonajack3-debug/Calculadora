/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorcalculadora;
import Modelos.modelo;
import vistaCalculadora.FrmCalculadora;
import excepciones.manejoExcepciones.DivisionPorCeroException;
import excepciones.manejoExcepciones.ValorInvalidoException;
import javax.swing.JOptionPane;
/**
 *
 * @author EMMAXZZ
 */
public class ControladorCalculadora {

    private FrmCalculadora vista;
    private modelo miModelo;
 
    // Estado interno de la calculadora (tipo "teclado")
    private String pantallaActual = "0";
    private double valorAnterior = 0;
    private String operadorPendiente = null;
    private boolean iniciarNuevoNumero = true;
 
    public ControladorCalculadora(FrmCalculadora vista, modelo miModelo) {
        this.vista = vista;
        this.miModelo = miModelo;
        inicializarEventos();
    }
 
    private void inicializarEventos() {
        vista.getBtnCero().addActionListener(e -> agregarDigito("0"));
        vista.getBtn1().addActionListener(e -> agregarDigito("1"));
        vista.getBtn2().addActionListener(e -> agregarDigito("2"));
        vista.getBtn3().addActionListener(e -> agregarDigito("3"));
        vista.getBtn4().addActionListener(e -> agregarDigito("4"));
        vista.getBtn5().addActionListener(e -> agregarDigito("5"));
        vista.getBtn6().addActionListener(e -> agregarDigito("6"));
        vista.getBtn7().addActionListener(e -> agregarDigito("7"));
        vista.getBtn8().addActionListener(e -> agregarDigito("8"));
        vista.getBtn9().addActionListener(e -> agregarDigito("9"));
 
        vista.getBtnPunto().addActionListener(e -> agregarPunto());
        vista.getBtnCambiarSigno().addActionListener(e -> cambiarSigno());
 
        vista.getBtnSumar().addActionListener(e -> operador("+"));
        vista.getBtnRestar().addActionListener(e -> operador("-"));
        vista.getBtnMul().addActionListener(e -> operador("*"));
        vista.getBtnDivision().addActionListener(e -> operador("/"));
 
        vista.getBtnIgual().addActionListener(e -> calcularIgual());
        vista.getBtnAC().addActionListener(e -> limpiarTodo());
        vista.getBtnCE().addActionListener(e -> limpiarEntrada());
        vista.getBtnBorrar().addActionListener(e -> borrarUltimo());
    }
 
    private void agregarDigito(String digito) {
        if (iniciarNuevoNumero) {
            pantallaActual = digito.equals("0") ? "0" : digito;
            iniciarNuevoNumero = false;
        } else {
            pantallaActual = pantallaActual.equals("0") ? digito : pantallaActual + digito;
        }
        actualizarPantalla();
    }
 
    private void agregarPunto() {
        if (iniciarNuevoNumero) {
            pantallaActual = "0.";
            iniciarNuevoNumero = false;
        } else if (!pantallaActual.contains(".")) {
            pantallaActual += ".";
        }
        actualizarPantalla();
    }
 
    private void cambiarSigno() {
        if (pantallaActual.startsWith("-")) {
            pantallaActual = pantallaActual.substring(1);
        } else if (!pantallaActual.equals("0")) {
            pantallaActual = "-" + pantallaActual;
        }
        actualizarPantalla();
    }
 
    private void operador(String nuevoOperador) {
        try {
            double actual = Double.parseDouble(pantallaActual);
 
            if (operadorPendiente != null && !iniciarNuevoNumero) {
                valorAnterior = miModelo.calcular(valorAnterior, actual, operadorPendiente);
                vista.getLblPantalla().setText(formatear(valorAnterior));
            } else {
                valorAnterior = actual;
            }
 
            operadorPendiente = nuevoOperador;
            vista.getLblOperacion().setText(formatear(valorAnterior) + " " + nuevoOperador);
            iniciarNuevoNumero = true;
 
        } catch (DivisionPorCeroException | ValorInvalidoException ex) {
            mostrarError(ex.getMessage());
            limpiarTodo();
        }
    }
 
    private void calcularIgual() {
        if (operadorPendiente == null) {
            return;
        }
        try {
            double actual = Double.parseDouble(pantallaActual);
            double resultado = miModelo.calcular(valorAnterior, actual, operadorPendiente);
 
            vista.getLblOperacion().setText(
                    formatear(valorAnterior) + " " + operadorPendiente + " " + formatear(actual) + " =");
            pantallaActual = String.valueOf(resultado);
            actualizarPantalla();
 
            operadorPendiente = null;
            iniciarNuevoNumero = true;
 
        } catch (DivisionPorCeroException | ValorInvalidoException ex) {
            mostrarError(ex.getMessage());
            limpiarTodo();
        }
    }
 
    private void limpiarTodo() {
        pantallaActual = "0";
        valorAnterior = 0;
        operadorPendiente = null;
        iniciarNuevoNumero = true;
        actualizarPantalla();
        vista.getLblOperacion().setText("");
    }
 
    private void limpiarEntrada() {
        pantallaActual = "0";
        iniciarNuevoNumero = true;
        actualizarPantalla();
    }
 
    private void borrarUltimo() {
        if (pantallaActual.length() > 1) {
            pantallaActual = pantallaActual.substring(0, pantallaActual.length() - 1);
        } else {
            pantallaActual = "0";
            iniciarNuevoNumero = true;
        }
        actualizarPantalla();
    }
 
    private void actualizarPantalla() {
        vista.getLblPantalla().setText(pantallaActual);
    }
 
    private String formatear(double valor) {
        if (valor == Math.floor(valor) && !Double.isInfinite(valor)) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(valor);
    }
 
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}


