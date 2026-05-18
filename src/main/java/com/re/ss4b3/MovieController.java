package com.re.ss4b3;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    static class Movie {
        private String movieId;
        private String title;
        private String genre;
        private double rating;

        public Movie(String movieId, String title, String genre, double rating) {
            this.movieId = movieId;
            this.title = title;
            this.genre = genre;
            this.rating = rating;
        }

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }

    private List<Movie> getMovieData() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("M001", "Inception", "Sci-Fi", 8.8));
        movies.add(new Movie("M002", "Parasite", "Drama", 8.6));
        movies.add(new Movie("M003", "Interstellar", "Sci-Fi", 8.7));
        return movies;
    }

    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable String movieId) {
        return getMovieData()
                .stream()
                .filter(movie -> movie.getMovieId().equals(movieId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping
    public List<Movie> getMoviesByGenre(@RequestParam String genre) {
        return getMovieData()
                .stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }
}
