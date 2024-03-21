package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserBean;

class LoginDAOTest {

	@Test
	void test1SelectUserName() {
		LoginDAO dao = new LoginDAO();
		UserBean user = null;
		String userName = null;
			try {
				user = dao.selectName("admin", "admin");
				// userBean型で帰ってきているのでuserNameを取り出す
				userName = user.getUserName();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			// 期待値と実際値が同じかどうか判定する
			assertEquals("山田",userName);
	}

	@Test
	void test2SelectUserName() {
		LoginDAO dao = new LoginDAO();
		UserBean user = null;
		String userName = null;
			try {
				user = dao.selectName("", "");
				userName = user.getUserName();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			// 
			assertNull(userName);
	}
}
