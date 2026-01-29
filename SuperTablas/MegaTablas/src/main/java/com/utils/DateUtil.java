package com.utils;

import java.util.Date;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {
	
	static public String dateToString(Date date) {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}
	
	static public String dateToString(Date date, String format) {
		final SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	static public Date stringToDate(String s) {
		Date date = null;
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
            date = sdf.parse(s);            
        } catch(ParseException pe) {}		
		
		return date;
	}
	
	static public Date stringToDate(String s, String format) {
		Date date = null;
		final SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		try {
            date = sdf.parse(s);            
        }
        catch(ParseException pe) {
        }
		
		return date;
	}
	
	static public java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
		return new java.sql.Date(utilDate.getTime());
	}
	
	static public java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate) {
		return new java.util.Date(sqlDate.getTime());
	}
	
	public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }

    public static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        return formateador.format(ahora);
    }

    
    public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public static synchronized java.sql.Date restarFechasDias(java.sql.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, -dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public static synchronized int diferenciasDeFechas(java.util.Date fechaInicial, java.util.Date fechaFinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }
    
}
