package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ColumnsFormatter {

    private static List<Item> items;
    private static String itemKind;

    public ColumnsFormatter(List<Item> items, String itemKind) {
        this.items = items;
        this.itemKind = itemKind;
    }

    public List<String> formatColumns(){
        int COLUMN_DISTANCE = 5;
        List<String > returnList = new ArrayList<>();

        if (itemKind.equals("books")){
            int nameColumnSize = getSizeBiggestItem(items.stream().
                    map(Item::getName).
                    collect(Collectors.toList()))
                    +COLUMN_DISTANCE;

            int authorColumnSize = getSizeBiggestItem(items.stream().
                    map(Item::getAuthor).
                    collect(Collectors.toList()))
                    +COLUMN_DISTANCE;


            for (int line = 0; line < items.size(); line++) {

                returnList.add(String.format("%-" + nameColumnSize + "s%-" + authorColumnSize + "s%s",
                        items.get(line).getName(),
                        items.get(line).getAuthor(),
                        items.get(line).getYear()));
            }
        } else{
            int nameColumnSize = getSizeBiggestItem(items.stream().
                    map(Item::getName).
                    collect(Collectors.toList()))
                    +COLUMN_DISTANCE;

            int directorColumnSize = getSizeBiggestItem(items.stream().
                    map(Item::getDirector).
                    collect(Collectors.toList()))
                    +COLUMN_DISTANCE;

            int ratingColumnSize = getSizeBiggestItem(items.stream().
                    map(Item::getRating).
                    collect(Collectors.toList()))
                    +COLUMN_DISTANCE;

            for (int line = 0; line < items.size(); line++){

                returnList.add(String.format("%-" + nameColumnSize + "s%-" + directorColumnSize+"s%-" + ratingColumnSize + "s%s",
                        items.get(line).getName(),
                        items.get(line).getDirector(),
                        items.get(line).getRating(),
                        items.get(line).getYear()));
            }
        }
        return returnList;
    }

    public int getSizeBiggestItem(List<String> listToTest)throws NoSuchElementException {
        try {
            String maxString = listToTest.stream().max((current, other) -> {
                Integer length = current.length();
                return length.compareTo(other.length());
            }).get();

            return maxString.length();
        }catch (NoSuchElementException noItem){
            throw new NoSuchElementException("Items list is empty");
        }
    }
}
