package com.example.cinnamon.tryinteresting;

import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.factory.primitive.CharBags;
import org.eclipse.collections.impl.string.immutable.CharAdapter;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Wordle(String string) {
    Wordle(String string) {
        this.string = string.toLowerCase();
    }

    public String guess(String guess) {
        CharAdapter guessChars = Strings.asChars(guess.toLowerCase());
        CharAdapter hiddenChars = Strings.asChars(this.string);
        MutableCharBag remaining = hiddenChars
                .injectIntoWithIndex(
                        CharBags.mutable.empty(),
                        (bag, each, i) -> guessChars.get(i) != each ? bag.with(each) : bag);
        return guessChars.collectWithIndex((each, i) -> hiddenChars.get(i) == each ?
                        Character.toUpperCase(each) : this.replaceDifferentPositionOrNoMatch(remaining, each))
                .makeString("");
    }

    private char replaceDifferentPositionOrNoMatch(MutableCharBag remaining, char each) {
        return remaining.remove(each) ? each : '.';
    }


    // https://www.youtube.com/watch?v=wW7uzc61tZ8&t=996s
    public String guess2(Map<Letter, LetterCount> chars)
    {
        Map<LetterCount, List<Letter>> map = chars.entrySet().stream()
                .map(LetterByCount::new)
                .collect(Collectors.groupingBy(
                        LetterByCount::count,
                        Collectors.mapping(LetterByCount::letter, Collectors.toList())
                        ));

        LettersByCount mostSeenLetter = map.entrySet().stream()
             .map(LettersByCount::new)
             .max(LettersByCount.comparingByCount())
             .orElseThrow();

        List<LettersByCount> mostSeenLetters = map.entrySet().stream()
                .map(LettersByCount::new)
                .sorted(LettersByCount.comparingByCount().reversed())
                .toList();

        return null;
    }

    record Letter(int codePoint)
    {
        Letter(int codePoint) {
            this.codePoint = Character.toLowerCase(codePoint);
        }
    }

    record LetterCount(long count) implements Comparable<LetterCount>
    {
        @Override
        public int compareTo(LetterCount other) {
            return Long.compare(this.count, other.count);
        }
    }

    record LetterByCount(Letter letter, LetterCount count)
    {
        LetterByCount(Map.Entry<Letter, LetterCount> entry) {
            this(entry.getKey(), entry.getValue());
        }
    }

    record LettersByCount(LetterCount count, List<Letter> letters)
    {
        LettersByCount(Map.Entry<LetterCount, List<Letter>> entry) {
            this(entry.getKey(), entry.getValue());
        }

        public static Comparator<? super LettersByCount> comparingByCount() {
           return Comparator.comparing(LettersByCount::count);
        }
    }

}

