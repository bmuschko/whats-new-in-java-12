package com.bmuschko.java12.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Unicode11Test {
    @Test
    void canUseColdFaceEmoji() {
        String unicodeCharacter = "\uD83E\uDD76";
        assertEquals("🥶", unicodeCharacter);
    }
}
