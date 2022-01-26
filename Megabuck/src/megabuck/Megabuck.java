/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package megabuck;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author mati
 */
public class Megabuck {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        String clave = "megabuck";
        String mensaje = "EL MISSATGE SECRET";
         */
        runMenu();
        /*
        int numcolumnas = clave.length();
        int numfilas = (mensaje.length() / clave.length()) + 1;
        char[][] matriz = new char[numfilas][numcolumnas];

        llenarMatrizMensaje(numfilas, numcolumnas, mensaje, matriz);
        leerMatriz(numfilas, numcolumnas, matriz);
        int[] numerosLetra = obtenerOrdenNumeros(clave);

        String mensajeEncriptado = encriptarMensaje(numerosLetra, numfilas, matriz);
        System.out.println("");
        System.out.println(mensajeEncriptado);

        String mensajeDesencriptado = desencriptarMensaje(mensajeEncriptado, clave);
        System.out.println("");
        System.out.println(mensajeDesencriptado);
         */
    }

    private static void runMenu() {
        String clave = "megabuck";
        String mensaje = "EL MISSATGE SECRET";
        String mensajeEncriptado = "";
        String mensajeDesencriptado = "";

        boolean flag = false;
        while (!flag) {
            System.out.println("0. salir");
            System.out.println("1. cambiar clave");
            System.out.println("2. cambiar mensaje");
            System.out.println("3. encriptar");
            System.out.println("4. desencriptar");
            String opcion = input("seleccione una opcion: ");
            switch (opcion) {
                case "0":
                    flag = true;
                    break;
                case "1":
                    clave = cambiarClave();
                    System.out.println(clave);

                    break;
                case "2":
                    mensaje = input("introduce el nuevo mensaje: ");
                    break;
                case "3":
                    mensajeEncriptado = cifrado(clave, mensaje);
                    System.out.println(mensajeEncriptado);
                    break;
                case "4":
                    mensajeDesencriptado = desencriptarMensaje(mensajeEncriptado, clave);
                    System.out.println("");
                    System.out.println(mensajeDesencriptado);
                    break;
                default:
                    System.out.println("opcion no valida");
                    break;
            }
        }
    }

    public static String cifrado(String clave, String mensaje) {
        String mensajeEncriptado = "";
        int numcolumnas = clave.length();
        int numfilas = (mensaje.length() / clave.length()) + 1;
        char[][] matriz = new char[numfilas][numcolumnas];
        llenarMatrizMensaje(numfilas, numcolumnas, mensaje, matriz);
        //leerMatriz(numfilas, numcolumnas, matriz);
        int[] numerosLetra = obtenerOrdenNumeros(clave);
        mensajeEncriptado = encriptarMensaje(numerosLetra, numfilas, matriz);
        System.out.println(mensajeEncriptado);
        return mensajeEncriptado;
    }

    public static String encriptarMensaje(int[] numerosLetra, int numfilas, char[][] matriz) {
        String mensajeEncriptado = "";
        for (int i : numerosLetra) {
            for (int j = 0; j < numfilas; j++) {
                mensajeEncriptado += matriz[j][i];
            }
        }
        return mensajeEncriptado;
    }

    public static int[] obtenerOrdenNumeros(String clave) {
        char[] auxchar = clave.toCharArray();
        Arrays.sort(auxchar);
        int[] numerosLetra = new int[clave.length()];
        int cont1 = 0;
        for (char c : auxchar) {
            numerosLetra[cont1] = clave.indexOf(c);
            cont1++;
        }
        return numerosLetra;
    }

    public static void leerMatriz(int numfilas, int numcolumnas, char[][] matriz) {
        for (int i = 0; i < numfilas; i++) {
            for (int j = 0; j < numcolumnas; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }
    }

    public static void llenarMatrizMensaje(int numfilas, int numcolumnas, String mensaje, char[][] matriz) {
        //mensaje
        int cont = 0;
        for (int i = 0; i < numfilas; i++) {
            for (int j = 0; j < numcolumnas; j++) {
                if (mensaje.length() > cont) {
                    matriz[i][j] = mensaje.charAt(cont);
                    cont++;
                } else {
                    matriz[i][j] = ' ';
                }
            }
        }
    }

    private static String desencriptarMensaje(String mensajeEncriptado, String clave) {
        String mensajeDesencriptado = "";
        int numcolumnas = clave.length();
        int numfilas = (mensajeEncriptado.length() / clave.length());
        int[] numerosLetra = obtenerOrdenNumeros(clave);
        char[][] matriz = new char[numcolumnas][numfilas];
        int cont = 0;
        for (int j = 0; j < numcolumnas; j++) {
            for (int i = 0; i < numfilas; i++) {
                if (mensajeEncriptado.length() > cont) {
                    matriz[numerosLetra[j]][i] = mensajeEncriptado.charAt(cont);
                    cont++;
                }
            }
        }
        for (int j = 0; j < numfilas; j++) {
            for (int i = 0; i < numcolumnas; i++) {
                mensajeDesencriptado += matriz[i][j];

            }
        }
        return mensajeDesencriptado;
    }

    private static String input(String mensaje) {
        System.out.println(mensaje);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static String cambiarClave() {
        String result = input("introduzca la clave: ");
        if (result.length() >= 5 && result.length() <= 15) {
            if (result.matches("[a-zA-Z]+")) {
                if (letrasDuplicadas(result)) {
                    result = "megabuck";
                }
            } else {
                result = "megabuck";
            }
        } else {
            result = "megabuck";
        }

        return result;
    }

    private static boolean letrasDuplicadas(String clave) {
        boolean result = false;
        //Converts given string into character array  
        char string[] = clave.toCharArray();
        int count = 0;
        for (int i = 0; i < string.length; i++) {
            for (int j = i + 1; j < string.length; j++) {
                if (string[i] == string[j] && string[i] != ' ') {
                    count++;
                }
            }
        }
        if (count > 0) {
            result = true;
        }
        return result;
    }

}
