package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserCategoryStatusTaskBean;

/**
 * TaskAlterFormDAOをテストするクラス
 * @author 吉井
 */
class TaskAlterFormDAOTest {

	@Test
	void testSelectTask1() {
		
		//TaskAlterFormDAOをインスタンス化
		TaskAlterFormDAO taskAlterFormDao = new TaskAlterFormDAO();
		
		//メソッドの引数となるタスクIDを121に指定
		int taskId = 121;
		
		//メソッドの戻り値となるタスク情報をインスタンス化
		UserCategoryStatusTaskBean taskData = new UserCategoryStatusTaskBean();
		
		//メソッドを実行
		try {
			taskData = taskAlterFormDao.selectTask(taskId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//戻り値からタスク名を切り出し、実測値とする
		String actual = taskData.getTaskName();
		
		//期待値を「テストタスク1」とする
		String expected = "テストタスク1";
		
		//実測値と期待値を比較
		assertEquals(actual, expected);
	}
	
	@Test
	void testSelectTask2() {
		
		//TaskAlterFormDAOをインスタンス化
		TaskAlterFormDAO taskAlterFormDao = new TaskAlterFormDAO();
		
		//メソッドの引数となるタスクIDを119に指定
		int taskId = 119;
		
		//メソッドの戻り値となるタスク情報をインスタンス化
		UserCategoryStatusTaskBean taskData = new UserCategoryStatusTaskBean();
		
		//メソッドを実行
		try {
			taskData = taskAlterFormDao.selectTask(taskId);
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
