package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskListDAO;
import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク削除実行前の確認画面に使用するサーブレット
 * @author 吉井
 * Servlet implementation class TaskDeleteServlet
 */
@WebServlet("/TaskDeleteServlet")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDeleteServlet() {
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
	 * @author 吉井
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストオブジェクトのエンコーディング方式の指定
		request.setCharacterEncoding("UTF-8");
		
		//一覧表示画面で選択されたレコードのtaskIdを取得
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		
		//DAOをインスタンス化、当該レコードの詳細情報取得
		TaskListDAO dao = new TaskListDAO();
		UserCategoryStatusTaskBean task = dao.getTaskData(taskId);
		
		//セッションスコープに詳細情報を設定
		HttpSession session = request.getSession();
		session.setAttribute("task", task);
		
		//転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher("delete-confirm.jsp");
		rd.forward(request, response);
		
	}

}
