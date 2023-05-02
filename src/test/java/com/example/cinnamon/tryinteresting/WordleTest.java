package com.example.cinnamon.tryinteresting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordleTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void wordleTest()
    {
        Assertions.assertEquals(".....", new Wordle("aaaaa").guess("bbbbb"));
        Assertions.assertEquals("A....", new Wordle("aaaaa").guess("abbbb"));
        Assertions.assertEquals(".A...", new Wordle("aaaaa").guess("babbb"));
        Assertions.assertEquals("..A..", new Wordle("aaaaa").guess("bbabb"));
        Assertions.assertEquals("...A.", new Wordle("aaaaa").guess("bbbab"));
        Assertions.assertEquals("....A", new Wordle("aaaaa").guess("bbbba"));

        Assertions.assertEquals(".a...", new Wordle("abbbb").guess("caccc"));
        Assertions.assertEquals("..a..", new Wordle("abbbb").guess("ccacc"));
        Assertions.assertEquals("...a.", new Wordle("abbbb").guess("cccac"));
        Assertions.assertEquals("....a", new Wordle("abbbb").guess("cccca"));

        Assertions.assertEquals("A....", new Wordle("abbbb").guess("accca"));
        Assertions.assertEquals("A....", new Wordle("abbbb").guess("accaa"));
        Assertions.assertEquals("A..a.", new Wordle("aabbb").guess("accaa"));
        Assertions.assertEquals("AA...", new Wordle("aabbb").guess("aacaa"));
        Assertions.assertEquals("...aa", new Wordle("aabbb").guess("cccaa"));

        Assertions.assertEquals("..A..", new Wordle("bbabb").guess("aaaaa"));

        Assertions.assertEquals("AAAAA", new Wordle("aaaaa").guess("aaaaa"));
        Assertions.assertEquals("BRAVO", new Wordle("bravo").guess("bravo"));
    }
}