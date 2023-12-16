package com.hft.towatchlist.movie;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> allMovies() {
        List<Movie> movies = movieService.findMovies();
        return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getSingleMovie(@PathVariable ObjectId id) {
        return new ResponseEntity<Movie>(movieService.findSingleMovie(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieJson movie) {
        return new ResponseEntity<Movie>(movieService.saveMovie(movie.getMovieTitle()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable ObjectId id) {
        Movie movie = movieService.findSingleMovie(id);
        if (movie != null) {
            movieService.deleteMovie(movie);
            return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Movie to be deleted not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable ObjectId id, @RequestBody MovieJson movieBody) {
        Movie movie = movieService.findSingleMovie(id);
        if (movie != null) {
            movie.setMovieTitle(movieBody.getMovieTitle());
            movieService.updateMovie(movie);
            return new ResponseEntity<String>(movieBody.getMovieTitle(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
