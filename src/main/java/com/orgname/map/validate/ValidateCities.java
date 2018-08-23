package com.orgname.map.validate;

import com.orgname.map.exception.CityNameRequiredExcpetion;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ValidateCities {

   public  void validateCities(String orgCity, String desCity) throws CityNameRequiredExcpetion {
       validateCity(orgCity);
       validateCity(desCity);
    }

   private  void validateCity(String city) throws CityNameRequiredExcpetion {
       if(StringUtils.isEmpty(city)) {
           throw new CityNameRequiredExcpetion("OriginCity required");
       }
    }
}
