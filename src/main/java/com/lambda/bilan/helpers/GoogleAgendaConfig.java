package com.lambda.bilan.helpers;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

public class GoogleAgendaConfig {
	/** Application name. */
    private static final String APPLICATION_NAME = "Agenda Lambda Bilan";

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required . */
    private static final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR);
    
    private static final String EMAIL_SYSTEM ="account-1@neon-opus-114922.iam.gserviceaccount.com";
    
    private static  String P12_FILE ;
  	static {
  		System.out.println("debug : static fct");
      	try {
              HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
              java.net.URL url = GoogleAgendaConfig.class.getResource("/pfile.p12");
              P12_FILE=url.getPath();
              
          } catch (Throwable t) {
              t.printStackTrace();
              System.exit(1);
          }	
  	}
   
    
    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     * @throws GeneralSecurityException 
     */
    private static GoogleCredential authorize() throws IOException, GeneralSecurityException {
    	
    	GoogleCredential credential = new GoogleCredential.Builder()
    	    .setTransport(HTTP_TRANSPORT)
    	    .setJsonFactory(JSON_FACTORY)
    	    .setServiceAccountId(EMAIL_SYSTEM)
    	    .setServiceAccountPrivateKeyFromP12File(new File(P12_FILE))
    	    .setServiceAccountScopes(SCOPES)
    	    .build();
    	return credential;
    }

    /**
     * Build and return an authorized Calendar client service.
     * @return an authorized Calendar client service
     * @throws IOException
     * @throws GeneralSecurityException 
     */
    public  static Calendar  getCalendarService()
    throws IOException, GeneralSecurityException {
    	   	 
        // Build a new authorized API client service.
        // Note: Do not confuse this class with the
        //   com.google.api.services.calendar.model.Calendar class.	
    	
        GoogleCredential credential = authorize();
        
        return new Calendar.Builder( HTTP_TRANSPORT, JSON_FACTORY, credential)
                		   .setApplicationName(APPLICATION_NAME)
                		   .build();
    }

}
