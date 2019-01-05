package shixian;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileDivided {
	private static String trainingDataFilePath = "D:" + File.separator + "PingCAP" + File.separator + "Filedivision"
			+ File.separator + "url.txt";
	private static final String directory = "D:" + File.separator + "PingCAP" + File.separator + "Filedivision" + File.separator 
			+ "smalldir" + File.separator;
	private long mod = 1000000007;
	private long seed = 19260817; // Hash seed
	private static long si = 209715201; // 200M bytes
	private static int[] id = new int[505]; // This is used to record the number of files already stored in each directory
	// (Because the file naming is 1-500, if id[i] == 0, there is no current purpose)
	

	FileDivided() {
	}

	public long Hash1URL(String s) { // function of Hash
		long ans = 0;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			ans = ans * seed + (int) (s.charAt(i));
			ans = ans % mod;
		}
		return ans;
	}

	public void bigFileDivided() {
		for (int i = 0; i < 505; i++) {
			id[i] = 0;
		} // initialize id array
		BufferedReader br = null;
		String url;
		try {
			br = new BufferedReader(new FileReader(trainingDataFilePath));
			while ((url = br.readLine()) != null) {
				long ans = Hash1URL(url);
				ans = ans % 500 + 1; // Hash to number
				// divided into 500 small files, each about 200M each
				String fs = directory + String.valueOf(ans) + File.separator;
				File file = new File(fs);
				if (!file.exists()) file.mkdir();
				int pos = (int)ans;
				if (id[pos] == 0) ++ id[pos];
				String s1 = String.valueOf(id[pos]) + ".txt";	
				if (new File(fs + s1).length() >= si) ++ id[pos];
				// the current file size is too large, and the file is newly opened in the same directory
				String path = fs + String.valueOf(id[pos]) + ".txt";
				BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
				// put each url address into the corresponding small file with the hash function conversion
				// because the hash function is the same, the same url must be placed in the same file
				bw.write(url + "\r\n");
				bw.flush();
				bw.close();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getFileId(int i) {
		return id[i];
	}
}
