package com.example.wordlespring.repositories;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

@Repository
public class AlphabetRepository {
    public AlphabetRepository() {
    }

    public static SortedSet<Character> AlphabetGenerator() {
        SortedSet<Character> Alphabet = new TreeSet<>();
        Collections.addAll(Alphabet, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        return Alphabet;
    }

    public static SortedSet<Character> BulgarianAlphabetGenerator() {
        SortedSet<Character> Alphabet = new TreeSet<>();
        Collections.addAll(Alphabet, 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й',
                'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ','ь','ю', 'я');
        return Alphabet;
    }
}
