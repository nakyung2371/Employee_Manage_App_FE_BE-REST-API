package com.example.ajaxex.dto;

import com.example.ajaxex.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
@NoArgsConstructor      //기본 생성자
public class MovieDto {

    //FE(JSON) -> Controller -> MovieDTO -> MovieService -> MovieRepository -> DB

    private String backdrop_path;
    private Long id_num;
    private String original_language;
    private String original_title;
    private String overview;
    private String poster_path;
    private String release_date;
    private String title;
    private int vote_count;

    //API에서 가져오면 필드가 상당히 많을 수 있음
    //Dto에 있는 값을 Entity 필드에 넣어줘야 함
    //필드 값을 Getter로 꺼내서 Entity에 Setter로 넣을 수 있음
    //ModelMapper: Dto에 있는 필드를 Entity 필드에 있는 값을 자동으로 주입하는 것
    //      -> 필드 이름과 형식이 동일해야 함
    //사용하려면 build.gradle에 라이브러리가 등록되어 있어야 함
    private static ModelMapper modelMapper = new ModelMapper();

    //Client form 에서 넘어오는 값을 DTO에 담아서 Movie Entity 클래스에 적용후 DB에 저장
    public Movie createMovie() {
        return modelMapper.map(this, Movie.class);
    }

    //DB에서 가져온 Movie Entity 클래스를 DTO 에 주입해서 client 로 전송 하기 위한 매핑
    public static MovieDto of(Movie movie) {
        return modelMapper.map(movie,MovieDto.class);
    }



}
