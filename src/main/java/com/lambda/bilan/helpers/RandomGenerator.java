package com.lambda.bilan.helpers;

import java.util.Random;

public class RandomGenerator {

	private static final String CHAR_LIST = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String NUMBER_LIST = "0123456789";
	
	private static final int RANDOM_STRING_LENGTH = 6;
	private static final int RANDOM_NUMBER_LENGTH = 20;
	static Random rnd = new Random();

	public static String randomString(){
	   StringBuilder sb = new StringBuilder(RANDOM_STRING_LENGTH);
	   for( int i = 0; i < RANDOM_STRING_LENGTH; i++ ) 
	      sb.append( CHAR_LIST.charAt( rnd.nextInt(CHAR_LIST.length()) ) );
	   return sb.toString();
	}

	public static String randomNumber(){
		   StringBuilder sb = new StringBuilder(RANDOM_STRING_LENGTH);
		   for( int i = 0; i < RANDOM_STRING_LENGTH; i++ ) 
		      sb.append( NUMBER_LIST.charAt( rnd.nextInt(NUMBER_LIST.length()) ) );
		   return sb.toString();
		}
}
