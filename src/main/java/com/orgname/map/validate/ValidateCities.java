package com.orgname.map.validate;

import com.orgname.map.exception.CityNameRequiredExcpetion;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ValidateCities {

   public  void validateCities(String orgCity, String desCity) throws CityNameRequiredExcpetion {
       validateCity(orgCity , "Origin City is required");
       validateCity(desCity , "Destination City is required");
    }

   private  void validateCity(String city, String errMsg) throws CityNameRequiredExcpetion {
       if(StringUtils.isEmpty(StringUtils.trimWhitespace(city))) {
           throw new CityNameRequiredExcpetion(errMsg);
       }
    }
}
