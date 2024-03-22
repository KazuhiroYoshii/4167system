package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskListDAO;
import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク一覧表示画面で使用するサーブレット
 * @author 吉井
 * Servlet implementation class TaskListServlet
 */
@WebServlet("/TaskListServlet")
public class TaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 一覧表示画面のリストを取得する
	 * @author 吉井
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//TaskListDAOをインスタンス化
		TaskListDAO taskListDao = new TaskListDAO();
		
		//taskListDaoを使って一覧表示用リストを取得
		List<UserCategoryStatusTaskBean> taskList = new ArrayList<>();
		taskList = taskListDao.getTaskList();
		
		//一覧表示用リストをリクエストスコープに設定
		request.setAttribute("taskList", taskList);
		
		//転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher("task-list.jsp");
		rd.forward(request, response);
		
	}

}
