#!/bin/bash

# Script para compilar y ejecutar todos los programas del trabajo práctico
# Algoritmos y Estructura de Datos II - DATA Investigaciones

echo "=========================================="
echo "TRABAJO PRÁCTICO - DATA INVESTIGACIONES"
echo "Algoritmos y Estructura de Datos II"
echo "=========================================="
echo ""

# Función para mostrar separadores
separador() {
    echo ""
    echo "=========================================="
    echo "$1"
    echo "=========================================="
    echo ""
}

# Compilar todos los archivos
separador "COMPILANDO PROGRAMAS"

echo "Compilando Actividad1_Simulacion.java..."
javac Actividad1_Simulacion.java
if [ $? -eq 0 ]; then
    echo "✓ Actividad 1 compilada exitosamente"
else
    echo "✗ Error compilando Actividad 1"
    exit 1
fi

echo "Compilando Actividad2_Josephus.java..."
javac Actividad2_Josephus.java
if [ $? -eq 0 ]; then
    echo "✓ Actividad 2 compilada exitosamente"
else
    echo "✗ Error compilando Actividad 2"
    exit 1
fi

echo "Compilando Actividad2_Josephus_BST.java..."
javac Actividad2_Josephus_BST.java
if [ $? -eq 0 ]; then
    echo "✓ Actividad 2 (BST) compilada exitosamente"
else
    echo "✗ Error compilando Actividad 2 (BST)"
    exit 1
fi

echo "Compilando Actividad3_Huffman.java..."
javac Actividad3_Huffman.java
if [ $? -eq 0 ]; then
    echo "✓ Actividad 3 compilada exitosamente"
else
    echo "✗ Error compilando Actividad 3"
    exit 1
fi

echo ""
echo "Todos los programas compilados exitosamente!"

# Ejecutar programas
separador "ACTIVIDAD 1: SIMULACIÓN Y ALEATORIEDAD"
java Actividad1_Simulacion

separador "ACTIVIDAD 2: PROBLEMA DE JOSEPHUS"
java Actividad2_Josephus

separador "ACTIVIDAD 2 (OPCIONAL): JOSEPHUS CON BST"
java Actividad2_Josephus_BST

separador "ACTIVIDAD 3: COMPRESIÓN HUFFMAN"
java Actividad3_Huffman

separador "EJECUCIÓN COMPLETADA"
echo "Todos los programas se ejecutaron correctamente."
echo ""
echo "Revisa el informe técnico para el análisis completo:"
echo "  → Informe_Tecnico_DATA_Investigaciones.docx"
echo ""
echo "=========================================="
