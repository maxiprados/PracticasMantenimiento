package org.mps;

import java.util.Arrays;

import org.mps.crossover.CrossoverOperator;
import org.mps.crossover.TwoPointCrossover;
import org.mps.mutation.GaussianMutation;
import org.mps.mutation.MutationOperator;
import org.mps.selection.SelectionOperator;
import org.mps.selection.TournamentSelection;

public class Pruebas {
    public static void main(String[] args) throws EvolutionaryAlgorithmException {
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new GaussianMutation(0.5,1.0);
        CrossoverOperator crossoverOperator = new TwoPointCrossover();
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        
        int[][] initialPopulation = {
            {10, 20, 30, 40},
            {40, 30, 20, 10},
            {5,  15, 25, 35},
            {35, 25, 15, 5}
        };

        int [][] resultado = algoritmo.optimize(initialPopulation);
        System.out.println("Resultado de la optimización: " + Arrays.deepToString(resultado));
        
    }
}
