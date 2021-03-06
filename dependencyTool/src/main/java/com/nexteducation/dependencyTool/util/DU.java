package com.nexteducation.dependencyTool.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DU {
	private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();

	public static Timestamp now() {
		final Calendar zoneCalendar = Calendar.getInstance(DEFAULT_TIME_ZONE);
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, zoneCalendar.get(Calendar.DATE));
		calendar.set(Calendar.MONTH, zoneCalendar.get(Calendar.MONTH));
		calendar.set(Calendar.YEAR, zoneCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.HOUR, zoneCalendar.get(Calendar.HOUR));
		calendar.set(Calendar.HOUR_OF_DAY, zoneCalendar.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.AM_PM, zoneCalendar.get(Calendar.AM_PM));
		calendar.set(Calendar.MINUTE, zoneCalendar.get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, zoneCalendar.get(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, zoneCalendar.get(Calendar.MILLISECOND));
		return new Timestamp(calendar.getTime().getTime());
	}

	public static Timestamp addWorkingDays(final Timestamp date, final int days) {
		return addWorkingDays(date, days, DEFAULT_TIME_ZONE);
	}

	public static Timestamp addWorkingDays(final Timestamp date, final int days, final TimeZone zone) {
		if (days == 0) {
			return date;
		}
		final Calendar oldCal = new GregorianCalendar(zone);
		oldCal.setTime(date);
		final int oldWeekDay = oldCal.get(Calendar.DAY_OF_WEEK);
		final Timestamp newDay = addDays(date, days, zone);
		final Calendar newCal = new GregorianCalendar(zone);
		newCal.setTime(newDay);
		final int newWeekDay = newCal.get(Calendar.DAY_OF_WEEK);
		if (newWeekDay <= oldWeekDay) {
			return addDays(newDay, 2 * (days / 7), zone);
		} else if (newWeekDay == 7) {
			return addDays(newDay, 1 + 2 * (days / 7 - 1), zone);
		}
		return newDay;
	}

	public static Timestamp addDaysAndHours(final Timestamp date, final int days, final int hours) {
		return addDaysAndHours(date, days, hours, DEFAULT_TIME_ZONE);
	}

	public static Timestamp addDaysAndHours(final Timestamp date, final int days, final int hours,
			final TimeZone zone) {
		final Calendar newDate = new GregorianCalendar(zone);
		newDate.setTime(date);
		newDate.add(Calendar.DATE, days);
		newDate.add(Calendar.HOUR, hours);
		return new Timestamp(newDate.getTime().getTime());
	}

	public static Timestamp addDaysHoursAndMinutes(final Timestamp date, final int days, final int hours,
			final int minutes) {
		return addDaysHoursAndMinutes(date, days, hours, minutes, DEFAULT_TIME_ZONE);
	}

	public static Timestamp addDaysHoursAndMinutes(final Timestamp date, final int days, final int hours,
			final int minutes, final TimeZone zone) {
		final Calendar newDate = new GregorianCalendar(zone);
		newDate.setTime(date);
		newDate.add(Calendar.DATE, days);
		newDate.add(Calendar.HOUR, hours);
		newDate.add(Calendar.MINUTE, minutes);
		return new Timestamp(newDate.getTime().getTime());
	}

	public static Date addDaysHoursAndMinutes(final Date date, final int days, final int hours, final int minutes) {
		final Calendar newDate = new GregorianCalendar(DEFAULT_TIME_ZONE);
		newDate.setTime(date);
		newDate.add(Calendar.DATE, days);
		newDate.add(Calendar.HOUR, hours);
		newDate.add(Calendar.MINUTE, minutes);
		return newDate.getTime();
	}

	public static Timestamp addDays(final Timestamp date, final int days) {
		return addDays(date, days, DEFAULT_TIME_ZONE);
	}

	public static Timestamp addDays(final Timestamp date, final int days, final TimeZone zone) {
		final Calendar tomorrow = new GregorianCalendar(zone);
		tomorrow.setTime(date);
		tomorrow.add(Calendar.DATE, days);
		return new Timestamp(tomorrow.getTime().getTime());
	}

	public static int getDifferenceDays(final Timestamp day1, final Timestamp day2) {
		if (day1 == null || day2 == null) {
			return 0;
		}
		int diff = 0;
		diff = (int) ((day1.getTime() - day2.getTime()) / (1000 * 60 * 60 * 24));
		return diff;
	}

	public static int getDifferenceDays(final Date day1, final Date day2) {
		if (day1 == null || day2 == null) {
			return 0;
		}
		double diff = 0;
		diff = (day1.getTime() - day2.getTime()) / (1000 * 60 * 60 * 24.0);
		return (int) Math.ceil(diff);
	}

	public static int getDifferenceSeconds(final Date day1, final Date day2) {
		if (day1 == null || day2 == null) {
			return 0;
		}
		int diff = 0;
		diff = (int) ((day1.getTime() - day2.getTime()) / 1000);
		return diff;
	}

	public static boolean isWeekDay(final Timestamp day, final int weekDay) {
		return isWeekDay(day, weekDay, DEFAULT_TIME_ZONE);
	}

	public static boolean isWeekDay(final Timestamp day, final int weekDay, final TimeZone zone) {
		final Calendar today = new GregorianCalendar(zone);
		today.setTime(day);
		return today.get(Calendar.DAY_OF_WEEK) == weekDay;
	}

	public static Timestamp getCurrentMonth() {
		final Calendar zoneCalendar = Calendar.getInstance(DEFAULT_TIME_ZONE);
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, zoneCalendar.get(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, zoneCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.YEAR, zoneCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTime().getTime());
	}

	public static Timestamp getNextMonth() {
		final Calendar zoneCalendar = Calendar.getInstance(DEFAULT_TIME_ZONE);
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, zoneCalendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, zoneCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.YEAR, zoneCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTime().getTime());
	}

	// This gives date in format which client wants
	public static String format(final Date date) {
		return format(date, "dd/MM/yyyy-HH:mm:ss");
	}

	public static String format(final Date date, final String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	// This gives date in format which client wants
	public static Date parse(final String dateString) throws ParseException {
		return parse(dateString, "dd/MM/yyyy-HH:mm:ss");
	}

	public static Date parse(final String dateString, final String format) throws ParseException {
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(format);
		return sdf.parse(dateString);
	}

	public static Date parse(final String dateString, final String format, final boolean isLenient)
			throws ParseException {
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(format);
		sdf.setLenient(isLenient);
		return sdf.parse(dateString);
	}

	public static Date parse(final long timeInSecs) {
		final Date date = new Date();
		date.setTime(timeInSecs * 1000);
		return date;
	}

	public static Date addDays(final Date date, final int days, final boolean tillLastMinOfDay) {
		final Calendar tomorrow = new GregorianCalendar(DEFAULT_TIME_ZONE);
		tomorrow.setTime(date);
		tomorrow.add(Calendar.DATE, days);
		if (tillLastMinOfDay) {
			tomorrow.set(Calendar.AM_PM, 0);
			tomorrow.set(Calendar.HOUR_OF_DAY, 23);
			tomorrow.set(Calendar.MINUTE, 59);
			tomorrow.set(Calendar.SECOND, 59);
		}
		return tomorrow.getTime();
	}

	public static Date getStartDateOfWeek(final Date date) {
		final Calendar today = new GregorianCalendar();
		today.setTime(date);
		return DU.addDays(date, -1 * (today.get(Calendar.DAY_OF_WEEK) - 1), true);
	}

	public static int getYear(final Date date) {
		final Calendar today = new GregorianCalendar(DEFAULT_TIME_ZONE);
		today.setTime(date);
		return today.get(Calendar.YEAR);
	}

	public static Date getStartDateOfMonth(Date date, final boolean fromFirstMinOfDay) {
		final Calendar cal = new GregorianCalendar(DEFAULT_TIME_ZONE);
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		if (fromFirstMinOfDay) {
			cal.set(Calendar.AM_PM, 0);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
		}
		date = cal.getTime();
		return date;
	}

	public static String getFinancialYear(final Date date) throws ParseException {
		final Calendar inputDate = Calendar.getInstance();
		inputDate.setTime(date);
		int year = inputDate.get(Calendar.YEAR);

		if (inputDate.get(Calendar.MONTH) > Calendar.MARCH) {
			year = inputDate.get(Calendar.YEAR);
			return year + "-" + (year + 1);
		} else {
			year = inputDate.get(Calendar.YEAR);
			return year - 1 + "-" + year;
		}

	}

	public static String getFyForPricing(final String date, final boolean flag) throws ParseException {
		final Date date2 = DU.parse(date, "MMMM-yy");
		final String fy = getFinancialYear(date2);
		if (flag) {
			return "FY" + fy.substring(2, 4) + fy.substring(7, 9);
		} else {
			return "FY" + fy.substring(2, 4) + "-" + fy.substring(7, 9);
		}
	}

	public static int getFinancialYear(final Date date, final boolean shortForm) {
		final Calendar inputDate = Calendar.getInstance();
		inputDate.setTime(date);
		int year = inputDate.get(Calendar.YEAR);
		if (inputDate.get(Calendar.MONTH) > Calendar.MARCH) {
			year = inputDate.get(Calendar.YEAR);
			return year + 1;
		} else {
			return year;
		}
	}

	public static Date getEndDateOfMonth(Date date, final boolean tillLastMinOfDay) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.add(Calendar.DATE, -1);
		if (tillLastMinOfDay) {
			cal.set(Calendar.AM_PM, 0);
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
		}
		date = cal.getTime();
		return date;
	}

	public static Date addMonths(Date date, final int months) {
		final Calendar cal = new GregorianCalendar(DEFAULT_TIME_ZONE);
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + months);
		// cal.add(Calendar.DATE, -1);
		date = cal.getTime();
		return date;
	}

	public static Date addMonthsUsingAdd(Date date, final int months) {
		final Calendar cal = new GregorianCalendar(DEFAULT_TIME_ZONE);
		final Calendar cal2 = new GregorianCalendar(DEFAULT_TIME_ZONE);
		cal.setTime(date);
		cal2.setTime(date);
		cal2.add(Calendar.MONTH, months);
		date = cal2.getTime();
		return date;
	}

	public static Date addYears(Date date, final int years) {
		final Calendar cal = new GregorianCalendar(DEFAULT_TIME_ZONE);
		cal.setTime(date);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + years);
		date = cal.getTime();
		return date;
	}

	public static String logCurrentDate() {
		return DU.format(new Date(), "d MMM HH:mm:ss.SSS");
	}

	public static String dbDateFormate(final String dateStrIn, final String formate) {
		final SimpleDateFormat sdfTarget = new SimpleDateFormat();
		String dateStrOut = "";
		try {
			sdfTarget.applyPattern("yyyy-MM-dd");
			dateStrOut = sdfTarget.format(DU.parse(dateStrIn, formate));
		} catch (final Exception e) {
			dateStrOut = DU.format(new Date(), "yyyy-MM-dd");
			ServiceLogger.error("In Exception, startDate: " + dateStrOut);
		}
		return dateStrOut;
	}

	public static Date getDate(Date date, final boolean isEod) {

		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		if (isEod) {
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			date = cal.getTime();
		} else {

			// if the date selected is today and EOD is false set time as now
			cal.set(Calendar.HOUR_OF_DAY, 00);
			cal.set(Calendar.MINUTE, 00);
			cal.set(Calendar.SECOND, 00);
			date = cal.getTime();
		}

		return date;
	}

	public static java.sql.Date getSQLDate(final Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Date getSQLDate(final String date, final String pattern) throws ParseException {
		return new java.sql.Date(parse(date, pattern).getTime());
	}

	static String[] dateFormats = { "dd-MMM-yyyy", "MMM-dd-yyyy", "yyyy-MMM-dd", "MM-dd-yyyy", "MMM-yyyy" };

	public static Date toDate(final Object o) {
		if (o instanceof Date) {
			return (Date) o;
		}

		String s = o.toString();
		s = s.replace(' ', '-').replace('/', '-');
		for (final String df : dateFormats) {
			try {
				return new SimpleDateFormat(df).parse(s);
			} catch (final ParseException e) {
				// TODO Auto-generated catch block
				// try next format
				ServiceLogger.error("parseException ", e);
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static double workingDayDiff(final Date date1, final Date date2) {
		final double dayDiff = differenceInDays(date1, date2);
		int nextSundayDiff;
		if (date1.getDay() + 1 == 1) {
			nextSundayDiff = 0;
		} else {
			nextSundayDiff = 7 - (date1.getDay() + 1 - 1);
		}
		final Date nextSunday = addDays(date1, nextSundayDiff, true);
		final int newDayDiff = getDifferenceDays(date2, nextSunday);
		int numSundays;
		if (newDayDiff <= 0) {
			numSundays = 0;
		} else {
			numSundays = (int) Math.floor(newDayDiff / 7) + 1;
		}
		Date id = null;
		Date rd = null;
		Date gj = null;
		Date eid = null;
		Date erd = null;
		Date egj = null;
		try {
			id = DU.parse("15-08-" + getYear(date1), "dd-MM-yyyy");
			rd = DU.parse("26-01-" + getYear(date1), "dd-MM-yyyy");
			gj = DU.parse("02-10-" + getYear(date1), "dd-MM-yyyy");
			if (getYear(date1) != getYear(date2)) {
				eid = DU.parse("15-08-" + getYear(date2), "dd-MM-yyyy");
				erd = DU.parse("26-01-" + getYear(date2), "dd-MM-yyyy");
				egj = DU.parse("02-10-" + getYear(date2), "dd-MM-yyyy");
			}
		} catch (final ParseException e) {
			ServiceLogger.error("Parse Exception ", e);
		}
		if (isBetween(id, date1, date2) || isBetween(rd, date1, date2) || isBetween(gj, date1, date2) || eid != null
				&& (isBetween(eid, date1, date2) || isBetween(erd, date1, date2) || isBetween(egj, date1, date2))) {
			numSundays += 1;
		}
		return dayDiff - numSundays;
	}

	public static Date toDay() {
		return toDate(format(new Date(), "dd-MMM-yyyy"));
	}

	public static double differenceInMonths(final Date date1, final Date date2) {
		return differenceInYears(date1, date2) * 12;
	}

	public static double differenceInMonthsBasedOnMonthField(final Date date1, final Date date2) {
		final Calendar cal = new GregorianCalendar();
		cal.setTime(date1);
		final Calendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);
		final int diffYear = cal2.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
		final int diffMonth = diffYear * 12 + cal2.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
		return diffMonth;
	}

	public static double differenceInYears(final Date date1, final Date date2) {
		final double days = differenceInDays(date1, date2);
		return days / 365.2425;
	}

	public static double differenceInDays(final Date date1, final Date date2) {
		return differenceInHours(date1, date2) / 24.0;
	}

	public static double differenceInDaysFromToday(final Date date2) {
		return differenceInHours(new Date(), date2) / 24.0;
	}

	public static double differenceInHours(final Date date1, final Date date2) {
		return differenceInMinutes(date1, date2) / 60.0;
	}

	public static double differenceInMinutes(final Date date1, final Date date2) {
		return differenceInSeconds(date1, date2) / 60.0;
	}

	public static double differenceInSeconds(final Date date1, final Date date2) {
		return differenceInMilliseconds(date1, date2) / 1000.0;
	}

	private static double differenceInMilliseconds(final Date date1, final Date date2) {
		return Math.abs(getTimeInMilliseconds(date1) - getTimeInMilliseconds(date2));
	}

	private static long getTimeInMilliseconds(final Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getTimeInMillis() + cal.getTimeZone().getOffset(cal.getTimeInMillis());
	}

	public static void main(final String[] args) throws ParseException {
		/*
		 * CustomDate customDate1 = new CustomDate(); customDate1.setDate(1);
		 * customDate1.setMonth(12); customDate1.setYear(2008); CustomDate
		 * customDate2 = new CustomDate(); customDate2.setDate(2);
		 * customDate2.setMonth(12); customDate2.setYear(2008);
		 * System.out.println(getStartDateOfMonth(customDate1.getUtilDate(),true
		 * )); System.out.println(getEndDateOfMonth(new Date(),true));
		 * System.out.println("Difference in seconds: " +
		 * getDifferenceSeconds(customDate2.getUtilDate(),
		 * customDate1.getUtilDate()));
		 */
		// System.out.println(new Date() + ":" + DU.format(new Date(),
		// "yyyy-MM-dd HH:mm:ss"));
		// final Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.DAY_OF_MONTH, 29);
		// cal.set(Calendar.MONTH, Calendar.DECEMBER);
		// cal.set(Calendar.YEAR, 2010);
		// cal.set(Calendar.HOUR_OF_DAY, 13);
		// System.out.println(DU.getDifferenceDays(cal.getTime(),
		// ActivationUtil.getActivationRefDate()));
		final Date date = DU.parse("2014-08-31", "yyyy-MM-dd");
		final Date date2 = DU.parse("2015-04-06", "yyyy-MM-dd");
		final Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		final Calendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);
		final int diffYear = cal2.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
		final int diffMonth = diffYear * 12 + cal2.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
		System.out.println(date + "\n" + date2);
		System.out.println(diffMonth);
	}

	public static boolean isBetween(final Date date, final Date fromDate, final Date toDate) {
		return date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0;
	}

	/**
	 * COALESCE implementation
	 */
	public static Object coalesce(final Object... objects) {
		for (final Object o : objects) {
			if (o != null) {
				return o;
			}
		}
		return null;
	}

	public static String getDurationFormat(final String timeinseconds) {
		String result = "00";
		long hour = 0;
		long minute = 0;
		long sec = 0;
		Long input = Long.parseLong(timeinseconds);

		hour = input / 3600;
		input = input - hour * 3600;

		minute = input / 60;
		input = input - minute * 60;

		sec = input;
		result = (hour > 9 ? hour : "0" + hour) + ":" + (minute > 9 ? minute : "0" + minute) + ":"
				+ (sec > 9 ? sec : "0" + sec);
		return result;
	}

	public static Date makeItFirstDayOfMonth(Date newDate) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(newDate);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		newDate = cal.getTime();
		return newDate;
	}

	/**
	 *
	 * @param date
	 * @param format.
	 *            'dd' must be the first two characters
	 * @return
	 */
	public static String formatWithThRd(final Date date, final String format) {
		if (date == null) {
			return null;
		}
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		final int dateInt = c.get(Calendar.DATE);
		if (!format.substring(0, 2).equalsIgnoreCase("dd")) {
			throw new IllegalArgumentException("Invalid Date format. 'dd' must be the first two characters");
		}

		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(format.substring(0, 2));
		String dateStr = sdf.format(date);
		dateStr += DU.getDayOfMonthSuffix(dateInt);
		sdf = new SimpleDateFormat(format.substring(2));
		dateStr += sdf.format(date);
		return dateStr;
	}

	public static String getDayOfMonthSuffix(final int n) {
		if (n < 1 || n > 31) {
			throw new IllegalArgumentException("illegal day of month: " + n);
		}
		if (n >= 11 && n <= 13) {
			return "th";
		}
		switch (n % 10) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		}
	}

	public final static double AVERAGE_MILLIS_PER_MONTH = 365.24 * 24 * 60 * 60 * 1000 / 12;

	public static int getDifferenceMonths(final Date d1, final Date d2) {
		return new Double((d2.getTime() - d1.getTime()) / AVERAGE_MILLIS_PER_MONTH + 1).intValue();
	}

	@SuppressWarnings("deprecation")
	public static int getDurationInMonths(final Date d1, final Date d2) {
		return d2.getYear() * 12 + d2.getMonth() - (d1.getYear() * 12 + d1.getMonth());
	}

	public static Date getEndOfFinancialYear(final Date financialStartDate) {
		return DU.addDays(DU.addMonths(financialStartDate, 12), -1, true);
	}

	public static int getNumberOfDaysInMonth(final Date date) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(DU.getStartDateOfMonth(date, true));
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int getDaysInFinancialYear(final String financialYear) {
		final String[] years = financialYear.split("-");
		Date date = null, date1 = null;
		try {
			date = DU.parse(years[0] + "-04-01", "yyyy-MM-dd");
			date1 = DU.parse(years[1] + "-03-31", "yyyy-MM-dd");
		} catch (final ParseException e) {
			ServiceLogger.error("parseException ", e);
		}
		return DU.getDifferenceDays(date1, date) + 1;
	}

	public static boolean compareOnlyDates(final Date date, final Date date2) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		final Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		calendar2.set(Calendar.HOUR_OF_DAY, 0);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		calendar2.set(Calendar.MILLISECOND, 0);

		return calendar.compareTo(calendar2) == 0;
	}

	public static Date getEndOfDay(final Date date) {
		try {
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTime();
		} catch (final Exception e) {
			// returning original date in case of any exception.
			return date;
		}
	}

	public static Date getStartOfDay(final Date date) {
		try {
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		} catch (final Exception e) {
			// returning original date in case of any exception.
			return date;
		}
	}

}
