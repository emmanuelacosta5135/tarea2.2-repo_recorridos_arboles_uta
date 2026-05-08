import java.util.LinkedList;
import java.util.Queue;

public class Main {

    // ─────────────────────────────────────────────
    //  Clase Nodo
    // ─────────────────────────────────────────────
    static class Nodo {
        int dato;
        String modulo;
        Nodo izquierda;
        Nodo derecha;

        Nodo(int valor, String mod) {
            this.dato      = valor;
            this.modulo    = mod;
            this.izquierda = null;
            this.derecha   = null;
        }

        Nodo(int valor) {
            this(valor, "");
        }
    }

    // ─────────────────────────────────────────────
    //  Funcion auxiliar para imprimir un nodo
    // ─────────────────────────────────────────────
    static void imprimirNodo(Nodo n) {
        if (!n.modulo.isEmpty())
            System.out.print("[" + n.dato + " - " + n.modulo + "] ");
        else
            System.out.print(n.dato + " ");
    }

    // ─────────────────────────────────────────────
    //  PREORDEN: Raiz → Izquierda → Derecha
    // ─────────────────────────────────────────────
    static void preorden(Nodo raiz) {
        if (raiz == null) return;
        imprimirNodo(raiz);
        preorden(raiz.izquierda);
        preorden(raiz.derecha);
    }

    // ─────────────────────────────────────────────
    //  INORDEN: Izquierda → Raiz → Derecha
    //  Produce valores en orden ascendente en BST
    // ─────────────────────────────────────────────
    static void inorden(Nodo raiz) {
        if (raiz == null) return;
        inorden(raiz.izquierda);
        imprimirNodo(raiz);
        inorden(raiz.derecha);
    }

    // ─────────────────────────────────────────────
    //  POSTORDEN: Izquierda → Derecha → Raiz
    //  Util para procesar hijos antes que el padre
    // ─────────────────────────────────────────────
    static void postorden(Nodo raiz) {
        if (raiz == null) return;
        postorden(raiz.izquierda);
        postorden(raiz.derecha);
        imprimirNodo(raiz);
    }

    // ─────────────────────────────────────────────
    //  BFS: nivel por nivel usando Queue
    // ─────────────────────────────────────────────
    static void bfs(Nodo raiz) {
        if (raiz == null) return;
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int nodosEnNivel = cola.size();
            System.out.print("\n  Nivel " + nivel + ": ");
            for (int i = 0; i < nodosEnNivel; i++) {
                Nodo actual = cola.poll();
                imprimirNodo(actual);
                if (actual.izquierda != null) cola.add(actual.izquierda);
                if (actual.derecha   != null) cola.add(actual.derecha);
            }
            nivel++;
        }
    }

    // ─────────────────────────────────────────────
    //  Contar nodos totales
    // ─────────────────────────────────────────────
    static int contarNodos(Nodo raiz) {
        if (raiz == null) return 0;
        return 1 + contarNodos(raiz.izquierda) + contarNodos(raiz.derecha);
    }

    // ─────────────────────────────────────────────
    //  Contar nodos hoja (sin hijos)
    // ─────────────────────────────────────────────
    static int contarHojas(Nodo raiz) {
        if (raiz == null) return 0;
        if (raiz.izquierda == null && raiz.derecha == null) return 1;
        return contarHojas(raiz.izquierda) + contarHojas(raiz.derecha);
    }

    // ─────────────────────────────────────────────
    //  Altura del arbol
    // ─────────────────────────────────────────────
    static int altura(Nodo raiz) {
        if (raiz == null) return 0;
        return 1 + Math.max(altura(raiz.izquierda), altura(raiz.derecha));
    }

    // ─────────────────────────────────────────────
    //  Separador visual
    // ─────────────────────────────────────────────
    static void separador(String titulo) {
        System.out.println("\n================================================");
        System.out.println("  " + titulo);
        System.out.println("================================================");
    }

    // ─────────────────────────────────────────────
    //  MAIN
    // ─────────────────────────────────────────────
    public static void main(String[] args) {

        System.out.println("================================================");
        System.out.println("  RECORRIDOS DE ARBOLES BINARIOS - UTA          ");
        System.out.println("  Estructura de Datos | Tercero B               ");
        System.out.println("================================================");

        // ── Arbol original (7 nodos) ──────────────────
        separador("ARBOL ORIGINAL (7 nodos)");

        Nodo raiz = new Nodo(10);
        raiz.izquierda                    = new Nodo(5);
        raiz.derecha                      = new Nodo(15);
        raiz.izquierda.izquierda          = new Nodo(2);
        raiz.izquierda.derecha            = new Nodo(7);
        raiz.derecha.izquierda            = new Nodo(12);
        raiz.derecha.derecha              = new Nodo(20);

        System.out.print("\nPreorden   : "); preorden(raiz);
        System.out.print("\nInorden    : "); inorden(raiz);
        System.out.print("\nPostorden  : "); postorden(raiz);
        System.out.print("\nBFS        : "); bfs(raiz);
        System.out.println("\n\nTotal nodos : " + contarNodos(raiz));
        System.out.println("Nodos hoja  : " + contarHojas(raiz));
        System.out.println("Altura      : " + altura(raiz));

        raiz = null; // El Garbage Collector libera la memoria

        // ── Arbol ampliado: Sistema Web (11 nodos) ────
        separador("SISTEMA WEB - Arbol ampliado (11 nodos)");

        /*
                          10 (Sistema Principal)
                       /                  \
              5 (Usuarios)          15 (Inventario)
               /        \             /          \
          2 (Reg.)   7 (Busc.)  12 (Prod.)  20 (Rep.)
           /    \                           /       \
        1(Log.) 3(Elim.)             18(Actua.)  25(Export.)
        */

        Nodo sistema = new Nodo(10, "Sistema Principal");
        sistema.izquierda                             = new Nodo(5,  "Usuarios");
        sistema.derecha                               = new Nodo(15, "Inventario");
        sistema.izquierda.izquierda                   = new Nodo(2,  "Registrar");
        sistema.izquierda.derecha                     = new Nodo(7,  "Buscar");
        sistema.derecha.izquierda                     = new Nodo(12, "Productos");
        sistema.derecha.derecha                       = new Nodo(20, "Reportes");
        sistema.izquierda.izquierda.izquierda         = new Nodo(1,  "Login");
        sistema.izquierda.izquierda.derecha           = new Nodo(3,  "Eliminar");
        sistema.derecha.derecha.izquierda             = new Nodo(18, "Actualizar");
        sistema.derecha.derecha.derecha               = new Nodo(25, "Exportar");

        System.out.print("\nPreorden (menu jerarquico)    : \n  "); preorden(sistema);
        System.out.print("\n\nInorden  (orden ascendente)   : \n  "); inorden(sistema);
        System.out.print("\n\nPostorden (liberar recursos)  : \n  "); postorden(sistema);
        System.out.print("\n\nBFS      (nivel por nivel)    : "); bfs(sistema);
        System.out.println("\n\nTotal nodos : " + contarNodos(sistema));
        System.out.println("Nodos hoja  : " + contarHojas(sistema));
        System.out.println("Altura      : " + altura(sistema));

        sistema = null;

        System.out.println("\n================================================");
        System.out.println("  Fin del programa - Memoria gestionada por GC  ");
        System.out.println("================================================");
    }
}
