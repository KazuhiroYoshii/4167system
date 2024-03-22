package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserBean;

class LoginDAOTest {

	@Test
	void test1SelectUserName() {
		//DAOをインスタンス化
		LoginDAO loginDao = new LoginDAO();
		//メソッドの戻り値を格納する変数の初期化
		UserBean user = null;
	
		try {
				// ユーザIDが「admin」、パスワードが「admin」のユーザ情報を呼び出す
				user = loginDao.selectName("admin", "admin");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		// 期待値(山田)と実際値が同じかどうか判定する
		assertEquals("山田",user.getUserName());
	}

	@Test
	void test2SelectUserName() {
		//DAOをインスタンス化
		LoginDAO loginDao = new LoginDAO();
		
		//メソッドの戻り値を格納する変数の初期化
		UserBean user = null;
		
		try {
				// ユーザIDが「」、パスワードが「」のユーザ情報を呼び出す
				user = loginDao.selectName("", "");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		// 実際値がnullかどうか判定する
		assertNull(user.getUserName());
	}
}
