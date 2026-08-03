/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Dario R
 */
public class manejoExcepciones {
    public static class ValorInvalidoException extends Exception {

        public ValorInvalidoException(String mensaje) {
            super(mensaje);
        }

        public ValorInvalidoException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }
    public static class DivisionPorCeroException extends Exception {

        public DivisionPorCeroException(String mensaje) {
            super(mensaje);
        }

        public DivisionPorCeroException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }
}