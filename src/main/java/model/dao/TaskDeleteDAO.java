package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * タスク削除機能で使用するDAO
 * @author 吉井
 */
public class TaskDeleteDAO {
	
	/**
	 * @author 吉井
	 * @param taskId
	 * @return 該当レコードの削除実行件数
	 */
	public int delete(int taskId) {
		
		//実行件数を0で初期化
		int result = 0;
		
		String sql = "DELETE\n"
				+ "    FROM t_task\n"
				+ "WHERE\n"
				+ "    task_id = ?";
		
		try {
			//データベースへの接続、sql文の用意
			try(Connection con = ConnectionManager.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
				
				//プレースホルダに値を設定、実行
				pstmt.setInt(1, taskId);
				result = pstmt.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
