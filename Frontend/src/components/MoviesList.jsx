import React, { useState, useEffect } from 'react';
import axios from 'axios';

const MovieList = () => {
    const [movies, setMovies] = useState([]);
    const [newMovieTitle, setNewMovieTitle] = useState('');

    useEffect(() => {
        // Fetch movies when the component mounts
        fetchMovies();
    }, []);

    const fetchMovies = async () => {
        try {
            const response = await fetch('http://localhost:8055/api/v1/movies');
            const data = await response.json();
            setMovies(data);
        } catch (error) {
            console.error('Error fetching movies:', error);
        }
    };

    const handleInputChange = (event) => {
        setNewMovieTitle(event.target.value);
    };

    const handleAddMovie = async () => {
        try {
            await fetch('http://localhost:8055/api/v1/movies', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ movieTitle: newMovieTitle }),
            });

            // Clear the input field and fetch the updated movie list
            setNewMovieTitle('');
            fetchMovies();
        } catch (error) {
            console.error('Error adding movie:', error);
        }
    };

    const handleEditMovie = async (id, updatedTitle) => {
        try {
            await fetch(`http://localhost:8055/api/v1/movies/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ movieTitle: updatedTitle }),
            });

            // Fetch the updated movie list
            fetchMovies();
        } catch (error) {
            console.error('Error updating movie:', error);
        }
    };

    const handleDeleteMovie = async (id) => {
        try {
            await fetch(`http://localhost:8055/api/v1/movies/${id}`, {
                method: 'DELETE',
            });

            // Fetch the updated movie list
            fetchMovies();
        } catch (error) {
            console.error('Error deleting movie:', error);
        }
    };


    return (
        <div>
            <h2>Movies</h2>
            <ul>
                {movies.map((movie, index) => (
                    <li key={index}>
                        {movie.movieTitle}
                        <button onClick={() => handleEditMovie(movie.movieId, prompt('Enter new title:', movie.movieTitle))}>
                            Edit
                        </button>
                        <button onClick={() => handleDeleteMovie(movie.movieId)}>Delete</button>
                    </li>
                ))}
            </ul>
            <div>
                <input type="text" value={newMovieTitle} onChange={handleInputChange} />
                <button onClick={handleAddMovie}>Add Movie</button>
            </div>
        </div>
    );
};

export default MovieList;
