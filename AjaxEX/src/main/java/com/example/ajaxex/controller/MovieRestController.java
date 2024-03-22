package com.example.ajaxex.controller;

import com.example.ajaxex.dto.MovieDto;
import com.example.ajaxex.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
//@RestController       @RestController + @Controller
//메소드에서 리턴되는 모든 타입을 JSON으로 변환
//swagger: API 문서를 자동으로 생성
public class MovieRestController {
    /*
    REST API: URL에 동사를 사용하지 않는다
        swagger 라이브러리: @RestController 하위의 메소드의 요청 주소를 게더링해서 API 문서를 자동으로 생성

    GET:    /movie  : selectAll,   리턴: [{}, {}, {}],  List<MovieDto>, Page<MovieDto>
    GET:    /movie/1: select,      리턴: {}, MovieDto
    POST:   /movie  : insert, data(서버로 insert 값 전송할, JSON) 리턴: (x)
    PUT:    /movie/1: update(모든 컬럼), data(서버로 update 값 전송할, JSON)
    DELETE: /movie/1: delete,

     */



    //DI 객체 주입: @RequiredArgsConstructor
    private final MovieService movieService;

    //Movie의 POST 요청을 처리하는 메소드: insert
    @PostMapping("/movie")
    public ResponseEntity<String> movieInsert(
            //client에서 던지는 객체를 자바에서 input
            @RequestBody MovieDto movieDto
    ) {
        System.out.println("=========================");
        System.out.println(movieDto.getBackdrop_path());
        System.out.println(movieDto.getOriginal_language());
        System.out.println(movieDto.getTitle());
        System.out.println(movieDto.getPoster_path());
        System.out.println("=========================");


        String complate = movieService.movieInsert(movieDto);

        return new ResponseEntity<String>(complate, HttpStatus.OK);
    }

    //get, movie 테이블의 전체 내용을 출력
    @GetMapping("/movie")
    public ResponseEntity<List<MovieDto>> getMovieAll( ) {
        System.out.println("/movie 요청 잘 받음");

        List<MovieDto> movieList = movieService.seletAll();

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    //수정 로직: put, /movie/{id}
    @PutMapping("/movie/{id}")
    public ResponseEntity updateMovie(
            @PathVariable("id") long id,
            @RequestBody MovieDto movieDto
    ) {
//        System.out.println("put 요청 처리됨");
//        System.out.println(id);
//        System.out.println(movieDto.getTitle());

        movieService.updateMovie(id, movieDto);

        return ResponseEntity.ok(movieDto);
    }

    //삭제 로직: delete, /movie/{id}
    @DeleteMapping("/movie/{id}")
    public ResponseEntity <String> deleteMovie(
            @PathVariable("id") long id
    ) {
        System.out.println("delete 요청 성공: " + id);

        movieService.deleteMovie(id);

//        return ResponseEntity.ok("서버에서 삭제 성공");
        return new ResponseEntity<>("서버에서 삭제 성공", HttpStatus.OK);
    }

    //글 상세 내용 보기, get, /move/{id}
    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDto> getDetail(
            @PathVariable("id") long id
    ) {
//        System.out.println("상세보기 요청 성공: " + id);

        MovieDto movieDto = movieService.getMovieDetail(id);

        return ResponseEntity.ok(movieDto);
    }

}
