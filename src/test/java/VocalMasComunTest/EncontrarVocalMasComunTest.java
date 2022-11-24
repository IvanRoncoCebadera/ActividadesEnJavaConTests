package VocalMasComunTest;

import org.junit.jupiter.api.Test;

import static VocalMasComun.EncontrarVocalMasComun.*;
import static org.junit.jupiter.api.Assertions.*;


public class EncontrarVocalMasComunTest {

    @Test
    public void esUnaPalabraTest(){
        String palabra = "Alabarda";
        assertTrue(esUnaPalabra(palabra));
    }

    @Test
    public void noEsUnaPalabraTest(){
        String palabra = "42llmkdnu8853p5";
        Exception exception = assertThrows(ArithmeticException.class, ()->{esUnaPalabra(palabra);});
        String actualMessage = exception.getMessage();
        assertEquals("No has introducido una palabra.", actualMessage);
    }

    @Test
    public void limpiarTildesTest(){
        String palabra = "álabarda";
        assertEquals("alabarda", limpiarTildes(palabra));
    }

    @Test
    public void contarVocalesTest(){
        String palabra = "alabarda";
        String numeroVocales = "40000";
        String numeroVocalesReal = escribirMensaje(contarVocales(palabra));
        assertEquals(numeroVocales, numeroVocalesReal);
    }

    private String escribirMensaje(int[] contarVocales) {
        String mensaje = "";
        for(int i=0; i<contarVocales.length; i++){
            mensaje = mensaje + contarVocales[i];
        }
        return mensaje;
    }

    @Test
    public void mostrarVocalMasComunTest(){
        int[] numeroVocales = {3,0,0,0,0};
        int posicion = 0;
        assertEquals(posicion, mostrarVocalMasComun(numeroVocales));
    }
}
