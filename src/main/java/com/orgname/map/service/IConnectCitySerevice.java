package com.orgname.map.service;

import com.orgname.map.exception.CityNameRequiredExcpetion;

public interface IConnectCitySerevice {
    /**
     * This API is resposible to validate if there is road available between two city.
     * <p> If the road is avaialbe then return yes other wise no</p>
     * <p>If any of the value is given empty or null then it will return validation Exception</p>
     * @param orgCity
     * @param desCity
     * @return
     */
    public String checkIfCityConnected (String orgCity, String desCity) throws CityNameRequiredExcpetion;
}
