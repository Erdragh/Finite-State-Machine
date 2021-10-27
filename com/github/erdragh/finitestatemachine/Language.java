package com.github.erdragh.finitestatemachine;

public interface Language {
    /**
     * @return
     * this should return a char[] in the order in which the characters are used in the map
     */
    public char[] getAlphabet();
    /**
     * @return
     * this should return the final states on which the automaton can end.
     */
    public int[] getFinalStates();
    /**
     * @return
     * this should return a two-dimensional map allocating a new state for every state+charIndex pair.
     * Organized: [state][charIndex]
     */
    public int[][] getMap();
}
