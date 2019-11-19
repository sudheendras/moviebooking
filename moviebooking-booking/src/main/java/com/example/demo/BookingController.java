package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	MovieRepository movieRepository;
	
	@RequestMapping(value="/getmovies", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovieEntity> getMovies(@RequestBody Moviemodel moviemodel) {
		logger.info("at begining - com.example.demo.BookingController.getMovies(Moviemodel)");
		List<MovieEntity> result = new ArrayList<MovieEntity>();
		List<MovieEntity> movies = movieRepository.findByMovienameAndCityname(moviemodel.getMoviename(), moviemodel.getCityname());
		if(movies.get(0).getNooftickets()>0) {
			result = movies;
		}
		logger.info("at ending - com.example.demo.BookingController.getMovies(Moviemodel)");
		return result;
	}
	
	@RequestMapping(value="/bookmovies", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public String bookMovies(@RequestBody BookingModel bookingmodel) {
		logger.info("at begining - com.example.demo.BookingController.bookMovies(BookingModel)");
		List<MovieEntity> movies = movieRepository.findByMovienameAndCityname(bookingmodel.getMoviename(), bookingmodel.getCityname());
		if(movies.get(0).getNooftickets()>0) {
			int setTickets = movies.get(0).getNooftickets()-bookingmodel.getNooftickets();
			movies.get(0).setNooftickets(setTickets);
			movieRepository.save(movies.get(0));
			logger.info("at ending - com.example.demo.BookingController.bookMovies(BookingModel)");
			return "tickets have been booked";
		}
		logger.info("at ending - com.example.demo.BookingController.bookMovies(BookingModel)");
		return "tickets have not been booked";
	}
}
