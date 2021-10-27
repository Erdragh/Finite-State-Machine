package com.github.erdragh.finitestatemachine;

import java.util.ArrayList;

public class StateMachine {
    /**
     * This is used to store the current state.
     */
    private int state = 0;
    /**
     * the alphabet as an ArrayList so that I am able to use ArrayList Methods.
     */
    private ArrayList<Character> alphabet;
    /**
     * ArrayList created for convenience later (to use .contains() method)
     */
    private ArrayList<Integer> finalStates;
    /**
     * a map coordinating the [state][charIndex] pairs to new states
     */
    private int[][] map;

    /**
     * This constructor takes in the information of the language and converts some to ArrayLists for easier use later.
     * @param alphabet
     * a char array containing all characters of the language
     * @param map
     * a map covering the states another state will switch to when given a certain character
     * @param finalStates
     * the states which, if they're the last, mean that the test is positive and the string is part of the language
     */
    public StateMachine(Language language) {
        int[] finalStatesIn = language.getFinalStates();
        char[] alphabetIn = language.getAlphabet();
        finalStates = new ArrayList<>();
        alphabet = new ArrayList<>();
        map = language.getMap();
        for (int i = 0; i < finalStatesIn.length; i++) {
            finalStates.add(finalStatesIn[i]);
        }
        for (int i = 0; i < alphabetIn.length; i++) {
            alphabet.add(alphabetIn[i]);
        }
    }

    /**
     * This is the method used from the outside.
     * @param string
     * the string you give the method it checks to see wether it conforms to the rules
     * @return
     * returns true if the string conformed to the rules
     */
    public boolean testString(String string) {
        state = 0;
        for (int i = 0; i < string.length(); i++) {
            setNewState(string.charAt(i));
            if (state == -1) return false;
        }
        return finalStates.contains(state);
    }

    /**
     * This method is used to advance the state in the state machine.
     * @param character
     * the current char that is given to the state machine to check what the new state will be.
     */
    private void setNewState(char character) {
        Logger.println("Current State: " + state + "; current character: '" + character + "'");
        int charIndex = alphabet.indexOf(character);
        Logger.println("Index of Character in alphabet: " + charIndex);
        if (charIndex == -1) {
            state = -1; 
            Logger.println("charIndex was -1, therefore not part of the alphabet. Setting state to -1 and returning out of testing");
            return;
        }
        Logger.println("New state is: " + map[state][charIndex]);
        state = map[state][charIndex];
    }
}
