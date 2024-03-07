package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク登録画面で使用するDAO
 * @author 森田
 */
public class TaskAddDAO {

	/**
	 * 入力されたタスク情報を登録します
	 * @param taskInfo
	 * @return processionNumber 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insertTask(UserCategoryStatusTaskBean taskInfo) throws SQLException, ClassNotFoundException {

		int processingNumber = 0; //処理件数

		String sql = "INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo)VALUES(?, ?, ?, ?, ?, ?)";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setString(1, taskInfo.getTaskName());
			pstmt.setInt(2, taskInfo.getCategoryId());
			if (taskInfo.getLimitDate().equals("")) {
				pstmt.setDate(3, null);
			} else {
				Date date = Date.valueOf(taskInfo.getLimitDate());
				pstmt.setDate(3, date);
			}
			pstmt.setString(4, taskInfo.getUserId());
			pstmt.setString(5, taskInfo.getStatusCode());
			pstmt.setString(6, taskInfo.getMemo());

			// 処理結果
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;
	}
	
	/**
	 * カテゴリIDからカテゴリ名を取得する
	 * @param categoryId
	 * @return categoryName
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String categoryChange(int categoryId) throws SQLException, ClassNotFoundException {

		//
		String categoryName = null;
		String sql = "SELECT category_name FROM m_category WHERE category_id = ?";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setInt(1, categoryId);
			ResultSet res = pstmt.executeQuery();
			
			// 結果の操作
			while (res.next()) {
				categoryName = res.getString("category_name");
			}
			
		}
		return categoryName;
	}

}
