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
 * Servlet implementation class DeleteExecuteServlet
 */
@WebServlet("/DeleteExecuteServlet")
public class DeleteExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteExecuteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//セッションオブジェクトの取得、セッションスコープから選択したタスクの詳細情報を取得
		HttpSession session = request.getSession();
		UserCategoryStatusTaskBean task = (UserCategoryStatusTaskBean)session.getAttribute("task");
		
		//TaskListDAOの削除実行メソッドの引数に用いるtaskIdを切り出し
		int taskId = task.getTaskId();
		
		//TaskListDAOをインスタンス化、メソッドを用いて削除を実行し実行件数を取得
		TaskListDAO dao = new TaskListDAO();
		int result = dao.delete(taskId);
		
		//実行件数をリクエストスコープに設定
		request.setAttribute("result", result);
		
		//転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher("delete-result.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
