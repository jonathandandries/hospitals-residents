package com.dandries.hr;

import com.dandries.hr.data.Hospital;
import com.dandries.hr.data.Resident;
import com.dandries.hr.logic.DeferredAcceptance;
import com.dandries.hr.util.ColumnPrinter;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        runNMRP();
        runCommons();
    }

    public void runNMRP() {
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
        Hospital.getPrinter(hospitalMatches).print();
    }

    public void runCommons() {
        Map<String, Hospital> hospitalMap = new HashMap();
        hospitalMap.put("Team 1", Hospital.builder("Team 1").capacity(7).build());
        hospitalMap.put("Team 2", Hospital.builder("Team 2").capacity(7).build());
        hospitalMap.put("Team 3", Hospital.builder("Team 3").capacity(7).build());
        hospitalMap.put("Team 4", Hospital.builder("Team 4").capacity(7).build());
        hospitalMap.put("Team 5", Hospital.builder("Team 5").capacity(7).build());
        hospitalMap.put("Team 6", Hospital.builder("Team 6").capacity(7).build());
        hospitalMap.put("Team 7", Hospital.builder("Team 7").capacity(7).build());
        hospitalMap.put("Team 8", Hospital.builder("Team 8").capacity(7).build());
        hospitalMap.put("Team 9", Hospital.builder("Team 9").capacity(7).build());
        hospitalMap.put("Team 10", Hospital.builder("Team 10").capacity(7).build());

        Map<String, Resident> residentMap = new HashMap();
        residentMap.put("Student 1", Resident.builder("Student 1")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 5"))).build());
        residentMap.put("Student 2", Resident.builder("Student 2")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 1"))).build());
        residentMap.put("Student 3", Resident.builder("Student 3")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 4"))).build());
        residentMap.put("Student 4", Resident.builder("Student 4")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 5"))).build());
        residentMap.put("Student 5", Resident.builder("Student 5")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 2"))).build());
        residentMap.put("Student 6", Resident.builder("Student 6")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 10"))).build());
        residentMap.put("Student 7", Resident.builder("Student 7")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 8", Resident.builder("Student 8")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 9", Resident.builder("Student 9")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 10", Resident.builder("Student 10")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 3"))).build());
        residentMap.put("Student 11", Resident.builder("Student 11")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 12", Resident.builder("Student 12")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 2"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 13", Resident.builder("Student 13")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 5"))).build());
        residentMap.put("Student 14", Resident.builder("Student 14")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 4"))).build());
        residentMap.put("Student 15", Resident.builder("Student 15")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 10"))).build());
        residentMap.put("Student 16", Resident.builder("Student 16")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 17", Resident.builder("Student 17")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 7"))).build());
        residentMap.put("Student 18", Resident.builder("Student 18")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 19", Resident.builder("Student 19")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 4"))).build());
        residentMap.put("Student 20", Resident.builder("Student 20")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 5"))).build());
        residentMap.put("Student 21", Resident.builder("Student 21")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 1"))).build());
        residentMap.put("Student 22", Resident.builder("Student 22")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 1"))).build());
        residentMap.put("Student 23", Resident.builder("Student 23")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 2"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 1"))).build());
        residentMap.put("Student 24", Resident.builder("Student 24")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 25", Resident.builder("Student 25")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 4"))).build());
        residentMap.put("Student 26", Resident.builder("Student 26")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 3"))).build());
        residentMap.put("Student 27", Resident.builder("Student 27")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 3"))).build());
        residentMap.put("Student 28", Resident.builder("Student 28")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 3"))).build());
        residentMap.put("Student 29", Resident.builder("Student 29")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 4"))).build());
        residentMap.put("Student 30", Resident.builder("Student 30")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 10"))).build());
        residentMap.put("Student 31", Resident.builder("Student 31")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 5"))).build());
        residentMap.put("Student 32", Resident.builder("Student 32")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 5"))).build());
        residentMap.put("Student 33", Resident.builder("Student 33")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 34", Resident.builder("Student 34")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 5"))).build());
        residentMap.put("Student 35", Resident.builder("Student 35")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 4"))).build());
        residentMap.put("Student 36", Resident.builder("Student 36")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 5"))).build());
        residentMap.put("Student 37", Resident.builder("Student 37")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 10"))).build());
        residentMap.put("Student 38", Resident.builder("Student 38")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 10"))).build());
        residentMap.put("Student 39", Resident.builder("Student 39")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 10"))).build());
        residentMap.put("Student 40", Resident.builder("Student 40")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 10"))).build());
        residentMap.put("Student 41", Resident.builder("Student 41")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 9"))).build());
        residentMap.put("Student 42", Resident.builder("Student 42")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 4"))).build());
        residentMap.put("Student 43", Resident.builder("Student 43")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 5"))).build());
        residentMap.put("Student 44", Resident.builder("Student 44")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 4"))).build());
        residentMap.put("Student 45", Resident.builder("Student 45")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 9"))).build());
        residentMap.put("Student 46", Resident.builder("Student 46")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 47", Resident.builder("Student 47")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 48", Resident.builder("Student 48")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 49", Resident.builder("Student 49")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 5"))).build());
         residentMap.put("Student 50", Resident.builder("Student 50")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 2"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 51", Resident.builder("Student 51")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"),
                        hospitalMap.get("Team 6"))).build());
        residentMap.put("Student 52", Resident.builder("Student 52")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"))).build());
        residentMap.put("Student 53", Resident.builder("Student 53")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 8"))).build());
        residentMap.put("Student 54", Resident.builder("Student 54")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"))).build());
        residentMap.put("Student 55", Resident.builder("Student 55")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"))).build());
        residentMap.put("Student 56", Resident.builder("Student 56")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 2"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 8"))).build());
        residentMap.put("Student 57", Resident.builder("Student 57")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"))).build());
        residentMap.put("Student 58", Resident.builder("Student 58")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 2"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"))).build());
        residentMap.put("Student 59", Resident.builder("Student 59")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 2"),
                        hospitalMap.get("Team 8"))).build());
         residentMap.put("Student 60", Resident.builder("Student 60")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 8"))).build());
        residentMap.put("Student 61", Resident.builder("Student 61")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 8"))).build());
        residentMap.put("Student 62", Resident.builder("Student 62")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 2"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 8"))).build());
        residentMap.put("Student 63", Resident.builder("Student 63")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 10"))).build());
        residentMap.put("Student 64", Resident.builder("Student 64")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 10"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 1"),
                        hospitalMap.get("Team 3"))).build());
        residentMap.put("Student 65", Resident.builder("Student 65")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 1"))).build());
        residentMap.put("Student 66", Resident.builder("Student 66")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 3"),
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 4"))).build());
        residentMap.put("Student 67", Resident.builder("Student 67")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 2"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 4"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 9"))).build());
        residentMap.put("Student 68", Resident.builder("Student 68")
                .preferredHospitals(asList(
                        hospitalMap.get("Team 9"),
                        hospitalMap.get("Team 6"),
                        hospitalMap.get("Team 7"),
                        hospitalMap.get("Team 5"),
                        hospitalMap.get("Team 10"))).build());
        
        hospitalMap.get("Team 1").setPreferredResidents(asList(
                residentMap.get("Student 15"),
                residentMap.get("Student 5"),
                residentMap.get("Student 13"),
                residentMap.get("Student 29"),
                residentMap.get("Student 30"),
                residentMap.get("Student 47"),
                residentMap.get("Student 52"),
                residentMap.get("Student 61"),
                residentMap.get("Student 62"),
                residentMap.get("Student 8"),
                residentMap.get("Student 37"),
                residentMap.get("Student 2"),
                residentMap.get("Student 7"),
                residentMap.get("Student 21"),
                residentMap.get("Student 22"),
                residentMap.get("Student 23"),
                residentMap.get("Student 54"),
                residentMap.get("Student 64"),
                residentMap.get("Student 65")
        ));
        hospitalMap.get("Team 2").setPreferredResidents(asList(
                residentMap.get("Student 67"),
                residentMap.get("Student 59"),
                residentMap.get("Student 58"),
                residentMap.get("Student 53"),
                residentMap.get("Student 52"),
                residentMap.get("Student 39"),
                residentMap.get("Student 37"),
                residentMap.get("Student 7"),
                residentMap.get("Student 6"),
                residentMap.get("Student 5"),
                residentMap.get("Student 2")
        ));
        hospitalMap.get("Team 3").setPreferredResidents(asList(
                residentMap.get("Student 27"),
                residentMap.get("Student 10"),
                residentMap.get("Student 7"),
                residentMap.get("Student 8"),
                residentMap.get("Student 11"),
                residentMap.get("Student 15"),
                residentMap.get("Student 17"),
                residentMap.get("Student 21"),
                residentMap.get("Student 25"),
                residentMap.get("Student 28"),
                residentMap.get("Student 33"),
                residentMap.get("Student 37"),
                residentMap.get("Student 42"),
                residentMap.get("Student 49"),
                residentMap.get("Student 53"),
                residentMap.get("Student 55"),
                residentMap.get("Student 57"),
                residentMap.get("Student 61"),
                residentMap.get("Student 63")
        ));
        hospitalMap.get("Team 4").setPreferredResidents(asList(
                residentMap.get("Student 52"),
                residentMap.get("Student 24"),
                residentMap.get("Student 5"),
                residentMap.get("Student 67"),
                residentMap.get("Student 21"),
                residentMap.get("Student 42"),
                residentMap.get("Student 7"),
                residentMap.get("Student 16"),
                residentMap.get("Student 44")
        ));
        hospitalMap.get("Team 5").setPreferredResidents(asList(
                residentMap.get("Student 20"),
                residentMap.get("Student 42"),
                residentMap.get("Student 52"),
                residentMap.get("Student 57"),
                residentMap.get("Student 68"),
                residentMap.get("Student 1"),
                residentMap.get("Student 4"),
                residentMap.get("Student 8"),
                residentMap.get("Student 14"),
                residentMap.get("Student 47")
        ));
        hospitalMap.get("Team 6").setPreferredResidents(asList(
                residentMap.get("Student 53"),
                residentMap.get("Student 18"),
                residentMap.get("Student 56"),
                residentMap.get("Student 55"),
                residentMap.get("Student 49"),
                residentMap.get("Student 47"),
                residentMap.get("Student 46"),
                residentMap.get("Student 44"),
                residentMap.get("Student 39"),
                residentMap.get("Student 20"),
                residentMap.get("Student 16"),
                residentMap.get("Student 15"),
                residentMap.get("Student 11"),
                residentMap.get("Student 7"),
                residentMap.get("Student 8"),
                residentMap.get("Student 4"),
                residentMap.get("Student 33")
        ));
        hospitalMap.get("Team 7").setPreferredResidents(asList(
                residentMap.get("Student 8"),
                residentMap.get("Student 17"),
                residentMap.get("Student 18"),
                residentMap.get("Student 20"),
                residentMap.get("Student 44"),
                residentMap.get("Student 47"),
                residentMap.get("Student 57"),
                residentMap.get("Student 61"),
                residentMap.get("Student 52")
        ));
        hospitalMap.get("Team 8").setPreferredResidents(asList(
                residentMap.get("Student 49"),
                residentMap.get("Student 20"),
                residentMap.get("Student 17"),
                residentMap.get("Student 63"),
                residentMap.get("Student 54"),
                residentMap.get("Student 42"),
                residentMap.get("Student 39"),
                residentMap.get("Student 37"),
                residentMap.get("Student 52"),
                residentMap.get("Student 7"),
                residentMap.get("Student 18"),
                residentMap.get("Student 48"),
                residentMap.get("Student 13"),
                residentMap.get("Student 47"),
                residentMap.get("Student 36")
        ));
        hospitalMap.get("Team 9").setPreferredResidents(asList(
                residentMap.get("Student 19"),
                residentMap.get("Student 30"),
                residentMap.get("Student 58"),
                residentMap.get("Student 63"),
                residentMap.get("Student 65"),
                residentMap.get("Student 11"),
                residentMap.get("Student 45"),
                residentMap.get("Student 53"),
                residentMap.get("Student 56"),
                residentMap.get("Student 67")
        ));
        hospitalMap.get("Team 10").setPreferredResidents(asList(
                residentMap.get("Student 64"),
                residentMap.get("Student 58"),
                residentMap.get("Student 53"),
                residentMap.get("Student 50"),
                residentMap.get("Student 18"),
                residentMap.get("Student 8"),
                residentMap.get("Student 6"),
                residentMap.get("Student 30"),
                residentMap.get("Student 13"),
                residentMap.get("Student 2"),
                residentMap.get("Student 16"),
                residentMap.get("Student 7"),
                residentMap.get("Student 63"),
                residentMap.get("Student 59"),
                residentMap.get("Student 37"),
                residentMap.get("Student 15"),
                residentMap.get("Student 39")
        ));

        List<Resident> residentsToMatch = asList(
                residentMap.get("Student 1"),
                residentMap.get("Student 2"),
                residentMap.get("Student 3"),
                residentMap.get("Student 4"),
                residentMap.get("Student 5"),
                residentMap.get("Student 6"),
                residentMap.get("Student 7"),
                residentMap.get("Student 8"),
                residentMap.get("Student 9"),
                residentMap.get("Student 10"),
                residentMap.get("Student 11"),
                residentMap.get("Student 12"),
                residentMap.get("Student 13"),
                residentMap.get("Student 14"),
                residentMap.get("Student 15"),
                residentMap.get("Student 16"),
                residentMap.get("Student 17"),
                residentMap.get("Student 18"),
                residentMap.get("Student 19"),
                residentMap.get("Student 20"),
                residentMap.get("Student 21"),
                residentMap.get("Student 22"),
                residentMap.get("Student 23"),
                residentMap.get("Student 24"),
                residentMap.get("Student 25"),
                residentMap.get("Student 26"),
                residentMap.get("Student 27"),
                residentMap.get("Student 28"),
                residentMap.get("Student 29"),
                residentMap.get("Student 30"),
                residentMap.get("Student 31"),
                residentMap.get("Student 32"),
                residentMap.get("Student 33"),
                residentMap.get("Student 34"),
                residentMap.get("Student 35"),
                residentMap.get("Student 36"),
                residentMap.get("Student 37"),
                residentMap.get("Student 38"),
                residentMap.get("Student 39"),
                residentMap.get("Student 40"),
                residentMap.get("Student 41"),
                residentMap.get("Student 42"),
                residentMap.get("Student 43"),
                residentMap.get("Student 44"),
                residentMap.get("Student 45"),
                residentMap.get("Student 46"),
                residentMap.get("Student 47"),
                residentMap.get("Student 48"),
                residentMap.get("Student 49"),
                residentMap.get("Student 50"),
                residentMap.get("Student 51"),
                residentMap.get("Student 52"),
                residentMap.get("Student 53"),
                residentMap.get("Student 54"),
                residentMap.get("Student 55"),
                residentMap.get("Student 56"),
                residentMap.get("Student 57"),
                residentMap.get("Student 58"),
                residentMap.get("Student 59"),
                residentMap.get("Student 60"),
                residentMap.get("Student 61"),
                residentMap.get("Student 62"),
                residentMap.get("Student 63"),
                residentMap.get("Student 64"),
                residentMap.get("Student 65"),
                residentMap.get("Student 66"),
                residentMap.get("Student 67"),
                residentMap.get("Student 68")
        );
        
        List<Hospital> hospitalsToMatch = asList(
                hospitalMap.get("Team 1"),
                hospitalMap.get("Team 2"),
                hospitalMap.get("Team 3"),
                hospitalMap.get("Team 4"),
                hospitalMap.get("Team 5"),
                hospitalMap.get("Team 6"),
                hospitalMap.get("Team 7"),
                hospitalMap.get("Team 8"),
                hospitalMap.get("Team 9"),
                hospitalMap.get("Team 10")
        );

        List<Hospital> hospitalMatches = DeferredAcceptance.optimizeForResidents(residentsToMatch, hospitalsToMatch);
        Hospital.getPrinter(hospitalMatches).print();
    }
}
