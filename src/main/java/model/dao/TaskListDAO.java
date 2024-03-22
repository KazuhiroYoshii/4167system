package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク一覧表示機能で使用するDAO
 * @author 吉井
 */
public class TaskListDAO {

	/**
	 * @return 一覧表示用のタスクリスト。タスクリストはUserCategoryStatusTaskBeanオブジェクト
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<UserCategoryStatusTaskBean> getTaskList() throws ClassNotFoundException, SQLException {

		//戻り値のタスクリストをインスタンス化
		List<UserCategoryStatusTaskBean> taskList = new ArrayList<>();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("tt.task_id, ");
		sb.append("tt.task_name, ");
		sb.append("mc.category_name, ");
		sb.append("tt.limit_date, ");
		sb.append("mu.user_name, ");
		sb.append("mu.user_id, ");
		sb.append(" ms.status_name, ");
		sb.append("tt.memo ");
		sb.append("FROM t_task tt ");
		sb.append("INNER JOIN m_category mc ON tt.category_id = mc.category_id ");
		sb.append("INNER JOIN m_user mu ON tt.user_id = mu.user_id ");
		sb.append("INNER JOIN m_status ms ON tt.status_code = ms.status_code ");
		sb.append("ORDER BY tt.task_id ASC");

		String sql = sb.toString();

		//データベースへの接続、sql文の用意
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//実行
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				//リストに追加するレコードをインスタンス化
				UserCategoryStatusTaskBean task = new UserCategoryStatusTaskBean();

				//レコードに情報を格納
				task.setTaskId(rs.getInt("task_id"));
				task.setTaskName(rs.getString("task_name"));
				task.setCategoryName(rs.getString("category_name"));
				task.setLimitDate(rs.getString("limit_date"));
				task.setUserName(rs.getString("user_name"));
				task.setUserId(rs.getString("user_id"));
				task.setStatusName(rs.getString("status_name"));
				task.setMemo(rs.getString("memo"));

				//レコードをリストに追加
				taskList.add(task);
			}
		}

		return taskList;
	}

	/**
	 * @param taskId 取得したいタスクのID
	 * @return 指定されたtaskIdに対応するUserCategoryStatusTaskBeanオブジェクト。
	 *         対応するタスクが存在しない場合はnull。
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public UserCategoryStatusTaskBean getTaskData(int taskId) throws SQLException, ClassNotFoundException {

		//戻り値のタスク情報をインスタンス化
		UserCategoryStatusTaskBean task = new UserCategoryStatusTaskBean();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT tt.task_id, ");
		sb.append("tt.task_name, ");
		sb.append("mc.category_name, ");
		sb.append("tt.limit_date, ");
		sb.append("mu.user_name, ");
		sb.append("ms.status_name, ");
		sb.append("tt.memo ");
		sb.append("FROM t_task tt ");
		sb.append( "INNER JOIN m_category mc ON tt.category_id = mc.category_id ");
		sb.append("INNER JOIN m_user mu ON tt.user_id = mu.user_id ");
		sb.append("INNER JOIN m_status ms ON tt.status_code = ms.status_code ");
		sb.append("WHERE tt.task_id = ?");
		
		String sql = sb.toString();

		//データベースへの接続、PreparedStatementの用意
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//プレースホルダに値を設定、実行
			pstmt.setInt(1, taskId);
			ResultSet rs = pstmt.executeQuery();

			//結果をtaskに格納
			while (rs.next()) {
				task.setTaskId(rs.getInt("task_id"));
				task.setTaskName(rs.getString("task_name"));
				task.setCategoryName(rs.getString("category_name"));
				task.setLimitDate(rs.getString("limit_date"));
				task.setUserName(rs.getString("user_name"));
				task.setStatusName(rs.getString("status_name"));
				task.setMemo(rs.getString("memo"));
			}

		}

		return task;
	}

}
