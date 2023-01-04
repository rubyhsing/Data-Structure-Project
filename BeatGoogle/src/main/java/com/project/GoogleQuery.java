package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.json.JSONArray;
import org.apache.commons.lang.StringEscapeUtils;

public class GoogleQuery 
{
	public String searchKeyword;
	public String url;
	public String content;
	
	public GoogleQuery(String searchKeyword)
	{
		this.searchKeyword = searchKeyword;
		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=15";
	}
	
	public GoogleQuery(String searchKeyword, String topic) 
	{
		this.searchKeyword = searchKeyword;
		this.url = String.format("http://www.google.com/search?q=%s\"%s\"&oe=utf8&num=15",searchKeyword,topic);
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";

		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		//set HTTP header
		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine()) != null)
		{
			retVal += line;
		}
		return retVal;
	}
	
	public void FetchContentJSoup() throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Chrome/107.0.5304.107").get();
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		for(Element li : lis)
		{
			try 
			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) 
				{
					continue;
				}
				
				System.out.println("Title: " + title + " , url: " + citeUrl);
				
				//put title and pair into HashMap

			} catch (IndexOutOfBoundsException e) 
			{
//				e.printStackTrace();
			}
		}
	
	}
	
	public ArrayList<WebTree> query() throws IOException
	{
		if(content == null)
		{
			content = fetchContent();
		}
		
		ArrayList<WebTree> trees = new ArrayList<WebTree>();

		HashMap<String, String> retVal = new HashMap<String, String>();
		
		
		/* 
		 * some Jsoup source
		 * https://jsoup.org/apidocs/org/jsoup/nodes/package-summary.html
		 * https://www.1ju.org/jsoup/jsoup-quick-start
 		 */
		
		//using Jsoup analyze html string
		Document doc = Jsoup.parse(content);
		
		//select particular element(tag) which you want 
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		for(Element li : lis)
		{
			try 
			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) 
				{
					continue;
				}
				
				citeUrl = citeUrl.replace("/url?q=", "");
				String[] arrOfStr = citeUrl.split("&sa", 2);
				citeUrl = arrOfStr[0];
				
				System.out.println("Title: " + title + " , url: " + citeUrl);
				
				//put title and pair into HashMap
				retVal.put(title, citeUrl);
				WebTree root = new WebTree( new WebPage(citeUrl, title) );
				trees.add(root);

			} catch (IndexOutOfBoundsException e) 
			{
//				e.printStackTrace();
			}
		}
		return trees;
	}
	
	  public static final Map<String, String> LANGUAGE_MAP = new HashMap();

	   static {
	     init();
	   }

	   /**
	    * 初始化语言类
	    */
	   private static void init() {
	       LANGUAGE_MAP.put("auto", "Automatic");
	       
	   }
	   public static String translate(String langFrom, String langTo, String word) throws Exception {
		   
	       String url = 
	               "https://translate.googleapis.com/translate_a/single?" +
	               "client=gtx" +
	               "&sl=" + langFrom +
	               "&tl=" + langTo +
	               "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");

	       URL obj = new URL(url);
	       HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	       con.setRequestProperty("User-Agent", "Mozilla/5.0");

	       BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	       String inputLine;
	       StringBuffer response = new StringBuffer();

	       while ((inputLine = in.readLine()) != null) {
	           response.append(inputLine);
	       }
	       in.close();

	       return parseResult(response.toString());
	   }

	   private static String parseResult(String inputJson) throws Exception {

	       JSONArray jsonArray = new JSONArray(inputJson);
	       JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
//	     JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);
	       String result ="";

	       for(int i =0;i < jsonArray2.length();i ++){
	           result += ((JSONArray) jsonArray2.get(i)).get(0).toString();
	       }

	       return result;
	   }
}