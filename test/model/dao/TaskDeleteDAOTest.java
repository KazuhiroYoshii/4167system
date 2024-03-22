package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TaskDeleteDAOTest {

	@Test
	void delete() {
		
		//DAOをインスタンス化
		TaskDeleteDAO taskDeleteDao = new TaskDeleteDAO();
		
		//メソッドの戻り値を格納する変数の初期化
		int result = 0;
		
		//タスクIDが「60」の時、削除したタスク数を取得する
		result = taskDeleteDao.delete(60);
		
		//期待値(1)と実際値が同じかどうか判定する
		assertEquals(1, result);
	}

	@Test
	void countCommentsTest() {

		//DAOをインスタンス化
		TaskDeleteDAO taskDeleteDao = new TaskDeleteDAO();

		//メソッドの戻り値を格納する変数の初期化
		int result = 0;

		try {
			//タスクIDが「1」の時のコメントの数を取得する
			result = taskDeleteDao.countComments(1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		//期待値と実際値が同じかどうか判定する
		assertEquals(9, result);
	}

	@Test
	void deleteAllComments() {
		
		//DAOをインスタンス化
		TaskDeleteDAO taskDeleteDao = new TaskDeleteDAO();
		
		//メソッドの戻り値を格納する変数の初期化
		int result = 0;
		
		try {
			//タスクIDが「70」の時の削除したコメントの数を取得する
			result = taskDeleteDao.deleteAllComments(70);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		//期待値と実際値が同じかどうか判定する
		assertEquals(3, result);
	}

}
