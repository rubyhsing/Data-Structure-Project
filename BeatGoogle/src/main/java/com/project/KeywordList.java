package com.project;

import java.util.*;

public class KeywordList {
	ArrayList<Keyword>  keywordList = new ArrayList<>();
	public KeywordList() {
		
		
		keywordList.add(new Keyword("Aritificial",5));
		keywordList.add(new Keyword("Intelligence",5));
		//keywordList.add(new Keyword("人工智慧",5));
		
		keywordList.add(new Keyword("Machine Learning",4));
		//keywordList.add(new Keyword("機器學習",4));
		
		keywordList.add(new Keyword("Deep Learning",4));
		//keywordList.add(new Keyword("深度學習",4));	
		
		keywordList.add(new Keyword("NLP",3));
		//keywordList.add(new Keyword("自然語言",3));
		
		keywordList.add(new Keyword("Python",5));
		keywordList.add(new Keyword("Pytorch",1));
		keywordList.add(new Keyword("Tensorflow",1));
		keywordList.add(new Keyword("Sklearn",1));
		//keywordList.add(new Keyword("Course",5));

		/*
		
		keywordList.add(new Keyword("Adobe",-4));
		keywordList.add(new Keyword("Illustrator",-4));
//		keywordList.add(new Keyword("PS",-3));
		keywordList.add(new Keyword("Photoshop",-3));
		keywordList.add(new Keyword("Design",-3));
		//keywordList.add(new Keyword("設計",-3));
		
		keywordList.add(new Keyword("Animation",-3));
		//keywordList.add(new Keyword("動畫",-3));
		
		keywordList.add(new Keyword("ACP",-3));
		keywordList.add(new Keyword("vector",-3));
		//keywordList.add(new Keyword("向量",-3));
		*/
	}
	
	
	public void add(Keyword k) {
		this.add(k);
	}	
	
	public ArrayList getList() {
		return keywordList;
	}
}
