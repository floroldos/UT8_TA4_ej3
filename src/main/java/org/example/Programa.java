package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // cargar grafo con actores y relaciones
        ArrayList<TVertice> vertices = new ArrayList<>();
        ArrayList<TArista> aristas = new ArrayList<>();

        for(String s : ManejadorArchivosGenerico.leerArchivo("src/main/java/org/example/ciudades.txt", false)){
            vertices.add(new TVertice(s));
        }

        for(String s : ManejadorArchivosGenerico.leerArchivo("src/main/java/org/example/relaciones.txt", false)){
            String[] datos = s.split(",");
            aristas.add(new TArista(datos[0], datos[1], Double.parseDouble(datos[2])));
        }

        TGrafoNoDirigido gnd = new TGrafoNoDirigido(vertices, aristas);


        System.out.println(gnd.puntosArticulacion("NAIROBI"));

        //ManejadorArchivosGenerico.escribirArchivo("src/salida.txt", new String[]{kevin});


    }
}
