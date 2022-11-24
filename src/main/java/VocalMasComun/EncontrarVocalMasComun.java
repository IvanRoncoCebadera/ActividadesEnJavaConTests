package VocalMasComun;

import java.text.Normalizer;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EncontrarVocalMasComun {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        String palabra = introducirPalabra();
        palabra = limpiarTildes(palabra);

        int[] vocales = contarVocales(palabra);

        switch(mostrarVocalMasComun(vocales)){
            case 0 -> System.out.println("La \'a\' es la vocal más común, y se repite un total de " + vocales[0] + " veces");
            case 1 -> System.out.println("La \'e\' es la vocal más común, y se repite un total de " + vocales[1] + " veces");
            case 2 -> System.out.println("La \'i\' es la vocal más común, y se repite un total de " + vocales[2] + " veces");
            case 3 -> System.out.println("La \'o\' es la vocal más común, y se repite un total de " + vocales[3] + " veces");
            case 4 -> System.out.println("La \'u\' es la vocal más común, y se repite un total de " + vocales[4] + " veces");
        }

    }

    public static String limpiarTildes(String palabra) {
        String nfdNormalizedString = Normalizer.normalize(palabra,  Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static int mostrarVocalMasComun(int[] vocales) {
        int posicion = 0;
        int valor = -1;
        for(int i=0; i<vocales.length; i++){
            if(vocales[i] > valor){
                valor = vocales[i];
                posicion = i;
            }
        }
        return posicion;
    }

    public static int[] contarVocales(String palabra) {
        int[] vocales = new int[5];
        for(int i=0; i<palabra.length(); i++){
            char c = palabra.charAt(i);
            switch (c){
                case'a' -> vocales[0]++;
                case'e' -> vocales[1]++;
                case'i' -> vocales[2]++;
                case'o' -> vocales[3]++;
                case'u' -> vocales[4]++;
            }
        }
        return vocales;
    }

    private static String introducirPalabra() {
        String palabra = "";
        do {
            try {
                System.out.println("Por favor, introduzca una palabra (puede incluir tildes):");
                palabra = sc.nextLine().toLowerCase();
                esUnaPalabra(palabra);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                palabra = "";
            }
        }while(palabra == "");
        return palabra;
    }

    public static Boolean esUnaPalabra(String palabra) {
        var regex = "[a-zA-ZáéíóúÁÉÍÓÚ]+";
        if(!palabra.matches(regex)){
            throw new ArithmeticException("No has introducido una palabra.");
        }
        return true;
    }
}
