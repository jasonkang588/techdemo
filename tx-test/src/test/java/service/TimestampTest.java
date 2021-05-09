package service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public class TimestampTest {
	public static void main(String[] args) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println(ts.getNanos());
		
		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
		System.out.println("getnano result : " + ts2.getNanos());
		
		Timestamp ts3 = Timestamp.from(Instant.now());
		System.out.println("getnano result : " + ts3.getNanos());
		
		System.out.println(LocalDateTime.now().getNano());
		
		
	}
}
