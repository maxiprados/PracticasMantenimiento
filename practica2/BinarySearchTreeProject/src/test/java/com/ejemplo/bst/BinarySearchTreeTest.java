package com.ejemplo.bst;

import com.ejemplo.bst.BinarySearchTree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {
    
    




    @Nested
    class removeBranchClass{
        @Test
        @DisplayName("Limpiar rama con un nodo hoja")
        public void arbolConRamaHoja_seElimina(){
            //Arrange
            Comparator<Integer> comparator = Integer::compareTo;
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);
            tree.insert(50);
            tree.insert(30);
            tree.insert(70);
            String expected = "50(30,)";

            //Act: Eliminamos cualquier rama con nodo hoja
            tree.removeBranch(70);

            //Assert: Nos aseguramos de haber eliminado 
            assertFalse(tree.contains(70));
            assertEquals(expected, tree.render());

        }
    }

}
