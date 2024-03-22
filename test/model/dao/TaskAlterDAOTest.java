/**
 * 
 */
package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserCategoryStatusTaskBean;

/**
 * TaskAlterDAOをテストするクラス
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
		
		//TaskAlterDAOをインスタンス化
		TaskAlterDAO taskAlterDao = new TaskAlterDAO();
		
		//メソッドの引数となるタスクIDを120に指定
		int taskId = 120;
		
		//メソッドの戻り値となるタスク情報をインスタンス化
		UserCategoryStatusTaskBean taskData = new UserCategoryStatusTaskBean();
		
		//メソッドを実行
		try {
			taskData = taskAlterDao.selectTask(taskId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//戻り値からタスク名を切り出し、実測値とする
		String actual = taskData.getTaskName();
		
		//期待値を「テストタスク2」とする
		String expected = "テストタスク2";
		
		//実測値と期待値を比較
		assertEquals(actual, expected);

	}
	
	@Test
	void selectTask2() {
		
		//TaskAlterDAOをインスタンス化
		TaskAlterDAO taskAlterDao = new TaskAlterDAO();
		
		//メソッドの引数となるタスクIDを119に指定
		int taskId = 119;
		
		//メソッドの戻り値となるタスク情報をインスタンス化
		UserCategoryStatusTaskBean taskData = new UserCategoryStatusTaskBean();
		
		//メソッドを実行
		try {
			taskData = taskAlterDao.selectTask(taskId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//戻り値からタスク名を切り出し、実測値とする
		String actual = taskData.getTaskName();
		
		//期待値をnullとする
		String expected = null;
		
		//実測値と期待値を比較
		assertEquals(actual, expected);

	}
}
