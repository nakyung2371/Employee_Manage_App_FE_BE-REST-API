package com.example.ajaxex.controller;

import com.example.ajaxex.dto.AjaxDto;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxController {

    //http://localhost:9696/ex01
    @GetMapping("/ex01")
    public String ex01() {
        System.out.println("ex01 요청 성공");

        //res: index.html 파일의 소스코드가 그대로 전송
        return "index";
    }

    //http://localhost:9696/ex01
    //@ResponseBody: JSON 포맷으로 바꾸어서 전송
    @PostMapping("/ex02")
    public @ResponseBody String ex02() {
        System.out.println("ex02 요청 성공");
        return "안녕하세요";
    }

    //get 요청에서 param으로 넘기는 값을 받기
    //  client에서 넘기는 date 받기: param1, param2
    @GetMapping("/ex03")
    public @ResponseBody String ex03(
            @RequestParam("param1") String param1,
            @RequestParam("param2") String param2
    ) {
        System.out.println("ex03 요청 성공-get");
        System.out.println("param1의 값: " + param1);
        System.out.println("param2의 값: " + param2);
        return "03 요청 성공";
    }

    //post 요청에서 param으로 넘기는 값을 받기: /ex04?param1=값&param2=값
    @PostMapping("/ex04")
    public @ResponseBody String ex04(
            @RequestParam("param1") int param1,
            @RequestParam("param2") String param2
    ) {
        System.out.println("ex04 요청 성공-post");
        System.out.println("param1의 값: " + (param1 + 10));
        System.out.println("param2의 값: " + param2);
        return "04 요청 성공";
    }

    //get 요청에서 param으로 보내는 변수의 값을 DTO의 필드에 주입
    //      - 주의: param의 변수명과 DTO 필드 명이 같아야 자동 주입
    //      @ModelAttribute <- param으로 넘어오는 값을 DTO에 주입
    @GetMapping("/ex05")
    public @ResponseBody AjaxDto ex05(
            //param
            @ModelAttribute AjaxDto ajaxDto
            ) {
        System.out.println("ex05 요청 성공-get");
        System.out.println("Dto param1의 값: " + ajaxDto.getParam1());
        System.out.println("Dto param2의 값: " + ajaxDto.getParam2());
        return ajaxDto;
    }

    @PostMapping("/ex06")
    public @ResponseBody AjaxDto ex06(
            @ModelAttribute AjaxDto ajaxDto
    ) {
        System.out.println("ex06 요청 성공-post");
        System.out.println("Dto param1의 값: " + ajaxDto.getParam1());
        System.out.println("Dto param2의 값: " + ajaxDto.getParam2());
        return null;
    }

    //client에서 JSON으로 서버로 전송
    //       @RequestBody(역직렬화): JSON -> DTO 주입(객체, RAM)
    //       @ResponseBody(직렬화): DTO -> JSON
    //  주의: JSON Data는 get으로 서버로 전송하면 오류 발생
    @PostMapping("/ex07")
    public @ResponseBody AjaxDto ex07(
            @RequestBody AjaxDto ajaxDto
    ) {
        System.out.println("ex07 요청 성공-get");
        System.out.println("Dto param1의 값: " + ajaxDto.getParam1());
        System.out.println("Dto param2의 값: " + ajaxDto.getParam2());
        return ajaxDto;
    }

    //List<AjaxDto>: [{객체}, {객체}, {객체}] <- DB 테이블의 각각의 레코드, select ALL
    @PostMapping("/ex08")
    public @ResponseBody List<AjaxDto> ex08(
        @RequestBody AjaxDto ajaxDto
    ) {
        System.out.println("ex08 요청 성공-get");
        System.out.println("Dto param1의 값: " + ajaxDto.getParam1());
        System.out.println("Dto param2의 값: " + ajaxDto.getParam2());

        List<AjaxDto> dtoList = new ArrayList<>();

        dtoList.add(ajaxDto);
        dtoList.add(new AjaxDto("고양이", "햄스터"));
        dtoList.add(new AjaxDto("토끼", "강아지"));

        return dtoList;
    }

    //Rest API 통신할 때
    //ResponseEntity: JSON 형식으로 변환해서 내보냄 + HTTP 상태 코드를 내보낼 수 있다
    //  data + http 상태 코드를 리턴, 클라이언트에서 좀 더 세밀한 컨트롤을 할 수 있음
    @PostMapping("/ex09")
    public ResponseEntity ex09(
            @RequestBody AjaxDto ajaxDto
            ) {
        System.out.println("ex09 요청 성공-get");
        System.out.println("Dto param1의 값: " + ajaxDto.getParam1());
        System.out.println("Dto param2의 값: " + ajaxDto.getParam2());

        return new ResponseEntity<>(ajaxDto, HttpStatus.OK);
//        return new ResponseEntity<>(ajaxDto, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/ex10")
    public  ResponseEntity ex10(
            @RequestBody AjaxDto ajaxDto
    ){

        List<AjaxDto> dtoList = new ArrayList<>();

        dtoList.add(ajaxDto);
        dtoList.add(new AjaxDto("사과", "오렌지"));
        dtoList.add(new AjaxDto("바나나", "레몬"));

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
//        return new ResponseEntity<>(dtoList, HttpStatus.UNAUTHORIZED);
    }






}
