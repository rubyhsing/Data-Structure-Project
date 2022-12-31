package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;


public class KeywordCounter {
	private String urlStr;
    private String content;
    
    public KeywordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
	
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}
//		System.out.print(retVal);
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
    
    public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = BoyerMoore(content ,keyword); 
		// 1. calculates appearances of keyword (Bonus: Implement Boyer-Moore Algorithm)
	
		return retVal;
    }
}
