

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class EntryPoint {

    private static final String fileName = "src/guategrafo.txt";

    public static void main(String[] args) {
        // inicializar grafo
        GraphMatrixDirected<String, Float> grafo =  new GraphMatrixDirected<>(30);
        // leer archivo para crear grafo
        iniciarGrafo(grafo);
        System.out.println("Se agregaron las ciudades al grafo...");
        // loop
        Scanner s = new Scanner(System.in);
        int opcion = -1;
        while(opcion != 6) {
            mostrarMenu();
            opcion = Integer.parseInt(s.next());
            try {
                String ciudad, ciudad1, ciudad2;
                Float distancia;
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese ciudad: ");
                        s.nextLine();
                        ciudad = s.nextLine();
                        if(!grafo.addVertex(ciudad)) {
                            System.out.println("Error en nombre de ciudad...");
                            break;
                        }
                        System.out.println("Ciudad agregada...");
                        break;
                    case 2:
                        System.out.println("Ingrese ciudad1: ");
                        s.nextLine();
                        ciudad1 = s.nextLine();
                        System.out.println("Ingrese ciudad2: ");
                        ciudad2 = s.nextLine();
                        System.out.println("Ingrese distancia: ");
                        distancia = Float.parseFloat(s.nextLine());
                        if (distancia < 0f) {
                            System.out.println("No se permite distancia negativa.");
                        }
                        if (!grafo.addEdge(ciudad1, ciudad2, distancia)) {
                            System.out.println("Error en nombre de ciudad.");
                            break;
                        }
                        System.out.println("Arista agregada...");
                        break;
                    case 3:
                        System.out.println("Ingrese ciudad1: ");
                        s.nextLine();
                        ciudad1 = s.nextLine();
                        System.out.println("Ingrese ciudad2: ");
                        ciudad2 = s.nextLine();
                        if(grafo.removeEdge(ciudad1, ciudad2) == null) {
                            System.out.println("Error en nombre de ciudad.");
                        }
                        System.out.println("Coneccion entre ciudades eliminada.");
                        break;
                    case 4:
                        System.out.println("Ejecutando algoritmo de Floyd...");
                        break;
                    case 5:
                        grafo.printAdjacencyMatrix();
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        break;
                }
            }
            catch(Exception e) {
                System.out.println("Error en datos ingresados...");
            }
        }

    }

    private static void mostrarMenu() {
        System.out.println("************ Menu ************");
        System.out.println("1. Agregar ciudad");
        System.out.println("2. Agregar coneccion entre ciudades");
        System.out.println("3. Eliminar coneccion entre ciudades");
        System.out.println("4. Ejecutar algoritmo de Floyd");
        System.out.println("5. Imprimir matriz de adyacencia");
        System.out.println("6. Salir del programa");
        System.out.print("Ingrese numero de opcion: ");
    }

    private static void iniciarGrafo(Graph<String, Float> grafo) {
        try {
            BufferedReader rdr = new BufferedReader(new FileReader(fileName));
            String line = rdr.readLine();
            while(line != null) {
                String[] parts = line.split(":");
                if(parts.length != 3) {
                    System.out.println("guategrafo.txt: Error en formato.");
                    return;
                }
                grafo.addVertex(parts[0]);
                grafo.addVertex(parts[1]);
                grafo.addEdge(parts[0], parts[1], Float.parseFloat(parts[2]));
                line = rdr.readLine();
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
