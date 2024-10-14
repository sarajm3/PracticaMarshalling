package org.example;


import java.util.*;

import static org.example.Auxiliares.enter;


public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ListaCasetas listaCasetas = new ListaCasetas();
        String ruta = "src/main/java/org/example/casetas.txt";
        listaCasetas.leerDatosCasetas(ruta);
        //Menu
        menu(listaCasetas);
    }

    //Menú principal
    public static void menu(ListaCasetas listaCasetas) {
        int opcion;
        boolean control = false;
        String rutitaXML = "src/main/java/org/example/ficheros/casetas.xml";
        String rutitaJSON = "src/main/java/org/example/ficheros/casetas.json";
        do {
            try {
                System.out.println("\nMenú. Introduzca el número según la opción que quiera elegir");
                System.out.println("1. Marshalling casetas a XML.\n" +
                        "2. Unmarshalling casetas de XML.\n" +
                        "3. Mostrar la caseta número X desde XML.\n" +
                        "4. Marshalling casetas a JSON.\n" +
                        "5. Unmarshalling casetas a JSON.\n" +
                        "6. Mostrar la caseta número X desde JSON.\n" +
                        "7. Salir.");
                opcion = sc.nextInt();
                if (opcion < 1 || opcion > 7) {
                    throw new InputMismatchException();
                }
                switch (opcion) {
                    //Marshalling casetas a XML
                    case 1:
                        MetodosXML.marshallingXML(listaCasetas, rutitaXML);
                        break;
                    //Unmarshalling casetas de XML
                    case 2:
                        MetodosXML.unmarshallingXML(rutitaXML);
                        break;
                    //Mostrar la caseta número X
                    case 3:
                        mostrarCasetas(rutitaXML, 1);
                        break;
                    //Marshalling casetas a JSON
                    case 4:
                        MetodosJSON.marshallingJSON(listaCasetas, rutitaJSON);
                        break;
                    //Unmarshalling casetas de JSON
                    case 5:
                        MetodosJSON.unmarshallingJSON(rutitaJSON);
                        break;
                    //Mostrar la caseta número X desde JSON
                    case 6:
                        mostrarCasetas(rutitaJSON, 2);
                        break;
                    //Salir
                    case 7:
                        control = true;
                        System.out.println("Saliendo del programa...");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nValores inválidos. Tienes que introducir un numero correspondiente a alguna opción(1-7)");
                control = false;
                sc.nextLine();
                enter();
            }
        } while (!control);
    }

    //Metodo base para mostrar casetas, acompañado de un int para saber que fichero utilizar
    public static void mostrarCasetas(String ruta, int metodo) {
        Boolean control;
        do {
            try {
                System.out.println("Introduce por teclado el ID de la caseta a buscar");
                int id = sc.nextInt();
                //1 es XML, 2 es JSON
                control = false;
                switch (metodo) {
                    case 1:
                        MetodosXML.mostrarCasetaXML(ruta, id);
                        break;
                    case 2:
                        MetodosJSON.mostrarCasetaJSON(ruta, id);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: El ID debe ser numérico.");
                //Limpiamos el búffer
                sc.nextLine();
                control = true;
            }
        } while (control);
    }
}
