package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SecondLayer {
	
	private WebTree tree;
	private String content;
	private int count;
	
	public SecondLayer(WebTree tree) {
		this.tree = tree;
		this.content = "";
		this.count = 0;
	}
	

	
	private String fetchContent(String url) throws IOException
	{
		String retVal = "";

		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setConnectTimeout(1000);
		conn.setReadTimeout(1000);
		//set HTTP header
		//conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine()) != null)
		{
			retVal += line;
		}
		
		this.content = retVal;
		return retVal;
	
	}
	

	
	private String URLConverter(String url) {
		if (url.contains("https://hahow.in/courses/")) {
			
			
			Pattern pattern = Pattern.compile("https://hahow.in/courses/(.*)");
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				String courseid = (matcher.group(1));
				if (courseid.contains("/")) {
					courseid = courseid.split("/")[0];
				}
				url = String.format("https://api.hahow.in/api/courses/%s", courseid) ;
			}
			
			
		}
		
		if (url.contains("https://hiskio.com/courses/")) {
			
			
			Pattern pattern = Pattern.compile("https://hiskio.com/courses/(.*)");
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				String courseid = (matcher.group(1));
				if (courseid.contains("/")) {
					courseid = courseid.split("/")[0];
				}
				url = String.format("https://api.hiskio.com/v2/courses/%s", courseid) ;
			}
		}
		
		return url;
	}
	
	protected WebTree AddChild() throws IOException{
		System.out.println("---------------");
		//Hahow
		
		String url = tree.root.webPage.url;
		// When deal with Hahow and Hiskio url, normal request would only retrieve javascript
		// Must convert to api url first
		url = URLConverter(url);
		System.out.println("Converted URL" + url);

		try {
			fetchContent(url);
		}
		catch (IOException e) {
			System.out.println("Fail");
			return tree;
		}

		
		if (content == "") {
			return tree;
		}
		
		Document doc = Jsoup.parse(content);
		Elements list =  doc.select("a");
		ArrayList<WebPage> temp = new ArrayList<WebPage>();
		System.out.println("Parent webpage"+url);
		for (Element li : list) {
			String citeUrl = li.attr("href");
			String title = li.text();
			//System.out.println(citeUrl);
			// if either title or citeurl is empty, ignore this subwebsite
			if(title.equals("") || citeUrl.equals("")) 
			
			{
				continue;
			}
			
			if (citeUrl.startsWith("/")) {
				// Reconstruct the url if only path is presented
				try {
					URL uri = new URL(url);
					citeUrl = uri.getProtocol() + "://" + uri.getHost() + citeUrl;
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					continue;
				}
				
			}
			
			if (!citeUrl.startsWith("http")) {
				continue;
			}
			temp.add(new WebPage(citeUrl, title));
			
			count += 1;

		}
		
		// sample at most three subpage from all a tags
		
		if (count >= 10) {

			temp.subList((int)(0.2*count), (int)(0.8 * count));
			Collections.shuffle(temp);
		}
		
		else if (count >= 2) {
			Collections.shuffle(temp);
		}
		
		for (int i = 0 ; i < Math.min(count, 2); i++) {
			
			WebNode child  = new WebNode(temp.get(i));
			System.out.println(temp.get(i).name + temp.get(i).url);
			tree.root.addChild(child);
		}
		
		
		return tree;
		
	}
}
