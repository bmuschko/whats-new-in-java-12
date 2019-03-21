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
    @DisplayName("Can use traditional switch statement")
    @ParameterizedTest
    @MethodSource("storageTypes")
    void canUseSwitchStatement(StorageType storageType, String target)
            throws URISyntaxException {
        URI result;

        switch (storageType) {
            case LOCAL_FILE: result = new URI("file://~/storage");
                             break;
            case CLOUD: result = new URI("http://mycloud.com/data");
                        break;
            default: throw new IllegalArgumentException("Unknown storage type");
        }

        assertEquals(new URI(target), result);
    }

    @DisplayName("Can use switch arrow syntax and avoid break statement")
    @ParameterizedTest
    @MethodSource("storageTypes")
    void canUseSwitchArrowSyntaxToAvoidBreakStatement(StorageType storageType, String target)
            throws URISyntaxException {
        URI result = switch (storageType) {
            case LOCAL_FILE -> new URI("file://~/storage");
            case CLOUD -> new URI("http://mycloud.com/data");
            default -> throw new IllegalArgumentException("Unknown storage type");
        };

        assertEquals(new URI(target), result);
    }

    @DisplayName("Can use switch arrow syntax and break with return value")
    @ParameterizedTest
    @MethodSource("storageTypes")
    void canUseSwitchArrowSyntaxAndReturnWithBreakStatement(StorageType storageType, String target)
            throws URISyntaxException {
        URI result = switch (storageType) {
            case LOCAL_FILE -> {
                System.out.println("Retrieving the local file storage URI");
                break new URI("file://~/storage");
            }
            case CLOUD -> {
                System.out.println("Retrieving the cloud storage URI");
                break new URI("http://mycloud.com/data");
            }
            default -> throw new IllegalArgumentException("Unknown storage type");
        };

        assertEquals(new URI(target), result);
    }

    private enum StorageType {
        LOCAL_FILE,
        CLOUD
    }

    private static Stream<Arguments> storageTypes() {
        return Stream.of(
                Arguments.of(StorageType.LOCAL_FILE, "file://~/storage"),
                Arguments.of(StorageType.CLOUD, "http://mycloud.com/data")
        );
    }
}
