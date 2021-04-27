package service;

import java.util.List;

import com.kks.txtest.dto.Post;
import com.kks.txtest.service.BoardService;

public class BoardServiceTest {
	public static void main(String[] args) {
		try {
			List<Post> result = BoardService.getInstance().getPostList();
			for(Post p : result) {
				System.out.println(p);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
