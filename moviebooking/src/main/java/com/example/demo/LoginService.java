package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping(value="/validateUser")
	@HystrixCommand(fallbackMethod="getValidateUserBackup")
	public boolean validateUser(@RequestBody User user) throws JsonProcessingException {
		logger.info("at begining - com.example.demo.LoginService.validateUser(User)");
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    ObjectMapper obj = new ObjectMapper();
	    String request = obj.writeValueAsString(user);
	    HttpEntity<String> entity = new HttpEntity<String>(request,headers);
	    logger.info("at ending - com.example.demo.LoginService.validateUser(User)");
	    return restTemplate.exchange("http://moviebooking-authentication/moviebooking-authentication/api/authenticate",HttpMethod.POST, entity, boolean.class).getBody();
	}
	
	public boolean getValidateUserBackup(@RequestBody User user) throws JsonProcessingException {
		logger.info("in circuit breaker - com.example.demo.LoginService.getValidateUserBackup(User)");
		return false;
	}
	
	@PostMapping(value="/getmovies")
	@HystrixCommand(fallbackMethod="getMoviesBackup")
	public List<MovieEntity> getMovies(@RequestBody Moviemodel moviemodel) throws JsonProcessingException{
		logger.info("at begining - com.example.demo.LoginService.getMovies(Moviemodel)");
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    ObjectMapper obj = new ObjectMapper();
	    String request = obj.writeValueAsString(moviemodel);
	    HttpEntity<String> entity = new HttpEntity<String>(request,headers);
	    ResponseEntity<List<MovieEntity>> rateResponse = restTemplate.exchange("http://moviebooking-booking/moviebooking-booking/api/getmovies",
	                        HttpMethod.POST, entity, new ParameterizedTypeReference<List<MovieEntity>>() {
	                });
	    List<MovieEntity> rates = rateResponse.getBody();
	    logger.info("at ending - com.example.demo.LoginService.getMovies(Moviemodel)");
		return rates;
	}
	
	public List<MovieEntity> getMoviesBackup(@RequestBody Moviemodel moviemodel) throws JsonProcessingException{
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setSno(0);
		movieEntity.setCityname("circuit breaker");
		movieEntity.setMoviename("circuit breaker");
		movieEntity.setNooftickets(0);
		movieEntity.setTheatername("circuit breaker");
		ArrayList<MovieEntity> movieEntities = new ArrayList<MovieEntity>();
		movieEntities.add(movieEntity);
		logger.info("in circuit breaker - com.example.demo.LoginService.getMoviesBackup(Moviemodel)");
		return movieEntities;
	}
	
	@PostMapping(value="/bookmovies")
	@HystrixCommand(fallbackMethod="bookMoviesBackup")
	public String bookMovies(@RequestBody BookingModel bookingmodel) throws JsonProcessingException{
		logger.info("at begining - com.example.demo.LoginService.bookMovies(BookingModel)");
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    ObjectMapper obj = new ObjectMapper();
	    String request = obj.writeValueAsString(bookingmodel);
	    HttpEntity<String> entity = new HttpEntity<String>(request,headers);
	    logger.info("at ending - com.example.demo.LoginService.bookMovies(BookingModel)");
	    return restTemplate.exchange("http://moviebooking-booking/moviebooking-booking/api/bookmovies",HttpMethod.POST, entity, String.class).getBody();
	}
	
	public String bookMoviesBackup(@RequestBody BookingModel bookingmodel) throws JsonProcessingException{
		logger.info("at begining - com.example.demo.LoginService.bookMoviesBackup(BookingModel)");
		return "circuit breaker";
	}
}
