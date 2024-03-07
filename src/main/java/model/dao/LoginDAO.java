package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

public class LoginDAO {
	public UserBean selectName(String userId, String password) throws SQLException, ClassNotFoundException {

		String userName = null; //氏名
		
		UserBean userBean = new UserBean();

		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt
				= con.prepareStatement("SELECT user_name FROM m_user WHERE user_id = ? AND password = ?")) {

			// プレースホルダへの値の設定
			pstmt.setString(1, userId);
			pstmt.setString(2, password);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			if (res.next()) {
				userName = res.getString("user_name");
				userBean.setUserName(userName);
			}
		}

		return userBean;
	}
}
