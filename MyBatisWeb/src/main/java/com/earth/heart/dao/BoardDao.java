package com.earth.heart.dao;

import java.util.List;
import com.earth.heart.domain.BoardDTO;

public interface BoardDao {
	
	BoardDTO 		select(Integer bno)         		throws Exception;
	List<BoardDTO>  selectAll()							throws Exception;
	int 			insert(BoardDTO boardDTO)   		throws Exception;
	int 			update(BoardDTO boardDTO)   		throws Exception;
	int				delete(Integer bno, String writer) 	throws Exception;
	int      		deleteAll()                 		throws Exception;
	int			    count() 							throws Exception;
}
