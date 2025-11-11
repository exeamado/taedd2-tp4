import java.util.Random;

/**
 * Actividad 1 - Simulación y control de aleatoriedad
 * Carrera: Ingeniería en Software
 * Materia: Taller de Algoritmos y Estructura de Datos II
 */
public class Actividad1_Simulacion {
    
    public static void main(String[] args) {
        System.out.println("=== ACTIVIDAD 1: SIMULACIÓN Y CONTROL DE ALEATORIEDAD ===\n");
        
        // Parte a) y b): Generar secuencia con semilla inicial
        long semilla1 = 12345L;
        System.out.println("1. Primera ejecución con semilla " + semilla1 + ":");
        int[] secuencia1 = generarSecuencia(semilla1, 100);
        imprimirSecuencia(secuencia1);
        
        // Parte c): Repetir con la misma semilla
        System.out.println("\n2. Segunda ejecución con la MISMA semilla " + semilla1 + ":");
        int[] secuencia2 = generarSecuencia(semilla1, 100);
        imprimirSecuencia(secuencia2);
        
        // Verificar que son idénticas
        boolean sonIdenticas = verificarIdenticas(secuencia1, secuencia2);
        System.out.println("\n¿Las secuencias son idénticas? " + sonIdenticas);
        
        // Parte d): Cambiar la semilla
        long semilla2 = 67890L;
        System.out.println("\n3. Tercera ejecución con NUEVA semilla " + semilla2 + ":");
        int[] secuencia3 = generarSecuencia(semilla2, 100);
        imprimirSecuencia(secuencia3);
        
        // Comparar diferencias
        System.out.println("\n¿Las secuencias 1 y 3 son idénticas? " + 
                           verificarIdenticas(secuencia1, secuencia3));
        
        // Parte 3 (Opcional): Dos objetos Random con misma semilla
        System.out.println("\n=== EXPERIMENTO OPCIONAL ===");
        System.out.println("Dos objetos Random distintos con la misma semilla:\n");
        dosObjetosRandom(semilla1);
    }
    
    /**
     * Genera una secuencia de números aleatorios entre 0 y 9
     */
    public static int[] generarSecuencia(long semilla, int cantidad) {
        Random random = new Random(semilla);
        int[] secuencia = new int[cantidad];
        
        for (int i = 0; i < cantidad; i++) {
            secuencia[i] = random.nextInt(10); // 0 a 9
        }
        
        return secuencia;
    }
    
    /**
     * Imprime la secuencia en formato de matriz 10x10
     */
    public static void imprimirSecuencia(int[] secuencia) {
        for (int i = 0; i < secuencia.length; i++) {
            System.out.print(secuencia[i] + " ");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }
    
    /**
     * Verifica si dos secuencias son idénticas
     */
    public static boolean verificarIdenticas(int[] seq1, int[] seq2) {
        if (seq1.length != seq2.length) return false;
        
        for (int i = 0; i < seq1.length; i++) {
            if (seq1[i] != seq2[i]) return false;
        }
        return true;
    }
    
    /**
     * Experimento opcional: dos objetos Random con misma semilla
     */
    public static void dosObjetosRandom(long semilla) {
        Random random1 = new Random(semilla);
        Random random2 = new Random(semilla);
        
        System.out.println("Primeros 20 números de cada objeto Random:\n");
        System.out.println("Random 1 | Random 2");
        System.out.println("---------|----------");
        
        boolean sonIdenticos = true;
        for (int i = 0; i < 20; i++) {
            int num1 = random1.nextInt(10);
            int num2 = random2.nextInt(10);
            System.out.printf("   %d     |    %d\n", num1, num2);
            if (num1 != num2) sonIdenticos = false;
        }
        
        System.out.println("\nConclusión: Las secuencias son " + 
                          (sonIdenticos ? "IDÉNTICAS" : "DIFERENTES"));
        System.out.println("Cada objeto Random con la misma semilla genera la misma secuencia.");
    }
}
