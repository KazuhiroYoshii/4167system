package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク一覧表示画面で使用するDAO
 * @author 吉井
 */
public class TaskListDAO {
	
	/**
	 * @author 吉井
	 * @return 一覧表示用のタスクリスト
	 */
	public List<UserCategoryStatusTaskBean> getTaskList() {

		//戻り値のタスクリストをインスタンス化
		List<UserCategoryStatusTaskBean> taskList = new ArrayList<>();
		
		String sql = "SELECT\n"
				+ "    tt.task_id,\n"
				+ "    tt.task_name,\n"
				+ "    mc.category_name,\n"
				+ "    tt.limit_date,\n"
				+ "    mu.user_name,\n"
				+ "    ms.status_name,\n"
				+ "    tt.memo\n"
				+ "FROM\n"
				+ "    t_task tt\n"
				+ "    INNER JOIN m_category mc\n"
				+ "        ON tt.category_id = mc.category_id\n"
				+ "    INNER JOIN m_user mu\n"
				+ "        ON tt.user_id = mu.user_id\n"
				+ "    INNER JOIN m_status ms\n"
				+ "        ON tt.status_code = ms.status_code";
		
		try {
			//データベースへの接続、sql文の用意
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
				
				//実行
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					//リストに追加するレコードをインスタンス化
					UserCategoryStatusTaskBean task = new UserCategoryStatusTaskBean();
					
					//レコードに情報を格納
					task.setTaskId(rs.getInt("task_id"));
					task.setTaskName(rs.getString("task_name"));
					task.setCategoryName(rs.getString("category_name"));
					task.setLimitDate(rs.getString("limit_date"));
					task.setUserName(rs.getString("user_name"));;
					task.setStatusName(rs.getString("status_name"));
					task.setMemo(rs.getString("memo"));
					
					//レコードをリストに追加
					taskList.add(task);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return taskList;
	}
}
