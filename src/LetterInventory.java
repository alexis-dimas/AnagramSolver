/*
 * The LetterInventory class is able to return
 * and change data from an inventory of letters.
 * A LetterInventory is not case-sensitive.
 */

import java.util.*;

public class LetterInventory {
    public static final int ALPHABET_SIZE = 26;
    private int[] inventory;
    private int size;
    
    // Contstructs a new LetterInventory object with an empty inventory.    
    public LetterInventory() {
        this.inventory = new int[ALPHABET_SIZE];
    }

    /*
     * Constructs a new LetterInventory object with an
     * inventory of letters from a String. Non-alphabetic 
     * characters in the String are ignored.
     * Parameters:
     *  String data - the collection of letters to be stored
     */
    public LetterInventory(String data) {
        this();
        data = data.toLowerCase();
        for (int i = 0; i < data.length(); i++) {
            char letter = data.charAt(i);
            if (letter >= 'a' && letter <= 'z') {
                int index = letter - 'a';
                inventory[index]++;
                size++;
            }
        }
    }
    
    /*
     * Returns the count of the given letter. Requires
     * a valid character. Throws an IllegalArgumentException 
     * if the character is not a letter.
     * Parameters:
     *  char letter - the letter selected to return its count
     */
    public int get(char letter) {
        letter = Character.toLowerCase(letter);
        if (letter < 'a' || letter > 'z') {
            throw new IllegalArgumentException();
        }
        
        int index = letter - 'a';
        return inventory[index];
    }
    
    /*
     * Sets the count of the letter. Requires a valid 
     * character and integer (int value >= 0). Throws an 
     * IllegalArgumentException if the character is 
     * not a letter or the value is negative (int value < 0).
     * Parameters:
     *  char letter - the letter selected to set its count
     *  int value - the value used to set the count
     */
    public void set(char letter, int value) {
        letter = Character.toLowerCase(letter);
        if (letter < 'a' || letter > 'z' || value < 0) {
            throw new IllegalArgumentException();
        }
        
        int index = letter - 'a';      
        size += (value - inventory[index]);
        inventory[index] = value;
    }
    
    // Returns the size of the inventory, size is the sum of all counts.
    public int size() {
        return size;
    }

    /*
     * Returns true if the size is 0, indicating that the 
     * inventory is empty. Returns false if the inventory 
     * is not empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Returns a String representation of this inventory.
    public String toString() {
        String representation = "[";
        if (size > 0) {
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                for (int j = 0; j < inventory[i]; j++) {
                char letter = (char)('a' + i);
                representation += letter;
                }
            }
        }
        
        return representation + "]";
    }
    
    /*
     * Returns a new LetterInventory object which 
     * contains the sum of this inventory and the other inventory.
     * Parameters:
     *  LetterInventory other - the given object that contains the other inventory.
     */
    public LetterInventory add(LetterInventory other) {
        LetterInventory newObject = new LetterInventory();
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            newObject.inventory[i] = this.inventory[i] + other.inventory[i];
        }
        
        newObject.size = this.size + other.size;
        return newObject;
    }
    
    /*
     * Returns a new LetterInventory object which
     * contains the difference of this inventory
     * and the other inventory. Returns null if the
     * difference is negative.
     * Parameters:
     *  LetterInventory other - the given object that contains the other inventory.
     */
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory newObject = new LetterInventory();
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            newObject.inventory[i] = this.inventory[i] - other.inventory[i];
            
            if (newObject.inventory[i] < 0) {
                return null;
            }
        }
        
        newObject.size = this.size - other.size;
        return newObject;
    }
}