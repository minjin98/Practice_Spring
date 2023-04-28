package utils;

public class StringUtils {
	public static String ltrim(String str)
	{
		int cnt = 0;
	    while (cnt < str.length() && Character.isWhitespace(str.charAt(cnt))) {
	    	cnt++;
	    }
	    return str.substring(cnt);
	}
	 
	public static String rtrim(String str)
    {
        int cnt = str.length() - 1;
        while (cnt >= 0 && Character.isWhitespace(str.charAt(cnt))) {
        	cnt--;
        }
        return str.substring(0, cnt + 1);
    }
}
