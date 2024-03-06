package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク登録画面とタスク編集画面で使用する情報を取得する
 */
public class SelectBoxDAO {

	/**
	 * カテゴリ情報を取得するメソッド
	 * @return　categoryList 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<UserCategoryStatusTaskBean> selectCategory()throws SQLException, ClassNotFoundException {

		List<UserCategoryStatusTaskBean> categoryList = new ArrayList<UserCategoryStatusTaskBean>();
		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT catgory_id, category_name FROM m_category")) {
			// 結果の操作
			while (res.next()) {
				int categoryID = res.getInt("category_id");
				String categoryName = res.getString("category_name");
				UserCategoryStatusTaskBean category = new UserCategoryStatusTaskBean();
				category.setCategoryId(categoryID);
				category.setCategoryName(categoryName);
				categoryList.add(category);
			}
		}
		return categoryList;
	}
	
	/**
	 * ユーザー情報を取得するメソッド
	 * @return userList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<UserCategoryStatusTaskBean> selectUser()throws SQLException, ClassNotFoundException {

		List<UserCategoryStatusTaskBean> userList = new ArrayList<UserCategoryStatusTaskBean>();
		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT user_id, user_name FROM m_user")) {
			// 結果の操作
			while (res.next()) {
				String userID = res.getString("user_id");
				String userName = res.getString("user_name");
				UserCategoryStatusTaskBean user = new UserCategoryStatusTaskBean();
				user.setUserId(userID);
				user.setUserName(userName);
				userList.add(user);
			}
		}
		return userList;
	}
	
	/**
	 * ステータス情報を取得するメソッド
	 * @return statusList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<UserCategoryStatusTaskBean> selectStatus()throws SQLException, ClassNotFoundException {

		List<UserCategoryStatusTaskBean> statusList = new ArrayList<UserCategoryStatusTaskBean>();
		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT status_code, status_name FROM m_status")) {
			// 結果の操作
			while (res.next()) {
				String statusCode = res.getString("status_code");
				String statusName = res.getString("status_name");
				UserCategoryStatusTaskBean status = new UserCategoryStatusTaskBean();
				status.setStatusCode(statusCode);
				status.setStatusName(statusName);
				statusList.add(status);
			}
		}
		return statusList;
	}
}
