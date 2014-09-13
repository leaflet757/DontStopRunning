package com.myertse.framework.impl;

import java.util.ArrayList;
import java.util.Random;

public class UniqueRandom {

	int start = 0;
	int end = 0;
	ArrayList<Integer> numberStore = new ArrayList<Integer>();
	
	public UniqueRandom(int startingNum, int endNum) {
		start = startingNum;
		end = endNum;
		for(int i = start; i <= end; i++)
		{
			numberStore.add(i);
		}
		
	}
	
	public int nextUniqueRandom()
	{
		Random random = new Random();
		int selection = random.nextInt(end);
		int returnNumber = numberStore.get(selection);
		numberStore.remove(selection);
		return returnNumber;
	}

}
