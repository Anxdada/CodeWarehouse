package shixian;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainProcessing {
	private static String resultFilePath = "D:" + File.separator + "PingCAP" + File.separator + "Filedivision"
			+ File.separator + "res.txt";

	MainProcessing() {
	}

	private List<URLAddress> list = new ArrayList<URLAddress>();
	private HashMap<String, Integer> mp = null;

	public void countTimes(int k) {  // statistics the number and only save top100
		for (int i = 1; i <= 500; i++) {
			String url;
			try {
				String file = "D:" + File.separator + "PingCAP" + File.separator + "Filedivision" + File.separator
						+ "smallFile" + File.separator + String.valueOf(i) + ".txt";
				if (!new File(file).exists()) // file skipping that does not exist
					continue;
				BufferedReader br = new BufferedReader(new FileReader(file)); // open file
				mp = new HashMap<String, Integer>();  // Count the number of occurrences of each address
				while ((url = br.readLine()) != null) {
					if (mp.containsKey(url)) {
						mp.put(url, mp.get(url) + 1);
					} else
						mp.put(url, 1);
				}  // count times
                // merge the same url in each small file
				for (String key : mp.keySet()) {
					list.add(new URLAddress(mp.get(key), key));
				} // traverse the map to put the number and number of each address hash into the list
				mp.clear();
				Collections.sort(list);
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				int tot = k;
				for (int j = 0; j < list.size() && tot != 0; j++) {
					bw.write(list.get(j).getTimes() + " " + list.get(j).getUrlAddress() + "\r\n");
					--tot;
				}  // only need top 100
				bw.flush();
				bw.close();
				br.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			list.clear();  // free memory
		}
		list.clear();
	}

	public void countAllUrlTopK(int k) {
		for (int i = 1; i <= 500; i++) {
			String s;
			try {
				String file = "D:" + File.separator + "PingCAP" + File.separator + "Filedivision" + File.separator
						+ "smallFile" + File.separator + String.valueOf(i) + ".txt";
				if (!new File(file).exists())
					continue;
				BufferedReader br = new BufferedReader(new FileReader(file));
				while ((s = br.readLine()) != null) {
					int v = Integer.parseInt(s.substring(0, s.indexOf(" ")));
					String url = s.substring(s.indexOf(" ") + 1);
					list.add(new URLAddress(v, url));
				}
	            // merge the top100 heap maintained for each file and finally take the final top100
				br.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(resultFilePath));
			Collections.sort(list);
			for (int i = 0; i < list.size() && k != 0; i++) {
				bw.write(list.get(i) + "\r\n");
				--k;
			} // output the result to file
			bw.flush();
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		list.clear();
	}
}
