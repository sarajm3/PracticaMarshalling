package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.Optional;
import java.util.Scanner;

import static org.example.Auxiliares.enter;
import static org.example.Auxiliares.ficheroNoExiste;

public class MetodosXML {
    private static Scanner sc = new Scanner(System.in);

    public static void marshallingXML(ListaCasetas lCasetas,String xmlFilePath) {
        try {
            // Crear un contexto JAXB para la clase Lista de Casetas
            JAXBContext jaxbContext = JAXBContext.newInstance(ListaCasetas.class);

            // Crear un marshaller para convertir el objeto a XML
            Marshaller marshaller = jaxbContext.createMarshaller();

            // Formatear el XML para que sea más legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            String xmlString = "";
            // Serializar el objeto Usuario a una cadena XML
            marshaller.marshal(lCasetas, sw);
            xmlString = sw.toString();

            // Serializar el objeto ListaCasetas a un fichero
            marshaller.marshal(lCasetas, new File(xmlFilePath));

            // Mostrar el XML resultante
            System.out.println("Objeto Lista de Casetas serializado a XML:");
            System.out.println(xmlString);
            System.out.println("");

            Auxiliares.enter();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void unmarshallingXML(String xmlFilePath) {
        // Deserializar objeto ListaCasetas desde fichero XML
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ListaCasetas.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ListaCasetas lCasetasFromXML = (ListaCasetas) unmarshaller.unmarshal(new File(xmlFilePath));
            // Mostramos la lista de casetas recuperada del XML
            System.out.println("Objeto lista de casetas después de deserializar, mostrando todas las casetas:");
            lCasetasFromXML.mostrarCasetas();
            Auxiliares.enter();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }catch (IllegalArgumentException e ){
            Auxiliares.ficheroNoExiste();
        }
    }

    // El usuario introducirá el ID de una caseta y el programa recuperará dicha caseta del archivo XML casetas.xml y se mostraár su info
    public static void mostrarCasetaXML(String xmlFilePath, int id) {
        try {
            //Sacamos las casetas del fichero de texto a objeto
            JAXBContext jaxbContext = JAXBContext.newInstance(ListaCasetas.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ListaCasetas lCasetasFromXML = (ListaCasetas) unmarshaller.unmarshal(new File(xmlFilePath));
            //Ahora buscamos entre la lista de casetas
            Optional<Caseta> casetaOptional = lCasetasFromXML.getCasetas().stream().filter(c -> c.getId() == id).findAny();
            casetaOptional.ifPresentOrElse(
                    caseta -> {
                        System.out.println("Se ha encontrado la caseta con id " + id + ", imprimiendo sus datos:");
                        System.out.println(caseta.toString());
                    },
                    () -> {
                        System.out.println("No se ha encontrado la caseta con id " + id);
                    }
            );
            //Limpiamos el buffer
            enter();
        }catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e ){
            ficheroNoExiste();
        }
    }
}
