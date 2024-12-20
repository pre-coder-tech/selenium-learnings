package com.learnings.seleniumwithjava.testdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SpicejetSearchFlightData {

    public static List<Map<String, String>> getSearchFlightData() {
        List<Map<String, String>> searchFlightData = new ArrayList<>();
        Map<String, String> searchFlightData1 = new HashMap<>();
        searchFlightData1.put("from", "BLR");
        searchFlightData1.put("to", "DEL");
        searchFlightData1.put("departureDate", "2025-02-01");
        searchFlightData1.put("returnDate", "2025-02-10");
        searchFlightData1.put("passengerAdult", "2");
        searchFlightData1.put("passengerChildren", "1");
        searchFlightData1.put("passengerInfant", "1");
        searchFlightData1.put("currency", "INR");
        searchFlightData1.put("tripType", "Round Trip");
        searchFlightData1.put("passengerType", "Unaccompanied Minor");

        searchFlightData.add(searchFlightData1);

        Map<String, String> searchFlightData2 = new HashMap<>();
        searchFlightData2.put("from", "LKO");
        searchFlightData2.put("to", "TRV");
        searchFlightData2.put("departureDate", "2025-01-10");
        searchFlightData2.put("returnDate", "2025-01-12");
        searchFlightData2.put("passengerAdult", "4");
        searchFlightData2.put("passengerChildren", "2");
        searchFlightData2.put("passengerInfant", "0");
        searchFlightData2.put("currency", "USD");
        searchFlightData2.put("tripType", "Round Trip");
        searchFlightData2.put("passengerType", "Family & Friends");
        searchFlightData.add(searchFlightData2);

        Map<String, String> searchFlightData3 = new HashMap<>();
        searchFlightData3.put("from", "STV");
        searchFlightData3.put("to", "RAJ");
        searchFlightData3.put("departureDate", "2024-12-31");
        searchFlightData3.put("returnDate", "");
        searchFlightData3.put("passengerAdult", "2");
        searchFlightData3.put("passengerChildren", "0");
        searchFlightData3.put("passengerInfant", "0");
        searchFlightData3.put("currency", "INR");
        searchFlightData3.put("tripType", "One Way");
        searchFlightData3.put("passengerType", "Senior Citizen");
        searchFlightData.add(searchFlightData3);

        return searchFlightData;
    }
    
}
