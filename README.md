# Concesionaria Multimarcas

Trabajo Final Integrador de Programación II (UTN FRRe). Aplicación Java de consola para administrar automóviles, camionetas y motocicletas, nuevas o usadas.

## Requisitos y ejecución

Se necesita únicamente un JDK 17 o superior. No requiere Maven ni dependencias externas.

En PowerShell, desde la raíz del repositorio:

```powershell
$fuentes = Get-ChildItem -Recurse src -Filter *.java | ForEach-Object FullName
javac -encoding UTF-8 -d out $fuentes
java -cp out aplicacion.Principal
```

## Funciones incluidas

- Registrar automóviles, camionetas y motocicletas.
- Listar el inventario, buscar por ID y eliminar unidades.
- Registrar marca, modelo, año, color, precio, kilometraje, condición, estado de stock y atributos específicos del tipo de vehículo.
- Validar campos obligatorios e ID positivo; una unidad nueva debe tener 0 km.
- Guardar y volver a cargar el inventario en `data/vehiculos.txt`.

El archivo `data/vehiculos.txt` guarda una lista de vehículos mediante serialización Java (`ObjectOutputStream`/`ObjectInputStream`), como en la filmina. Aunque conserva la extensión `.txt`, su contenido es binario y no se puede editar como texto. Los modelos implementan `Serializable`. Si el archivo no existe, el inventario comienza vacío y se crea al guardar la primera unidad. Los inventarios delimitados V2/V3 anteriores se importan y se respaldan como `.v2.bak` o `.v3.bak` antes de convertirse al formato serializado.

## Organización

- `modelos`: clases de dominio entregadas, subtipos y enumeraciones.
- `servicio`: reglas del negocio y validaciones.
- `persistencia`: interfaz del repositorio e implementación en archivo.
- `aplicacion`: menú y lectura de consola.
- `excepcion`: excepciones propias.
- `documentacion`: informe técnico en preparación, guía de defensa y fuente del diagrama UML.

Las clases usan encapsulamiento, convenciones JavaBean, clases abstractas, herencia y sobrescritura de `toString`, `equals` y `hashCode`. El repositorio genérico se parametriza por entidad e identificador; la persistencia usa `Map` para localizar por ID numérico y `List` para exponer el inventario. El ingreso numérico usa `Integer` y `Double`. Los métodos están documentados brevemente con JavaDoc.

La carpeta `src/modelos` contiene las clases de dominio utilizadas por el resto de la aplicación. El paquete `modelos` permite importarlas desde las capas de aplicación, servicio y persistencia. La persistencia del inventario es exclusivamente en archivos locales; no hay conexión a SQL Server.

