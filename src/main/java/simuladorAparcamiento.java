import ModelsAparcamiento.Aparcamiento;
import ModelsAparcamiento.Conductor;
import ModelsAparcamiento.Vehiculo;

public class simuladorAparcamiento {

    public static void main(String[] args){
        Aparcamiento aparcamiento = new Aparcamiento();
        aparcamiento.aparcamientos[0][0] = new Vehiculo("7493-AAA", 2020, "coche", new Conductor("Iván", "53907934M"));
        aparcamiento.contadorVehiculosAparcados++;
        int opcion = 0;
        do {
            System.out.println("Seleccione la opción que deseé:");
            opcion = aparcamiento.menu();
            switch(opcion){
                case 1 -> aparcamiento.aparcar();
                case 2 -> aparcamiento.sacarVehiculoDeAparcamiento();
                case 3 -> aparcamiento.listadoDeVehiculos();
                case 4 -> aparcamiento.cuantosVehiculosTieneUnConductorAparcados();
                case 5 -> aparcamiento.recaudacion();
            }
        }while(opcion != 0);
        System.out.println("Adios..");
    }
}
