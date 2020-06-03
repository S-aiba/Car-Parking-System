package com.newpark.newcarkparking;

public class Twodstatus {

	private int[][] empty;
	private int[][] reserve;
	public Twodstatus(int[][] empty, int[][] reserve) {
		super();
		this.empty = empty;
		this.reserve = reserve;
	}
	public int[][] getEmpty() {
		return empty;
	}
	public void setEmpty(int[][] empty) {
		this.empty = empty;
	}
	public int[][] getReserve() {
		return reserve;
	}
	public void setReserve(int[][] reserve) {
		this.reserve = reserve;
	}
	
	
}

