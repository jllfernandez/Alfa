package com.luke.bie.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Util {

	public static java.util.Date stringToDate(String date, String format) {
		try {
			SimpleDateFormat formatter = (format == null) ? new SimpleDateFormat(Constants.DATE_DDMMYYYY_HHMMSS)
					: new SimpleDateFormat(format);
			// formatter.setLenient(false);
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Double.
	 * 
	 * @param param String
	 * @return Double
	 */
	public static Double toDouble(String param) {
		Double result = null;
		if (null != param && !param.equals("")) {
			String car = enteroAString(160); //
			String cad = param.replace(" ", "").replace("€", "").replace(".", "").replace(",", ".").replace(car, "");
			try {
				result = Double.valueOf(cad.trim());
			} catch (Exception e) {

			}
		}
		return result;
	}

	/**
	 * doubleToString.
	 * 
	 * @param d Double
	 * @return String
	 */
	private String doubleToString(Double d) {
		String result = null;
		if (null != d) {
			result = NumberFormat.getCurrencyInstance(new Locale("es", "ES")).format(d);
		}
		return result;
	}

	/**
	 * numberDoubleToString.
	 * 
	 * @param d Double
	 * @return String
	 */
	private String numberDoubleToString(Double d) {
		String result = null;
		if (null != d) {
			result = NumberFormat.getInstance(new Locale("es", "ES")).format(d);
		}
		return result;
	}

	/**
	 * enteroAString.
	 * 
	 * @param numero int
	 * @return String
	 */
	public static String enteroAString(int numero) {
		return "" + (char) numero;
	}

	/**
	 * isNumeric.
	 * 
	 * @param s String
	 * @return boolean
	 */
	private static boolean isNumeric(String s) {
		return s != null && s.matches("[0-9]+");
	}

	/**
	 * numberDoubleToPorcentaje.
	 * 
	 * @param d Double
	 * @return String
	 */
	public static String numberDoubleToPorcentaje(Double d) {
		String result = null;
		if (null != d) {
			result = new DecimalFormat("00.0").format(d) + "%";
		}
		return result;
	}

	/**
	 * stringExcelToDate.
	 * 
	 * @param s String
	 * @return LocalDate
	 */
	public static LocalDate stringExcelToDate(String date) {
		return toLocalDate(formatMonth(date), "MM/dd/yy");
	}

	/**
	 * toLocalDate.
	 * 
	 * @param s      String
	 * @param format String
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate(String s, String format) {
		LocalDate datetime = null;
		if (null != s && !"".equals(s)) {
			DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
					.append(DateTimeFormatter.ofPattern(format)).toFormatter();
			try {
				datetime = LocalDate.parse(s, f);
				return datetime;
			} catch (Exception e) {
			}
		}

		return datetime;
	}

	/**
	 * toLocalDate.
	 * 
	 * @param s String
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate(String s) {

		return toLocalDate(s, "yyyy-MM-dd");
		/*
		 * DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
		 * .append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter(); try {
		 * LocalDate datetime = LocalDate.parse(s, f); return datetime; } catch
		 * (Exception e) { }
		 * 
		 * return null;
		 */
	}

	/**
	 * formatMonth.
	 * 
	 * @param s String
	 * @return String
	 */
	private static String formatMonth(String s) {
		String result = s;
		String month = s.split("/")[0];

		if (month.length() == 1) {
			result = "0".concat(s);
		}

		return result;
	}
}
