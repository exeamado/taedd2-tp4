import java.util.TreeSet;

/**
 * Actividad 2 - OPCIONAL
 * Implementación del Problema de Josephus con Árbol Binario de Búsqueda Balanceado
 * Usando TreeSet de Java (implementado con Red-Black Tree)
 */
public class Actividad2_Josephus_BST {
    
    public static void main(String[] args) {
        int n = 20;
        int k = 4;
        
        System.out.println("=== JOSEPHUS CON ÁRBOL BINARIO BALANCEADO (TreeSet) ===");
        System.out.println("n = " + n + " personas");
        System.out.println("k = " + k + "\n");
        
        // Implementación con BST
        long inicioBST = System.nanoTime();
        int sobrevivienteBST = josephusConBST(n, k);
        long finBST = System.nanoTime();
        long tiempoBST = finBST - inicioBST;
        
        System.out.println("\nSobreviviente: Posición " + sobrevivienteBST);
        System.out.println("Tiempo: " + tiempoBST + " ns (" + 
                          (tiempoBST / 1000.0) + " µs)");
        
        // Comparación con otras implementaciones
        System.out.println("\n=== COMPARACIÓN DE LAS TRES IMPLEMENTACIONES ===");
        compararImplementaciones(n, k, 1000);
    }
    
    /**
     * Implementación usando TreeSet (Red-Black Tree)
     * Complejidad: O(n * k * log n)
     * - Búsqueda del k-ésimo elemento: O(k * log n)
     * - Eliminación: O(log n)
     * - Repetido n veces
     */
    public static int josephusConBST(int n, int k) {
        TreeSet<Integer> personas = new TreeSet<>();
        
        // Inicializar el conjunto con las posiciones 1 a n
        for (int i = 1; i <= n; i++) {
            personas.add(i);
        }
        
        int posicionActual = 0; // Índice lógico (0-based)
        
        System.out.println("Orden de eliminación:");
        
        while (personas.size() > 1) {
            // Calcular qué posición eliminar
            posicionActual = (posicionActual + k - 1) % personas.size();
            
            // Obtener el elemento en esa posición
            Integer[] array = personas.toArray(new Integer[0]);
            int aEliminar = array[posicionActual];
            
            System.out.print("Posición " + aEliminar + " ");
            if ((n - personas.size() + 1) % 5 == 0) System.out.println();
            
            // Eliminar
            personas.remove(aEliminar);
            
            // Ajustar la posición actual si es necesario
            if (posicionActual >= personas.size() && personas.size() > 0) {
                posicionActual = 0;
            }
        }
        
        System.out.println();
        return personas.first();
    }
    
    /**
     * Compara rendimiento de las tres implementaciones
     */
    public static void compararImplementaciones(int n, int k, int iteraciones) {
        long totalArreglo = 0;
        long totalLista = 0;
        long totalBST = 0;
        
        System.out.println("Ejecutando " + iteraciones + " iteraciones...\n");
        
        for (int i = 0; i < iteraciones; i++) {
            // Arreglo
            long inicio = System.nanoTime();
            josephusArregloSilencioso(n, k);
            totalArreglo += System.nanoTime() - inicio;
            
            // Lista Circular
            inicio = System.nanoTime();
            josephusListaSilencioso(n, k);
            totalLista += System.nanoTime() - inicio;
            
            // BST
            inicio = System.nanoTime();
            josephusBSTSilencioso(n, k);
            totalBST += System.nanoTime() - inicio;
        }
        
        double promArreglo = totalArreglo / (double) iteraciones;
        double promLista = totalLista / (double) iteraciones;
        double promBST = totalBST / (double) iteraciones;
        
        System.out.println("Resultados promedios:");
        System.out.println("┌─────────────────────┬──────────────┬──────────────┐");
        System.out.println("│ Implementación      │ Tiempo (ns)  │ Relativo     │");
        System.out.println("├─────────────────────┼──────────────┼──────────────┤");
        
        double min = Math.min(promArreglo, Math.min(promLista, promBST));
        
        System.out.printf("│ Arreglo             │ %12.2f │ %.2fx         │\n", 
                         promArreglo, promArreglo / min);
        System.out.printf("│ Lista Circular      │ %12.2f │ %.2fx         │\n", 
                         promLista, promLista / min);
        System.out.printf("│ BST (TreeSet)       │ %12.2f │ %.2fx         │\n", 
                         promBST, promBST / min);
        System.out.println("└─────────────────────┴──────────────┴──────────────┘");
        
        // Análisis de complejidad
        System.out.println("\nAnálisis de complejidad teórica:");
        System.out.println("• Arreglo:        O(n × k) - Simple pero ineficiente con k grande");
        System.out.println("• Lista Circular: O(n × k) - Mejor en práctica, elimina directamente");
        System.out.println("• BST Balanceado: O(n × k × log n) - Más complejo pero escalable");
        
        System.out.println("\nConclusión:");
        if (promLista < promArreglo && promLista < promBST) {
            System.out.println("Lista Circular es la más eficiente para este caso.");
            System.out.println("Razón: Eliminación directa sin búsquedas, estructura simple.");
        } else if (promBST < promLista && promBST < promArreglo) {
            System.out.println("BST es la más eficiente para este caso.");
            System.out.println("Razón: Log n para operaciones con n muy grande.");
        } else {
            System.out.println("Arreglo es la más eficiente para este caso.");
            System.out.println("Razón: Simplicidad con n y k pequeños.");
        }
    }
    
    // Implementaciones silenciosas para benchmarking
    
    private static int josephusArregloSilencioso(int n, int k) {
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
    
    private static int josephusListaSilencioso(int n, int k) {
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
    
    private static int josephusBSTSilencioso(int n, int k) {
        TreeSet<Integer> personas = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            personas.add(i);
        }
        
        int posicionActual = 0;
        
        while (personas.size() > 1) {
            posicionActual = (posicionActual + k - 1) % personas.size();
            Integer[] array = personas.toArray(new Integer[0]);
            int aEliminar = array[posicionActual];
            personas.remove(aEliminar);
            
            if (posicionActual >= personas.size() && personas.size() > 0) {
                posicionActual = 0;
            }
        }
        
        return personas.first();
    }
    
    static class Nodo {
        int valor;
        Nodo siguiente;
        
        Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }
}
