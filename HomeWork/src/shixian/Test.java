package shixian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
//	private static List<URLAddress> list = new ArrayList<URLAddress>();

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		FileDivided F = new FileDivided();
		F.bigFileDivided();
		MainProcessing M = new MainProcessing();
		M.countTimes(100);
		M.countAllUrlTopK(100);
		long endTime = System.currentTimeMillis();   //calculate the running time of the program
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

//		list.add(new URLAddress(1, "c"));
//		list.add(new URLAddress(2, "b"));
//		list.add(new URLAddress(1, "b"));
//		System.out.println("Before sort");
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
//		Collections.sort(list);
//		System.out.println("After sort");
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
	}
}
