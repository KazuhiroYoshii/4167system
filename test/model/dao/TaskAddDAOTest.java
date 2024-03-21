/**
 * 
 */
package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserCategoryStatusTaskBean;

/**
 * TaskAddDAOのメソッドをテストする
 * @author 吉井
 */
class TaskAddDAOTest {

	@Test
	void test1InsertTask() {
		
		//TaskAddDAOをインスタンス化
		TaskAddDAO taskAddDao = new TaskAddDAO();
		
		//テストに用いるタスク情報をインスタンス化、情報を格納
		UserCategoryStatusTaskBean taskData = new UserCategoryStatusTaskBean();
		taskData.setTaskName("テストタスク1");
		taskData.setCategoryId(1);
		taskData.setLimitDate("");
		taskData.setUserId("test1");
		taskData.setStatusCode("00");
		taskData.setMemo("");
		
		//insertTaskメソッドの戻り値に使う変数を0で初期化
		int actual = 0;
		
		//taskDataを用いてinsertTaskメソッドを実行
		try {
			actual = taskAddDao.insertTask(taskData);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//期待値を1とする
		int expected = 1;
		
		//実測値と期待値を比較
		assertEquals(actual, expected);
		
	}
	
	@Test
	void test2InsertTask() {
		
		//TaskAddDAOをインスタンス化
		TaskAddDAO taskAddDao = new TaskAddDAO();
		
		//テストに用いるタスク情報をインスタンス化、情報を格納（ユーザIDのみnullとする）
		UserCategoryStatusTaskBean taskData = new UserCategoryStatusTaskBean();
		taskData.setTaskName("テストタスク2");
		taskData.setCategoryId(1);
		taskData.setLimitDate("");
		taskData.setUserId(null);
		taskData.setStatusCode("00");
		taskData.setMemo("");
		
		//insertTaskメソッドの戻り値に使う変数を0で初期化
		int actual = 0;
		
		//taskDataを用いてinsertTaskメソッドを実行
		try {
			actual = taskAddDao.insertTask(taskData);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//期待値を0とする
		int expected = 0;
		
		//実測値と期待値を比較
		assertEquals(actual, expected);
		
	}

}
