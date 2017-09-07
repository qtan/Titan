package rpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;


/**
 * Servlet implementation class SearchItem
 */
@WebServlet("/search")
public class SearchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnectionFactory.getDBConnection(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*  PrintWriter out = response.getWriter();
		if(request.getParameter("username")!=null) {
			String username = request.getParameter("username");
			out.print("Hello "+username);
		}
		out.flush();
		out.close();
	}
	*/
	/*
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1> This is a HTML page</h1>");
		out.println("</body></html>");
		out.flush();
		out.close();
	}
	*/
	/*
		response.setContentType("application/json");
		String username = "";
		if(request.getParameter("username")!=null) {
			username = request.getParameter("username");
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("username", username);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.print(obj);
		out.flush();
		out.close();
	}
	*/
	/*
		JSONArray array = new JSONArray();
		try {
			array.put(new JSONObject().put("username", "abcd"));
		}catch (JSONException e) {
			e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, array);
	}
	*/
		String userId = request.getParameter("user_id");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		// Term can be empty or null.
		String term = request.getParameter("term");
		//ExternalAPI externalAPI = ExternalAPIFactory.getExternalAPI();
		//List<Item> items = externalAPI.search(lat, lon, term);
		List<Item> items = conn.searchItems(userId, lat, lon, term);
		List<JSONObject> list = new ArrayList<>();
		try {
			for (Item item : items) {
				JSONObject obj = item.toJSONObject();
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray array = new JSONArray(list);
		RpcHelper.writeJsonArray(response, array);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
