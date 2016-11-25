/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dandries.hr.logic;

import com.dandries.hr.data.Hospital;
import com.dandries.hr.data.Resident;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonathandandries
 */
public class DeferredAcceptance {

    public static List<Hospital> optimizeForResidents(List<Resident> residentsToMatch, List<Hospital> hospitalsToMatch) {
        List<Resident> residents = new ArrayList<>(residentsToMatch);
        List<Hospital> hospitals = new ArrayList<>(hospitalsToMatch);
        while (!residents.isEmpty()) {
            Resident res = residents.remove(0);
            List<Hospital> preferredHospitals = new ArrayList<>(res.getPreferredHospitals());
            while (!preferredHospitals.isEmpty()) {
                Hospital candidateHospital = preferredHospitals.remove(0);
                if (candidateHospital.willAcceptResident(res)) {
                    Resident removedResident = candidateHospital.acceptResident(res);
                    if (removedResident != null) {
                        // handle the removed resident next in line.
                        residents.add(0, removedResident);
                    }
                    res.setPreferredHospitals(preferredHospitals);
                    break;
                }
            }
        }
        return hospitals;
    }
}
