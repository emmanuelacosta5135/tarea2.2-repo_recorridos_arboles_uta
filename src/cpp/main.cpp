#include <iostream>
#include <queue>
using namespace std;

struct Nodo {
    int dato;
    string modulo;      
    Nodo* izquierda;
    Nodo* derecha;

    Nodo(int valor, string mod = "") {
        dato      = valor;
        modulo    = mod;
        izquierda = nullptr;
        derecha   = nullptr;
    }
};

void imprimirNodo(Nodo* n) {
    if (n->modulo != "")
        cout << "[" << n->dato << " - " << n->modulo << "] ";
    else
        cout << n->dato << " ";
}


void preorden(Nodo* raiz) {
    if (raiz == nullptr) return;
    imprimirNodo(raiz);
    preorden(raiz->izquierda);
    preorden(raiz->derecha);
}

void inorden(Nodo* raiz) {
    if (raiz == nullptr) return;
    inorden(raiz->izquierda);
    imprimirNodo(raiz);
    inorden(raiz->derecha);
}

void postorden(Nodo* raiz) {
    if (raiz == nullptr) return;
    postorden(raiz->izquierda);
    postorden(raiz->derecha);
    imprimirNodo(raiz);
}

void bfs(Nodo* raiz) {
    if (raiz == nullptr) return;
    queue<Nodo*> cola;
    cola.push(raiz);
    int nivel = 0;

    while (!cola.empty()) {
        int nodosEnNivel = cola.size();
        cout << "\n  Nivel " << nivel << ": ";
        for (int i = 0; i < nodosEnNivel; i++) {
            Nodo* actual = cola.front();
            cola.pop();
            imprimirNodo(actual);
            if (actual->izquierda != nullptr) cola.push(actual->izquierda);
            if (actual->derecha   != nullptr) cola.push(actual->derecha);
        }
        nivel++;
    }
}

int contarNodos(Nodo* raiz) {
    if (raiz == nullptr) return 0;
    return 1 + contarNodos(raiz->izquierda) + contarNodos(raiz->derecha);
}

int contarHojas(Nodo* raiz) {
    if (raiz == nullptr) return 0;
    if (raiz->izquierda == nullptr && raiz->derecha == nullptr) return 1;
    return contarHojas(raiz->izquierda) + contarHojas(raiz->derecha);
}

int altura(Nodo* raiz) {
    if (raiz == nullptr) return 0;
    return 1 + max(altura(raiz->izquierda), altura(raiz->derecha));
}

void liberarMemoria(Nodo* raiz) {
    if (raiz == nullptr) return;
    liberarMemoria(raiz->izquierda);
    liberarMemoria(raiz->derecha);
    delete raiz;
}

void separador(string titulo) {
    cout << "\n================================================" << endl;
    cout << "  " << titulo << endl;
    cout << "================================================" << endl;
}

int main() {

    cout << "================================================" << endl;
    cout << "  RECORRIDOS DE ARBOLES BINARIOS - UTA          " << endl;
    cout << "  Estructura de Datos | Tercero B               " << endl;
    cout << "================================================" << endl;

    separador("ARBOL ORIGINAL (7 nodos)");

    Nodo* raiz = new Nodo(10);
    raiz->izquierda                    = new Nodo(5);
    raiz->derecha                      = new Nodo(15);
    raiz->izquierda->izquierda         = new Nodo(2);
    raiz->izquierda->derecha           = new Nodo(7);
    raiz->derecha->izquierda           = new Nodo(12);
    raiz->derecha->derecha             = new Nodo(20);

    cout << "\nPreorden   : "; preorden(raiz);
    cout << "\nInorden    : "; inorden(raiz);
    cout << "\nPostorden  : "; postorden(raiz);
    cout << "\nBFS        : "; bfs(raiz);
    cout << "\n\nTotal nodos : " << contarNodos(raiz);
    cout << "\nNodos hoja  : " << contarHojas(raiz);
    cout << "\nAltura      : " << altura(raiz) << endl;

    liberarMemoria(raiz);
    raiz = nullptr;

    separador("SISTEMA WEB - Arbol ampliado (11 nodos)");

    Nodo* sistema = new Nodo(10, "Sistema Principal");
    sistema->izquierda                            = new Nodo(5,  "Usuarios");
    sistema->derecha                              = new Nodo(15, "Inventario");
    sistema->izquierda->izquierda                 = new Nodo(2,  "Registrar");
    sistema->izquierda->derecha                   = new Nodo(7,  "Buscar");
    sistema->derecha->izquierda                   = new Nodo(12, "Productos");
    sistema->derecha->derecha                     = new Nodo(20, "Reportes");
    sistema->izquierda->izquierda->izquierda      = new Nodo(1,  "Login");
    sistema->izquierda->izquierda->derecha        = new Nodo(3,  "Eliminar");
    sistema->derecha->derecha->izquierda          = new Nodo(18, "Actualizar");
    sistema->derecha->derecha->derecha            = new Nodo(25, "Exportar");

    cout << "\nPreorden (menu jerarquico)    : \n  "; preorden(sistema);
    cout << "\n\nInorden  (orden ascendente)   : \n  "; inorden(sistema);
    cout << "\n\nPostorden (liberar recursos)  : \n  "; postorden(sistema);
    cout << "\n\nBFS      (nivel por nivel)    : "; bfs(sistema);
    cout << "\n\nTotal nodos : " << contarNodos(sistema);
    cout << "\nNodos hoja  : " << contarHojas(sistema);
    cout << "\nAltura      : " << altura(sistema) << endl;

    liberarMemoria(sistema);
    sistema = nullptr;

    cout << "\n================================================" << endl;
    cout << "  Fin del programa - Memoria liberada           " << endl;
    cout << "================================================" << endl;

    return 0;
}
