package com.newpark.newcarkparking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ParkingServices {
	private List<Twodstatus> tdstat=new ArrayList<>(Arrays.asList(new Twodstatus(new int[][] {{1,1,0,1,1},{0,0,1,0},{0,0,0,0,0,1}},new int[][] {{0,0,1,0,0},{1,1,0,0},{1,1,1,0,0,0}})));
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
	private List<Park> parking=new ArrayList<>(Arrays.asList(new Park("JH01X4017",1,1,formatter.format(new Date())),new Park("JH01X4050",2,3,formatter.format(new Date()))));	
	
	public String isavailable()
	{
		int avail=0;
		for(Twodstatus p:tdstat)
		{
			int temp[][]=p.getEmpty();
			int res[][]=p.getReserve();
			for(int a=0;a<temp.length;a++)
			{
				int inside[]=temp[a];
				for(int b=0;b<inside.length;b++)
					if(inside[b]==0 && res[a][b]==0)
						avail++;
			}
		}
		if(avail!=0)
			return "Enough space "+avail;
		return "Not enough space";
	}
	
	public String isavailablestaff()
	{
		int avail=0;
		for(Twodstatus p:tdstat)
		{
			int temp[][]=p.getEmpty();
			int res[][]=p.getReserve();
			for(int a=0;a<res.length;a++)
			{
				int inside[]=res[a];
				for(int b=0;b<inside.length;b++)
					if(inside[b]==1 && temp[a][b]==0)
						avail++;
			}
		}
		if(avail!=0)
			return "Enough space for staff "+avail;
		return "Not enough space";

	}
	
	public String addparking(Park park)
	{
		
		parking.add(park);
		int f=park.getFloor();
		int l=park.getLot();
		for(Twodstatus s:tdstat)
		{
			int temp[][]=s.getEmpty();
			temp[f-1][l-1]=1;
			s.setEmpty(temp);
		}
		return "Successful Parking";
	}
	
	public String nearest()
	{
		for(Twodstatus k:tdstat)
		{
			int temp[][]=k.getEmpty();
			int res[][]=k.getReserve();
			for(int a=0;a<temp.length;a++)
			{
				int inside[]=temp[a];
				for(int b=0;b<inside.length;b++)
				{
					if(inside[b]==0 && res[a][b]==0)
					{
						int x=a+1;
						int y=b+1;
						return "Nearest Empty space at floor number "+x+" and lot number "+y;
					}
						
				}
			}
		}
		return "Sorry No space Avaiable";
	}
	
	public String nearestStaff()
	{
		for(Twodstatus k:tdstat)
		{
			int temp[][]=k.getEmpty();
			int res[][]=k.getReserve();
			for(int a=0;a<res.length;a++)
			{
				int inside[]=res[a];
				for(int b=0;b<inside.length;b++)
				{
					if(inside[b]==1 && temp[a][b]==0)
					{
						int x=a+1;
						int y=b+1;
						return "Nearest Empty space for Staff is  at floor number "+x+" and lot number "+y;
					}
						
				}
			}
		}
		return "Sorry No space Avaiable";
	}

	public List<Park> getallvehicles() {
			return parking;
	}

	public String calc(String vehiclenum) throws ParseException {
		int cost=10;
		int extracost=2;
		for(Park i:parking)
		{
			if(i.getVehiclenum().equals(vehiclenum))
			{
				int f=i.getFloor();
				int l=i.getLot();
				for(Twodstatus k:tdstat)
				{
					int temp[][]=k.getEmpty();
					temp[f-1][l-1]=0;
					k.setEmpty(temp);
				}
				String exit=formatter.format(new Date());
				String entry=i.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Date entryparsed = dateFormat.parse(entry);
				Date exitparsed = dateFormat.parse(exit);
				
				long diff=exitparsed.getTime()-entryparsed.getTime();
				int x=(int) (diff/1000);
				
				parking.removeIf(p->p.getVehiclenum().equals(vehiclenum));
				
				if(x<30)
					return "Seconds parked is "+x+" and the cost is "+cost;
				else
				{
					int m=(int) cost+extracost*(x-30);	
					return "Seconds parked is "+x+" and the cost is "+m;
				}
				
				
			}
		}
		return "Sorry enter the right car number";
	}

}
