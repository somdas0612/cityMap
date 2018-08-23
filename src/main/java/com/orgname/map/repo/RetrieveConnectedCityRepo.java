package com.orgname.map.repo;

import com.orgname.map.model.Road;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RetrieveConnectedCityRepo implements IRetrieveConnectedCityRepo {
    private static final Logger LOG = Logger.getLogger(RetrieveConnectedCityRepo.class.getName());

    @Override
    public List<Road> retrieveConnectedCities() {
        return readCitiesFromFile();
    }

    /**
     * This method is responsible for getting the cities from flat file and populate the road (edge).     *
     * @return
     */
    private List<Road> readCitiesFromFile() {
       // BufferedReader buffReader = null;
        List<Road> roads = new ArrayList<Road>();
        Scanner scanner =null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("city.txt").getFile());
            scanner = new Scanner(file) ;

                while (scanner.hasNextLine()) {
                    Road road = new Road();
                    String line = scanner.nextLine();
                    String[] cities = line.split(",");
                    road.setOrgCity(cities[0]);
                    road.setDesCity(cities[1]);
                    roads.add(road);

                }

                scanner.close();


//            buffReader = file.ge
//            String line = buffReader.readLine();
//
//            while (line != null) {
//                Road road = new Road();
//                line = buffReader.readLine();
//                String[] cities = line.split(",");
//                road.setOrgCity(cities[0]);
//                road.setDesCity(cities[1]);
//                roads.add(road);
//            }
        } catch (IOException ioe) {
            LOG.log(Level.SEVERE, "Exception occur", ioe);
        } finally {
                scanner.close();

        }
        return roads;
    }

}
