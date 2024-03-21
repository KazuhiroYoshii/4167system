/**
 * 
 */
package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserCategoryStatusTaskBean;

/**
 * TaskAlterDAOをテストする
 * @author 吉井
 */
class TaskAlterDAOTest {

	@Test
	void testUpdate1() {
		
		//TaskAlterDAOをインスタンス化
		TaskAlterDAO taskAlterDao = new TaskAlterDAO();
		
		//テストに用いるタスク情報をインスタンス化、情報を格納
		UserCategoryStatusTaskBean taskData = new UserCategoryStatusTaskBean();
		taskData.setTaskName("テストタスク2");
		taskData.setCategoryId(1);
		taskData.setLimitDate("");
		taskData.setUserId("test1");
		taskData.setStatusCode("99");
		taskData.setMemo("");
		taskData.setTaskId(120);
		
		//Updateメソッドの戻り値に用いる変数を0で初期化
		int actual = 0;
		
		//taskDataを用いてUpdateメソッドを実行
		try {
			actual = taskAlterDao.update(taskData);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//期待値を1とする
		int expected = 1;
		
		//実測値と期待値を比較
		assertEquals(actual, expected);
		
	}

	@Test
	void testUpdate2() {
		
		//TaskAlterDAOをインスタンス化
		TaskAlterDAO taskAlterDao = new TaskAlterDAO();
		
		//テストに用いるタスク情報をインスタンス化、情報を格納
		UserCategoryStatusTaskBean taskData = new UserCategoryStatusTaskBean();
		taskData.setTaskName("テストタスク2");
		taskData.setCategoryId(1);
		taskData.setLimitDate("");
		taskData.setUserId("test1");
		taskData.setStatusCode("99");
		taskData.setMemo("");
		taskData.setTaskId(119);
		
		//Updateメソッドの戻り値に用いる変数を0で初期化
		int actual = 0;
		
		//taskDataを用いてUpdateメソッドを実行
		try {
			actual = taskAlterDao.update(taskData);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//期待値を0とする
		int expected = 0;
		
		//実測値と期待値を比較
		assertEquals(actual, expected);
	}

	@Test
	void selectTask1() {
		
	}
}
