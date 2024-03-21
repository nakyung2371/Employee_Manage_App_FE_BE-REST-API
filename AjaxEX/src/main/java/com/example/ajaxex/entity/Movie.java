package com.example.ajaxex.entity;

//javax(spring boot 2.x) -> jakarta(spring boot 3.x)
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor //필요 없음
@NoArgsConstructor  //기본 생성자 필요
public class Movie {

    @Id
    @Column(name="movie_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(nullable = false, length = 500)
    private String backdrop_path;

    @Column(nullable = false)
    private Long id_num ;

    @Column(nullable = false, length = 500)
    private String original_language;

    @Column(nullable = false, length = 500)
    private String original_title;

    @Column(nullable = false, length = 500)
    private String overview ;

    @Column(nullable = false, length = 500)
    private String poster_path;

    @Column(nullable = false, length = 500)
    private String release_date;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false)
    private Long vote_count;



}