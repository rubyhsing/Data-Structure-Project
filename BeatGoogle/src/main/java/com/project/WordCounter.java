package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = URLConverter(urlStr);
    }
    
	private String URLConverter(String url) {
		if (url.contains("https://hahow.in/courses/")) {
			
			
			Pattern pattern = Pattern.compile("https://hahow.in/courses/(.*)(/*)");
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				String courseid = (matcher.group(1));
				url = String.format("https://api.hahow.in/api/courses/%s", courseid) ;
			}
			
			
		}
		
		if (url.contains("https://hiskio.com/courses/")) {
			
			
			Pattern pattern = Pattern.compile("https://hiskio.com/courses/(.*)(/lectures)");
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				String courseid = (matcher.group(1));
				url = String.format("https://api.hiskio.com/v2/courses/%s/chapters", courseid) ;
			}
		}
		
		return url;
	}
    
    private String fetchContent() throws IOException{
    	
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}
	
		return retVal;
    }
    
    public int countKeyword(String keyword){
    	if (content == "") {
    		return 0;
    	}
		if (content == null){
			System.out.println("Count URL"+this.urlStr);
			try {
				content = fetchContent();
			}
			catch (IOException e) {
				content = "";
				return 0;
			}
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		/*
		int retVal = 0;
		int fromIdx = 0;
		int found = -1;
	
		while ((found = content.indexOf(keyword, fromIdx)) != -1){
		    retVal++;
		    fromIdx = found + keyword.length();
		}
		*/
		
		int retVal = BoyerMoore(content,keyword);
	
		return retVal;
    }
    public int BoyerMoore(String T, String P){
        int i = P.length() -1;
        int j = P.length() -1;
        int l;
        int k = 0;
        	while(i<T.length()-P.length()) {
		        if (T.charAt(i) == P.charAt(j)) {
		        	if(j == 0) {
		        		k++;
		        		i = i+4;
		        	}else {
		        		i = i-1;
		        		j = j-1;
		        	}
		        }else {       
		         l = last(T.charAt(i),P);
		         i = i+P.length()- min(j, 1 + l);
		         j = P.length()-1;
		        }         	        
        	}
        
        return k;
	}

    public int last(char c, String P){
    	
    	int n = -1;
    	
    	for(int i = 0;i<P.length();i++) {
    		
    		if(c == P.charAt(i)) {
    			n = i;
    		}else {}
    	}
        return n;
    }

    public int min(int a, int b){
        if (a < b)
            return a;
        else if (b < a)
            return b;
        else 
            return a;
    }
}
