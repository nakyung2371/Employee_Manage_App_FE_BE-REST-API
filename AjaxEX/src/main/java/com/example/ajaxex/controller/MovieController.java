package com.example.ajaxex.controller;

import com.example.ajaxex.dto.AjaxDto;
import com.example.ajaxex.entity.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    @PostMapping("/movie/post")

    public ResponseEntity movieLab(
            @RequestBody Movie movie
    ) {
        List<Movie> movieList = new ArrayList<>();


        return new ResponseEntity<>(movieList, HttpStatus.OK);
//        return new ResponseEntity<>(dtoList, HttpStatus.UNAUTHORIZED);
    }
}