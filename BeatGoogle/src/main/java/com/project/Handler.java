package com.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * Servlet implementation class Handler
 */
@WebServlet("/Handler")
public class Handler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Handler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HashMap<String, Double> map = new HashMap<String, Double>();
		/*
		map.put("First Data", 14);
		map.put("Second Data", 34);
		for (String k:map.keySet()) {
			System.out.println(k+map.get(k));
		}
		*/
		
		
		double st = System.currentTimeMillis();		
		
		String query = (String) request.getParameter("query");
		String platform = (String) request.getParameter("platform");


		
		String search = query;
		//&#1575;&#1604;&#1584;&#1603;&#1575;&#1569;&#1575;&#1604;&#1575;&#1589;&#1591;&#1606;&#1575;&#1593;&#1610;
		//  الذكاءالاصطناعي
		
		search = StringEscapeUtils.unescapeHtml(query);
		
		try {
			search = GoogleQuery.translate("auto", "en", query);
			search = search.replace(" ", "+");
			System.out.println("Search Word"+search);
		}
		catch (Exception e) {
			System.out.println("Translate error");
		}
		
		search += "+AI";
		
		GoogleQuery G = new GoogleQuery(search,platform);
		ArrayList<WebTree> trees = (G.query());
		
		

		for (WebTree tree:trees) {
			SecondLayer childFinder = new SecondLayer(tree);
			tree = childFinder.AddChild();
		}
		
		KeywordList  a = new KeywordList();
		
		int count = 0;
		for (WebTree tree:trees) {
			count += 1;
			System.out.println("-----------"+count+"/"+trees.size());
			tree.setPostOrderScore(a.getList());
			tree.eularPrintTree();
			//tree.root.webPage.setScore(a.getList());
			System.out.println(tree.root.webPage.score);
			//map does not guarantee order
			
		}
		System.out.println("Google Search Order");
		Sort sort = new Sort(trees);
		sort.sort();
		System.out.println("Our Search Engine");
		
		System.out.println("Done");
		System.out.println("Related Keyword" + G.relatedUrls);

		System.out.println("Time Elapse: " + (System.currentTimeMillis()-st)/1000 + "Seconds");
		request.setAttribute("data", trees);
		request.setAttribute("related", G.relatedUrls);
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);
	}

}
