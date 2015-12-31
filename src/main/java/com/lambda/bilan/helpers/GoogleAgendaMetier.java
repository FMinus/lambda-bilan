package com.lambda.bilan.helpers;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

@Service
public class GoogleAgendaMetier {

	private com.google.api.services.calendar.Calendar service;

	@PostConstruct
	public void init(){
		System.out.println("debug : init agendaDao");
		try {
			service=GoogleAgendaConfig.getCalendarService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//return true si le systeme possède le droit de creer des evenements sur le celendrier passé en parametre 
	//et false sinon
	public Boolean checkAuthorityToWrite(String CalendarID) throws IOException{
		for(CalendarListEntry entry : service.calendarList().list().execute().getItems())
			if( CalendarID.equals(entry.getId()) && entry.getAccessRole().equals("writer"))
				return true;

		return false;
	}

	//Creer un evenement sur le calendrier passé en parametre
	public Event createAllDayEvent(String Titre,String Description,DateTime date,String CalendarID)
			throws IOException{
		System.out.println("Event init");
		Event event = new Event()
				.setSummary(Titre)
				.setDescription(Description);

		EventDateTime start = new EventDateTime()
				.setDate(date);

		event.setStart(start)
		.setEnd(start);

		return service.events().insert(CalendarID, event).execute();  	
	}



}