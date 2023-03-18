package com.vet.manage.util;

import com.vet.manage.model.dto.Province;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Util class load master data
 * @author Jane Aarthy Joseph
 */
public class MasterData {

    /**
     * Load country list master data
     * @return map of country code and country name
     */
    public static Map<String, String> getCountries(){
        Map<String, String> countries = new TreeMap<>();
        countries.put("CA", "Canada");
        countries.put("US", "America");

        return countries;
    }

    /**
     * Load provinces master data
     * @return map of @Province master data
     */
    public static Map<Province, String> getProvinces(){
        return Arrays.stream(Province.values()).collect(Collectors.toMap(e->e, e->e.getValue(), (oldValue, newValue) -> oldValue, TreeMap::new));
    }
}
