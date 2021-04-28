package service;

import org.springframework.beans.factory.annotation.Autowired;

import com.kks.txtest.context.AppContextHolder;
import com.kks.txtest.controller.BoardController;
import com.kks.txtest.service.BoardService;

public class BoardServiceTest {
	
	public static void main(String[] args) {
//		try {
//			List<Post> result = BoardService.getInstance().getPostList();
//			for(Post p : result) {
//				System.out.println(p);	
//			}	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		BoardController c = (BoardController) AppContextHolder.getApplicationContext().getBean("boardController");
		c.getBoardService().removePost();
	}
}
