package org.example;

import java.util.Scanner;

public class DondeEstaElRobot {

    public static int ROBOT = 1;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int[][] matriz = new int[7][7];
        matriz[0][0] = ROBOT;

        String decision = "";
        do {
            decision = escribirDecision();
            if(decision.equals("pasos")){
                matriz = movimientos(matriz);
            }
        }while(decision.equals("pasos"));
        System.out.println("Ha sido un placer, adios.");
    }

    private static int[][] movimientos(int[][] matriz) {
        String pasos = "";
        pasos = introducirAcciones();
        String[] movimientos = new String[5];
        movimientos = pasos.split(",");

        leerVector(movimientos);

        try {
            matriz = moverRobot(matriz, movimientos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        return matriz;
    }

    private static String escribirDecision() {
        String decision;
        do {
            System.out.println("Por favor usuario, es tu turno de elegir, quiere introducir más pasos o quiere salir del programa? ( si quiere más pasos introduza: \"pasos\", si quiere salir introduzca: \"salir\")");
            decision = sc.nextLine().trim().toLowerCase();
            if(decision.isEmpty()){
                System.out.println("No has elegido ninguna opcion.");
            }
            if(!decision.equals("pasos") && !decision.equals("salir")){
                System.out.println("No has elegido ninguna de las opciones válidas.");
            }
        }while(decision.isEmpty() && !decision.equals("pasos") && !decision.equals("salir"));
        return decision;
    }

    private static int[][] moverRobot(int[][] matriz, String[] movimientos) throws InterruptedException {
        int accion = 0;
        String direccion = "abajo";
        int cont = 0;
        int[] posRobot = new int[2];
        do{
            accion = Integer.parseInt(movimientos[cont]);
            if(direccion == "abajo" && accion != 0){
                while(posRobot[0] + 1 < matriz.length && accion != 0){
                    if(accion > 0) {
                        posRobot[0]++;
                        accion--;
                    }else{
                        if(posRobot[0] - 1 >= 0) {
                            posRobot[0]--;
                            accion++;
                        }else{
                            accion = -accion;
                            posRobot[0]++;
                            accion--;
                        }
                    }
                }
                direccion = "derecha";
            }
            if(direccion == "derecha" && accion != 0){
                while(posRobot[1] + 1 < matriz.length && accion != 0){
                    if(accion > 0) {
                        posRobot[1]++;
                        accion--;
                    }else{
                        if(posRobot[1] - 1 >= 0) {
                            posRobot[1]--;
                            accion++;
                        }else{
                            accion = -accion;
                            posRobot[1]++;
                            accion--;
                        }
                    }
                }
                direccion = "arriba";
            }
            if(direccion == "arriba" && accion != 0){
                while(posRobot[0] - 1 >= 0 && accion != 0){
                    if(accion > 0) {
                        posRobot[0]--;
                        accion--;
                    }else{
                        if(posRobot[0] + 1 < matriz.length) {
                            posRobot[0]++;
                            accion++;
                        }else{
                            accion = -accion;
                            posRobot[0]--;
                            accion--;
                        }
                    }
                }
                direccion = "izquierda";
            }
            if(direccion == "izquierda" && accion != 0){
                while(posRobot[1] - 1 >= 0 && accion != 0){
                    if(accion > 0) {
                        posRobot[1]--;
                        accion--;
                    }else{
                        if(posRobot[1] + 1 < matriz.length) {
                            posRobot[1]++;
                            accion++;
                        }else{
                            accion = -accion;
                            posRobot[1]--;
                            accion--;
                        }
                    }
                }
                direccion = "abajo";
            }
            matriz = escribir0Matriz(matriz);
            matriz[posRobot[0]][posRobot[1]] = ROBOT;
            System.out.println("La posicion actual del robot es: " + (posRobot[0] + 1) + "," + (posRobot[1] + 1));
            System.out.println("Ademas, el tablero actualmente se ve así:");
            leerMatriz(matriz);
            cont++;
            Thread.sleep(1000);
        }while(cont < movimientos.length);
        return matriz;
    }

    private static int[][] escribir0Matriz(int[][] matriz) {
        for(int i = 0; i<matriz.length; i++){
            for(int j = 0; j<matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }
        return matriz;
    }

    private static void leerMatriz(int[][] matriz) {
        String mensaje = "";
        for(int i = 0; i<matriz.length; i++){
            for(int j = 0; j<matriz[i].length; j++) {
                mensaje = mensaje + matriz[i][j];
            }
            System.out.println(mensaje);
            mensaje = "";
        }
    }

    private static void leerVector(String[] movimientos) {
        String mensaje = "";
        for(int i = 0; i<movimientos.length; i++){
            String numero = String.valueOf(movimientos[i]);
            mensaje = mensaje;
        }
        System.out.println(mensaje);
    }

    private static String introducirAcciones() {
        String pasos = "";
        do {
            try {
                System.out.println("De acuerdo, entonces por favor introduzca una secuencia de acciones que quiera que lleve el robot, mínimo has de introducir 1 y máximo 5 (ejemplo: 12,3,5,-10,4. Nota: se debe cumplir el patrón mostrado en el ejemplo.)");
                pasos = sc.nextLine();
                validarPasos(pasos);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                pasos = "";
            }
        }while(pasos.equals(""));
        return pasos;
    }

    private static void validarPasos(String pasos) {
        var patron = "(-?[0-9]+,?){1,4}(-?[0-9]+)?";
        if(pasos.matches(patron)){
        }else{
            throw new IllegalArgumentException("Las acciones introducidas, no cumplen con los requisitos pedidos.");
        }

        int cont = 0;
        for(int i = 0; i<pasos.length(); i++){
            char c = pasos.charAt(i);
            if(c == ','){
                cont++;
            }
        }

        if(cont <= 4){
            System.out.println("Las acciones introducidas son válidas.");
        }else{
            throw new IllegalArgumentException("Las acciones introducidas, no son válidas ya que has introducido más de 5 acciones.");
        }

    }
}
