package model.entity;

/**
 * コメント画面を作成するための情報を保持するクラス
 * @author 櫻井
 */
public class UserCommentBean {

	// ユーザID
	private String userId;
	
	// ユーザ名
	private String userName;
	
	// コメントID
	private int commentId;
	
	// タスクID
	private int taskId;
	
	// コメント
	private String comment;
	
	// 更新日時
	private String updateDatetime;

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return commentId
	 */
	public int getCommentId() {
		return commentId;
	}

	/**
	 * @return taskId
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @return updateDatetime
	 */
	public String getUpdateDatetime() {
		return updateDatetime;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param commentId セットする commentId
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	/**
	 * @param taskId セットする taskId
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * @param comment セットする comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param updateDatetime セットする updateDatetime
	 */
	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
