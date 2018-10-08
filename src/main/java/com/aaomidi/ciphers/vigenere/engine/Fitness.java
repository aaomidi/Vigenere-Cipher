package com.aaomidi.ciphers.vigenere.engine;

import com.aaomidi.ciphers.NGram;
import com.aaomidi.ciphers.vigenere.util.StreamHandler;

import java.util.List;

public class Fitness {
    private final List<NGram> models;

    public Fitness() {
        models = NGram.getAllDefaultNGrams();
    }

    private List<NGram> getFitnessModels() {
        return models;
    }

    public double getAvgFitness(String s) {
        s = StreamHandler.sanitize(s);
        double sum = 0;
        for (NGram nGram : getFitnessModels()) {
            sum += nGram.getFitness(s);
        }
        return sum / getFitnessModels().size();
    }
}
