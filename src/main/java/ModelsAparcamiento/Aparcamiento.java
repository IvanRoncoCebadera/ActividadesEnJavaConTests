package ModelsAparcamiento;

import java.util.Scanner;

public class Aparcamiento {
    private static Scanner sc = new Scanner(System.in);

    public Vehiculo[][] aparcamientos = new Vehiculo[4][4];

    private Vehiculo[] vectorVehiculos = new Vehiculo[16];

    Vehiculo vehiculo = new Vehiculo("7493-AAA", 2020, "coche", new Conductor("Iván", "53907934M"));

    public int contadorVehiculosAparcados = 0;

    public int menu(){
        System.out.println("[1] aparcar un nuevo vehículo");
        System.out.println("[2] sacar un vehículo del aparcamiento");
        System.out.println("[3] mostrar un listado de vehiculos");
        System.out.println("[4] cuantos vehículos aparcados tiene un conductor");
        System.out.println("[5] calcular recaudación total");
        System.out.println("[0] salir");
        int opcion = -1;
        do{
            try{
                opcion = sc.nextInt();
                opcionValida(opcion);
            }catch(Exception e){
                System.out.println(e.getMessage());
                opcion = -1;
            }
        }while(opcion == -1);
        return opcion;
    }

    private void opcionValida(int opcion) {
        if(opcion < 0 && opcion > 5){
            throw new IllegalArgumentException("La opción seleccionada es inválida, vuelva a probar:");
        }
    }

    public Vehiculo aparcar(){
        String posicionLibre = buscarPrimeraPosicionLibre();
        Vehiculo nuevoVehiculo = null;
        if (posicionLibre != "") {

            System.out.println("Te toca introducir la información del nuevo vehículo:");
            System.out.println("Introduce la matrícula del vehículo:");
            String matricula = vehiculo.introducirMatricula();
            System.out.println("Introduce el año de fabricación del vehículo:");
            int añoFabricacion = vehiculo.introducirAñoFabricacion();
            System.out.println("Introduce el tipo de vehículo que tienes:");
            String tipo = vehiculo.introducirTipoVehiculo();

            System.out.println("Ahora te toca introducir la información del conductor del nuevo vehículo:");
            System.out.println("Introduce el nombre del conductor:");
            String nombre = vehiculo.conductor.introducirNombre();
            System.out.println("Introduce el dni del conductor:");
            String dni = vehiculo.conductor.introducirDni();

            nuevoVehiculo = vehiculo.create(matricula, añoFabricacion, tipo, vehiculo.conductor.create(nombre, dni));


            int fila = Integer.parseInt(posicionLibre.split("-")[0]);
            int columna = Integer.parseInt(posicionLibre.split("-")[1]);
            aparcamientos[fila][columna] = nuevoVehiculo;
            vectorVehiculos[fila*4+columna] = nuevoVehiculo;

            contadorVehiculosAparcados++;
        }else{
            System.out.println("No hay más aparcamientos libres donde poder meter coches, espera a que se libere el espacio.");
        }
        return nuevoVehiculo;
    }

    public void sacarVehiculoDeAparcamiento(){
        System.out.println("Introduzca la matrícula del vehículo que deseas sacar del apartamento:");
        String posicionVehiculo = buscarVehiculoSegunMatricula(vehiculo.introducirMatricula());
        if(posicionVehiculo != ""){
            System.out.println("El coche: "+ eliminarCocheDeAparcamiento(posicionVehiculo)+", que se encontraba en la posición "+posicionVehiculo+" ha salido.");
        }else{
            System.out.println("No hay ningún coche con esa matrícula.");
        }
    }

    private Vehiculo eliminarCocheDeAparcamiento(String posicionVehiculo) {
        int fila = Integer.parseInt(posicionVehiculo.split("-")[0]);
        int columna = Integer.parseInt(posicionVehiculo.split("-")[1]);
        Vehiculo vehiculoAuxiliar = new Vehiculo(aparcamientos[fila][columna].getMatricula(), aparcamientos[fila][columna].getAñoFabricacion(), aparcamientos[fila][columna].getTipo(), new Conductor(aparcamientos[fila][columna].conductor.getNombre(), aparcamientos[fila][columna].conductor.getDni()));
        aparcamientos[fila][columna] = null;
        vectorVehiculos[fila*4+columna] = null;
        return vehiculoAuxiliar;
    }

    private int cuantosVehiculosHayAparcados(){
        int contadorVehiculosAparcados = 0;
        for(int i = 0; i< aparcamientos.length; i++){
            for(int j = 0; j< aparcamientos[i].length; j++){
                if(aparcamientos[i][j] != null) {
                    contadorVehiculosAparcados++;
                }
            }
        }
        return contadorVehiculosAparcados;
    }

    public void listadoDeVehiculos(){
        pasarMatrizAVector(vectorVehiculos);

        ordenarMetodoBurbujaDescendente(vectorVehiculos);

        System.out.println("Hay un total de "+cuantosVehiculosHayAparcados()+" vehículos aparcados, y son:");
        for(int i=0; i<vectorVehiculos.length; i++){
            if(vectorVehiculos[i] != null) {
                System.out.println(vectorVehiculos[i]);
            }
        }
    }

    public void cuantosVehiculosTieneUnConductorAparcados(){
        System.out.println("Introduzca el dni del conductor cuya cantidad de vehículos aparcados quiere comprobar:");
        String dni = vehiculo.conductor.introducirDni();
        System.out.println("El conductor del dni: "+dni+" tiene aparcados un total de: "+contarVehiculosAparcadosDeConductor(dni)+" coches");
    }

    public void recaudacion(){
        System.out.println("Por ahora, a habido un total de "+contadorVehiculosAparcados+" vehículos, " +
                "por lo que la recaudación actual ( a 3.75 euros el aparcamiento ) es de: "
                +String.format("%.2f",calcularRecaudacion(contadorVehiculosAparcados))+" euros");
    }

    private double calcularRecaudacion(int contadorVehiculosAparcados) {
        return contadorVehiculosAparcados*3.75;
    }

    private int contarVehiculosAparcadosDeConductor(String dni) {
        int contador = 0;
        for(int i = 0; i< aparcamientos.length; i++){
            for(int j = 0; j< aparcamientos[i].length; j++){
                if(aparcamientos[i][j] != null) {
                    if (aparcamientos[i][j].conductor.getDni().equals(dni)) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    private static void ordenarMetodoBurbujaDescendente(Vehiculo[] vectorVehiculos) {
        for(int i = 0; i< 4; i++){
            for(int j = 0; j< 4-i; j++){
                if(vectorVehiculos[j] != null && vectorVehiculos[j + 1] != null) {
                    if (vectorVehiculos[j].getAñoFabricacion() < vectorVehiculos[j + 1].getAñoFabricacion()) {
                        Vehiculo vehiculoAuxiliar = vectorVehiculos[j + 1];
                        vectorVehiculos[j + 1] = vectorVehiculos[j];
                        vectorVehiculos[j] = vehiculoAuxiliar;
                    }
                }
            }
        }
    }

    private void pasarMatrizAVector(Vehiculo[] vectorVehiculos) {
        int contador = 0;
        for(int i = 0; i< aparcamientos.length; i++){
            for(int j = 0; j< aparcamientos[i].length; j++){
                vectorVehiculos[contador]  = aparcamientos[i][j];
                contador++;
            }
        }
    }

    private String buscarVehiculoSegunMatricula(String matricula) {
        String posicion = "";
        for(int i = 0; i< aparcamientos.length; i++){
            for(int j = 0; j< aparcamientos[i].length; j++){
                if(aparcamientos[i][j] != null) {
                    if (aparcamientos[i][j].getMatricula().equals(matricula)) {
                        posicion = i + "-" + j;
                        break;
                    }
                }
            }
            if(posicion != ""){
                break;
            }
        }
        return posicion;
    }

    private String buscarPrimeraPosicionLibre() {
        String posicion = "";
        for(int i = 0; i< aparcamientos.length; i++){
            for(int j = 0; j< aparcamientos[i].length; j++){
                if(aparcamientos[i][j] == null){
                    posicion = i+"-"+j;
                    break;
                }
            }
            if(posicion != ""){
                break;
            }
        }
        return posicion;
    }
}
