package com.earth.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.earth.domain.CommentDTO;
import com.earth.domain.ComuDTO;
import com.earth.domain.SearchItem;

public interface ComuService {

	int post(ComuDTO comuDTO) throws Exception;
	int deletePost(Integer post_id, String user_email) throws Exception;
	int updatePost(ComuDTO comuDTO) throws Exception;
	ComuDTO readPost(Integer post_id) throws Exception;
	int getSearchResultCnt(SearchItem sc)  throws Exception;
	int getCategoryResultCnt(Integer post_ctgr_id, SearchItem sc) throws Exception;
	List<ComuDTO> getSearchSelectPage(SearchItem sc) throws Exception;
	List<ComuDTO> getSearchCategoryPage(Integer post_ctgr_id, SearchItem sc)  throws Exception;
	int comment(CommentDTO commentDTO) throws Exception;
	int deleteComment(Integer cmt_id, String user_email) throws Exception;
	List<CommentDTO> getCommentsByPostId(Integer post_id) throws Exception;
	
}