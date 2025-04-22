//Pedro Scarpati Sundblad y Máximo Prados Meléndez
package org.mps.selection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.EvolutionaryAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentSelectionTest {

    @Test
    @DisplayName("Constructor lanza excepción si el tamaño del torneo es 0 o menor")
    void testConstructorThrowsExceptionForInvalidTournamentSize() {
        // Arrange - nada que preparar porque se prueba directamente la construcción

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> new TournamentSelection(0));
        assertThrows(EvolutionaryAlgorithmException.class, () -> new TournamentSelection(-1));
    }

    @Test
    @DisplayName("Select lanza excepción si el individuo es nulo o de tamaño menor que el torneo")
    void testSelectThrowsExceptionForInvalidInput() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selection = new TournamentSelection(3);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> selection.select(null));
        assertThrows(EvolutionaryAlgorithmException.class, () -> selection.select(new int[]{1, 2}));

    }

    @Test
    @DisplayName("Select lanza excepción si la poblacion es de tamanyo 0")
    public void testSelectThrowsExceptionForEmptyPopulation() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selection = new TournamentSelection(3);
        int[] individual = {};

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> selection.select(individual));
    }

    @Test
    @DisplayName("Select devuelve un array del mismo tamaño que el original")
    void testSelectReturnsArrayOfSameSize() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selection = new TournamentSelection(3);
        int[] individual = {1, 5, 3, 10, 2, 6};

        // Act
        int[] selected = selection.select(individual);

        // Assert
        assertEquals(individual.length, selected.length);
    }

    @Test
    @DisplayName("Todos los genes seleccionados deben existir en el individuo original")
    void testSelectReturnsGenesFromOriginalIndividual() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selection = new TournamentSelection(3);
        int[] individual = {1, 5, 3, 10, 2, 6};

        // Act
        int[] selected = selection.select(individual);

        // Assert
        for (int gene : selected) {
            assertTrue(contains(individual, gene));
        }
    }

    // Función auxiliar para verificar que un valor existe en un array
    private boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }
}
