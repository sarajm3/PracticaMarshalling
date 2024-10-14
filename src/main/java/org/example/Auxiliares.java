package org.example;

import java.util.Scanner;

public class Auxiliares {
    private static Scanner sc = new Scanner(System.in);

    //Manejo de excepcion FileNotFound
    public static void ficheroNoExiste(){
        System.out.println("El fichero no existe o no se encuentra en la ruta específicada");
        enter();
    }

    //Método auxiliar para mejorar la legibilidad del código
    public static void enter(){
        //Esto es para que le tengas que dar a enter para volver a ver el menu
        System.out.println("\nPulse para continuar...");
        sc.nextLine();
    }
}
