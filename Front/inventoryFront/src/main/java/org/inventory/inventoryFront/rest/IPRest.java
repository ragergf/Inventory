package org.inventory.inventoryFront.rest;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.inventory.inventoryFront.model.ApiRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IPRest {
	@RequestMapping(value = "/apiRest/ip/", method = RequestMethod.GET)
    public ResponseEntity<ApiRest> apiRest() {
		
		ApiRest apiRest = new ApiRest();
		apiRest.setIp("localhost");
		
		try {
			apiRest.setIp(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return new ResponseEntity<ApiRest>(apiRest, HttpStatus.OK);
    }
}
