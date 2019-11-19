package com.example.demo;

public class MovieEntity {
	
	private int sno;
	private String moviename;
	private String cityname;
	private String theatername;
	private int nooftickets;

	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getTheatername() {
		return theatername;
	}
	public void setTheatername(String theatername) {
		this.theatername = theatername;
	}
	public int getNooftickets() {
		return nooftickets;
	}
	public void setNooftickets(int nooftickets) {
		this.nooftickets = nooftickets;
	}
}
