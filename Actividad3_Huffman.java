import java.util.*;

/**
 * Actividad 3 - Compresión sin pérdida con Huffman
 * Carrera: Ingeniería en Software
 * Materia: Taller de Algoritmos y Estructura de Datos II
 */
public class Actividad3_Huffman {
    
    public static void main(String[] args) {
        String frase = "CIENCIA DE DATOS Y DESARROLLO";
        
        System.out.println("=== COMPRESIÓN HUFFMAN ===");
        System.out.println("Frase: \"" + frase + "\"");
        System.out.println("Longitud: " + frase.length() + " caracteres\n");
        
        // Paso 1: Calcular frecuencias
        Map<Character, Integer> frecuencias = calcularFrecuencias(frase);
        System.out.println("1. TABLA DE FRECUENCIAS:");
        mostrarFrecuencias(frecuencias);
        
        // Paso 2: Construir árbol de Huffman
        System.out.println("\n2. CONSTRUCCIÓN DEL ÁRBOL DE HUFFMAN:");
        NodoHuffman raiz = construirArbolHuffman(frecuencias);
        
        // Paso 3: Generar códigos
        Map<Character, String> codigos = new HashMap<>();
        generarCodigos(raiz, "", codigos);
        System.out.println("\n3. CÓDIGOS DE HUFFMAN (0=izquierda, 1=derecha):");
        mostrarCodigos(codigos, frecuencias);
        
        // Paso 4: Codificar la frase
        System.out.println("\n4. CODIFICACIÓN DE LA FRASE:");
        String fraseCodificada = codificar(frase, codigos);
        System.out.println("Frase codificada:");
        mostrarFraseCodificada(fraseCodificada);
        
        // Paso 5: Calcular tamaños y compresión
        System.out.println("\n5. ANÁLISIS DE COMPRESIÓN:");
        calcularCompresion(frase, fraseCodificada, codigos, frecuencias);
        
        // Paso 6: Decodificar (verificación)
        System.out.println("\n6. DECODIFICACIÓN (VERIFICACIÓN):");
        String fraseDecodificada = decodificar(fraseCodificada, raiz);
        System.out.println("Frase decodificada: \"" + fraseDecodificada + "\"");
        System.out.println("¿Es correcta? " + frase.equals(fraseDecodificada));
        
        // Visualización del árbol
        System.out.println("\n7. ESTRUCTURA DEL ÁRBOL:");
        visualizarArbol(raiz, "", true);
    }
    
    /**
     * Calcula la frecuencia de cada carácter en la frase
     */
    public static Map<Character, Integer> calcularFrecuencias(String frase) {
        Map<Character, Integer> frecuencias = new HashMap<>();
        for (char c : frase.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }
        return frecuencias;
    }
    
    /**
     * Muestra la tabla de frecuencias ordenada
     */
    public static void mostrarFrecuencias(Map<Character, Integer> frecuencias) {
        System.out.println("Carácter | Frecuencia");
        System.out.println("---------|------------");
        
        List<Map.Entry<Character, Integer>> lista = new ArrayList<>(frecuencias.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        for (Map.Entry<Character, Integer> entry : lista) {
            char c = entry.getKey();
            String carMostrar = (c == ' ') ? "ESPACIO" : String.valueOf(c);
            System.out.printf("   %s     |     %d\n", carMostrar, entry.getValue());
        }
    }
    
    /**
     * Construye el árbol de Huffman usando una cola de prioridad
     */
    public static NodoHuffman construirArbolHuffman(Map<Character, Integer> frecuencias) {
        PriorityQueue<NodoHuffman> cola = new PriorityQueue<>();
        
        // Crear nodos hoja para cada carácter
        for (Map.Entry<Character, Integer> entry : frecuencias.entrySet()) {
            cola.add(new NodoHuffman(entry.getKey(), entry.getValue()));
        }
        
        System.out.println("Proceso de construcción del árbol:");
        int paso = 1;
        
        // Construir el árbol
        while (cola.size() > 1) {
            NodoHuffman izq = cola.poll();
            NodoHuffman der = cola.poll();
            
            NodoHuffman padre = new NodoHuffman('\0', izq.frecuencia + der.frecuencia);
            padre.izquierda = izq;
            padre.derecha = der;
            
            System.out.printf("Paso %d: Combinar %s(%d) + %s(%d) = Nodo(%d)\n",
                paso++, 
                izq.caracter == '\0' ? "Nodo" : String.valueOf(izq.caracter),
                izq.frecuencia,
                der.caracter == '\0' ? "Nodo" : String.valueOf(der.caracter),
                der.frecuencia,
                padre.frecuencia);
            
            cola.add(padre);
        }
        
        return cola.poll();
    }
    
    /**
     * Genera los códigos binarios para cada carácter
     */
    public static void generarCodigos(NodoHuffman nodo, String codigo, 
                                     Map<Character, String> codigos) {
        if (nodo == null) return;
        
        if (nodo.esHoja()) {
            codigos.put(nodo.caracter, codigo.isEmpty() ? "0" : codigo);
            return;
        }
        
        generarCodigos(nodo.izquierda, codigo + "0", codigos);
        generarCodigos(nodo.derecha, codigo + "1", codigos);
    }
    
    /**
     * Muestra los códigos generados
     */
    public static void mostrarCodigos(Map<Character, String> codigos, 
                                     Map<Character, Integer> frecuencias) {
        System.out.println("Carácter | Frecuencia | Código");
        System.out.println("---------|------------|--------");
        
        List<Map.Entry<Character, String>> lista = new ArrayList<>(codigos.entrySet());
        lista.sort((a, b) -> a.getValue().length() - b.getValue().length());
        
        for (Map.Entry<Character, String> entry : lista) {
            char c = entry.getKey();
            String carMostrar = (c == ' ') ? "ESPACIO" : String.valueOf(c);
            System.out.printf("   %s     |     %d      |  %s\n", 
                carMostrar, frecuencias.get(c), entry.getValue());
        }
    }
    
    /**
     * Codifica la frase usando los códigos de Huffman
     */
    public static String codificar(String frase, Map<Character, String> codigos) {
        StringBuilder resultado = new StringBuilder();
        for (char c : frase.toCharArray()) {
            resultado.append(codigos.get(c));
        }
        return resultado.toString();
    }
    
    /**
     * Muestra la frase codificada en bloques
     */
    public static void mostrarFraseCodificada(String codificada) {
        for (int i = 0; i < codificada.length(); i += 50) {
            int fin = Math.min(i + 50, codificada.length());
            System.out.println(codificada.substring(i, fin));
        }
        System.out.println("\nLongitud total: " + codificada.length() + " bits");
    }
    
    /**
     * Calcula y muestra el análisis de compresión
     */
    public static void calcularCompresion(String original, String codificada,
                                         Map<Character, String> codigos,
                                         Map<Character, Integer> frecuencias) {
        int bitsASCII = original.length() * 8;
        int bitsHuffman = codificada.length();
        int ahorro = bitsASCII - bitsHuffman;
        double porcentaje = (ahorro * 100.0) / bitsASCII;
        
        System.out.println("Tamaño original (ASCII): " + bitsASCII + " bits");
        System.out.println("  (" + original.length() + " caracteres × 8 bits)");
        
        System.out.println("\nTamaño comprimido (Huffman): " + bitsHuffman + " bits");
        System.out.println("  Desglose por carácter:");
        for (Map.Entry<Character, Integer> entry : frecuencias.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();
            String codigo = codigos.get(c);
            int bitsUsados = freq * codigo.length();
            System.out.printf("    %s: %d × %d bits = %d bits\n",
                c == ' ' ? "ESPACIO" : String.valueOf(c),
                freq, codigo.length(), bitsUsados);
        }
        
        System.out.println("\nAhorro: " + ahorro + " bits");
        System.out.printf("Porcentaje de compresión: %.2f%%\n", porcentaje);
        System.out.printf("Factor de compresión: %.2fx\n", 
                         (double) bitsASCII / bitsHuffman);
    }
    
    /**
     * Decodifica una secuencia binaria usando el árbol de Huffman
     */
    public static String decodificar(String codificada, NodoHuffman raiz) {
        StringBuilder resultado = new StringBuilder();
        NodoHuffman actual = raiz;
        
        for (char bit : codificada.toCharArray()) {
            actual = (bit == '0') ? actual.izquierda : actual.derecha;
            
            if (actual.esHoja()) {
                resultado.append(actual.caracter);
                actual = raiz;
            }
        }
        
        return resultado.toString();
    }
    
    /**
     * Visualiza la estructura del árbol
     */
    public static void visualizarArbol(NodoHuffman nodo, String prefijo, boolean esUltimo) {
        if (nodo == null) return;
        
        System.out.print(prefijo);
        System.out.print(esUltimo ? "└── " : "├── ");
        
        if (nodo.esHoja()) {
            String carMostrar = (nodo.caracter == ' ') ? "ESPACIO" : 
                               String.valueOf(nodo.caracter);
            System.out.println(carMostrar + " [" + nodo.frecuencia + "]");
        } else {
            System.out.println("Nodo [" + nodo.frecuencia + "]");
            
            String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");
            visualizarArbol(nodo.izquierda, nuevoPrefijo, false);
            visualizarArbol(nodo.derecha, nuevoPrefijo, true);
        }
    }
    
    /**
     * Clase para representar un nodo del árbol de Huffman
     */
    static class NodoHuffman implements Comparable<NodoHuffman> {
        char caracter;
        int frecuencia;
        NodoHuffman izquierda;
        NodoHuffman derecha;
        
        NodoHuffman(char caracter, int frecuencia) {
            this.caracter = caracter;
            this.frecuencia = frecuencia;
            this.izquierda = null;
            this.derecha = null;
        }
        
        boolean esHoja() {
            return izquierda == null && derecha == null;
        }
        
        @Override
        public int compareTo(NodoHuffman otro) {
            return this.frecuencia - otro.frecuencia;
        }
    }
}
