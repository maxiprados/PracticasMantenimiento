package org.mps.mutation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.EvolutionaryAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class GaussianMutationTest {
    @DisplayName("Mutacion sin cambios")
    @Test
    void testNoMutationOccursWhenRateIsZero() throws EvolutionaryAlgorithmException {
        //Arrange
        GaussianMutation mutation = new GaussianMutation(0.0, 1.0);
        int[] original = {1, 2, 3, 4};

        //Act
        int[] mutated = mutation.mutate(original);

        //Assert
        assertArrayEquals(original, mutated); // Deben ser iguales
    }

    @DisplayName("Mutacion completa correctamente hecha")
    @Test
    void testNoMutationOccursWhenRateIsOne() throws EvolutionaryAlgorithmException {
        //Arrange
        GaussianMutation mutation = new GaussianMutation(1.0, 1.0);
        int[] original = {1, 2, 3, 4};

        //Act
        int[] mutated = mutation.mutate(original);

        //Assert
        boolean atLeastOneMutated = false;
        for (int i = 0; i < mutated.length; i++) {
            if (original[i] != mutated[i]) {
                atLeastOneMutated = true;
            }
        }

        assertTrue(atLeastOneMutated);
    }

    @DisplayName("Mutacion invalida para tirar excepcion")
    @Test
    void testMutationThrowsExceptionOnNullInput() {
        //Arrange
        GaussianMutation mutation = new GaussianMutation(1.0, 1.0);

        //Act && Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> mutation.mutate(null));
    }
}
