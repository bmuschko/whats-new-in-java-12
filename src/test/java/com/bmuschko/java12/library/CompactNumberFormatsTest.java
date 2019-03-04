package com.bmuschko.java12.library;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompactNumberFormatsTest {
    @Test
    void canFormatNumbersInDifferentLocales() {
        final Long twentyFiveThousand = 25000L;
        // US number formatting
        NumberFormat numberFormatUsShort
                = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        NumberFormat numberFormatUsLong
                = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        assertEquals(numberFormatUsShort.format(twentyFiveThousand), "25K");
        assertEquals(numberFormatUsLong.format(twentyFiveThousand), "25 thousand");

        // German number formatting
        NumberFormat numberFormatDeShort
                = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.SHORT);
        NumberFormat numberFormatDeLong
                = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.LONG);
        assertEquals(numberFormatDeShort.format(twentyFiveThousand), "25.000");
        assertEquals(numberFormatDeLong.format(twentyFiveThousand), "25 Tausend");
    }
}
