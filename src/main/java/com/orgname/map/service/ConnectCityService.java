package com.orgname.map.service;

import com.orgname.map.exception.CityNameRequiredExcpetion;
import com.orgname.map.model.Road;
import com.orgname.map.repo.IRetrieveConnectedCityRepo;
import com.orgname.map.validate.ValidateCities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConnectCityService implements IConnectCitySerevice {

    @Autowired
    IRetrieveConnectedCityRepo retrieveConnectedCityRepo;

    @Autowired
    ValidateCities validateCities;

    /**
     * Validate the city input , throw validation exception if they are blank or null.
     * <p> Call repo to retrieve connected cities and apply the logic to check if the cities are connected</p>
     * <p>remember this class will just check if the cities are connected nothing more than this so that we will comply with SRP priciple</p>
     * @param orgCity
     * @param desCity
     * @return
     * @throws CityNameRequiredExcpetion
     */

    @Override
    public String checkIfCityConnected(String orgCity, String desCity) throws CityNameRequiredExcpetion {


        //Validate city first before you read the data from repo
        validateCities.validateCities(orgCity,desCity);
        List<Road>  roads = retrieveConnectedCityRepo.retrieveConnectedCities();
        //Use DRY priciple
        List<Road> listOrgRoads = getListOfNodes(roads,orgCity);
        List<Road> listDesRoads = getListOfNodes(roads,desCity);
        return  evaluateIfRoadExists(orgCity, listOrgRoads, listDesRoads);
    }

    /**
     * <p> To determine if the road exist , apply the following algorithm
     * </p>
     * <p> 1. Iterate the origin city list and finds the connected city of given origin
     *     2. Check if the connected city is there in destination city list</p>
     * @param orgCity
     * @param listOrgRoads
     * @param listDesRoads
     * @return
     */
    private String evaluateIfRoadExists(String orgCity, List<Road> listOrgRoads, List<Road> listDesRoads) {
        String ifConnected ="No";

        // If any of the node is not found that means there is not connectivity between those road
        if(listOrgRoads.isEmpty()
                ||listOrgRoads.isEmpty()) {
            return ifConnected;
        }

        for(int i=0; i<listOrgRoads.size() &&!"Yes".equals(ifConnected);i++ ) {

                String connectedNode = null;
                Road road =listOrgRoads.get(i);
                if (road.getOrgCity().equals(orgCity)) {
                    connectedNode = road.getDesCity();
                } else {
                    connectedNode = road.getOrgCity();
                }
                ifConnected = checkIfThereInDestNode(listDesRoads, connectedNode);

        }
        return ifConnected;
    }

    /**
     * Finds the list of nodes that matches with given city
     * @param roads
     * @param city
     * @return
     */
    private List<Road> getListOfNodes(List<Road> roads , String city) {
        List<Road> listOfNodes = new ArrayList<Road>() ;
        for(Road road : roads) {
            if(road.getOrgCity().equals(city)
                    || road.getDesCity().equals(city)) {
                listOfNodes.add(road);
            }
        }
        return listOfNodes;

    }

    /**
     * If the Node is found in destination Roads (which is grap edge) then return yes otherwise no
     * @param listDesRoads
     * @param connectedNode
     * @return
     */
    private String checkIfThereInDestNode(List<Road> listDesRoads, String connectedNode) {
        for(Road road :listDesRoads){
            if (connectedNode.equals(road.getDesCity())
               ||connectedNode.equals(road.getOrgCity()) ){
                return  "Yes";
            }
        }
        return "No";
    }
}
