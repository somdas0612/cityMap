package com.orgname.map.service;

import com.orgname.map.exception.CityNameRequiredExcpetion;
import com.orgname.map.model.Road;
import com.orgname.map.repo.IRetrieveConnectedCityRepo;
import com.orgname.map.validate.ValidateCities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
        Map<String, Set<String>> adjacentCityMap = getAdjacentCityMap(roads);
        return  evaluateIfRoadExists(adjacentCityMap,orgCity,desCity);
    }

    /**
     * <p> To determine if the road exist , apply the following algorithm
     * </p>
     * <p> 1. Get the list of adjacent cities of orgin city
     *     2. Find out if the destination is there in adjacent cities if not then
     *        a. find the adjacent cities of adjacent city selected in step#2
     *        b. If the adjacent city is already visited do not visit them , get the next adjacent city
     *        c. go to step 2 and loop through.
     *     2. While all the adjacent cities of adjacent city of origin visited , terminate the program/p>
     * @param adjacentCityMap
     * @param orgCity
     * @param desCity
     * @return
     */
    private String evaluateIfRoadExists(Map<String, Set<String>> adjacentCityMap, String orgCity, String desCity) {
        boolean found = false;
        List<String>  visitedCities =  new ArrayList<>();
        visitedCities.add(orgCity);
        Set<String>  adjacentCities = adjacentCityMap.get(orgCity);
        Queue<String> waitingQueue = new LinkedList<>();
        populateAdjacentCityToQueue(waitingQueue,adjacentCities,visitedCities);
        while(!found && !waitingQueue.isEmpty()) {
             if(adjacentCities.contains(desCity)){
                 found=true;
             }else{
                 String visitedCity = waitingQueue.remove();
                 visitedCities.add(visitedCity);
                 adjacentCities=adjacentCityMap.get(visitedCity);
                 populateAdjacentCityToQueue(waitingQueue,adjacentCities,visitedCities);

             }
        }
        return found?"Yes":"No";
    }

    private void populateAdjacentCityToQueue(Queue<String> waitingQueue, Set<String> adjacentCities, List<String> visitedCities) {
        adjacentCities.forEach(city->{
            if(!visitedCities.contains(city) &&
                    !waitingQueue.contains(city)){
                waitingQueue.add(city);
            }
        });
    }

    /**
     * <p> if there is a road between A->B then it sgould return like this
     *  {
     *    {"A" {B}},
     *    {"B", {A}}
     *    Since A and B are adjacent city of each other</p>
     * @param roads
     * @return
     */
    private Map<String, Set<String>> getAdjacentCityMap(List<Road> roads) {
        Map<String , Set<String>> map  =  new HashMap<String, Set<String>>();
        roads.forEach(road->{
            mapAdjacentCity(map, road.getOrgCity(),road.getDesCity());
            mapAdjacentCity(map, road.getDesCity(),road.getOrgCity());

        });
        return map;
    }

    private void mapAdjacentCity(Map<String, Set<String>> map, String OrgCity,String destCity) {
        Set<String> origAdjacentCity = map.get(OrgCity);
        if(origAdjacentCity==null){
            origAdjacentCity =new HashSet<>();
            map.put(OrgCity,origAdjacentCity);
        }
        origAdjacentCity.add(destCity);
    }


}
