package knapsack01;

import java.util.*;

public class _2knapsack01 {
	
	static boolean knapsack(int targetWeight, int[] wt, int index, int n, List<Integer> ans) {
		
		if (targetWeight == 0)
			return true;
		
		if (index >= n || targetWeight < 0)
			return false;
		
		ans.add(wt[index]);
		System.out.println(ans);
		if (knapsack(targetWeight-wt[index], wt, index+1, n, ans))
			return true;
		
		ans.remove(ans.size()-1);
		//System.out.println(ans);	// will show more detailed version if commented out
		return knapsack(targetWeight, wt, index+1, n, ans);

	}

	public static void main(String[] args) {
		int targetW = 10;
		int[] weightList = {3, 2, 8, 7, 4};
		
		List<Integer> ans = new ArrayList<>();
		boolean hasSolution = knapsack(targetW, weightList, 0, weightList.length, ans);
		
		if (hasSolution) {
			System.out.println("\n" + "list to form target weight");
			System.out.println(ans);
		}
		else
			System.out.println("no solution");
	}

}
