package shixian;

public class Test {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		FileDivided F = new FileDivided();
		F.bigFileDivided();
		MainProcessing M = new MainProcessing();
		M.countTimes(100);
		M.countAllUrlTopK(100);
		long endTime = System.currentTimeMillis();   //calculate the running time of the program
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
	}
}
