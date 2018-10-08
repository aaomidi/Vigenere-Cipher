package com.aaomidi.ciphers.vigenere.engine;

public class VigenereCryptanalysis {
    private final Fitness fitness;
    private final String input;
    private final int size;

    public VigenereCryptanalysis(Fitness fitness, String input, int size) {
        this.fitness = fitness;
        this.input = input;
        this.size = size;

    }

    public void decrypt() {
        Key bestKey = Key.getZeroKey(size);
        double maxScore = Double.MIN_VALUE;

        Key key = bestKey;

        int i = 0;

        while (true) {
            String deciphered = key.decipher(input);
            double score = getScore(deciphered);

            if (score > maxScore) {
                bestKey = key;
                maxScore = score;

                System.out.printf(
                        "Result Found (%d):\n\tTime: %d\n\tKey: %s\n\tPlain text: %s\n\t Score: %f\n",
                        i,
                        System.currentTimeMillis(),
                        bestKey.getStringRepresentation(),
                        deciphered,
                        maxScore);
            }
            try {
                key = key.increment();
            } catch (Exception ex) {
                break;
            }
        }
    }

    private double getScore(String deciphered) {
        return fitness.getAvgFitness(deciphered);
    }
}
