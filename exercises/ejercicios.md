# Ejercicios para clase

## Ejercicio 1
Dado el árbol:

```text
        10
       /  \
      5    15
     / \   / \
    2   7 12 20
```

Escriba manualmente:

- Preorden
- Inorden
- Postorden
- BFS

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

## Ejercicio 2
Modifique el árbol anterior agregando los nodos 1, 3, 18 y 25. Ejecute nuevamente los recorridos.

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

## Ejercicio 3
Implemente una función que cuente la cantidad total de nodos del árbol.

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

## Ejercicio 4
Implemente una función que cuente las hojas del árbol.

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

## Ejercicio 5 aplicado al proyecto final
Represente los módulos de un sistema web como un árbol binario. Ejemplo:

```text
            Sistema Web
           /           \
     Usuarios        Inventario
      /    \          /      \
 Registrar Buscar  Productos Reportes
```

Explique qué recorrido usaría para:

1. Mostrar el menú principal.
2. Procesar primero los módulos internos.
3. Mostrar módulos nivel por nivel.

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
