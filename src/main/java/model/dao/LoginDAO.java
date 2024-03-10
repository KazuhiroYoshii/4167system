package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

/**
 * ログイン機能に使用するDAO
 * @author 古野
 * 
 */
public class LoginDAO {
	
	/**
	 * ログイン時入力されたuserId、passwordに該当するユーザーが存在するか確認をする
	 * @param userId,password ログインに必要なパラメーター
	 * @return ユーザIDとパスワードに該当するユーザ情報 存在しない場合はnullを返す
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserBean selectName(String userId, String password) throws SQLException, ClassNotFoundException {

		//氏名
		String userName = null; 
		
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
			while (res.next()) {
				userName = res.getString("user_name");
				userBean.setUserName(userName);
			}
		}

		return userBean;
	}
}
