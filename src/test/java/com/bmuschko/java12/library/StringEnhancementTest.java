package com.bmuschko.java12.library;

import org.junit.jupiter.api.Test;

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
        StringBuilder formattedToDos = new StringBuilder();
        List<ToDoItem> toDoItems = List.of(
                new ToDoItem(true, "Buy milk"),
                new ToDoItem(false, "File taxes"));
        for (ToDoItem item : toDoItems) {
            if (item.isChecked()) {
                formattedToDos.append(item.getDescription()
                        .transform(input -> "[ ] " + item.getDescription() + "\n")
                        .transform(String::toString));
            } else {
                formattedToDos.append(item.getDescription()
                        .transform(input -> "[X] " + item.getDescription() + "\n")
                        .transform(String::toString));
            }
        }
        assertEquals("[ ] Buy milk\n" +
                "[X] File taxes\n", formattedToDos.toString());
    }

    private static class ToDoItem {
        private final boolean checked;
        private final String description;

        public ToDoItem(boolean checked, String description) {
            this.checked = checked;
            this.description = description;
        }

        public boolean isChecked() {
            return checked;
        }

        public String getDescription() {
            return description;
        }
    }
}
