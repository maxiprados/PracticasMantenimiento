package org.mps.selection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.EvolutionaryAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentSelectionTest {
//    @DisplayName("Metodo select retorna bien el individuo y su cantidad de genes")
//    @Test
//    void testSelectReturnsValidIndividual() throws EvolutionaryAlgorithmException {
//        TournamentSelection selection = new TournamentSelection(2);
//        int[][] population = {
//                {5, 5, 5},
//                {1, 1, 1},
//                {9, 9, 9}
//        };
//
//        int[] selected = selection.select(population[1]);
//
//        // Tiene que tener la misma longitud que los demás
//        assertEquals(3, selected.length);
//    }
//
//    @DisplayName("Metodo select retorna el mejor cuando la population es igual al size del tournament")
//    @Test
//    void testSelectReturnsFittestIfTournamentSizeEqualsPopulation() throws EvolutionaryAlgorithmException {
//        TournamentSelection selection = new TournamentSelection(3);
//        int[][] population = {
//                {9, 9, 9},  // fitness: 27
//                {1, 1, 1},  // fitness: 3 → mejor
//                {5, 5, 5}   // fitness: 15
//        };
//
//        int[] selected = selection.select(population);
//
//        assertArrayEquals(new int[]{1, 1, 1}, selected);
//    }
//
//    @DisplayName("Metodo select tira excepcion si el size del tournament es mayor que la population")
//    @Test
//    void testSelectThrowsExceptionIfTournamentTooLarge() throws EvolutionaryAlgorithmException {
//        TournamentSelection selection = new TournamentSelection(5);
//        int[][] population = {
//                {1, 2, 3},
//                {4, 5, 6}
//        };
//
//        assertThrows(EvolutionaryAlgorithmException.class, () -> selection.select(population));
//    }
    @DisplayName("Constructor con numero de torneo menor que 0")
    @Test
    void testSelectThrowsExceptionIfTournamentTooLarge() throws EvolutionaryAlgorithmException {


        assertThrows(EvolutionaryAlgorithmException.class, () -> new TournamentSelection(-1));
    }



}
