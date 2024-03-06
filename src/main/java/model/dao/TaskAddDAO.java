package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.UserCategoryStatusTaskBean;

public class TaskAddDAO {

	public int insertTask(UserCategoryStatusTaskBean taskInfo) throws SQLException, ClassNotFoundException {

		int processingNumber = 0; //処理件数

		String sql = "INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo)VALUES(?, ?, ?, ?, ?, ?)";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			Date date = Date.valueOf(taskInfo.getLimitDate());
			
			pstmt.setString(1, taskInfo.getTaskName());
			pstmt.setInt(2, taskInfo.getCategoryId());
			pstmt.setDate(3, date);
			pstmt.setString(4, taskInfo.getUserId());
			pstmt.setString(5, taskInfo.getStatusCode());
			pstmt.setString(6, taskInfo.getMemo());
			
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;
	}

}
