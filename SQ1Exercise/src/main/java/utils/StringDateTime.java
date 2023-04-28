// 날짜 라이브러리
package utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringDateTime {
	public static void main(String[] args)
	{
		LocalDateTime date = StringToLocalDateTime("2023-4-27");
		System.out.printf("date : " +date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss\n")));
		System.out.printf("date : " +LocalDateTimeToString(date,"yyyy-MM-dd HH:mm:ss\n"));
	}
	
	public static LocalDateTime StringToLocalDateTime(String strdate) { // "yyyy-MM-dd"
		String[]texts = strdate.split("-");
		if(texts.length != 3) {
			return null;
		}
		int year = Integer.parseInt(texts[0]);
		int mon = Integer.parseInt(texts[1]);
		int day = Integer.parseInt(texts[2]);
		
		LocalDateTime date = LocalDateTime.of(year, mon, day, 0, 0, 0);
		
		return date;
	}
	
	public static String LocalDateTimeToString(LocalDateTime date, String pattern) {
		return date.format(DateTimeFormatter.ofPattern(pattern));
	}
}