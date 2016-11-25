/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dandries.hr.data;

import com.dandries.hr.util.ColumnPrinter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author jonathandandries
 */
public @Builder
@Data
class Resident implements Serializable {

    private String name;
    private List<Hospital> preferredHospitals = new ArrayList<>();

    public static ResidentBuilder builder(String name) {
        return new ResidentBuilder().name(name);
    }

    public static class ResidentBuilder {
        private List<Hospital> preferredHospitals = new ArrayList<>();
    }
    
    public static ColumnPrinter getPreferencesPrinter(List<Resident> residents) {
        ColumnPrinter printer = new ColumnPrinter();
        for (Resident res : residents) {
            printer.addLine(res.getName() + " Preferences");
            printer.addLine((res.getName() + " Preferences").replaceAll(".", "-"));
            printer.addLine(res.getPreferredHospitals().stream().map(Hospital::getName).collect(Collectors.joining(" | ")));
            printer.addLine("");
        }
        return printer;
    }

}
