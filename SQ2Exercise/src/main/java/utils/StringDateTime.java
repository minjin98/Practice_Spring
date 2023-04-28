package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringDateTime {
	public static void main(String[] args) {
		LocalDateTime date = StringToLocalDateTime("2023-4-27 10:20:30");
		System.out.printf("date : %s\n", date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.printf("date : %s\n", LocalDateTimeToString(date, "yyyy-MM-dd HH:mm:ss"));
	}

	// 형식: "yyyy-MM-dd HH:mm:ss"
	public static LocalDateTime StringToLocalDateTime(String strdate) { 
		String[] texts = strdate.split(" ");
		if(texts.length != 2) {
			return null;
		}
		
		String[] dates = texts[0].split("-"); // 날짜 분리
		String[] times = texts[1].split(":"); // 시간 분리
		if(dates.length != 3 || times.length != 3) {
			return null;
		}
		
		int y = Integer.parseInt(dates[0]);	// 년
		int M = Integer.parseInt(dates[1]); // 월
		int d = Integer.parseInt(dates[2]); // 일
		
		int H = Integer.parseInt(times[0]); // 시
		int m = Integer.parseInt(times[1]); // 분
		int s = Integer.parseInt(times[2]); // 초
		
		LocalDateTime date = LocalDateTime.of(y, M, d, H, m, s);
		
		return date;
	}
	
	public static String LocalDateTimeToString(LocalDateTime date, String pattern) {
		return date.format(DateTimeFormatter.ofPattern(pattern));
	}


	public static void main2(String[] args) {
		String text = "2023-01-15";
		
		String[] texts = text.split("-");
		
		System.out.println("count=" + texts.length);

		for(int cnt=0; cnt < texts.length; cnt++) {
			System.out.printf("[%d] [%s]\n", cnt, texts[cnt]);
		}
		
		int year = Integer.parseInt(texts[0]);
		int mon = Integer.parseInt(texts[1]);
		int day = Integer.parseInt(texts[2]);
		
		System.out.printf("%d-%d-%d\n", year, mon, day);
		
		LocalDateTime date = LocalDateTime.of(year, mon, day, 10, 15, 30);
		
		System.out.printf("date : %s\n", date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
	}

}
