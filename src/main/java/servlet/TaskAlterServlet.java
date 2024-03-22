package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ChangeDAO;
import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク情報の編集処理を制御する
 * @author 櫻井
 */
@WebServlet("/TaskAlterServlet")
public class TaskAlterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 不正なアクセスがあったときの遷移を制御する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータから入力された値を取得
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		String taskName = request.getParameter("task_name");
		int categoryId = Integer.parseInt(request.getParameter("category_id"));
		String limitDate = request.getParameter("limit_date");
		String userId = request.getParameter("user_id");
		String statusCode = request.getParameter("status_code");
		String memo = request.getParameter("memo");
		
		//ChangeDAOをインスタンス化
		ChangeDAO changeDao = new ChangeDAO();
		
		String categoryName = null;
		String userName = null;
		String statusName = null;
		
		try {
			//DAOのメソッドを使い、入力された値と対応するカテゴリー名、ユーザー名、ステータス名を取得
			categoryName = changeDao.categoryChange(categoryId);
			userName = changeDao.userChange(userId);
			statusName = changeDao.statusChange(statusCode);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// 変更情報をnewTaskにセット
		UserCategoryStatusTaskBean newTask = new UserCategoryStatusTaskBean();
		newTask.setTaskId(taskId);
		newTask.setTaskName(taskName);
		newTask.setCategoryId(categoryId);
		newTask.setCategoryName(categoryName);
		newTask.setLimitDate(limitDate);
		newTask.setUserId(userId);
		newTask.setUserName(userName);
		newTask.setStatusCode(statusCode);
		newTask.setStatusName(statusName);
		newTask.setMemo(memo);
		
		//セッションオブジェクトの取得、newTaskをセッションスコープに設定
		HttpSession session = request.getSession();
		session.setAttribute("newTask", newTask);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-alter-confirm.jsp");
		rd.forward(request, response);

	}

}
