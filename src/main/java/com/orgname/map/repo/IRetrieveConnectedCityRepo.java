package com.orgname.map.repo;

import com.orgname.map.model.Road;

import java.util.List;

public interface IRetrieveConnectedCityRepo {

    /**
     * This inferface is responsible for retrieving the list of connected city from backend db or flat file.
     * @return
     */

    public List<Road>  retrieveConnectedCities() ;


}
