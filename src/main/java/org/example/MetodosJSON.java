package org.example;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static org.example.Auxiliares.enter;
import static org.example.Auxiliares.ficheroNoExiste;

public class MetodosJSON {
    private static Scanner sc = new Scanner(System.in);

    public static void marshallingJSON(ListaCasetas lCasetas, String jsonFilePath) {
        try {
            //Creamos un objeto que utiliza la biblioteca Jackson para convertir objetos Java a formatos como JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Serializar el objeto ListaCasetas a una cadena JSON
            String jsonString = objectMapper.writeValueAsString(lCasetas);

            // Mostrar el JSON resultante
            System.out.println("Objeto lCasetas serializado a JSON:");
            System.out.println(jsonString);

            // Serializar el objeto Usuario a un fichero JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath),lCasetas);

            enter();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void unmarshallingJSON(String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Deserializar el JSON a un objeto ListaCasetas
            ListaCasetas lCasetasFromJSON = objectMapper.readValue(new File(jsonFilePath), ListaCasetas.class);
            // Mostramos las casetas de las listas de casetas recuperado del JSON
            System.out.println("Lista de casetas después de deserializar:");
            lCasetasFromJSON.mostrarCasetas();
            enter();
        } catch (IOException e) {
            ficheroNoExiste();
        }
    }

    //El usuario introducirá el ID de una caseta y el programa recuperará dicha caseta del archivo JSON casetas.json y se mostrará su info
    public static void mostrarCasetaJSON(String jsonFilePath, int id){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Deserializar el JSON a un objeto ListaCasetas
            ListaCasetas lCasetasFromJSON = objectMapper.readValue(new File(jsonFilePath), ListaCasetas.class);
            //Ahora vamos a buscar si existe la caseta por el id de caseta
            Optional<Caseta> casetaOptional = lCasetasFromJSON.getCasetas().stream().filter(c -> c.getId()==id).findAny();
            casetaOptional.ifPresentOrElse(
                    caseta -> {
                        System.out.println("Se ha encontrado la caseta con id "+id+",imprimiendo datos:");
                        System.out.println(caseta.toString());
                    },
                    () -> {
                        System.out.println("No se ha encontrado la caseta con id "+id);
                    }
            );

            enter();

        //Excepciones
        }catch (FileNotFoundException e ){
            ficheroNoExiste();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
