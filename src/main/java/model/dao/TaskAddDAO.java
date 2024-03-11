package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク登録画面で使用するDAO
 * @author 森田
 */
public class TaskAddDAO {

	/**
	 * 入力されたタスク情報を登録します
	 * @param taskInfo タスク登録情報一覧が格納されたUserCategoryStatusTaskBeanオブジェクト（task_name, category_id, limit_date, user_id, status_code, memo）
	 * @return processionNumber 成功した場合に更新された行数を返します。
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insertTask(UserCategoryStatusTaskBean taskInfo) throws SQLException, ClassNotFoundException {

		// 処理件数
		int processingNumber = 0;

		String sql = "INSERT INTO t_task(task_name, category_id, limit_date, user_id, status_code, memo)VALUES(?, ?, ?, ?, ?, ?)";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setString(1, taskInfo.getTaskName());
			pstmt.setInt(2, taskInfo.getCategoryId());
			
			if(taskInfo.getLimitDate().equals("")) {
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
}
