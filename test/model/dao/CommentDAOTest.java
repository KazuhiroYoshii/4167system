package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import model.entity.UserCategoryStatusTaskBean;
import model.entity.UserCommentBean;

class CommentDAOTest {

	@Disabled
	@Test
	void testSelectTask() {
		CommentDAO CommentDAO = new CommentDAO();
		UserCategoryStatusTaskBean bean = new UserCategoryStatusTaskBean();
		
		try {
			bean = CommentDAO.selectTask(1);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//(期待値、実際の値)
		assertEquals("未着手サンプルタスク", bean.getTaskName());
		assertEquals("新商品A:開発プロジェクト", bean.getCategoryName());
		assertEquals("2024-04-02", bean.getLimitDate());
		assertEquals("山田", bean.getUserName());
		assertEquals("未着手", bean.getStatusName());
		assertEquals("サンプルメモ", bean.getMemo());
	}

	@Test
	void testSelectComment() {
		List<UserCommentBean> commentList = new ArrayList<>();
		CommentDAO commentDAO = new CommentDAO();
		//ファイル読み込みで使用する３つのクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;
		
		try {
			commentList = commentDAO.selectComment(1);
			//読み込みファイルのインスタンス生成
			//ファイル名を指定する
			fi = new FileInputStream("commentdao_selectcomment.csv");
			is = new InputStreamReader(fi);
			br = new BufferedReader(is);

			//読み込み行
			String line;

			//読み込み行数の管理
			int i = 0;

			//列名を管理する為の配列
			String[] arr = null;

			//1行ずつ読み込みを行う
			while ((line = br.readLine()) != null) {

				//先頭行は列名
				if (i == 0) {

					//カンマで分割した内容を配列に格納する
					// arr = { "comment_id","task_id","user_id","user_name","comment","update_datetime" };
					arr = line.split(",");

				} else {

					//カンマで分割した内容を配列に格納する
					String[] data = line.split(",");

					//(期待値、実際の値)
					assertEquals(Integer.parseInt(data[0]), commentList.get(i - 1).getCommentId());
					assertEquals(Integer.parseInt(data[1]), commentList.get(i - 1).getTaskId());
					assertEquals(data[2], commentList.get(i - 1).getUserId());
					assertEquals(data[3], commentList.get(i - 1).getUserName());
					assertEquals(data[4], commentList.get(i - 1).getComment());
					assertEquals(data[5], commentList.get(i - 1).getUpdateDatetime());

				}

				//行数のインクリメント
				i++;

			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Disabled
	@Test
	void testinsertComment() {
		CommentDAO CommentDAO = new CommentDAO();
		UserCommentBean commentInfo = new UserCommentBean();
		commentInfo.setTaskId(1);
		commentInfo.setUserId("admin");
		commentInfo.setComment("テストコメント");
		
		int count = 0;
		try {
			count = CommentDAO.insertComment(commentInfo);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//(期待値、実際の値)
		assertEquals(1, count);
	}
	
	@Disabled
	@Test
	void testDeleteComment() {
		CommentDAO CommentDAO = new CommentDAO();
		int count = 0;
		try {
			count = CommentDAO.deleteComment(23);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//(期待値、実際の値)
		assertEquals(1, count);
	}
}
