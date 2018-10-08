package com.aaomidi.ciphers.vigenere;

import com.aaomidi.ciphers.vigenere.engine.Fitness;
import com.aaomidi.ciphers.vigenere.engine.VigenereCryptanalysis;
import com.aaomidi.ciphers.vigenere.util.StreamHandler;

public class Main {
    private static final String input = "CTMYR DOIBS RESRR RIJYR EBYLD IYMLC CYQXS RRMLQ FSDXF OWFKT CYJRR IQZSM X";

    private Main(String... args) {
        Fitness registry = new Fitness();
        String input = Main.input;
        int size = 3;

        if (args.length > 1) {
            input = args[0];
            size = Integer.valueOf(args[1]);
        }

        VigenereCryptanalysis cipher = new VigenereCryptanalysis(registry, StreamHandler.sanitize(input), size);
        System.out.println("Start: " + System.currentTimeMillis());
        cipher.decrypt();
    }

    public static void main(String... args) {
        new Main(args);
    }


}
