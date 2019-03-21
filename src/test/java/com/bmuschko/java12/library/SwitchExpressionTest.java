package com.bmuschko.java12.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwitchExpressionTest {
    @DisplayName("Can use switch arrow syntax and avoid break statement")
    @ParameterizedTest
    @MethodSource("storageTypes")
    void canUseSwitchArrowSyntaxToAvoidBreakStatement(StorageType storageType, String target) throws URISyntaxException {
        URI result = switch (storageType) {
            case LOCAL_FILE, CLOUD -> storageType.getTarget();
            default -> throw new IllegalArgumentException("Unknown storage type");
        };

        assertEquals(new URI(target), result);
    }

    @DisplayName("Can use switch arrow syntax and break with return value")
    @ParameterizedTest
    @MethodSource("storageTypes")
    void canUseSwitchArrowSyntaxAndReturnWithBreakStatement(StorageType storageType, String target) throws URISyntaxException {
        URI result = switch (storageType) {
            case LOCAL_FILE -> {
                System.out.println("Handling local file storage type");
                break storageType.getTarget();
            }
            case CLOUD -> {
                System.out.println("Handling cloud storage type");
                break storageType.getTarget();
            }
            default -> {
                System.out.println("Fallthrough storage type");
                throw new IllegalArgumentException("Unknown storage type");
            }
        };

        assertEquals(new URI(target), result);
    }

    private static Stream<Arguments> storageTypes() {
        return Stream.of(
                Arguments.of(StorageType.LOCAL_FILE, "file://~/storage"),
                Arguments.of(StorageType.CLOUD, "http://mycloud.com/data")
        );
    }

    private enum StorageType {
        LOCAL_FILE("file://~/storage"),
        CLOUD("http://mycloud.com/data");

        private final URI target;

        StorageType(String target) {
            try {
                this.target = new URI(target);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        public URI getTarget() {
            return target;
        }
    }
}
