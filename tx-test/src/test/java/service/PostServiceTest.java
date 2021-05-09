package service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.kks.txtest.context.AppContextHolder;
import com.kks.txtest.dto.Post;
import com.kks.txtest.service.PostService;

public class PostServiceTest {
	private PostService service = (PostService) AppContextHolder.getApplicationContext().getBean("postService");
	
	public void testPostList() {
		System.out.println();
		System.out.println("*** test get post list start ***");

		List<Post> list = this.service.getPostList();
		this.printPost(list);
		System.out.println("*** test get post list end ***");
	}
	
	public void insertTest() {
		System.out.println();
		System.out.println("*** insert test start ***");
		List<Post> list = this.service.getPostList();
		long seq = 1;
		try {
			seq = list.get(list.size()-1).getNo();	
		} catch (Exception e) {
		}
		
		for(long i=seq; i<seq+10; i++) {
			try {
				long no = this.service.post("title" + i, "content" + i, "system");
				System.out.println("post regist success. no=" + no);	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("*** insert test end ***");
	}
	
	public void insertTxTest() {
		List<Post> list = new ArrayList<Post>();
		for(int i=100; i<200; i++) {
			Post post = new Post();
			post.setNo(i);
			post.setTitle("title" + i);
			post.setContent("content" + i);
			post.setCreateBy("system");
			list.add(post);
		}
		
		//service.postThread(list);
		
		service.postThread2(list);
	}
	
	public void insertTxBatchTest() {
		List<Post> list = new ArrayList<Post>();
		for(int i=100; i<200; i++) {
			Post post = new Post();
			post.setNo(i);
			post.setTitle("title" + i);
			post.setContent("content" + i);
			post.setCreateBy("system");
			list.add(post);
		}
		
		//service.postThread(list);
		
		try {
			service.postHybrid(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertBatchPerfomTest() {
		//100000건
		int testSize = 500000;
		List<Post> list = new ArrayList<Post>();
		for(int i=0; i<testSize; i++) {
			Post post = new Post();
			post.setNo(i);
			post.setTitle("title" + i);
			post.setContent("content" + i);
			post.setCreateBy("system");
			list.add(post);
		}
		
		long start = System.currentTimeMillis();
		service.post(list);
		long end = System.currentTimeMillis();
		System.out.println("none batch perfome : " + TimeUnit.MILLISECONDS.toSeconds(end-start));
		
		try {
			start = System.currentTimeMillis();
			service.postBatch(list, 100000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			end = System.currentTimeMillis();
		}
		System.out.println("batch perfome : " + TimeUnit.MILLISECONDS.toSeconds(end-start));
		
		
//		none batch perfome : 46
//		batch perfome : 27 //batchsize 5000
//		batch perfome : 28 //batchsize 10000
//		batch perfome : 20 //batchsize 50000
//      batch perfome : 22 //batchsize 1000000
		
	}
	
	public void printPost(List<Post> list) {
		if(list == null) {
			System.out.println("postList=null");
			return;
		}
		
		if(list.isEmpty()) {
			System.out.println("postList=[]");
			return;
		}
		
		System.out.println("postList=[");
		for(Post post : list) {
			System.out.println(post);
		}
		System.out.println("]");
	}
	
	public static void main(String[] args) {
		PostServiceTest test = new PostServiceTest();
		//test.testPostList();
		//test.insertTest();
		//test.testPostList();
		
		//test.insertTxTest();
		
		//test.insertTxBatchTest();
		
		test.insertBatchPerfomTest();
	}
}
