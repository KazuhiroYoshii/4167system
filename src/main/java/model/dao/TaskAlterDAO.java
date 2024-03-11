package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserCategoryStatusTaskBean;

/**
 * ユーザマスタ、カテゴリマスタ、ステータスマスタ、
 * タスクテーブルにアクセスするためのDAO
 * @author 櫻井
 */
public class TaskAlterDAO {

	/**
	 * 指定されたタスクIDに該当するタスクの情報を変更します
	 * @param taskBean 変更情報をセットしたBean型taskBean
	 * @return processingNumber 処理件数
	 * @throws SQLException, ClassNotFoundException 
	 */
	public int update(UserCategoryStatusTaskBean taskBean)
			throws SQLException, ClassNotFoundException {

		int processingNumber = 0; //処理件数

		String sql = "UPDATE t_task SET task_name = ?, category_id = ?, "
				+ "limit_date = ?, user_id = ?, status_code = ?, memo = ? WHERE task_id = ?";

		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダへの値の設定
			pstmt.setString(1, taskBean.getTaskName());
			pstmt.setInt(2, taskBean.getCategoryId());
			pstmt.setString(4, taskBean.getUserId());
			pstmt.setString(5, taskBean.getStatusCode());
			pstmt.setString(6, taskBean.getMemo());
			pstmt.setInt(7, taskBean.getTaskId());
			
			if(taskBean.getLimitDate().equals("")) {
				pstmt.setDate(3, null);
			} else {
				Date date = Date.valueOf(taskBean.getLimitDate());
				pstmt.setDate(3, date);
			}

			// SQLステートメントの実行
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;
	}
	
	/**
	 * 指定されたタスクIDのタスク情報を返します
	 * @param taskId タスクID
	 * @return alterTask 変更後のタスク情報
	 * @throws SQLException, ClassNotFoundException 
	 */
	public UserCategoryStatusTaskBean selectTask(int taskId) 
			throws SQLException, ClassNotFoundException {

		UserCategoryStatusTaskBean alterTask = new UserCategoryStatusTaskBean();

		String sql = "SELECT t1.task_name, t3.category_name, t1.limit_date, t2.user_name, t4.status_name, t1.memo "
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
				alterTask.setTaskId(taskId);
				alterTask.setTaskName(res.getString("task_name"));
				alterTask.setCategoryName(res.getString("category_name"));
				alterTask.setLimitDate(res.getString("limit_date"));
				alterTask.setUserName(res.getString("user_name"));
				alterTask.setStatusName(res.getString("status_name"));
				alterTask.setMemo(res.getString("memo"));
			}
		}

		return alterTask;
	}
	
}
