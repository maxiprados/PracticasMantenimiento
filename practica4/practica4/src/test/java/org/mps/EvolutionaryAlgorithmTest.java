//Pedro Scarpati Sundblad y Máximo Prados Meléndez
package org.mps;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        
        // Act
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Assert
        assertNotNull(algoritmo);
    }

    @Test
    @DisplayName("Test optimize(int[][] poblacion) - Población nula")
    public void optimize_PoblacionNula_ThrowsException() throws EvolutionaryAlgorithmException {
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new GaussianMutation();
        CrossoverOperator crossoverOperator = new TwoPointCrossover();
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        
        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            algoritmo.optimize(null);
        });
    }

    @Test
    @DisplayName("Test optimize(int[][] poblacion) - Población vacía")
    public void optimize_PoblacionVacia_ThrowsException() throws EvolutionaryAlgorithmException {
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new GaussianMutation();
        CrossoverOperator crossoverOperator = new TwoPointCrossover();
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        
        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            algoritmo.optimize(new int[0][0]);
        });
    }

    @Test
    @DisplayName("Test optimize(int[][] poblacion) - Población válida")
    public void optimize_PoblacionValida_NoLanzaExcepcion() throws EvolutionaryAlgorithmException {
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new GaussianMutation();
        CrossoverOperator crossoverOperator = new TwoPointCrossover();
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        
        int[][] population = {
            {5, 2, 3},
            {4, 4, 2},
            {6, 1, 1},
            {3, 5, 2}
        };
        
       
        // Act
        int[][] resultado = algoritmo.optimize(population);

        // Assert
        assertNotNull(resultado);
        

    }

    @Test
    @DisplayName("Test optimize(int[][] poblacion) - Población con número impar de individuos")
    public void optimize_PoblacionImpar_ThrowsException() throws EvolutionaryAlgorithmException {
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new GaussianMutation();
        CrossoverOperator crossoverOperator = new TwoPointCrossover();
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        
        int[][] population = {
            {5, 2, 3},
            {4, 4, 2},
            {6, 1, 1}
        };
        
        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            algoritmo.optimize(population);
        });
    }
}
