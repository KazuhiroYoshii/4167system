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
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskAlterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		ChangeDAO dao = new ChangeDAO();
		
		String categoryName = null;
		String userName = null;
		String statusName = null;
		
		try {
			//DAOのメソッドを使い、入力された値と対応するカテゴリー名、ユーザー名、ステータス名を取得
			categoryName = dao.categoryChange(categoryId);
			userName = dao.userChange(userId);
			statusName = dao.statusChange(statusCode);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
