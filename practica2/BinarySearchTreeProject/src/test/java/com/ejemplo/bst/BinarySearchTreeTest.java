package com.ejemplo.bst;

import com.ejemplo.bst.BinarySearchTree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;




public class BinarySearchTreeTest{
    @Nested
    class removeBranchAdditionalTests {

        @Test
        @DisplayName("No eliminar rama cuando el valor es null")
        public void noEliminarRamaCuandoValorEsNull() {
            // Arrange
            Comparator<Integer> comparator = Integer::compareTo;
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);
            tree.insert(50);
            tree.insert(30);
            tree.insert(70);
            String expected = "50(30,70)";

            // Act: Intentamos eliminar una rama con valor null
            tree.removeBranch(null);

            // Assert: El árbol permanece igual
            assertEquals(expected, tree.render());
        }

        @Test
        @DisplayName("Eliminar nodo raíz con hijos")
        public void eliminarNodoRaizConHijos() {
            // Arrange
            Comparator<Integer> comparator = Integer::compareTo;
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);
            tree.insert(50);
            tree.insert(30);
            tree.insert(70);

            // Act: Eliminamos el nodo raíz
            tree.removeBranch(50);

            // Assert: El árbol queda vacío
            assertFalse(tree.contains(50));
            assertEquals("", tree.render());
        }

        @Test
        @DisplayName("Eliminar nodo inexistente en el árbol")
        public void eliminarNodoInexistente() {
            // Arrange
            Comparator<Integer> comparator = Integer::compareTo;
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);
            tree.insert(50);
            tree.insert(30);
            tree.insert(70);
            String expected = "50(30,70)";

            // Act: Intentamos eliminar un nodo que no existe
            tree.removeBranch(100);

            // Assert: El árbol permanece igual
            assertEquals(expected, tree.render());
        }

        @Test
        @DisplayName("Eliminar nodo intermedio con subárbol izquierdo")
        public void eliminarNodoIntermedioConSubarbolIzquierdo() {
            // Arrange
            Comparator<Integer> comparator = Integer::compareTo;
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);
            tree.insert(50);
            tree.insert(30);
            tree.insert(20);
            String expected = "50(,)";

            // Act: Eliminamos el nodo intermedio
            tree.removeBranch(30);

            // Assert: El subárbol izquierdo se elimina
            assertFalse(tree.contains(30));
            assertEquals(expected, tree.render());
        }

        @Test
        @DisplayName("Eliminar nodo intermedio con subárbol derecho")
        public void eliminarNodoIntermedioConSubarbolDerecho() {
            // Arrange
            Comparator<Integer> comparator = Integer::compareTo;
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);
            tree.insert(50);
            tree.insert(70);
            tree.insert(80);
            String expected = "50(,)";

            // Act: Eliminamos el nodo intermedio
            tree.removeBranch(70);

            // Assert: El subárbol derecho se elimina
            assertFalse(tree.contains(70));
            assertEquals(expected, tree.render());
        }

        @Test
        @DisplayName("Eliminar nodo inexistente cuando no hay subárbol izquierdo")
        public void eliminarNodoInexistenteSinSubarbolIzquierdo() {
            // Arrange
            Comparator<Integer> comparator = Integer::compareTo;
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);
            tree.insert(50); // Nodo raíz
            tree.insert(70); // Solo subárbol derecho
            String expected = "50(,70)";

            // Act: Intentamos eliminar un nodo inexistente en el subárbol izquierdo (que no existe)
            tree.removeBranch(30);

            // Assert: El árbol permanece igual
            assertEquals(expected, tree.render());
        }

        @Test
        @DisplayName("Eliminar nodo existente en el subárbol izquierdo")
        public void eliminarNodoExistenteEnSubarbolIzquierdo() {
            // Arrange
            Comparator<Integer> comparator = Integer::compareTo;
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);
            tree.insert(50); // Nodo raíz
            tree.insert(30); // Subárbol izquierdo
            tree.insert(20); // Nodo en el subárbol izquierdo
            String expected = "50(30(,),)";

            // Act: Eliminamos un nodo existente en el subárbol izquierdo
            tree.removeBranch(20);

            // Assert: El nodo se elimina correctamente
            assertFalse(tree.contains(20));
            assertEquals(expected, tree.render());
        }
    }
}
