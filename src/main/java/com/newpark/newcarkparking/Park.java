package com.newpark.newcarkparking;

public class Park {
	
	private String vehiclenum;
	private int floor;
	private int lot;
	private String date;
	
	public Park()
	{
		
	}
	public Park(String vehiclenum, int floor, int lot, String date) {
		super();
		this.vehiclenum = vehiclenum;
		this.floor = floor;
		this.lot = lot;
		this.date = date;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getLot() {
		return lot;
	}
	public void setLot(int lot) {
		this.lot = lot;
	}
	public String getVehiclenum() {
		return vehiclenum;
	}
	public void setVehiclenum(String vehiclenum) {
		this.vehiclenum = vehiclenum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
