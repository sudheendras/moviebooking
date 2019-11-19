package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class MovieEntity {
	
	@Id
	@Column(name="sno")
	private int sno;
	@Column(name="moviename")
	private String moviename;
	@Column(name="cityname")
	private String cityname;
	@Column(name="theatername")
	private String theatername;
	@Column(name="nooftickets")
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
