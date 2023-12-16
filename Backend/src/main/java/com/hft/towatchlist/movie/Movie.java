package com.hft.towatchlist.movie;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
@Data
public class Movie {

    @Id
    private ObjectId movieId;
    private String movieTitle;

    public String getMovieId() {
        return movieId.toHexString();
    }

}
