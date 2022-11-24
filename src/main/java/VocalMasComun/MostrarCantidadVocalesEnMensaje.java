package VocalMasComun;

import java.text.Normalizer;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MostrarCantidadVocalesEnMensaje {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        String texto = introducirTexto();
        texto = limpiarTildes(texto);

        int[] vocales = contarVocalesTexto(texto);

         System.out.println("La \'a\' se repite un total de " + vocales[0] + " veces");
         System.out.println("La \'e\' se repite un total de " + vocales[1] + " veces");
         System.out.println("La \'i\' se repite un total de " + vocales[2] + " veces");
         System.out.println("La \'o\' se repite un total de " + vocales[3] + " veces");
         System.out.println("La \'u\' se repite un total de " + vocales[4] + " veces");
    }

    public static String limpiarTildes(String palabra) {
        String nfdNormalizedString = Normalizer.normalize(palabra,  Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static int[] contarVocalesTexto(String texto) {
        int[] vocales = new int[5];
        for(int i=0; i<texto.length(); i++){
            char c = texto.charAt(i);
            switch (c){
                case'a' -> vocales[0]++;
                case'e' -> vocales[1]++;
                case'i' -> vocales[2]++;
                case'o' -> vocales[3]++;
                case'u', 'ü' -> vocales[4]++;
            }
        }
        return vocales;
    }

    private static String introducirTexto() {
        String texto = "";
        do {
            try {
                System.out.println("Por favor, introduzca un texto (el texto puede incluir comas, puntos, tildes y dieresis):");
                texto = sc.nextLine().toLowerCase();
                esUnTexto(texto);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                texto = "";
            }
        }while(texto == "");
        return texto;
    }

    public static Boolean esUnTexto(String texto) {
        if(texto.isEmpty()){
            throw new IllegalArgumentException("El texto no puede estar vacio.");
        }
        var regex = "([a-zA-ZáéíóúüÁÉÍÓÚÜ]+,?.?\s?)+";
        if(!texto.matches(regex)){
            throw new IllegalArgumentException("No has introducido un texto, con solo los especificado.");
        }
        int cont = contarEspaciosEnBlanco(texto);
        if(cont == 0){
            throw new IllegalArgumentException("No has introducido un texto, solo has escrito una palabra, bruh.");
        }
        return true;
    }

    public static int contarEspaciosEnBlanco(String texto) {
        int cont = 0;
        for(int i = 0; i< texto.length(); i++){
            char c = texto.charAt(i);
            if(c == ' '){
                cont++;
            }
        }
        return cont;
    }
}
