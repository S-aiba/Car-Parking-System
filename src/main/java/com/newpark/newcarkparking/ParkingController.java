package com.newpark.newcarkparking;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingController {
	@Autowired
	private ParkingServices service;

	@RequestMapping("/check")
	public String check()
	{
	return service.isavailable();
	}

    @RequestMapping("/checkstaff")
	public String checkstaff()
	{
		return service.isavailablestaff();
	}

	@RequestMapping(method=RequestMethod.GET,value="/nearest")
	public String nearest()
	{
		return service.nearest();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/neareststaff")
	public String neareststaff()
	{
		return service.nearestStaff();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/post")
	public String parkcar(@RequestBody Park park) {
		return service.addparking(park);
	}
	
	@RequestMapping(value="/vehicles")
	public List<Park> getallvehicles(){
		return service.getallvehicles();
	}
	
	@RequestMapping(value="/cost/{vehiclenum}")
	public String cost(@PathVariable String vehiclenum) throws ParseException
	{
		return service.calc(vehiclenum);
	}

}


