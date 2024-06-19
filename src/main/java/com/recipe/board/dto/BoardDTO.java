package com.recipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BoardDTO {
    //필드 이름과 html의 name 이름이 일치할 것(값을 담아주기 위해서)
    private long id;
    private  String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;
}
