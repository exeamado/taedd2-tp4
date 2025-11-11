/**
 * Actividad 2 - El problema de Josephus
 * Comparación de implementaciones: Arreglo vs Lista Circular
 * Carrera: Ingeniería en Software
 * Materia: Taller de Algoritmos y Estructura de Datos II
 */
public class Actividad2_Josephus {
    
    public static void main(String[] args) {
        int n = 20; // Número de personas
        int k = 4;  // Contador de eliminación
        
        System.out.println("=== PROBLEMA DE JOSEPHUS ===");
        System.out.println("n = " + n + " personas");
        System.out.println("k = " + k + " (eliminar cada " + k + " persona)\n");
        
        // Implementación con Arreglo
        System.out.println("1. IMPLEMENTACIÓN CON ARREGLO:");
        long inicioArreglo = System.nanoTime();
        int sobrevivienteArreglo = josephusConArreglo(n, k);
        long finArreglo = System.nanoTime();
        long tiempoArreglo = finArreglo - inicioArreglo;
        System.out.println("Sobreviviente: Posición " + sobrevivienteArreglo);
        System.out.println("Tiempo de ejecución: " + tiempoArreglo + " nanosegundos");
        System.out.println("Tiempo en microsegundos: " + (tiempoArreglo / 1000.0) + " µs\n");
        
        // Implementación con Lista Circular
        System.out.println("2. IMPLEMENTACIÓN CON LISTA CIRCULAR:");
        long inicioLista = System.nanoTime();
        int sobrevivienteLista = josephusConListaCircular(n, k);
        long finLista = System.nanoTime();
        long tiempoLista = finLista - inicioLista;
        System.out.println("Sobreviviente: Posición " + sobrevivienteLista);
        System.out.println("Tiempo de ejecución: " + tiempoLista + " nanosegundos");
        System.out.println("Tiempo en microsegundos: " + (tiempoLista / 1000.0) + " µs\n");
        
        // Comparación
        System.out.println("=== COMPARACIÓN ===");
        double factor = (double) tiempoArreglo / tiempoLista;
        if (tiempoLista < tiempoArreglo) {
            System.out.println("Lista Circular es más rápida");
            System.out.printf("Factor: %.2fx más rápida que Arreglo\n", 1.0/factor);
        } else {
            System.out.println("Arreglo es más rápido");
            System.out.printf("Factor: %.2fx más rápido que Lista Circular\n", factor);
        }
        
        // Prueba adicional con más iteraciones para mejor medición
        System.out.println("\n=== PRUEBA CON 1000 ITERACIONES ===");
        pruebaRendimiento(n, k, 1000);
    }
    
    /**
     * Implementación del problema de Josephus usando un arreglo simple
     * Complejidad: O(n*k) - por cada persona eliminamos, recorremos k posiciones
     */
    public static int josephusConArreglo(int n, int k) {
        boolean[] vivos = new boolean[n];
        for (int i = 0; i < n; i++) {
            vivos[i] = true;
        }
        
        int eliminados = 0;
        int posicion = 0;
        int contador = 0;
        
        System.out.println("Orden de eliminación:");
        while (eliminados < n - 1) {
            if (vivos[posicion]) {
                contador++;
                if (contador == k) {
                    vivos[posicion] = false;
                    eliminados++;
                    System.out.print("Posición " + (posicion + 1) + " ");
                    if (eliminados % 5 == 0) System.out.println();
                    contador = 0;
                }
            }
            posicion = (posicion + 1) % n;
        }
        System.out.println();
        
        // Encontrar el sobreviviente
        for (int i = 0; i < n; i++) {
            if (vivos[i]) return i + 1;
        }
        return -1;
    }
    
    /**
     * Implementación del problema de Josephus usando una lista circular
     * Complejidad: O(n*k) pero con mejor rendimiento en la práctica
     */
    public static int josephusConListaCircular(int n, int k) {
        // Crear lista circular
        Nodo primero = new Nodo(1);
        Nodo actual = primero;
        
        for (int i = 2; i <= n; i++) {
            actual.siguiente = new Nodo(i);
            actual = actual.siguiente;
        }
        actual.siguiente = primero; // Cerrar el círculo
        
        // Resolver el problema
        actual = primero;
        Nodo anterior = null;
        int eliminados = 0;
        
        System.out.println("Orden de eliminación:");
        while (eliminados < n - 1) {
            // Avanzar k-1 posiciones
            for (int i = 1; i < k; i++) {
                anterior = actual;
                actual = actual.siguiente;
            }
            
            // Eliminar el nodo actual
            System.out.print("Posición " + actual.valor + " ");
            if ((eliminados + 1) % 5 == 0) System.out.println();
            
            anterior.siguiente = actual.siguiente;
            actual = anterior.siguiente;
            eliminados++;
        }
        System.out.println();
        
        return actual.valor;
    }
    
    /**
     * Prueba de rendimiento con múltiples iteraciones
     */
    public static void pruebaRendimiento(int n, int k, int iteraciones) {
        long totalArreglo = 0;
        long totalLista = 0;
        
        for (int i = 0; i < iteraciones; i++) {
            long inicio = System.nanoTime();
            josephusConArregloSilencioso(n, k);
            long fin = System.nanoTime();
            totalArreglo += (fin - inicio);
            
            inicio = System.nanoTime();
            josephusConListaCircularSilencioso(n, k);
            fin = System.nanoTime();
            totalLista += (fin - inicio);
        }
        
        double promedioArreglo = totalArreglo / (double) iteraciones;
        double promedioLista = totalLista / (double) iteraciones;
        
        System.out.println("Tiempo promedio Arreglo: " + promedioArreglo + " ns");
        System.out.println("Tiempo promedio Lista Circular: " + promedioLista + " ns");
        
        if (promedioLista < promedioArreglo) {
            System.out.printf("Lista Circular es %.2fx más rápida\n", 
                            promedioArreglo / promedioLista);
        } else {
            System.out.printf("Arreglo es %.2fx más rápido\n", 
                            promedioLista / promedioArreglo);
        }
    }
    
    // Versiones silenciosas para pruebas de rendimiento
    private static int josephusConArregloSilencioso(int n, int k) {
        boolean[] vivos = new boolean[n];
        for (int i = 0; i < n; i++) vivos[i] = true;
        
        int eliminados = 0, posicion = 0, contador = 0;
        
        while (eliminados < n - 1) {
            if (vivos[posicion]) {
                contador++;
                if (contador == k) {
                    vivos[posicion] = false;
                    eliminados++;
                    contador = 0;
                }
            }
            posicion = (posicion + 1) % n;
        }
        
        for (int i = 0; i < n; i++) {
            if (vivos[i]) return i + 1;
        }
        return -1;
    }
    
    private static int josephusConListaCircularSilencioso(int n, int k) {
        Nodo primero = new Nodo(1);
        Nodo actual = primero;
        
        for (int i = 2; i <= n; i++) {
            actual.siguiente = new Nodo(i);
            actual = actual.siguiente;
        }
        actual.siguiente = primero;
        
        actual = primero;
        Nodo anterior = null;
        int eliminados = 0;
        
        while (eliminados < n - 1) {
            for (int i = 1; i < k; i++) {
                anterior = actual;
                actual = actual.siguiente;
            }
            anterior.siguiente = actual.siguiente;
            actual = anterior.siguiente;
            eliminados++;
        }
        
        return actual.valor;
    }
    
    /**
     * Clase auxiliar para nodos de la lista circular
     */
    static class Nodo {
        int valor;
        Nodo siguiente;
        
        Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }
}
