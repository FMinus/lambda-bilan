package com.lambda.bilan.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.api.client.util.DateTime;
import com.lambda.bilan.entities.Collaborateur;

public class DateHelper {

	private static final String DATE_FORMAT_SQL = "yyyy-MM-dd";

	public static Date dateBAPthisYear(Collaborateur collaborateur) throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SQL);
			String dateInString;
			Date dateBAP;

			String dateEmbauche =collaborateur.getDateEmbaucheCollaborateur().toString();
			int moisEmbauche = Integer.parseInt(dateEmbauche.split("-")[1]) ;
			Calendar datenow = Calendar.getInstance();
			int moisNow= datenow.get(Calendar.MONTH) + 1;
			int anneeNow=datenow.get(Calendar.YEAR);

			if(moisNow <moisEmbauche){
				dateInString=anneeNow+"-"+moisEmbauche+"-01";
				dateBAP = sdf.parse(dateInString);
			}
			else{
				dateInString=anneeNow+1+"-"+moisEmbauche+"-01";
				dateBAP = sdf.parse(dateInString);
			}
			return dateBAP;
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("generale.date.notfound"));
		}
	}



	public static Date dateSubtractYear(Date date) throws Exception{
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, -12);
			date = cal.getTime();
			return date;
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("generale.date.notfound"));
		}
	}

	public static Date dateAddtYear(Date date) throws Exception{
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 12);
			date = cal.getTime();
			return date;
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("generale.date.notfound"));
		}
	}

	public static Date dateSubtract11Month(Date date) throws Exception{
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, -11);
			date = cal.getTime();
			return date;
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("generale.date.notfound"));
		}
	}
	
	public static DateTime getCurrentDate(){
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String stringDate = df.format(new Date());
    	DateTime dt = new DateTime(stringDate);
    	return dt;
	}
}
