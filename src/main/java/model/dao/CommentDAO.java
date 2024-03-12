package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserCategoryStatusTaskBean;
import model.entity.UserCommentBean;

/**
 * 【追加システム】コメント投稿機能のためのDAO
 * @author 森田
 */
public class CommentDAO {

	/**
	 * 指定されたタスクIDのタスク情報を返します
	 * @param taskId タスクID
	 * @return task タスク情報をセットしたBean型task
	 * @throws ClassNotFoundException
	 */
	public UserCategoryStatusTaskBean selectTask(int taskId)
			throws SQLException, ClassNotFoundException {

		UserCategoryStatusTaskBean taskDetail = new UserCategoryStatusTaskBean();

		String sql = "SELECT t1.task_name"
				+ " , t3.category_name"
				+ " , t1.limit_date"
				+ " , t2.user_name"
				+ " , t1.status_code"
				+ " , t4.status_name"
				+ " , t1.memo"
				+ " FROM t_task t1 "
				+ " INNER JOIN m_user t2 "
				+ " 	ON t1.user_id = t2.user_id "
				+ " INNER JOIN m_category t3 "
				+ "     ON t1.category_id = t3.category_id "
				+ " INNER JOIN m_status t4 "
				+ "     ON t1.status_code = t4.status_code"
				+ " WHERE task_id = ?";

		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダへの値の設定
			pstmt.setInt(1, taskId);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			while (res.next()) {
				taskDetail.setTaskId(taskId);
				taskDetail.setTaskName(res.getString("task_name"));
				taskDetail.setCategoryId(res.getInt("category_id"));
				taskDetail.setLimitDate(res.getString("limit_date"));
				taskDetail.setUserId(res.getString("user_id"));
				taskDetail.setStatusCode(res.getString("status_code"));
				taskDetail.setMemo(res.getString("memo"));
			}
		}
		return taskDetail;
	}

	/**
	 * 指定されたタスクIDのコメント情報を返します
	 * @param taskId タスクID
	 * @return commentList コメント情報をセットしたList型commentList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<UserCommentBean> selectComment(int taskId)
			throws SQLException, ClassNotFoundException {

		List<UserCommentBean> commentList = new ArrayList<>();

		String sql = "SELECT t1.comment_id"
				+ " , t1.task_id"
				+ " , t1.user_id"
				+ " , t2.user_name"
				+ " , t1.comment"
				+ " , t1.update_datetime"
				+ " FROM t_comment t1"
				+ " INNER JOIN m_user t2"
				+ " 	ON t1.user_id = t2.user_id"
				+ " ORDER BY comment_id ASC";

		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダへの値の設定
			pstmt.setInt(1, taskId);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();

			// 結果の操作
			while (res.next()) {
				// リストに追加するレコードをインスタンス化
				UserCommentBean comment = new UserCommentBean();

				// レコードに情報を格納
				comment.setCommentId(res.getInt("comment_id"));
				comment.setTaskId(res.getInt("task_id"));
				comment.setUserId(res.getString("user_id"));
				comment.setUserName(res.getString("user_name"));
				comment.setComment(res.getString("comment"));
				comment.setUpdateDatetime(res.getString("update_datetime"));
				
				// レコードをリストに追加
				commentList.add(comment);
			}
		}
		return commentList;
	}

	/**
	 * 入力されたコメントを登録します
	 * @param commentInfo コメント登録情報が格納されたUserCommentBeanオブジェクト(task_id, user_id, comment)
	 * @return processingNumber 処理件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insertComment(UserCommentBean commentInfo)
			throws SQLException, ClassNotFoundException {

		// 処理件数
		int processingNumber = 0;

		String sql = "INSERT INTO t_comment(task_id, user_id, comment)VALUES(?, ?, ?);";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setInt(1, commentInfo.getTaskId());
			pstmt.setString(2, commentInfo.getUserId());
			pstmt.setString(3, commentInfo.getComment());

			// 実行
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;

	}
	
	/**
	 * 指定されたコメントIDのコメントを削除します
	 * @param commentId コメントID
	 * @return processionNumber 処理件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int deleteComment(int commentId)
			throws SQLException, ClassNotFoundException {

		// 処理件数
		int deleteResult = 0;

		String sql = "DELETE FROM t_comment WHERE comment_id = ?";

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// プレースホルダへの値の設定
			pstmt.setInt(1, commentId);
			
			// 実行
			deleteResult = pstmt.executeUpdate();
		}
		return deleteResult;

	}
}
