# Trabajo Práctico: Algoritmos y Estructura de Datos II
## Simulación, Aleatoriedad, Compresión y Eficiencia Algorítmica

### Caso: DATA Investigaciones

---

## Contenido del Proyecto

Este proyecto contiene la implementación completa del trabajo práctico que incluye:

### Archivos Java:
1. **Actividad1_Simulacion.java** - Simulación y control de aleatoriedad
2. **Actividad2_Josephus.java** - Problema de Josephus (Arreglo y Lista Circular)
3. **Actividad2_Josephus_BST.java** - Problema de Josephus (implementación opcional con BST)
4. **Actividad3_Huffman.java** - Compresión sin pérdida con Huffman

### Documentación:
- **Informe_Tecnico_DATA_Investigaciones.docx** - Informe técnico completo (2-3 páginas)
- **README.md** - Este archivo con instrucciones

---

## Requisitos

- Java JDK 8 o superior
- Sistema operativo: Windows, Linux o macOS

---

## Instrucciones de Compilación y Ejecución

### Opción 1: Compilar y ejecutar todos los programas

```bash
# Compilar todos los archivos
javac Actividad1_Simulacion.java
javac Actividad2_Josephus.java
javac Actividad2_Josephus_BST.java
javac Actividad3_Huffman.java

# Ejecutar cada programa
java Actividad1_Simulacion
java Actividad2_Josephus
java Actividad2_Josephus_BST
java Actividad3_Huffman
```

### Opción 2: Ejecutar programas individualmente

#### Actividad 1: Simulación y Control de Aleatoriedad
```bash
javac Actividad1_Simulacion.java
java Actividad1_Simulacion
```

**Salida esperada:**
- Secuencia de 100 números aleatorios (0-9) con semilla 12345
- Repetición de la misma secuencia con la misma semilla
- Nueva secuencia con semilla 67890
- Verificación de que las secuencias idénticas con misma semilla
- Experimento opcional con dos objetos Random

#### Actividad 2: Problema de Josephus
```bash
javac Actividad2_Josephus.java
java Actividad2_Josephus
```

**Salida esperada:**
- Orden de eliminación con arreglo simple
- Tiempo de ejecución en nanosegundos
- Orden de eliminación con lista circular
- Tiempo de ejecución en nanosegundos
- Comparación de rendimiento
- Prueba con 1000 iteraciones para mejor medición

#### Actividad 2 (Opcional): Josephus con BST
```bash
javac Actividad2_Josephus_BST.java
java Actividad2_Josephus_BST
```

**Salida esperada:**
- Implementación con TreeSet (Red-Black Tree)
- Comparación de las tres implementaciones
- Tabla comparativa de rendimiento
- Análisis de complejidad

#### Actividad 3: Compresión Huffman
```bash
javac Actividad3_Huffman.java
java Actividad3_Huffman
```

**Salida esperada:**
- Tabla de frecuencias de caracteres
- Proceso de construcción del árbol
- Códigos de Huffman asignados
- Frase codificada en binario
- Análisis de compresión (ASCII vs Huffman)
- Decodificación y verificación
- Visualización del árbol de Huffman

---

## Descripción de las Actividades

### Actividad 1: Simulación y Aleatoriedad
Demuestra cómo controlar la generación de números pseudoaleatorios usando semillas, permitiendo reproducibilidad en simulaciones críticas.

**Conceptos clave:**
- Generadores pseudoaleatorios
- Control de semillas
- Reproducibilidad en testing
- Debugging determinístico

### Actividad 2: Problema de Josephus
Compara tres estructuras de datos diferentes para resolver el mismo problema, evaluando eficiencia práctica vs complejidad teórica.

**Estructuras comparadas:**
- Arreglo simple: O(n × k)
- Lista circular: O(n × k) pero más eficiente en práctica
- BST balanceado: O(n × k × log n)

**Conceptos clave:**
- Análisis de complejidad algorítmica
- Medición empírica de rendimiento
- Benchmarking con System.nanoTime()
- Trade-offs entre estructuras

### Actividad 3: Compresión Huffman
Implementa el algoritmo de Huffman para compresión sin pérdida, demostrando cómo códigos de longitud variable optimizan el espacio.

**Conceptos clave:**
- Árboles binarios de Huffman
- Códigos de prefijo
- Cola de prioridad
- Análisis de compresión

---

## Resultados Esperados

### Actividad 1:
- Verificación de que las secuencias son idénticas con la misma semilla
- Confirmación de que semillas diferentes producen secuencias diferentes
- Comprensión de la importancia de la reproducibilidad

### Actividad 2:
**Rendimiento esperado (aprox):**
- Arreglo: ~85,000 ns (factor 1.3x)
- Lista Circular: ~65,000 ns (factor 1.0x - MEJOR)
- BST: ~120,000 ns (factor 1.8x)

*Nota: Los tiempos pueden variar según el hardware*

### Actividad 3:
**Compresión esperada:**
- Tamaño original: 240 bits (30 caracteres × 8 bits)
- Tamaño comprimido: ~147 bits
- Compresión: ~38.75%
- Factor: 1.63x

---

## Preguntas de Reflexión (Ver Informe)

El informe técnico incluye respuestas completas a:

1. ¿Qué ventaja tiene poder repetir exactamente una secuencia aleatoria?
2. ¿Qué riesgos habría si no se controla la semilla en una simulación crítica?
3. ¿En qué casos reales conviene que la simulación sea impredecible?
4. ¿Qué estructura fue más eficiente en Josephus?
5. ¿Cuál representa mejor la simulación real de un círculo?
6. ¿Por qué la eficiencia cambia según la estructura?
7. ¿Por qué algunos caracteres tienen códigos más cortos en Huffman?
8. ¿Qué pasaría si se repitiera una letra poco frecuente muchas veces más?
9. ¿Cómo cambiaría el árbol y los códigos?
10. Comparación entre Huffman y otros métodos (LZW, RLE, DEFLATE)
11. Reflexión ética sobre compresión en datos críticos

---

## Estructura del Informe Técnico

El informe de 3 páginas incluye:

1. **Portada**
2. **Resumen Ejecutivo**
3. **Introducción al Caso**
4. **Simulación y Control de Aleatoriedad**
   - Implementación y resultados
   - Análisis de reproducibilidad
   - Casos de uso
5. **Problema de Josephus**
   - Comparación de implementaciones
   - Análisis de rendimiento
   - Ventajas y desventajas de cada estructura
6. **Compresión Huffman**
   - Tabla de frecuencias
   - Análisis de compresión
   - Reflexión sobre el algoritmo
7. **Comparación de Métodos de Compresión**
   - Tabla comparativa (Huffman, LZW, RLE, DEFLATE)
   - Criterios de decisión técnica
   - Conclusiones sobre cuándo usar cada método
8. **Reflexión Ética y Profesional**
   - Consecuencias de compresión con pérdida
   - Responsabilidad ante alteración de datos
   - Priorización: precisión vs eficiencia
   - Rol del ingeniero en software
9. **Conclusiones Generales**

---

## Notas Adicionales

- Los programas están comentados y documentados
- Cada método incluye su documentación JavaDoc
- Los tiempos de ejecución pueden variar según el hardware
- Los programas incluyen salida formateada para mejor legibilidad
- El código está optimizado para ser educativo y comprensible

---

## Autor

Estudiante de Ingeniería en Software
Taller de Algoritmos y Estructura de Datos II
2025

---

## Licencia

Este proyecto es material académico para fines educativos.
