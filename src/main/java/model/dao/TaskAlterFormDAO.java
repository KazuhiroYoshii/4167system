package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserCategoryStatusTaskBean;

/**
 * ユーザマスタ、カテゴリマスタ、ステータスマスタ、
 * タスクテーブルにアクセスするためのDAO
 * @author 櫻井
 */
public class TaskAlterFormDAO {
	
	/**
	 * 指定されたタスクIDのタスク情報を返します。
	 * @throws SQLException, ClassNotFoundException 
	 */
	public UserCategoryStatusTaskBean selectTask(int taskId) 
			throws SQLException, ClassNotFoundException {

		UserCategoryStatusTaskBean task = new UserCategoryStatusTaskBean();

		String sql = "SELECT task_id,task_name,t1.category_id,"
				+ "limit_date,t1.user_id,t1.status_code,memo "
				+ "FROM t_task t1 "
				+ "INNER JOIN m_user t2 ON t1.user_id = t2.user_id "
				+ "INNER JOIN m_category t3 ON t1.category_id = t3.category_id "
				+ "INNER JOIN m_status t4 ON t1.status_code = t4.status_code "
				+ "WHERE task_id = ?";

		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダへの値の設定
			pstmt.setInt(1, taskId);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			while (res.next()) {
				task.setTaskId(res.getInt("task_id"));
				task.setTaskName(res.getString("task_name"));
				task.setCategoryId(res.getInt("category_id"));
				task.setLimitDate(res.getString("limit_date"));
				task.setUserId(res.getString("user_id"));
				task.setStatusCode(res.getString("status_code"));
				task.setMemo(res.getString("memo"));
			}
		}

		return task;
	}
}
