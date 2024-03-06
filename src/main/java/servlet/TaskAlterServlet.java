package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskAlterDAO;
import model.dao.TaskAlterFormDAO;
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

		// リクエストオブジェクトのエンコーディング方式の指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int taskId = Integer.parseInt(request.getParameter("task_id"));
		String taskName = request.getParameter("task_name");
		int categoryId = Integer.parseInt(request.getParameter("category_id"));
		String limitDate = request.getParameter("limit_date");
		String statusCode = request.getParameter("status_code");
		String memo = request.getParameter("memo");

		UserCategoryStatusTaskBean taskBean = new UserCategoryStatusTaskBean();
		taskBean.setTaskId(taskId);
		taskBean.setTaskName(taskName);
		taskBean.setCategoryId(categoryId);
		taskBean.setLimitDate(limitDate);
		taskBean.setStatusCode(statusCode);
		taskBean.setMemo(memo);
		
		// DAOの生成
		TaskAlterDAO dao1 = new TaskAlterDAO();
		TaskAlterFormDAO dao2 = new TaskAlterFormDAO();

		int processingNumber = 0; //処理件数
		String url = null; // 転送先
		UserCategoryStatusTaskBean task = null;

		try {
			// DAOの利用
			processingNumber = dao1.update(taskBean);

			if (processingNumber == 0) {
				// 転送先の設定
				url = "task-alter-failure.jsp";
			} else {
				// 転送先の設定
				url = "task-alter-success.jsp";
			}
			
			// DAOの利用
			task = dao2.selectTask(taskId);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// リクエストスコープへの属性の設定
		request.setAttribute("task", task);
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
