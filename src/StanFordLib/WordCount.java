package StanFordLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WordCount {
	
	private String word;
	private int count;
	
	public WordCount(String word, int count){
		setWord(word);
		setCount(count);
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj!=null && obj instanceof WordCount){
			WordCount other = (WordCount)obj;
			if(this.getWord().equalsIgnoreCase(other.getWord())){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	public WordCount[] getFrequentWords(ArrayList<String> words){
        HashMap<String,WordCount> map = new HashMap<String,WordCount>();
        for(String s:words){
        	WordCount w = map.get(s);
            if(w==null)
                w = new WordCount(s,1);
            else
                w.count++;
            map.put(s, w);
        }
        WordCount[] list = map.values().toArray(new WordCount[]{});
        
//        for (WordCount item : list){
//        	System.out.println(item.getWord() + "  " + item.getCount());
//        }
        
        
        return doInsertionSort(list);
    }
	
	public WordCount[]  doInsertionSort(WordCount[] input){
        
		WordCount temp;
        for (int i = 1; i < input.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(input[j].getCount() > input[j-1].getCount()){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        return input;
    }
	
	

}