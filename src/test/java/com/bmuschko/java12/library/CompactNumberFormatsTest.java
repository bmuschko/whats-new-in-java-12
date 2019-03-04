package com.bmuschko.java12.library;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompactNumberFormatsTest {
    @Test
    void canFormatNumbersInDifferentLocales() {
        // US number formatting
        NumberFormat numberFormatUsShort
                = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        NumberFormat numberFormatUsLong
                = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        assertEquals(numberFormatUsShort.format(25000), "25K");
        assertEquals(numberFormatUsLong.format(25000), "25 thousand");

        // German number formatting
        NumberFormat numberFormatDeShort
                = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.SHORT);
        NumberFormat numberFormatDeLong
                = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.LONG);
        assertEquals(numberFormatDeShort.format(25000), "25.000");
        assertEquals(numberFormatDeLong.format(25000), "25 Tausend");
    }
}
