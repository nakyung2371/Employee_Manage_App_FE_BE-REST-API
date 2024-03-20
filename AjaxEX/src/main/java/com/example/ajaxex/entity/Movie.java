package com.example.ajaxex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Movie {


    private String backdrop_path;

    @Id
    private int id_num;
    private String original_language;
    private String original_title;
    private String overview;
    private String poster_path;
    private String release_date;
    private String title;
    private int vote_count;

    public Movie () {

    }

}
