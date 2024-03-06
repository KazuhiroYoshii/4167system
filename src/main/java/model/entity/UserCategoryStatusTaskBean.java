package model.entity;

/**
 * タスク登録・一覧・編集画面を作成するための情報を保持するクラス
 * @author 櫻井
 */
public class UserCategoryStatusTaskBean {	
	
	// タスクID
	private int taskId;
	
	// タスク名
	private String taskName;
	
	// カテゴリID
	private int categoryId;
	
	// カテゴリ名
	private String categoryName;
	
	// 期限
	private String limitDate;
	
	// ユーザID
	private String userId;
	
	// ユーザ名
	private String userName;
	
	// ステータスコード
	private String statusCode;
	
	// ステータス名
	private String statusName;
	
	// メモ
	private String memo;

	
	/**
	 * @return taskId
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * @return taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @return categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @return limitDate
	 */
	public String getLimitDate() {
		return limitDate;
	}

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
	 * @return statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**。
	 * @return statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param taskId セットする taskId
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * @param taskName セットする taskName
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @param categoryId セットする categoryId
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @param categoryName セットする categoryName
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @param limitDate セットする limitDate
	 */
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
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
	 * @param statusCode セットする statusCode
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @param statusName セットする statusName
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @param memo セットする memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
