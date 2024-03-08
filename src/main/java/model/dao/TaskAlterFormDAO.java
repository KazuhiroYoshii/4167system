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
	 * 指定されたタスクIDのタスク情報を返します
	 * @param taskId タスクID
	 * @return task タスク情報をセットしたBean型task
	 * @throws SQLException, ClassNotFoundException 
	 */
	public UserCategoryStatusTaskBean selectTask(int taskId) 
			throws SQLException, ClassNotFoundException {

		UserCategoryStatusTaskBean task = new UserCategoryStatusTaskBean();

		String sql = "SELECT task_name, category_id, limit_date,"
				+ "user_id, status_code, memo "
				+ "FROM t_task WHERE task_id = ?";

		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダへの値の設定
			pstmt.setInt(1, taskId);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			while (res.next()) {
				task.setTaskId(taskId);
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
