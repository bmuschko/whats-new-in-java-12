package com.bmuschko.java12.library;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringEnhancementTest {
    @Test
    void canIndentString() {
        StringBuilder yaml = new StringBuilder();
        yaml.append("spec:\n");
        yaml.append("containers:\n".indent(2));
        yaml.append("- name: nginx\n".indent(2));
        yaml.append("image: nginx:1.7.9".indent(4));
        assertEquals("spec:\n" +
                "  containers:\n" +
                "  - name: nginx\n" +
                "    image: nginx:1.7.9\n", yaml.toString());
    }

    @Test
    void canTransformString() {
        List<String> urls = List.of(
                "   http://google.com/",
                "http://my.search.com?query=java server&page=1");
        List<String> transformedUrls = new ArrayList<>();
        for (String url : urls) {
            String transformedUrl = url.transform(String::strip)
                    .transform(URLCleaner::encodeQueryParams);
            transformedUrls.add(transformedUrl);
        }
        assertEquals(List.of("http://google.com/",
                "http://my.search.com?query%3Djava+server%26page%3D1"), transformedUrls);
    }

    private static class URLCleaner {
        public static String encodeQueryParams(String rawURL) {
            try {
                if (rawURL.contains("?")) {
                    String[] splitURL = rawURL.split("\\?");
                    return splitURL[0] + "?" + URLEncoder.encode(splitURL[1], "UTF-8");
                }
                return rawURL;
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException("UTF-8 not supported", ex);
            }
        }
    }
}
