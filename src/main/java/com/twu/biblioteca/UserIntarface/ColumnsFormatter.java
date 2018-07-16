package com.twu.biblioteca.UserIntarface;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


import static java.util.Optional.ofNullable;

public class ColumnsFormatter <T> {

    private List<T> items;
    private List<String> fields;
    private final int COLUMN_DISTANCE = 5;

    public ColumnsFormatter(List<T> items, List<String> fields) {
        this.items = items;
        this.fields = fields;
    }


    public List<String> formatColumns() throws NoSuchMethodException, IllegalAccessException,InvocationTargetException, NullPointerException{

        List<String > returnList = new ArrayList<>();
        List<Integer> sizeOfColumns = new ArrayList<>();
        List<String> listToCheckLongestItem = new ArrayList<>();
        StringBuilder lineString = new StringBuilder();

        try {
            for(String field : fields){
                listToCheckLongestItem.clear();
                buildListToCheckLongestItem(listToCheckLongestItem, field);
                sizeOfColumns.add(getSizeOfLongestItem(listToCheckLongestItem)+COLUMN_DISTANCE);
            }

            for (T item : items){
                lineString.setLength(0);
                buildLineStringWithAllColumns(sizeOfColumns, lineString, item);
                returnList.add(lineString.toString());
            }

        }catch(NoSuchMethodException | IllegalAccessException |InvocationTargetException | NullPointerException exp) {
            System.out.println("Column Formatter error"); //TODO How throw this message?
        }
        return returnList;
    }

    private void buildLineStringWithAllColumns(List<Integer> sizeOfColumns, StringBuilder lineString, T item) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (int columnIndex = 0; columnIndex < fields.size(); columnIndex++){
            String textOfColumn = ofNullable((String) item.
                    getClass().
                    getMethod(fields.get(columnIndex)).
                    invoke(item)).
                    orElse("");

            lineString.append(String.format("%-" + sizeOfColumns.get(columnIndex) + "s",
                    textOfColumn));
        }
    }

    private void buildListToCheckLongestItem(List<String> listToCheckLongestItem, String field) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (T item : items){
            String textToCheckSize = ofNullable((String) item.
                    getClass().
                    getMethod(field).
                    invoke(item)).
                    orElse("");

            listToCheckLongestItem.add(textToCheckSize);
        }
    }

    private int getSizeOfLongestItem(List<String> listToTest)throws NoSuchElementException {
        try {
            String maxString = listToTest.stream().max((current, other) -> {
                Integer length = current.length();
                return length.compareTo(other.length());
            }).get();

            return maxString.length();
        }catch (NoSuchElementException exception){
            throw new NoSuchElementException("List is empty");
        }
    }
}
