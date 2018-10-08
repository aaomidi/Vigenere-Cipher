package com.aaomidi.ciphers.vigenere.engine;

import java.nio.charset.MalformedInputException;
import java.util.Arrays;
import java.util.function.Function;

public class Key {

    /* 0: first digit
     * 1: second digit
     * 2: third digit
     * n: n-1th digit
     */
    private final int[] key;
    private final int size;

    private Key(int size) {
        this(new int[size]);
    }

    public Key(int[] arr) {
        this.size = arr.length;
        this.key = arr;
    }

    public static Key getZeroKey(int size) {
        return new Key(size);
    }

    @Override
    public String toString() {
        return "Key{" +
                "key=" + Arrays.toString(key) +
                ", size=" + size +
                '}';
    }

    public Key increment() {
        int[] arr = key.clone();
        for (int i = 0; i < size; i++) {

            // Handle if one of the values is less than 25 (english alphabet - 1)
            if (arr[i] < 25) {
                arr[i]++;
                break;
            }

            // If we're at the end of the array
            if (i + 1 >= size) {
                throw new UnsupportedOperationException("End of array");
            }

            arr[i] = 0;
        }

        return new Key(arr);
    }

    public String decipher(String input) {
        Function<Integer, Integer> incrementer = integer -> {
            if (integer + 1 >= size)
                return 0;

            return integer + 1;
        };

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (char c : input.toCharArray()) {
            // Shift the character
            char shifted = shift(c, key[idx]);

            sb.append(shifted);

            // Increment the key index
            idx = incrementer.apply(idx);
        }

        return sb.toString();
    }

    private char shift(char c, int amount) {
        int min = (int) 'A';
        int max = (int) 'Z';
        int val = (int) c;

        // Don't touch other letters.
        if (c > max || c < min) {
            throw new RuntimeException("Invalid input");
        }

        val -= min;

        val = val - amount;

        if (val < 0) {
            val += 26;
        }

        val += min;
        return (char) val;

//        int dec = (int) c;
//
//        dec -= amount;
//
//        if (dec < min) {
//            int difference = min - dec + 1;
//            dec = max - difference;
//        }
//
//        return (char) dec;
    }

    public String getStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (int i : key) {
            int min = (int) 'A';
            sb.append((char) (min + i));
        }

        return sb.toString();
    }


}
