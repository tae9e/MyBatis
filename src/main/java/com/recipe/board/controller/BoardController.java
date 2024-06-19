package com.recipe.board.controller;

import com.recipe.board.dto.BoardDTO;
import com.recipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor //final이 붙은 field의 생성자를 만들어주는 어노테이션
public class BoardController {
    private final BoardService boardService;

    //게시글 작성
    @GetMapping("/save")
    public String save(){

        return "save";
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDTO){
        System.out.println("boardDTO= " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/list";
    }

    //게시글 목록 조회
   @GetMapping("/list")
   //Model: 데이터를 화면으로 가져갈 수 있도록 전달해주는 객체
   //       RestFul API에선 구현할 필요 없음(HTTP Body에 JSON 타입으로 return을 주기 때문에)
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        return "list";
   }

   //게시글 조회
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        //조회수 처리
        boardService.updateHits(id);
        //상세 내용 가져오기(게시글 하나)
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board",boardDTO);
        System.out.println("boardDTO= " + boardDTO);
        return "detail";
    }

    //게시글 수정
    //수정을 위한 기존 정보 가져오기
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board",boardDTO);
        return "update";
    }

    //수정한 게시글 등록
    @PostMapping("/update/{id}")
    public String update(BoardDTO boardDTO, Model model){
        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId()); //update가 반영된 데이터를 id를 통해서 다시 가져오기
        model.addAttribute("board",dto); //가져온 데이터를 보여주기
        return "detail";
    }

}
