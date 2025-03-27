package com.ejemplo.bst;
//Clase para pruebas


public class Pruebas {
    public static void main(String[] args) {
        // Crear un árbol binario de búsqueda para enteros
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);

        // Insertar elementos
        System.out.println("Insertando elementos...");
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(2);
        tree.insert(7);
        tree.insert(12);
        tree.insert(20);


        System.out.println(tree.inOrder());
        tree.balance();
        // Mostrar el árbol en formato renderizado
        System.out.println("Árbol actual: " + tree.render());

        // Buscar elementos
        System.out.println("¿El árbol contiene 7? " + tree.contains(7));
        System.out.println("¿El árbol contiene 99? " + tree.contains(99));

        // Obtener el mínimo y máximo
        System.out.println("Valor mínimo del árbol: " + tree.minimum());
        System.out.println("Valor máximo del árbol: " + tree.maximum());

        // Tamaño y profundidad
        System.out.println("Tamaño del árbol: " + tree.size());
        System.out.println("Profundidad del árbol: " + tree.depth());

        // Eliminar un nodo hoja (7)
        System.out.println("Eliminando nodo hoja (7)...");
        tree.removeBranch(7);
        System.out.println("Árbol después de eliminar 7: " + tree.render());

        // Eliminar un nodo con un solo hijo (5)
        System.out.println("Eliminando nodo con un solo hijo (5)...");
        tree.removeBranch(5);
        System.out.println("Árbol después de eliminar 5: " + tree.render());

        System.out.println(tree.inOrder());
        tree.balance();

        // Eliminar un nodo con dos hijos (10)
        //System.out.println("Eliminando nodo con dos hijos (10)...");
        //tree.removeBranch(10);
        //System.out.println("Árbol después de eliminar 10: " + tree.render());

        // Mostrar el árbol en formato renderizado
        System.out.println("Árbol actual: " + tree.render());

    }
}
