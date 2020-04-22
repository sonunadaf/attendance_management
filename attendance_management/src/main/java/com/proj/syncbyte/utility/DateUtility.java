package com.proj.syncbyte.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	public static Date getDate(Date date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDate = dateFormat.format(date);
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat2.parse(sDate);
	}

	public static boolean compareTwoDate(Date firstDate, Date secondDate) {
		if (dateToString(firstDate).equals(dateToString(secondDate))) {
			return true;
		}
		return false;

	}

	public static String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

}
