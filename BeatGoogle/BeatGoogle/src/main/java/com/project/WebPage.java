package com.project;

import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	
	public WebPage(String url, String name){
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);	
	}
	
	public void setScore(ArrayList<Keyword> keywords) throws IOException{
		score = 0;
//		1. calculate score
		for(int i = 0;i<keywords.size();i++ ) {
			score +=counter.countKeyword(keywords.get(i).name)*keywords.get(i).weight;
		}

	}	
}