package com.project;

import java.util.ArrayList;

public class Sort {
	private ArrayList<WebTree> lst;
	
	public Sort(ArrayList<WebTree> trees) {
		this.lst = trees;
	}
	
	public void sort(){
		if(lst.size() == 0)
		{
			System.out.println("InvalidOperation");
		}
		else 
		{
			quickSort(0, lst.size()-1);
		}
	}
	
	private void quickSort(int leftbound, int rightbound){
		//1. Implement QuickSort algorithm
		if (leftbound >= rightbound) {return;}
		
		double pk = lst.get(rightbound).root.webPage.score;

		int i = leftbound - 1;
		int j = leftbound;
		
		while (j <= rightbound) {
			
			if ( lst.get(j).root.webPage.score > pk ){
				
				i += 1;
				swap(i,j);
			}
			j += 1;
		}
		
		i += 1;
		swap(i, rightbound);
		
		quickSort(0,i-1);
		quickSort(i+1,rightbound);
		

	}
	
	
	
	private void swap(int aIndex, int bIndex){
		WebTree temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public void output(){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < lst.size(); i++){
			WebTree k = lst.get(i);
			if(i > 0)sb.append(" ");
			sb.append(k.toString());
		}
		
		System.out.println(sb.toString());	
	}
}
