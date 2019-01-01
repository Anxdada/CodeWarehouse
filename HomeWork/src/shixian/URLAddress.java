package shixian;

public class URLAddress implements java.lang.Comparable<URLAddress> {
	private int ti;
	private String url;

	URLAddress(){}
	
	URLAddress(int ti, String url) {
        this.ti = ti;
        this.url = url;
    }
	
	public int getTimes() {
		return ti;
	}

	public void setPrice(int ti) {
		this.ti = ti;
	}

	public String getUrlAddress() {
		return url;
	}

	public void setName(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "times: " + getTimes() + " URL: " + getUrlAddress();
	}

	/***
	 * Rewrite compareTo first, the number of times is in front, the number of times is the same as the lexicographical order in front
	 * 
	 * @param g
	 * @return
	 */
	public int compareTo(URLAddress g) {
		int result = 0;
		if (result == 0) {
			result = -(this.ti - g.ti); // 先按次数降序
			if (result == 0) {
				result = this.url.compareTo(g.url); // 次数一样按字典序升序
			}
		}

		return result;
	}

}
