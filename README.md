# concesionaria-multimarcas-tpi

Sistema de gestión de stock para una concesionaria multimarcas desarrollado en Java como Trabajo Final Integrador de Programación II (UTN FRRe, 2026). Implementa programación orientada a objetos, arquitectura en capas, persistencia de datos y gestión de vehículos.

## Estructura y compilación

`src` es la única carpeta de fuentes (ver `.vscode/settings.json`). Cada archivo debe declarar el paquete que coincide con su carpeta, por ejemplo `src/modelos/Vehiculo.java` empieza con `package modelos;`. Si se crea un paquete nuevo, se agrega la carpeta dentro de `src` y esa misma línea al archivo.

Para compilar desde la terminal, parado en la raíz del repositorio:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out aplicacion.Principal
```
