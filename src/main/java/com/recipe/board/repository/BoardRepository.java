package com.recipe.board.repository;

import com.recipe.board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;

    public void save(BoardDTO boardDTO) {
        sql.insert("Board.save",boardDTO); //Board: <mapper namespace="Board">의 Board, save:<insert id="save" parameterType="board">의 save
                                                    //boardDTO로 넘겨줌, 2가지를 넘길 때는 HashMap 사용
    }


    public List<BoardDTO> findAll() {

        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {

        sql.update("Board.updateHits",id);
    }

    public BoardDTO findById(Long id) {

        return sql.selectOne("Board.findById",id);
    }

    public void udpate(BoardDTO boardDTO) {
        sql.update("Board.update",boardDTO);
    }
}
