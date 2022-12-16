package ModelsAparcamiento;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Vehiculo {

    private static Scanner sc = new Scanner(System.in);

    public Conductor conductor;
    private static int id = 0;
    private String matricula;
    private int añoFabricacion;
    private String tipo;

    private int nextId() {
        id = id + 1;
        return id;
    }

    public Vehiculo(String matricula, int añoFabricacion, String tipo, Conductor conductor) {
        this.id = nextId();
        this.matricula = matricula;
        this.añoFabricacion = añoFabricacion;
        this.tipo = tipo;
        this.conductor = conductor;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getAñoFabricacion() {
        return añoFabricacion;
    }

    public String getTipo() {
        return tipo;
    }

    public Conductor getConductor() {
        return conductor;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "conductor=" + conductor +
                ", matricula='" + matricula + '\'' +
                ", añoFabricacion=" + añoFabricacion +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    public Vehiculo create(String matricula, int añoFabricacion, String tipo, Conductor conductor){
        return new Vehiculo( matricula, añoFabricacion, tipo, conductor);
    }

    private boolean matriculaValida(String matricula) {
        Pattern matriculaRegex = Pattern.compile("[0-9]{4}-[A-Z]{3}");
        if (!matriculaRegex.matcher(matricula).matches()) {
            throw new IllegalArgumentException("La matrícula introducida no es válida, vuelve a probar:");
        }
        return true;
    }

    private boolean añoFabricacionValido(int añoFabricacion) {
        if (añoFabricacion < 1950 || añoFabricacion > 2022) {
            throw new IllegalArgumentException("El año de fabricacion no puede ser menor que 1950, ni mayor que 2022, vuelve a probar:");
        }
        return true;
    }

    private boolean tipoValido(String tipo) {
        if (tipo.equals("coche") && tipo.equals("moto") && tipo.equals("patinete")) {
            throw new IllegalArgumentException("El tipo introducido no es ni un coche, ni una moto, ni un patinete, por lo que no está permitido en el aparcamiento, vuelve a probar:");
        }
        return true;
    }

    public String introducirMatricula(){
        String matricula = "";
        do{
            try{
                matricula = sc.nextLine().trim();
                matriculaValida(matricula);
            }catch(Exception e){
                System.out.println(e.getMessage());
                matricula = "";
            }
        }while(matricula == "");
        return matricula;
    }

    public int introducirAñoFabricacion(){
        int añoFabricacion = 0;
        do{
            try{
                añoFabricacion = sc.nextInt();
                añoFabricacionValido(añoFabricacion);
            }catch(Exception e){
                System.out.println(e.getMessage());
                añoFabricacion = 0;
            }
        }while(añoFabricacion == 0);
        return añoFabricacion;
    }

    public String introducirTipoVehiculo(){
        String tipo = "";
        do{
            try{
                tipo = sc.nextLine().trim();
                tipoValido(tipo);
            }catch(Exception e){
                System.out.println(e.getMessage());
                tipo = "";
            }
        }while(tipo == "");
        return tipo;
    }
}
