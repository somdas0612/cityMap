package com.orgname.map.v1.controller;

import com.orgname.map.exception.CityNameRequiredExcpetion;
import com.orgname.map.service.IConnectCitySerevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ConnectCityV1Controller {
    private static final Logger LOG = Logger.getLogger(ConnectCityV1Controller.class.getName());
    @Autowired
    IConnectCitySerevice connectCitySerevice;

    @RequestMapping(value="/connected/{origincity}/{destinationcity}")
    public String connectCity(@PathVariable("origincity") String origincity, @PathVariable("destinationcity") String destinationcity ) {
        String response = "No";
        try {
            response = connectCitySerevice.checkIfCityConnected(origincity, destinationcity);
        }catch (CityNameRequiredExcpetion ex) {
            LOG.log(Level.INFO, "Exception occur", ex);
            response ="origin and destination city name are required ";
        }
        return response;
    }
}
