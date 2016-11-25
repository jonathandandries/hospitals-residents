/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dandries.hr.data;

import com.dandries.hr.util.ColumnPrinter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

/**
 *
 * @author jonathandandries
 */
public @Builder
@Data
class Hospital {

    private String name;
    private int capacity;
    private List<Resident> acceptedResidents = new ArrayList<>();
    private List<Resident> preferredResidents = new ArrayList<>();

    public static HospitalBuilder builder(String name) {
        // The "name" value is required.
        return new HospitalBuilder().name(name);
    }

    public static class HospitalBuilder {

        // Builder default values:
        private List<Resident> acceptedResidents = new ArrayList<>();
        private List<Resident> preferredResidents = new ArrayList<>();
    }

    public Resident acceptResident(Resident res) {
        Resident removedResident = null;
        if (atCapacity()) {
            removedResident = removeLastAcceptedResident();
        }
        if (acceptedResidents.isEmpty() || isRankedBetterThan(lastAcceptedResident(), res)) {
            acceptedResidents.add(res);
        } else {
            for (int i = 0; i < acceptedResidents.size(); i++) {
                if (isRankedBetterThan(res, acceptedResidents.get(i))) {
                    acceptedResidents.add(i, res);
                    break;
                }
            }
        }
        return removedResident;
    }

    public boolean willAcceptResident(Resident res) {
        if (preferredResidents.contains(res)) {
            if (!atCapacity()) {
                return true;
            } else {
                // Find the acceptedResidents to see if an existing one
                // is ranked lower.
                if (isRankedBetterThan(res, lastAcceptedResident())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean atCapacity() {
        return (acceptedResidents.size() >= capacity);
    }

    public boolean isRankedBetterThan(Resident res1, Resident res2) {
        return preferredResidents.indexOf(res1) < preferredResidents.indexOf(res2);
    }

    public Resident lastAcceptedResident() {
        return acceptedResidents.get(acceptedResidents.size() - 1);
    }

    public Resident removeLastAcceptedResident() {
        return acceptedResidents.remove(acceptedResidents.size() - 1);
    }

    public static ColumnPrinter getPrinter(List<Hospital> hospitals) {
        ColumnPrinter printer = new ColumnPrinter().addLine(hospitals.stream().map(Hospital::getName).toArray(String[]::new));
        printer.addLine(hospitals.stream().map(h -> h.getName().replaceAll(".", "-")).toArray(String[]::new));
        Optional<Integer> max = hospitals.stream().map(Hospital::getCapacity).reduce(Integer::max);
        for (int i = 0; i < max.orElse(0); i++) {
            List<String> line = new ArrayList<>();
            for (Hospital hosp : hospitals) {
                if (i < hosp.getAcceptedResidents().size()) {
                    line.add(hosp.getAcceptedResidents().get(i).getName());
                } else {
                    line.add("");
                }
            }
            printer.addLine(line.toArray(new String[0]));
        }
        return printer;
    }
}
