package com.dandries.hr.logic;

import com.dandries.hr.data.Hospital;
import com.dandries.hr.data.Resident;
import static java.util.Arrays.asList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertArrayEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeferredAcceptanceTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void optimizeForResidents() {
        Map<String, Hospital> hospitalMap = new HashMap();
        hospitalMap.put("Mercy", Hospital.builder("Mercy").capacity(2).build());
        hospitalMap.put("City", Hospital.builder("City").capacity(2).build());
        hospitalMap.put("General", Hospital.builder("General").capacity(2).build());

        Map<String, Resident> residentMap = new HashMap();
        residentMap.put("Anderson", Resident.builder("Anderson")
                .preferredHospitals(asList(hospitalMap.get("City"))).build());
        residentMap.put("Chen", Resident.builder("Chen")
                .preferredHospitals(asList(hospitalMap.get("City"),
                        hospitalMap.get("Mercy"))).build());
        residentMap.put("Ford", Resident.builder("Ford")
                .preferredHospitals(asList(
                        hospitalMap.get("City"),
                        hospitalMap.get("General"),
                        hospitalMap.get("Mercy"))).build());
        residentMap.put("Davis", Resident.builder("Davis")
                .preferredHospitals(asList(hospitalMap.get("Mercy"),
                        hospitalMap.get("City"),
                        hospitalMap.get("General"))).build());
        residentMap.put("Eastman", Resident.builder("Eastman")
                .preferredHospitals(asList(hospitalMap.get("City"),
                        hospitalMap.get("Mercy"),
                        hospitalMap.get("General"))).build());

        hospitalMap.get("Mercy").setPreferredResidents(asList(
                residentMap.get("Chen"),
                residentMap.get("Ford")
        ));
        hospitalMap.get("City").setPreferredResidents(asList(
                residentMap.get("Eastman"),
                residentMap.get("Anderson"),
                residentMap.get("Chen"),
                residentMap.get("Davis"),
                residentMap.get("Ford")
        ));
        hospitalMap.get("General").setPreferredResidents(asList(
                residentMap.get("Eastman"),
                residentMap.get("Anderson"),
                residentMap.get("Ford"),
                residentMap.get("Davis")
        ));

        List<Resident> residentsToMatch = asList(
                residentMap.get("Anderson"),
                residentMap.get("Chen"),
                residentMap.get("Ford"),
                residentMap.get("Davis"),
                residentMap.get("Eastman")
        );

        List<Hospital> hospitalsToMatch = asList(
                hospitalMap.get("Mercy"),
                hospitalMap.get("City"),
                hospitalMap.get("General")
        );

        List<Hospital> hospitalMatches = DeferredAcceptance.optimizeForResidents(residentsToMatch, hospitalsToMatch);

        Map<String, List<String>> expecteds = new HashMap();
        expecteds.put("Mercy", asList(
                "Chen"
        ));
        expecteds.put("City", asList(
                "Eastman",
                "Anderson"
        ));
        expecteds.put("General", asList(
                "Ford",
                "Davis"
        ));
        for (Hospital hospital : hospitalMatches) {
            List<String> actuals = hospital
                    .getAcceptedResidents()
                    .stream().map(Resident::getName)
                    .collect(Collectors.toList());
            assertArrayEquals(expecteds.get(hospital.getName()).toArray(), actuals.toArray());
        }
    }
}
