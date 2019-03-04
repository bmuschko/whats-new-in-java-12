package com.bmuschko.java12.stream;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeeingCollectorTest {
    @Test
    void canCollectStreamFromTwoCollectorsAndMergeResult() {
        SalaryRange salaryRange = Stream
                .of(56700, 67600, 45200, 120000, 77600, 85000)
                .collect(teeing(
                        minBy(Integer::compareTo),
                        maxBy(Integer::compareTo),
                        SalaryRange::fromOptional));
        assertEquals("Salaries range between 45200 and 120000.", salaryRange.toString());
    }

    public static class SalaryRange {
        private final Integer min;
        private final Integer max;

        private SalaryRange(Integer min, Integer max) {
            this.min = min;
            this.max = max;
        }

        public static SalaryRange fromOptional(Optional<Integer> min, Optional<Integer> max) {
            if (min.isEmpty() || max.isEmpty()) {
                throw new IllegalStateException("Minimum and maximum salaries cannot be null");
            }
            return new SalaryRange(min.get(), max.get());
        }

        @Override
        public String toString() {
            return "Salaries range between " + min + " and " + max + ".";
        }
    }
}
