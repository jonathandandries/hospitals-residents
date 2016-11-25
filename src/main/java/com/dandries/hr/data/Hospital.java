/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dandries.hr.data;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author jonathandandries
 */
public @Builder(builderMethodName = "hiddenBuilder")
@Data
class Hospital {

    private String name;
    private int capacity;
    private List<Resident> acceptedResidents;
    private List<Resident> preferredResidents;
    
    public static HospitalBuilder builder(String name) {
        return hiddenBuilder()
                .name(name)
                .acceptedResidents(new ArrayList<>())
                .preferredResidents(new ArrayList<>());
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
}
