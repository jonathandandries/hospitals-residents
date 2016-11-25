/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dandries.hr.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author jonathandandries
 */
public @Builder(builderMethodName = "hiddenBuilder") @Data class Resident implements Serializable {
    private String name;
    private List<Hospital> preferredHospitals;
    
    public static ResidentBuilder builder(String name) {
        return hiddenBuilder()
                .name(name)
                .preferredHospitals(new ArrayList<>());
    }
}
