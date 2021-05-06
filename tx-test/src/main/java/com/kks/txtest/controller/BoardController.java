package com.kks.txtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kks.txtest.service.BoardService;

@Component
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	public BoardService getBoardService() {
		return boardService;
	}
}
