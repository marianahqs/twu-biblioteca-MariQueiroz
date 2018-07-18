package com.twu.biblioteca.UserIntarface.ColumnsFormatter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


import static java.util.Optional.ofNullable;

public class ItemsColumnsFormatter<T> {

    private final LinkedHashMap<String, Function<T, String>> columns;
    private List<T> items;
    private final int COLUMN_DISTANCE = 5;
    private List<String > table = new ArrayList<>();
    private StringBuilder lineString = new StringBuilder();
    private Map<String, Integer> sizeOfColumns = new HashMap<>();

    public ItemsColumnsFormatter(List<T> items, LinkedHashMap<String, Function<T, String>> columns) {
        this.items = items;
        this.columns = columns;
    }

    public List<String> createTable() {
        mapSizesOfColumns();

        buildHeader();

        buildTable();

        return table;
    }

    private void mapSizesOfColumns() {
        columns.forEach((columnName, columnValue) -> {
            Set<String> columnValues = items.stream().map(columnValue::apply).collect(Collectors.toSet());

            String biggestColumnValue = columnValues.stream()
                    .filter(Objects::nonNull)
                    .max((current, other) -> {
                Integer length = current.length();
                return length.compareTo(other.length());
            }).orElse("");

            int length = (columnName.length() > biggestColumnValue.length()) ? columnName.length() : biggestColumnValue.length();
            int columnSize = COLUMN_DISTANCE + length;
            sizeOfColumns.put(columnName, columnSize);
        });
    }

    private void buildHeader() {
        columns.keySet().forEach(name -> {
            String columnHeaderEntry = "%-" + sizeOfColumns.get(name) + "s";

            lineString.append(String.format(columnHeaderEntry, name));
        });
        table.add(lineString.toString());
    }

    private void buildTable() {
        items.forEach(item -> {
            lineString.setLength(0);
            columns.forEach((columnName, columnValue) -> {
                String text = ofNullable(columnValue.apply(item)).orElse("");

                lineString.append(String.format("%-" + sizeOfColumns.get(columnName) + "s", text));
            });

            table.add(lineString.toString());
        });
    }
}
