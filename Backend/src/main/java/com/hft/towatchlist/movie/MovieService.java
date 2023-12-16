package com.hft.towatchlist.movie;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findMovies() {
        return movieRepository.findAll();
    }

    public Movie findSingleMovie(ObjectId id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie saveMovie(String movieTitle) {
        Movie movieEntity = new Movie();
        movieEntity.setMovieTitle(movieTitle);
        return movieRepository.save(movieEntity);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
