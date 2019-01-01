package shixian;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileDivided {
	private static String trainingDataFilePath = "D:" + File.separator + "PingCAP" + File.separator + "Filedivision" + File.separator + "url.txt";
	private long mod = 1000000007; 
	private long seed = 19260817; // Hash seed
	
	FileDivided(){}
	
	public long Hash1URL(String s) { // function of Hash
        long ans = 0;
        int len = s.length();
        for (int i = 0 ; i < len ; i ++ ) {
            ans = ans * seed + (int)(s.charAt(i));
            ans = ans % mod;
        }
        return ans;
    }
	
	public void bigFileDivided() {
		BufferedReader br = null;
		String url;
		try {
			br = new BufferedReader(new FileReader(trainingDataFilePath));
			while ((url = br.readLine()) != null) {
				long ans = Hash1URL(url);
				ans = ans % 500 + 1; // Hash to number
	            //divided into 500 small files, each about 200M each
				String file = "D:" + File.separator + "PingCAP" + File.separator + "Filedivision" + File.separator + "smallFile" + File.separator + String.valueOf(ans) + ".txt";
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				//put each url address into the corresponding small file with the hash function conversion
	            //because the hash function is the same, the same url must be placed in the same file
				bw.write(url + "\r\n");
				bw.flush();
				bw.close();
			}
			br.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
