package ModelsAparcamiento;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Conductor {

    public static Scanner sc = new Scanner(System.in);

    private static int id = 0;
    private String nombre;
    private String dni;

    private int nextId(){
        id = id + 1;
        return id;
    }

    public Conductor(String nombre, String dni){
        this.id = nextId();
        this.nombre = nombre;
        this.dni = dni;
    }

    public Conductor create(String nombre, String dni){
        return new Conductor( nombre, dni );
    }

    public static int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public static void setId(int id) {
        Conductor.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Conductor{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }

    private boolean nombreValido(String nombre){
        Pattern nombreRegex = Pattern.compile("[a-zA-ZáéúíóÁÉÚÍÓ]+");
        if(!nombreRegex.matcher(nombre).matches()){
            throw new IllegalArgumentException("El nombre introducido no es válido, vuelve a probar:");
        }
        return true;
    }

    private boolean dniValido(String dni){
        Pattern dniRegex = Pattern.compile("[0-9]{8}[A-Z]");
        if(!dniRegex.matcher(dni).matches()){
            throw new IllegalArgumentException("El dni introducido no es válido, vuelve a probar:");
        }
        return true;
    }

    private int conseguirNumeroDni(String dni) {
        String numero = "";
        for(int i = 0; i<dni.length()-1; i++){
            char c = dni.charAt (1);
            numero = numero + c;
        }
        return Integer.parseInt(numero);
    }

    public String introducirNombre(){
        String nombre = "";
        do{
            try{
                nombre = sc.nextLine().trim();
                nombreValido(nombre);
            }catch(Exception e){
                System.out.println(e.getMessage());
                nombre = "";
            }
        }while(nombre == "");
        return nombre;
    }

    public String introducirDni(){
        String dni = "";
        do{
            try{
                dni = sc.nextLine().trim();
                dniValido(dni);
            }catch(Exception e){
                System.out.println(e.getMessage());
                dni = "";
            }
        }while(dni == "");
        return dni;
    }
}
