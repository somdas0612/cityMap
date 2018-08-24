package com.orgname.map.v1.controller;

import com.orgname.map.exception.CityNameRequiredExcpetion;
import com.orgname.map.service.IConnectCitySerevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ConnectCityV1Controller {
    private static final Logger LOG = Logger.getLogger(ConnectCityV1Controller.class.getName());
    @Autowired
    IConnectCitySerevice connectCitySerevice;

//    @RequestMapping(value="/connected/{origincity}/{destinationcity}")
//    public ResponseEntity<String> connectCity(@PathVariable("origincity") String origincity, @PathVariable("destinationcity") String destinationcity ) {
//        String response = "No";
//        try {
//            response = connectCitySerevice.checkIfCityConnected(origincity, destinationcity);
//        }catch (CityNameRequiredExcpetion ex) {
//            LOG.log(Level.INFO, "Exception occur", ex);
//            response =ex.getMessage();
//        }
//        return ResponseEntity.ok(response);
//    }
    @RequestMapping(value="/connected", method= RequestMethod.GET)
    public ResponseEntity<String> connectCity(@RequestParam(name ="origin") String origincity, @RequestParam(name ="destination") String destinationcity ) {
        String response = "No";
        try {
            response = connectCitySerevice.checkIfCityConnected(StringUtils.trimWhitespace(origincity), StringUtils.trimWhitespace(destinationcity));
        }catch (CityNameRequiredExcpetion ex) {
            LOG.log(Level.INFO, "Exception occur", ex);
            response =ex.getMessage();
        }
        return ResponseEntity.ok(response);
    }
}
