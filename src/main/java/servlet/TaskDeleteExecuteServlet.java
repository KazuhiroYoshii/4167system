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
import model.entity.UserCategoryStatusTaskBean;

/**
 * タスク削除の実行を制御するサーブレット
 * @author 吉井
 * Servlet implementation class DeleteExecuteServlet
 */
@WebServlet("/TaskDeleteExecuteServlet")
public class TaskDeleteExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDeleteExecuteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 選択されたタスクを削除する
	 * @author 吉井
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//セッションオブジェクトの取得、セッションスコープから選択したタスクの詳細情報を取得
		HttpSession session = request.getSession();
		UserCategoryStatusTaskBean task = (UserCategoryStatusTaskBean)session.getAttribute("task");
		
		//TaskListDAOの削除実行メソッドの引数に用いるtaskIdを切り出し
		int taskId = task.getTaskId();
		
		//セッションスコープからタスクに付随するコメント数を取得
		int numberOfComments = (int)session.getAttribute("numberOfComments");
		
		//TaskListDAOをインスタンス化、該当タスクにコメントが1件でもついている場合はコメント全件削除
		TaskDeleteDAO taskDeleteDao = new TaskDeleteDAO();
		int commentDeleteResult = 0;
		if(numberOfComments > 0) {
			try {
				commentDeleteResult = taskDeleteDao.deleteAllComments(taskId);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		//コメントの削除実行件数をリクエストスコープに設定
		request.setAttribute("commentDeleteResult", commentDeleteResult);
		
		//タスク削除を実行し実行件数を取得
		int taskDeleteResult = taskDeleteDao.delete(taskId);
		
		//実行件数が1の場合のみ削除完了画面のurl、それ以外の場合は削除失敗画面のurlを設定
		String url = null;
		if(taskDeleteResult == 1) {
			url = "delete-success.jsp";
		}else {
			url = "delete-failure.jsp";
		}
		
		//転送用オブジェクトの取得、転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
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
