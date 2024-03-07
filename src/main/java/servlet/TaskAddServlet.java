package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.SelectBoxDAO;
import model.dao.TaskAddDAO;
import model.entity.UserCategoryStatusTaskBean;

/*
 * タスク登録機能のサーブレット
 * @author 森田
 */
/**
 * Servlet implementation class TaskAddServlet
 */
@WebServlet("/TaskAddServlet")
public class TaskAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * タスク登録画面の表示の際に使用するカテゴリ情報、担当者情報、ステータス情報を渡す
	 * メニュー画面からタスク登録画面に遷移する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// 各情報を格納するリスト型の変数
		List<UserCategoryStatusTaskBean> CategoryList = null;
		List<UserCategoryStatusTaskBean> UserList = null;
		List<UserCategoryStatusTaskBean> StatusList = null;

		// DAOのインスタンス化
		SelectBoxDAO dao = new SelectBoxDAO();

		try {
			// プルダウン用のカテゴリ一覧を取得
			// カテゴリ情報
			CategoryList = dao.selectCategory();
			// 担当者情報
			UserList = dao.selectUser();
			// ステータス情報
			StatusList = dao.selectStatus();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// リクエストスコープへの属性の設定
		request.setAttribute("CategoryList", CategoryList);
		request.setAttribute("UserList", UserList);
		request.setAttribute("StatusList", StatusList);

		// 商品登録画面への転送
		RequestDispatcher rd = request.getRequestDispatcher("task-register.jsp");
		rd.forward(request, response);
	}

	/**
	 * タスク登録画面からタスク情報を取得し、DB上で登録する。
	 * タスク登録画面からタスク登録完了画面もしくはタスク登録失敗画面に遷移する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// DAOのインスタンス化
		TaskAddDAO dao = new TaskAddDAO();
		
		// Beanのインスタンス化
		UserCategoryStatusTaskBean taskInfo = new UserCategoryStatusTaskBean();

		// リクエストパラメータを取得しbeanにセット
		taskInfo.setTaskName(request.getParameter("task_name"));
		taskInfo.setCategoryId(Integer.parseInt(request.getParameter("category_id")));
		taskInfo.setLimitDate(request.getParameter("limit_date"));
		taskInfo.setUserId(request.getParameter("user_id"));
		taskInfo.setStatusCode(request.getParameter("status_code"));
		taskInfo.setMemo(request.getParameter("memo"));

		int processingNumber = 0; //処理件数
		String categoryName = null;
		
		try {
			// DAOの利用
			processingNumber = dao.insertTask(taskInfo); //登録処理

			categoryName = dao.categoryChange(taskInfo.getCategoryId());
			taskInfo.setCategoryName(categoryName);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = ""; //転送先
		// 遷移先画面の分岐
		if (processingNumber > 0) {
			url = "task-register-success.jsp"; //登録成功画面
		} else {
			url = "task-register-failure.jsp"; //登録失敗画面
		}

		// セッションオブジェクトを生成
		HttpSession session = request.getSession();

		// セッションスコープへの属性の設定
		request.setAttribute("taskInfo", taskInfo);
		

		// 画面遷移
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
