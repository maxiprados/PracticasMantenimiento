package com.ejemplo.bst;

import com.ejemplo.bst.BinarySearchTree;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BinarySearchTreeTest{
    @Nested
    class insertTests{
        Comparator<Integer> comparator = Integer::compareTo;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);
        @Test
        @DisplayName("Insert funciona correctamente")
        void insertTestRaiz(){
            //Arrange
            int nodo = 5;
            String expected = "5";
            //Act
            tree.insert(nodo);
            //Assert
            assertEquals(expected, tree.render());
        }

        @Test
        @DisplayName("Insertar un elemento a la izquierda funciona correctamente")
        void insertTestIzquierdo(){
            //Arrange
            int nodo = 5;
            int nodoIzquierdo = 4;
            String expected = "5(4,)";
            //Act
            tree.insert(nodo);
            tree.insert(nodoIzquierdo);
            //Assert
            assertEquals(expected, tree.render());
        }

        @Test
        @DisplayName("Insertar un elemento a la derecha funciona correctamente")
        void insertTestDerecho(){
            //Arrange
            int nodo = 5;
            int nodoIzquierdo = 6;
            String expected = "5(,6)";
            //Act
            tree.insert(nodo);
            tree.insert(nodoIzquierdo);
            //Assert
            assertEquals(expected, tree.render());
        }

        @Test
        @DisplayName("Insertar un elemento que ya esta en el arbol")
        void insertTestDuplicado(){
            //Arrange
            int nodo = 5;
            //Act & Assert
            tree.insert(nodo);
            assertThrows(BinarySearchTreeException.class, () -> tree.insert(nodo));
            //Assert
        }
    }
    @Nested
    class renderTests{
        Comparator<Integer> comparator = Integer::compareTo;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);

        @Test
        @DisplayName("Render funciona correctamente")
        public void renderCorrectamente(){
            //Arrange
            int nodoUno = 5;
            int nodoDos = 4;
            int nodoTres = 6;
            int nodoCuatro = 7;
            int nodoCinco = -1;
            String expected = "5(4(-1,),6(,7))";

            //Act
            tree.insert(nodoUno);
            tree.insert(nodoDos);
            tree.insert(nodoTres);
            tree.insert(nodoCuatro);
            tree.insert(nodoCinco);

            //Assert
            assertEquals(expected, tree.render());
        }

    }
    @Nested
    class containsTests{
        Comparator<Integer> comparator = Integer::compareTo;
        BinarySearchTree<Integer> tree;

        @BeforeEach
        void setup() {
            tree = new BinarySearchTree<>(comparator);
        }

        @Test
        @DisplayName("Contains funciona correctamente en el arbol derecho")
        public void containsTestNodoNull(){
            //Arrange & Act & Assert
            assertTrue(tree.contains(null) == false);
        }

        @Test
        @DisplayName("Contains funciona correctamente en la raiz")
        public void containsTestRaiz(){
            //Arrange
            int nodoUno = 5;

            //Act
            tree.insert(nodoUno);

            //Assert
            assertTrue(tree.contains(nodoUno));
        }

        @Test
        @DisplayName("Contains funciona correctamente en el arbol izquierdo")
        public void containsTestNodoIzq(){
            //Arrange
            int nodoUno = 5;
            int nodoDos = 4;

            //Act
            tree.insert(nodoUno);
            tree.insert(nodoDos);

            //Assert
            assertTrue(tree.contains(nodoDos));
        }

        @Test
        @DisplayName("Contains funciona correctamente en el arbol derecho")
        public void containsTestNodoDer(){
            //Arrange
            int nodoUno = 5;
            int nodoDos = 6;

            //Act
            tree.insert(nodoUno);
            tree.insert(nodoDos);

            //Assert
            assertTrue(tree.contains(nodoDos));
        }


    }
    @Nested
    class isLeafTests{
        Comparator<Integer> comparator = Integer::compareTo;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);

        @Test
        @DisplayName("Comprobar que un nodo es hoja")
        public void isLeafTestTrue(){
            //Arrange
            int nodoUno = 5;

            //Act
            tree.insert(nodoUno);

            //Assert
            assertTrue(tree.isLeaf());
        }

        @Test
        @DisplayName("Comprobar que un nodo no es hoja")
        public void isLeafTestFalseIzq(){
            //Arrange
            int nodoUno = 5;
            int nodoDos = 4;

            //Act
            tree.insert(nodoUno);
            tree.insert(nodoDos);

            //Assert
            assertFalse(tree.isLeaf());
        }

        @Test
        @DisplayName("Comprobar que un nodo no es hoja")
        public void isLeafTestFalseDer(){
            //Arrange
            int nodoUno = 5;
            int nodoDos = 6;

            //Act
            tree.insert(nodoUno);
            tree.insert(nodoDos);

            //Assert
            assertFalse(tree.isLeaf());
        }

        @Test
        @DisplayName("Comprobar que un nodo no es hoja siendo nulo")
        public void isLeafTestFalseNull(){
            //Arrange & Act & Assert
            assertFalse(tree.isLeaf());
        }
    }
    @Nested
    class minimumTests{
        Comparator<Integer> comparator = Integer::compareTo;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);

        @Test
        @DisplayName("El minimum es falso si le pasamos un nulo")
        public void minimumTestNull(){
            //Arrange & Act & Assert
            assertTrue(tree.minimum() == null);
        }

        @Test
        @DisplayName("El minimum coincide con el valor minimo cuando solo hay un nodo")
        public void minimumTestMinRaiz(){
            //Arrange
            int nodoUno = 5;
            //Act
            tree.insert(nodoUno);
            //Assert
            assertEquals(nodoUno,tree.minimum());
        }

        @Test
        @DisplayName("El minimum coincide con el valor minimo cuando solo hay un nodo")
        public void minimumTestMinNodoIzq(){
            //Arrange
            int nodoUno = 5;
            int nodoDos = 4;
            //Act
            tree.insert(nodoUno);
            tree.insert(nodoDos);
            //Assert
            assertEquals(nodoDos,tree.minimum());
        }
    }
    @Nested
    class maximumTests{
        Comparator<Integer> comparator = Integer::compareTo;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);

        @Test
        @DisplayName("El maximum es falso si le pasamos un nulo")
        public void maximumTestNull(){
            //Arrange & Act & Assert
            assertTrue(tree.maximum() == null);
        }

        @Test
        @DisplayName("El maximum coincide con el valor maximo cuando solo hay un nodo")
        public void maximumTestMaxRaiz(){
            //Arrange
            int nodoUno = 5;
            //Act
            tree.insert(nodoUno);
            //Assert
            assertEquals(nodoUno,tree.maximum());
        }

        @Test
        @DisplayName("El maximum coincide con el valor maximo cuando hay mas de un nodo")
        public void maximumTestMaxRaizNodoDer(){
            //Arrange
            int nodoUno = 5;
            int nodoDos = 6;
            //Act
            tree.insert(nodoUno);
            tree.insert(nodoDos);
            //Assert
            assertEquals(nodoDos,tree.maximum());
        }
    }
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
