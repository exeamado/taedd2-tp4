@echo off
REM Script para compilar y ejecutar todos los programas del trabajo práctico
REM Algoritmos y Estructura de Datos II - DATA Investigaciones

echo ==========================================
echo TRABAJO PRACTICO - DATA INVESTIGACIONES
echo Algoritmos y Estructura de Datos II
echo ==========================================
echo.

REM Compilar todos los archivos
echo ==========================================
echo COMPILANDO PROGRAMAS
echo ==========================================
echo.

echo Compilando Actividad1_Simulacion.java...
javac Actividad1_Simulacion.java
if %errorlevel% equ 0 (
    echo [OK] Actividad 1 compilada exitosamente
) else (
    echo [ERROR] Error compilando Actividad 1
    exit /b 1
)

echo Compilando Actividad2_Josephus.java...
javac Actividad2_Josephus.java
if %errorlevel% equ 0 (
    echo [OK] Actividad 2 compilada exitosamente
) else (
    echo [ERROR] Error compilando Actividad 2
    exit /b 1
)

echo Compilando Actividad2_Josephus_BST.java...
javac Actividad2_Josephus_BST.java
if %errorlevel% equ 0 (
    echo [OK] Actividad 2 (BST) compilada exitosamente
) else (
    echo [ERROR] Error compilando Actividad 2 (BST)
    exit /b 1
)

echo Compilando Actividad3_Huffman.java...
javac Actividad3_Huffman.java
if %errorlevel% equ 0 (
    echo [OK] Actividad 3 compilada exitosamente
) else (
    echo [ERROR] Error compilando Actividad 3
    exit /b 1
)

echo.
echo Todos los programas compilados exitosamente!
echo.

REM Ejecutar programas
echo ==========================================
echo ACTIVIDAD 1: SIMULACION Y ALEATORIEDAD
echo ==========================================
echo.
java Actividad1_Simulacion
echo.

echo ==========================================
echo ACTIVIDAD 2: PROBLEMA DE JOSEPHUS
echo ==========================================
echo.
java Actividad2_Josephus
echo.

echo ==========================================
echo ACTIVIDAD 2 (OPCIONAL): JOSEPHUS CON BST
echo ==========================================
echo.
java Actividad2_Josephus_BST
echo.

echo ==========================================
echo ACTIVIDAD 3: COMPRESION HUFFMAN
echo ==========================================
echo.
java Actividad3_Huffman
echo.

echo ==========================================
echo EJECUCION COMPLETADA
echo ==========================================
echo.
echo Todos los programas se ejecutaron correctamente.
echo.
echo Revisa el informe tecnico para el analisis completo:
echo   --^> Informe_Tecnico_DATA_Investigaciones.docx
echo.
echo ==========================================
pause
