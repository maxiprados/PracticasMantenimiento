package org.mps.crossover;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.EvolutionaryAlgorithmException;

public class TwoPointCrossoverTest {
    @Test
    @DisplayName("Prueba constructor correcto")
    public void twoPointCrossover_ConstructorCorrecto(){
        //Arrange
        TwoPointCrossover twoPointCrossover;

        //Act
        twoPointCrossover = new TwoPointCrossover();

        //Assert
        assertNotNull(twoPointCrossover);
    }

    @Test
    @DisplayName("Prueba crossover Parent 1 es null")
    public void twoPointCrossover_CrossoverParent1Null(){
        //Arrange
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();
        int[] parent1 = null;
        int[] parent2 = {1, 2, 3, 4, 5};
        
        //Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            twoPointCrossover.crossover(parent1, parent2);
        });
        
    }

    @Test
    @DisplayName("Prueba crossover Parent 2 es null")
    public void twoPointCrossover_CrossoverParent2Null(){
        //Arrange
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();
        int[] parent1 = {1, 2, 3, 4, 5};
        int[] parent2 = null;
        
        //Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            twoPointCrossover.crossover(parent1, parent2);
        });
        
    }

    @Test
    @DisplayName("Prueba crossover Parent1.length = 1")
    public void twoPointCrossover_CrossoverParent1LengthIs1(){
        //Arrange
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();
        int[] parent1 = {1};
        int[] parent2 = {2, 3, 4, 5};
        
        //Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            twoPointCrossover.crossover(parent1, parent2);
        });
    }

    @Test
    @DisplayName("Prueba crossover Parent1.length != Parent2.length")
    public void twoPointCrossover_CrossoverParent1LengthNotEqualParent2Length(){
        //Arrange
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();
        int[] parent1 = {1, 2, 3};
        int[] parent2 = {4, 5, 6, 7};
        
        //Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            twoPointCrossover.crossover(parent1, parent2);
        });
    }

    @Test
    @DisplayName("Prueba crossover Correcta")
    public void twoPointCrossover_CrossoverCorrecto() throws EvolutionaryAlgorithmException{
        //Arrange
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();
        int[] parent1 = {1, 2, 3, 4, 5};
        int[] parent2 = {6, 7, 8, 9, 10};
        
        //Act
        int[][] offspring = twoPointCrossover.crossover(parent1, parent2);
        
        //Assert
        assertNotNull(offspring);
        assertNotNull(offspring[0]);
        assertNotNull(offspring[1]);
    }
}
