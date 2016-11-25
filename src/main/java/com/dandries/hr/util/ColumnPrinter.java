/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dandries.hr.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jonathandandries
 */
public class ColumnPrinter {

    List<List<String>> lines = new ArrayList<>();
    List<Integer> maxLengths = new ArrayList<>();
    int numColumns = -1;

    public ColumnPrinter addLine(String... line) {

        if (numColumns == -1) {
            numColumns = line.length;
            for (int i = 0; i < numColumns; i++) {
                maxLengths.add(0);
            }
        }

        if (numColumns != line.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < numColumns; i++) {
            maxLengths.set(i, Math.max(maxLengths.get(i), line[i].length()));
        }

        lines.add(Arrays.asList(line));

        return this;
    }

    public void print() {
        System.out.println(toString());
    }

    public String toString() {
        String result = "";
        for (List<String> line : lines) {
            for (int i = 0; i < numColumns; i++) {
                result += pad(line.get(i), maxLengths.get(i) + 1);
            }
            result += System.lineSeparator();
        }
        return result;
    }

    private String pad(String word, int newLength) {
        while (word.length() < newLength) {
            word += " ";
        }
        return word;
    }

}
