# Recorridos de Árboles Binarios - Estructura de Datos

**Universidad Técnica de Ambato**  
**Carrera:** Ingeniería de Software  
**Asignatura:** Estructura de Datos  
**Curso:** Tercero B  
**Tema:** Recorridos de árboles binarios: Inorden, Preorden, Postorden y BFS

UNIVERSIDAD TÉCNICA DE AMBATO
Facultad de Ingeniería en Sistemas, Electrónica e Industrial
Carrera de Ingeniería de Software







Asignatura:	Estructura de Datos
Curso:	Tercero B
Tema:	Recorridos de Árboles Binarios
Estudiante:	Emmanuel Acosta
Docente:	___________________________________
Fecha:	07/05/2026___________________________


Ambato — Ecuador
2025
 
I. INTRODUCCIÓN
Los árboles binarios constituyen una de las estructuras de datos no lineales más relevantes en el ámbito de la ingeniería de software. Su capacidad de organizar información de forma jerárquica, respetando una propiedad de ordenamiento en el caso de los Árboles Binarios de Búsqueda (BST), los convierte en herramientas fundamentales tanto en algoritmos de búsqueda eficiente como en la representación de sistemas con estructura de árbol, como menús de navegación, sistemas de archivos u organigramas organizacionales.
Un recorrido de árbol es el proceso sistemático de visitar cada nodo exactamente una vez siguiendo un orden determinado. Dependiendo del orden en que se visitan el nodo actual, su subárbol izquierdo y su subárbol derecho, se obtienen resultados completamente distintos que tienen aplicaciones específicas en problemas reales. Los tres recorridos en profundidad (DFS) más utilizados son Inorden, Preorden y Postorden, cada uno implementado de forma recursiva. El recorrido por anchura (BFS) opera nivel por nivel y requiere una cola como estructura auxiliar.
Esta práctica tuvo como objetivo implementar los cuatro recorridos en C++, verificar su correcto funcionamiento sobre un árbol de prueba definido con siete nodos, y adaptar la estructura para representar los módulos de un sistema web aplicado al proyecto final de la asignatura.
II. MARCO TEÓRICO
A. Árbol Binario de Búsqueda
Un Árbol Binario de Búsqueda es una estructura de datos en la que cada nodo almacena un valor y cumple la propiedad de ordenamiento: todos los valores del subárbol izquierdo son menores que el nodo padre, y todos los del subárbol derecho son mayores. Esta propiedad permite realizar búsquedas, inserciones y eliminaciones con una complejidad promedio de O(log n) en árboles balanceados.
B. Recorridos DFS — Profundidad
Los recorridos DFS (Depth-First Search) descienden lo más profundo posible por una rama antes de retroceder. Se implementan con recursión y siguen tres variantes:
Recorrido	Orden de visita	La raíz aparece	Caso de uso principal
Inorden	Izq → Raíz → Der	En el medio	Listar en orden ascendente
Preorden	Raíz → Izq → Der	Primero	Clonar el árbol
Postorden	Izq → Der → Raíz	Al final	Liberar memoria
Tabla I. Comparación de recorridos DFS.

C. Recorrido BFS — Anchura
El recorrido BFS (Breadth-First Search) visita los nodos nivel por nivel, de izquierda a derecha. No usa recursión sino una cola (queue): se encola la raíz, se desencola un nodo visitándolo, se encolan sus hijos si existen, y se repite hasta que la cola queda vacía. Garantiza que todos los nodos de un nivel son visitados antes de pasar al siguiente.

III. DESARROLLO DE LOS EJERCICIOS PLANTEADOS
Ejercicio 1 — Recorridos del árbol
Árbol dado:
        10
       /  \
      5    15
     / \   / \
    2   7 12  20

Preorden  (Raíz → Izq → Der): 10, 5, 2, 7, 15, 12, 20
Inorden   (Izq → Raíz → Der): 2, 5, 7, 10, 12, 15, 20
Postorden (Izq → Der → Raíz): 2, 7, 5, 12, 20, 15, 10
BFS       (nivel por nivel):   10, 5, 15, 2, 7, 12, 20

Ejercicio 2 — Árbol con nodos 1, 3, 18 y 25 agregados
Reglas de inserción BST aplicadas:
  1 < 10 → izq → 1 < 5 → izq → 1 < 2 → izq de 2
  3 < 10 → izq → 3 < 5 → izq → 3 > 2 → der de 2
 18 > 10 → der → 18 > 15 → der → 18 < 20 → izq de 20
 25 > 10 → der → 25 > 15 → der → 25 > 20 → der de 20

Árbol resultante:
              10
            /    \
           5      15
          / \    /  \
         2   7  12   20
        / \       /    \
       1   3     18    25

Preorden:  10, 5, 2, 1, 3, 7, 15, 12, 20, 18, 25
Inorden:   1, 2, 3, 5, 7, 10, 12, 15, 18, 20, 25
Postorden: 1, 3, 2, 7, 5, 12, 18, 25, 20, 15, 10
BFS:       10, 5, 15, 2, 7, 12, 20, 1, 3, 18, 25



Ejercicio 3 — Función para contar nodos totales
La función recorre el árbol completo de forma recursiva. El caso base es nullptr que devuelve 0. Para cada nodo se suma 1 más el conteo del subárbol izquierdo más el del derecho.
// Funcion privada recursiva
int contarNodos(Nodo* actual) {
    if (actual == nullptr) return 0;
    return 1 + contarNodos(actual->izquierdo)
             + contarNodos(actual->derecho);
}
// Metodo publico que expone la funcion
void mostrarTotalNodos() {
    cout << "Total de nodos: " << contarNodos(raiz) << endl;
}
Para el árbol del Ejercicio 1 el resultado es 7. Para el árbol del Ejercicio 2 el resultado es 11.

Ejercicio 4 — Función para contar nodos hoja
Un nodo es hoja cuando sus dos punteros son nullptr. La función verifica esa condición: si se cumple suma 1, si no, sigue recorriendo recursivamente hacia los hijos.
// Funcion privada recursiva
int contarHojas(Nodo* actual) {
    if (actual == nullptr) return 0;
    // Si no tiene hijos es hoja
    if (actual->izquierdo == nullptr && actual->derecho == nullptr)
        return 1;
    return contarHojas(actual->izquierdo)
         + contarHojas(actual->derecho);
}
// Metodo publico
void mostrarTotalHojas() {
    cout << "Total de hojas: " << contarHojas(raiz) << endl;
}
Para el árbol del Ejercicio 1 hay 4 hojas: 2, 7, 12 y 20. Para el árbol del Ejercicio 2 hay 5 hojas: 1, 3, 7, 12 y 18 y 25 (6 hojas en total).

Ejercicio 5 — Sistema web representado como árbol
Árbol del sistema:
            Sistema Web
           /           \
       Usuarios       Inventario
       /     \         /      \
  Registrar Buscar  Productos Reportes

Pregunta 1 — Mostrar el menú principal
Se usaría Preorden (Raíz → Izquierda → Derecha) porque muestra primero el módulo padre antes que sus submódulos. Esto refleja exactamente cómo funciona un menú: primero aparece la opción principal y luego sus opciones internas.
Resultado: Sistema Web → Usuarios → Registrar → Buscar → Inventario → Productos → Reportes

Pregunta 2 — Procesar primero los módulos internos
Se usaría Postorden (Izquierda → Derecha → Raíz) porque procesa primero todos los módulos hoja (los más específicos) antes que los nodos padre. Esto es útil cuando los módulos hijos deben estar listos o cargados antes de que el módulo padre pueda funcionar, como ocurre en sistemas de dependencias o inicialización de componentes.
Resultado: Registrar → Buscar → Usuarios → Productos → Reportes → Inventario → Sistema Web

Pregunta 3 — Mostrar módulos nivel por nivel
Se usaría BFS (Recorrido por niveles) porque visita los nodos nivel a nivel de arriba hacia abajo. Esto es ideal para mostrar la jerarquía del sistema de forma visual, donde primero aparece el sistema completo, luego los módulos principales y finalmente los submódulos.
Resultado: Sistema Web → Usuarios → Inventario → Registrar → Buscar → Productos → Reportes

IV. DESARROLLO E IMPLEMENTACIÓN
En c++:
A. Estructura del árbol de prueba
Para verificar el funcionamiento de los recorridos se construyó el siguiente árbol binario de siete nodos con raíz en el valor 10:
              10
            /    \
           5      15
          / \    /  \
         2   7  12   20
Fig. 1. Árbol binario de prueba con 7 nodos.
B. Estructura del nodo en C++
Cada nodo del árbol se representa mediante la siguiente estructura que encapsula el dato y los dos punteros a los subárboles:
struct Nodo {
    int dato;
    Nodo* izquierda;
    Nodo* derecha;
    Nodo(int valor) {
        dato = valor;
        izquierda = nullptr;
        derecha = nullptr;
    }
};
Fig. 2. Definición de la estructura Nodo en C++.
C. Implementación de los recorridos
A continuación se presenta el código completo de los cuatro recorridos implementados:
// Preorden: Raiz → Izquierda → Derecha
void preorden(Nodo* raiz) {
    if (raiz == nullptr) return;
    cout << raiz->dato << " ";
    preorden(raiz->izquierda);
    preorden(raiz->derecha);
}

// Inorden: Izquierda → Raiz → Derecha
void inorden(Nodo* raiz) {
    if (raiz == nullptr) return;
    inorden(raiz->izquierda);
    cout << raiz->dato << " ";
    inorden(raiz->derecha);
}

// Postorden: Izquierda → Derecha → Raiz
void postorden(Nodo* raiz) {
    if (raiz == nullptr) return;
    postorden(raiz->izquierda);
    postorden(raiz->derecha);
    cout << raiz->dato << " ";
}

// BFS: nivel por nivel usando cola
void bfs(Nodo* raiz) {
    if (raiz == nullptr) return;
    queue<Nodo*> cola;
    cola.push(raiz);
    while (!cola.empty()) {
        Nodo* actual = cola.front();
        cola.pop();
        cout << actual->dato << " ";
        if (actual->izquierda != nullptr) cola.push(actual->izquierda);
        if (actual->derecha  != nullptr) cola.push(actual->derecha);
    }
}
Fig. 3. Implementación completa de los cuatro recorridos en C++.
D. Árbol ampliado con nodos del sistema web
Como parte de la adaptación al proyecto final, se modificó el árbol para representar los módulos de un sistema web. Se agregaron los nodos 1, 3, 18 y 25, que representan operaciones concretas dentro de los módulos de Usuarios e Inventario:
    // Nodos adicionales del sistema web
    raiz->izquierda->izquierda->izquierda = new Nodo(1);  // Login
    raiz->izquierda->izquierda->derecha   = new Nodo(3);  // Eliminar usuario
    raiz->derecha->derecha->izquierda     = new Nodo(18); // Actualizar stock
    raiz->derecha->derecha->derecha       = new Nodo(25); // Exportar reporte
Fig. 4. Inserción de nodos adicionales representando módulos del sistema web.
El árbol ampliado queda de la siguiente forma:
                  10  (Sistema Principal)
               /        \
              5          15
           (Usuarios)  (Inventario)
            /    \       /      \
           2      7    12        20
        (Reg.) (Busc.) (Prod.)  (Rep.)
        / \                    /     \
       1   3                  18      25
    (Log.) (Elim.)        (Actua.) (Export.)
Fig. 5. Árbol ampliado representando módulos del sistema web.
V. RESULTADOS
A. Salida del árbol original (7 nodos)
Tras compilar y ejecutar el programa con g++ -std=c++11 main.cpp -o recorridos, se obtuvo la siguiente salida en consola:
RECORRIDOS DE ARBOLES BINARIOS - UTA
Preorden:   10 5 2 7 15 12 20
Inorden:    2 5 7 10 12 15 20
Postorden:  2 7 5 12 20 15 10
BFS:        10 5 15 2 7 12 20
Fig. 6. Salida en consola del árbol original de 7 nodos.
B. Análisis de resultados
Recorrido	Resultado obtenido
Preorden	10  5  2  7  15  12  20
Inorden	2  5  7  10  12  15  20
Postorden	2  7  5  12  20  15  10
BFS	10  5  15  2  7  12  20
Tabla II. Resultados obtenidos de la ejecución del programa.

El recorrido Inorden confirmó la propiedad fundamental del BST al producir los valores ordenados de menor a mayor: 2, 5, 7, 10, 12, 15, 20. El recorrido Preorden mostró la raíz en primera posición, mientras que el Postorden la dejó al final. El BFS recorrió correctamente los tres niveles del árbol: primero el nivel 0 (nodo 10), luego el nivel 1 (nodos 5 y 15), y finalmente el nivel 2 (nodos 2, 7, 12 y 20).
Código: 






















































VI. EVIDENCIA DE EJECUCIÓN
A continuación, se presentan las capturas de pantalla que evidencian el correcto funcionamiento del programa compilado y ejecutado en consola:


En java:
Código:
















































Funcionamiento:

VII. REPOSITORIO GITHUB
Link del repositorio de GITHUB con la información de esta trabajo:
https://github.com/emmanuelacosta5135/tarea2.2-repo_recorridos_arboles_uta.git
VII. DISCUSIÓN
Los resultados obtenidos durante la práctica permitieron verificar empíricamente las propiedades teóricas de cada recorrido. El recorrido Inorden demostró ser el único que produce los valores en orden ascendente, lo cual lo convierte en la herramienta más directa para verificar si un árbol es un BST válido o para exportar datos ordenados sin necesidad de un algoritmo de ordenamiento adicional.
El recorrido Preorden resultó particularmente interesante en el contexto del sistema web, ya que al colocar la raíz primero —en este caso el módulo Sistema Principal— refleja con exactitud la jerarquía de un menú de navegación donde la opción padre siempre aparece antes que sus opciones internas. Esto evidencia que la elección del recorrido no es arbitraria sino que debe responder al problema específico que se quiere resolver.
El BFS confirmó la importancia de la cola como estructura auxiliar: al procesar cada nodo y encolar inmediatamente sus hijos, se garantiza que ningún nodo de un nivel superior queda pendiente antes de pasar al nivel siguiente. Esta característica lo hace especialmente valioso en algoritmos de búsqueda del camino más corto y en la visualización de estructuras jerárquicas como organigramas o árboles de dependencias de proyectos de software.
IX. CONCLUSIONES
- La propiedad fundamental del BST queda demostrada por el recorrido Inorden: independientemente de cómo esté estructurado el árbol, siempre producirá los valores en orden ascendente, lo que equivale a obtener los datos ordenados sin costo adicional de procesamiento.
- Los tres recorridos DFS comparten la misma estructura recursiva básica. La única diferencia es el momento en que se visita el nodo actual: antes de descender (Preorden), entre los dos subárboles (Inorden) o después de ambos (Postorden). Comprender esta diferencia es suficiente para deducir el comportamiento de cualquiera de los tres.
- El BFS no puede implementarse con recursión directa porque necesita recordar todos los nodos pendientes de un nivel antes de avanzar al siguiente. La cola resuelve exactamente esta necesidad gracias a su política FIFO, lo que la convierte en la estructura de soporte natural para este tipo de recorrido.
- La adaptación del árbol para representar módulos de un sistema web demostró que las estructuras de datos no son conceptos abstractos aislados, sino herramientas directamente aplicables al diseño de software. Un sistema con módulos jerárquicos puede modelarse como un árbol y recorrerse con el algoritmo adecuado según la operación que se desee realizar.
- La implementación en C++ reforzó la comprensión del manejo de punteros, la asignación dinámica de memoria con new y la importancia del caso base en funciones recursivas para evitar bucles infinitos. Cada función de recorrido se compone de apenas cuatro líneas, lo que evidencia la elegancia y potencia de la recursividad como paradigma de resolución de problemas.
X. RECOMENDACIONES
- Agregar un destructor a la clase o una función liberarMemoria() que recorra el árbol en Postorden y libere cada nodo con delete. Esto evita fugas de memoria (memory leaks) que, aunque no son visibles en programas pequeños, se vuelven críticas en sistemas de producción.
- Implementar la función de inserción automática (insert) en lugar de construir el árbol manualmente nodo por nodo en el main. Esto permitirá probar el árbol con conjuntos de datos más grandes y variados, incluyendo casos desfavorables como insertar valores en orden ascendente.
- Añadir las funciones contarNodos() y contarHojas() vistas en los ejercicios de clase como parte del repositorio GitHub, documentando su funcionamiento en el README. Esto enriquece el entregable y demuestra un dominio más completo de las operaciones sobre árboles.
- Explorar la implementación de un árbol AVL como extensión natural de este trabajo. Al ser un BST autobalanceado, el AVL garantiza que el árbol nunca se degenere en una lista enlazada, manteniendo la complejidad O(log n) incluso en el peor caso.
- Para el proyecto final, considerar el uso del recorrido Preorden para generar el mapa de sitio del sistema web y del Postorden para implementar la secuencia de cierre o limpieza de módulos al finalizar la sesión de usuario.
XI. REFERENCIAS
[1]  T. H. Cormen, C. E. Leiserson, R. L. Rivest, y C. Stein, Introduction to Algorithms, 4th ed. Cambridge, MA: MIT Press, 2022.
[2]  B. Stroustrup, The C++ Programming Language, 4th ed. Upper Saddle River, NJ: Addison-Wesley, 2013.
[3]  M. A. Weiss, Data Structures and Algorithm Analysis in C++, 4th ed. Upper Saddle River, NJ: Pearson, 2014.
[4]  cppreference.com, "std::queue — C++ Reference," 2024. [En línea]. Disponible: https://en.cppreference.com/w/cpp/container/queue
[5]  Universidad Técnica de Ambato, Guía práctica: Recorridos de árboles binarios. Ambato: Facultad de Ingeniería en Sistemas, 2025.
