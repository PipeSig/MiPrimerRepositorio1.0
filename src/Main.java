import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Trabajador> trabajadores = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarTrabajador();
                    break;
                case 2:
                    buscarTrabajadorPorCc();
                    break;
                case 3:
                    buscarTrabajadoresConSueldoMayor();
                    break;
                case 4:
                    eliminarTrabajadorPorCc();
                    break;
                case 5:
                    listarTrabajadores();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

            System.out.println();
        } while (opcion != 0);
    }

    static void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("        SISTEMA DE TRABAJADORES");
        System.out.println("========================================");
        System.out.println("1. Registrar trabajador");
        System.out.println("2. Buscar trabajador por cédula");
        System.out.println("3. Buscar trabajadores con sueldo > 1.000.000");
        System.out.println("4. Eliminar trabajador por cédula");
        System.out.println("5. Listar trabajadores");
        System.out.println("0. Salir");
        System.out.println("========================================");
    }

    static void registrarTrabajador() {
        System.out.println("\nREGISTRAR TRABAJADOR");

        System.out.print("Cédula: ");
        String cc = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Sueldo: ");
        double sueldo = leerDouble();

        Trabajador t = new Trabajador(cc, nombre, apellido, sueldo);
        trabajadores.add(t);

        System.out.println("Trabajador registrado correctamente.");
    }

    static void buscarTrabajadorPorCc() {
        System.out.println("\nBUSCAR TRABAJADOR POR CÉDULA");
        System.out.print("Ingrese la cédula: ");
        String cc = sc.nextLine();

        Trabajador encontrado = buscarTrabajador(cc);

        if (encontrado == null) {
            System.out.println("No se encontró ningún trabajador con esa cédula.");
        } else {
            System.out.println("Trabajador encontrado:");
            mostrarTrabajador(encontrado);
        }
    }

    static void buscarTrabajadoresConSueldoMayor() {
        System.out.println("\nTRABAJADORES CON SUELDO MAYOR A 1.000.000");
        int contador = 0;

        for (Trabajador t : trabajadores) {
            if (t.getSueldo() > 1000000) {
                mostrarTrabajador(t);
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("No hay trabajadores con sueldo superior a 1.000.000.");
        }
    }

    static void eliminarTrabajadorPorCc() {
        System.out.println("\nELIMINAR TRABAJADOR POR CÉDULA");
        System.out.print("Ingrese la cédula: ");
        String cc = sc.nextLine();

        Trabajador encontrado = buscarTrabajador(cc);

        if (encontrado == null) {
            System.out.println("No existe un trabajador con esa cédula.");
        } else {
            trabajadores.remove(encontrado);
            System.out.println("Trabajador eliminado correctamente.");
        }
    }

    static void listarTrabajadores() {
        System.out.println("\nLISTA DE TRABAJADORES");

        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
            return;
        }

        for (Trabajador t : trabajadores) {
            mostrarTrabajador(t);
        }
    }

    static Trabajador buscarTrabajador(String cc) {
        for (Trabajador t : trabajadores) {
            if (t.getCc().equalsIgnoreCase(cc)) {
                return t;
            }
        }
        return null;
    }

    static void mostrarTrabajador(Trabajador t) {
        System.out.println("Cédula: " + t.getCc());
        System.out.println("Nombre: " + t.getNombre());
        System.out.println("Apellido: " + t.getApellido());
        System.out.println("Sueldo: $" + t.getSueldo());
        System.out.println("------------------------------");
    }

    static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Intente nuevamente: ");
            }
        }
    }

    static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Intente nuevamente: ");
            }
        }
    }

    static class Trabajador {
        private String cc;
        private String nombre;
        private String apellido;
        private double sueldo;

        public Trabajador(String cc, String nombre, String apellido, double sueldo) {
            this.cc = cc;
            this.nombre = nombre;
            this.apellido = apellido;
            this.sueldo = sueldo;
        }

        public String getCc() {
            return cc;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public double getSueldo() {
            return sueldo;
        }
    }
}