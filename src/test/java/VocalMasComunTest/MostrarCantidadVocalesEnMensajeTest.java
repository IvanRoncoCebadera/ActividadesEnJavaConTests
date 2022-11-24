package VocalMasComunTest;

import org.junit.jupiter.api.Test;


import static VocalMasComun.EncontrarVocalMasComun.limpiarTildes;
import static VocalMasComun.MostrarCantidadVocalesEnMensaje.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MostrarCantidadVocalesEnMensajeTest {

    @Test
    public void esUnTextoTest(){
        String texto = "Alabarda de madera";
        assertTrue(esUnTexto(texto));
    }

    @Test
    public void noEsUnTextoTest(){
        String texto = "Alabar56";
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{esUnTexto(texto);});
        String actualMessage = exception.getMessage();
        assertEquals("No has introducido un texto, con solo los especificado.", actualMessage);
        String texto2 = "Alabarda";
        Exception exception2 = assertThrows(IllegalArgumentException.class, ()->{esUnTexto(texto2);});
        String actualMessage2 = exception2.getMessage();
        assertEquals("No has introducido un texto, solo has escrito una palabra, bruh.", actualMessage2);
        String texto3 = "";
        Exception exception3 = assertThrows(IllegalArgumentException.class, ()->{esUnTexto(texto3);});
        String actualMessage3 = exception3.getMessage();
        assertEquals("El texto no puede estar vacio.", actualMessage3);
    }

    @Test
    public void contarEspaciosEnBlancoTest(){
        String texto = "Alabarda de madera";
        assertEquals(2, contarEspaciosEnBlanco(texto));
    }

    @Test
    public void limpiarTildesTest(){
        String texto = "álabarda cómida";
        assertEquals("alabarda comida", limpiarTildes(texto));
    }

    @Test
    public void contarVocalesTextoTest(){
        String texto = "alabarda";
        String numeroVocales = "40000";
        String numeroVocalesReal = escribirMensaje(contarVocalesTexto(texto));
        assertEquals(numeroVocales, numeroVocalesReal);
    }

    private String escribirMensaje(int[] contarVocales) {
        String mensaje = "";
        for(int i=0; i<contarVocales.length; i++){
            mensaje = mensaje + contarVocales[i];
        }
        return mensaje;
    }
}
