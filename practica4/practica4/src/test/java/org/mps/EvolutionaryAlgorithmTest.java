package org.mps;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.mutation.*;
import org.mps.crossover.*;
import org.mps.selection.*;

public class EvolutionaryAlgorithmTest {
    
    @Test
    @DisplayName("Test EvolutionaryAlgorithm - Constructor con parámetros nulos")
    public void evolutionaryAlgorithm_ConstructorParametrosNulos_ThrowsException(){
        // Arrange
        SelectionOperator selectionOperator = null;
        MutationOperator mutationOperator = null;
        CrossoverOperator crossoverOperator = new TwoPointCrossover();
        
        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class,() -> {
            new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        });
    }
    
    @Test
    @DisplayName("Test EvolutionaryAlgorithm - Constructor con parámetros válidos")
    public void evolutionaryAlgorithm_ConstructorParametrosValidos_NoLanzaExcepcion() throws EvolutionaryAlgorithmException{
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new GaussianMutation();
        CrossoverOperator crossoverOperator = new TwoPointCrossover();
        
        // Act & Assert
        try {
            new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        } catch (EvolutionaryAlgorithmException e) {
            // Si se lanza una excepción, la prueba falla
            assert false;
        }
    }
}
