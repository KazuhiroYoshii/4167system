package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.UserCategoryStatusTaskBean;

/**
 * ユーザマスタ、カテゴリマスタ、ステータスマスタ、
 * タスクテーブルにアクセスするためのDAO
 * @author 櫻井
 */
public class TaskAlterDAO {

	/**
	 * 指定されたカテゴリコードに該当する商品の情報を変更します
	 */
	public int update(UserCategoryStatusTaskBean taskBean)
			throws SQLException, ClassNotFoundException {

		int processingNumber = 0; //処理件数

		String sql = "UPDATE t_task SET task_name = ?, category_id = ?, "
				+ "limit_date = ?, status_code = ?, memo = ? WHERE task_id = ?";
		
		int taskId = taskBean.getTaskId();
		String taskName = taskBean.getTaskName();
		int categoryId = taskBean.getCategoryId();
		Date limitDate = Date.valueOf(taskBean.getLimitDate());
		String statusCode = taskBean.getStatusCode();
		String memo = taskBean.getMemo();

		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダへの値の設定
			pstmt.setString(1, taskName);
			pstmt.setInt(2, categoryId);
			pstmt.setDate(3, limitDate);
			pstmt.setString(4, statusCode);
			pstmt.setString(5, memo);
			pstmt.setInt(6, taskId);

			// SQLステートメントの実行
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;
	}
	
}
