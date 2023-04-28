// 날짜 라이브러리
package utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringDate {
	public static void main(String[] args)
	{
		LocalDateTime date = StringToLocalDateTime("2023-4-27 10:20:30");// StringToLocalDateTime을 호출해서 값 대입(3)
		System.out.println("date : " +date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.println("date : " +LocalDateTimeToString(date,"yyyy-MM-dd HH:mm:ss")); // LocalDateTimeToString()으로 출력(1)
	}
	
	public static LocalDateTime StringToLocalDateTime(String strdate) { // (4)
		String[]texts = strdate.split(" ");
		if(texts.length != 2) {
			return null;
		}
		String[]dates = texts[0].split("-");
		String[]times = texts[1].split(":");
		
		int year = Integer.parseInt(dates[0]);
		int mon = Integer.parseInt(dates[1]);
		int day = Integer.parseInt(dates[2]);
		int hour = Integer.parseInt(times[0]);
		int miniute = Integer.parseInt(times[1]);
		int second = Integer.parseInt(times[2]);
		
		LocalDateTime date = LocalDateTime.of(year, mon, day, hour, miniute, second);
		
		return date;
	}
	
	public static String LocalDateTimeToString(LocalDateTime date, String pattern) {// 파라미터 중에 LocalDateTime date가 있으므로 
		return date.format(DateTimeFormatter.ofPattern(pattern));					// LocalDateTime date = StringToLocalDateTime("2023-4-27 10:20:30"); 호출(2)
	}
}