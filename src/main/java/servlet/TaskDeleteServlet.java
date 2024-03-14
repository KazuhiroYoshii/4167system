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

import model.dao.TaskDeleteDAO;
import model.dao.TaskListDAO;
import model.entity.UserCategoryStatusTaskBean;

/**
 * 一覧表示画面からタスク削除確認画面への遷移を制御するサーブレット
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
	 * 削除選択されたタスクをセッションスコープに設定する
	 * @author 吉井
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストオブジェクトのエンコーディング方式の指定
		request.setCharacterEncoding("UTF-8");
		
		//一覧表示画面で選択されたタスクのtaskIdを取得
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		
		//TaskListDAOをインスタンス化、当該タスクの詳細情報を取得
		TaskListDAO taskListDao = new TaskListDAO();
		UserCategoryStatusTaskBean task = taskListDao.getTaskData(taskId);
		
		//TaskDeleteDAOをインスタンス化、当該タスクに付随するコメント数を取得
		TaskDeleteDAO taskDeleteDao = new TaskDeleteDAO();
		int numberOfComments = 0;
		try {
			numberOfComments = taskDeleteDao.countComments(taskId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
//		//CommentDAOをインスタンス化、当該タスクのコメント情報を取得
//		CommentDAO commentDao = new CommentDAO();
//		List<UserCommentBean> commentList = new ArrayList<>();
//		try {
//			commentList = commentDao.selectComment(taskId);
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
		
		//セッションスコープにタスク詳細情報、コメント数を設定
		HttpSession session = request.getSession();
		session.setAttribute("task", task);
		session.setAttribute("numberOfComments", numberOfComments);
		
		//転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher("delete-confirm.jsp");
		rd.forward(request, response);
		
	}

}
