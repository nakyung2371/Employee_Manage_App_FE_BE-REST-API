package com.example.ajaxex.service;

import com.example.ajaxex.dto.MovieDto;
import com.example.ajaxex.entity.Movie;
import com.example.ajaxex.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    //MovieService: 보안, 중복 코드 방지, 모듈화(메소드)
    //  Controller -> Service -> Repository
    //호출: Controller
    //제어: Repository

    //DI(의존성 주입: 스프링 프레임워크에 객체를 주입) <- @RequiredArgsConstructor
    private final MovieRepository movieRepository;

    //DB에 값을 insert 메소드
    public String movieInsert(MovieDto movieDto) {

        //MovieDto: Client <-> MovieDto <-> Movie(Entity) == MovieRepository -> DB

        //MovieDto의 모든 필드의 값을 Movie(Entity)로 주입
        Movie movie = movieDto.createMovie();

        System.out.println("==== Movie(Entity의 값을 출력) ====");
        System.out.println(movie.getTitle());
        System.out.println(movie.getPoster_path());
        System.out.println(movie.getRelease_date());
        System.out.println(movie.getVote_count());
        System.out.println("=================================");

        movieRepository.save(movie);

        return "insert 성공됨";
    }

    //DB의 movie 테이블의 모든 레코드를 가지고 와서 List<MovieDto>
    public List<MovieDto> seletAll() {
        System.out.println("movieService 호출됨");

        List<MovieDto> movieList = new ArrayList<>();

        //DB에서 모든 레코드를 가지고 옴
        List<Movie> movieListDB = movieRepository.findAll();
//        //출력
//        for (int i=0; i<movieListDB.size(); i++) {
//            Movie movie = movieListDB.get(i);
//            System.out.println("=================" + i + "=================");
//            System.out.println(movie.getTitle());
//            System.out.println(movie.getPoster_path());
//            System.out.println(movie.getVote_count());
//            System.out.println(movie.getOriginal_language());
//            System.out.println("=====================================");
//        }

        //List<Movie> movieListDB -> List<MovieDto> movieList
        //주의: List는 for문 밖에서 선언
        //      List에 넣을 객체는 for문 안에서 선언되어야 객체 주소가 각각 다르게 만들어진다
        for (int i=0; i<movieListDB.size(); i++) {
            Movie movie = movieListDB.get(i);
            MovieDto movieDto = new MovieDto();
            //movie(Entity)의 값을 movieDTO에 주입
            movieDto = movieDto.of(movie);
            movieList.add(movieDto);

        }

        return movieList;
    }

    //수정: put,  매개변수: id(long), MovieDto
    public void updateMovie(long id, MovieDto movieDto) {
        //id에 대한 Movie 객체를 가지고 옴
        Optional<Movie> op = movieRepository.findById(id);
        Movie movie = op.get();

//        System.out.println(movie.getTitle());
//        System.out.println(movie.getVote_count());

        //Movie 객체에 movieDto
//        movie = movieDto.createMovie();
        movie.setTitle(movieDto.getTitle());
        movie.setVote_count((long) movieDto.getVote_count());
        movie.setOriginal_language(movieDto.getOriginal_language());

        //save(Movie)   //수정 완료
        movieRepository.save(movie);
    }

    //삭제 메소드: 리턴: void, 매개변수: id
    public void deleteMovie(long id) {

        //id를 인풋 받아서 findById(id)
        Optional<Movie> op =
            movieRepository.findById(id);
        Movie movie = null;
        try {
            ///op가 Null일 경우 예외 처리가 필요하다
            movie = op.get();
        } catch (Exception e) {

        }

//        System.out.println(movie.getTitle());

        movieRepository.delete(movie);

        System.out.println("DB에서 삭제 성공");

    }

    //상세보기: 리턴: MovieDto, 매개변수: id
    public MovieDto getMovieDetail(long id) {
        //
        //id를 인풋 받아서 findById(id)
        Optional<Movie> op =
                movieRepository.findById(id);
        Movie movie = null;
        try {
            ///op가 Null일 경우 예외 처리가 필요하다
            movie = op.get();
        } catch (Exception e) {

        }

        System.out.println("movie 객체 출력");
        System.out.println(movie.getTitle());

        //movie (Entity) -> MovieDto 매핑
        MovieDto movieDto = new MovieDto();     //movieDto - 비어있는 상태를 만든 것

        movieDto = movieDto.of(movie);

        System.out.println("movieDto의 값 출력");
        System.out.println(movieDto.getTitle());

        return movieDto;
    }

}
