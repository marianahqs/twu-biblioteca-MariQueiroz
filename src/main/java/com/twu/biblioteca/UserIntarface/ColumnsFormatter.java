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
    private List<String > returnList = new ArrayList<>();
    private List<Integer> sizeOfColumns = new ArrayList<>();
    private List<String> listToCheckLongestItem = new ArrayList<>();
    private StringBuilder lineString = new StringBuilder();

    public ColumnsFormatter(List<T> items, List<String> fields) {
        this.items = items;
        this.fields = fields;
    }

    public List<String> formatColumns() throws NoSuchMethodException, IllegalAccessException,InvocationTargetException, NullPointerException{
        try {
            buildListWithSizesOfColumns();

            buildHeader(sizeOfColumns, lineString);
            returnList.add(lineString.toString());  //TODO should put it inside the method???

            buildFormattedTableToReturn();

        }catch(NoSuchMethodException | IllegalAccessException |InvocationTargetException | NullPointerException exp) {
            System.out.println("Column Formatter error"); //TODO How throw this message?
        }
        return returnList;
    }

    private void buildFormattedTableToReturn() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (T item : items){
            buildLineStringWithAllColumns(sizeOfColumns, lineString, item);
            returnList.add(lineString.toString());
        }
    }

    private void buildListWithSizesOfColumns() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for(String field : fields){
            buildListToCheckLongestItem(listToCheckLongestItem, field); // TODO It was done by map before generalize
            sizeOfColumns.add(getSizeOfLongestItem(listToCheckLongestItem)+COLUMN_DISTANCE);
        }
    }

    private void buildHeader (List<Integer> sizeOfColumns, StringBuilder lineString) {
        lineString.setLength(0);
        for (int columnIndex = 0; columnIndex < fields.size(); columnIndex++){
            String textOfColumn = fields.
                    get(columnIndex).
                    substring(3).toUpperCase();
            lineString.append(String.format("%-" + sizeOfColumns.get(columnIndex) + "s",
                    textOfColumn));
        }
    }

    private void buildLineStringWithAllColumns(List<Integer> sizeOfColumns, StringBuilder lineString, T item) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        lineString.setLength(0);
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
        listToCheckLongestItem.clear();
        listToCheckLongestItem.add(field.substring(3));
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
            throw new NoSuchElementException("List is empty");  //TODO Doesn't happen anymore, because the header is always sent to check. Test is failing.
        }
    }
}
