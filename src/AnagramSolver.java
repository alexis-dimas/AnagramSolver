/*
 * The AnagramSolver class searches a given dictionary
 * of words for anagrams of an inputted phrase.
 */
import java.util.*;

public class AnagramSolver {
    private Map<String, LetterInventory> inventories;
    private List<String> order;
    /*
     * Constructs a new AnagramSolver object with a given
     * dictionary of words. A LetterInventory is created
     * for each word in the dictionary. Assumes that the
     * dictionary is non-empty, contains non-empty sequences
     * of letters, and does not contain duplicates.
     * Parameters:
     *  List<String> dictionary - a given list of words
     */
    public AnagramSolver(List<String> dictionary) {
        inventories = new HashMap<>();
        order = new ArrayList<>();
        for (String word : dictionary) {
            order.add(word);
            LetterInventory wordInventory = new LetterInventory(word);
            inventories.put(word, wordInventory);
        } 
    }

    /*
     * Throws an IllegalArugmentException if the inputted
     * max is negative (int max < 0). If max is 0 
     * (int max == 0) an unlimited number of words is
     * included in each anagram. If max is greater than 0
     * (int max > 0) only the max number of words are
     * included in each anagram.
     * Parameters:
     *  String text - an inputted phrase
     *  int max - the number of words to print
     */
    public void print(String text, int max) {      
        if (max < 0) {
            throw new IllegalArgumentException();
        }
                
        List<String> prunedDictionary = new ArrayList<>();
        LetterInventory inputInventory = new LetterInventory(text);
        for (String word : order) {
            LetterInventory wordInventory = inventories.get(word);
            if (inputInventory.subtract(wordInventory) != null) {
                prunedDictionary.add(word);
            }
        }
        explore(inputInventory, prunedDictionary, max, new Stack<String>());
    }
    
    /*
     * Prints the combination of words that are anagrams of the inputted phrase.
     * Parameters:
     *  LetterInventory inputInventory - the LetterInventory of the inputted phrase 
     *  List<String> prunedDictionary - the pruned list of words to consider 
     *  int max - the number of words to print
     *  Stack<String> anagram - the anagram to be printed
     */
    private void explore(LetterInventory inputInventory, List<String> prunedDictionary, 
                            int max, Stack<String> anagram) {
        if (inputInventory.isEmpty()) {
            System.out.println(anagram);
        } else if (max == 0 || anagram.size() < max) { 
            for (String word : prunedDictionary) {            
                LetterInventory wordDictionary = inventories.get(word);
                LetterInventory difference = inputInventory.subtract(wordDictionary);
                if (difference != null) {
                anagram.push(word);
                explore(difference, prunedDictionary, max, anagram);
                anagram.pop();
                }
            }
        }
    }
}