package com.example.demo;
 
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieEntity,Integer> {
	
	public List<MovieEntity> findByMovienameAndCityname(String moviename, String cityname);
}
