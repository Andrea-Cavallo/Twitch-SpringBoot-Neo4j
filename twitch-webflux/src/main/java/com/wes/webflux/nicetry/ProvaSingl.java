package com.wes.webflux.nicetry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProvaSingl {
	private final static Logger log =
	LoggerFactory.getLogger(ProvaSingl.class.getName());

	
	public static void main(String[] args) {
	SingletonInstance singletonInstance1 = 
			SingletonInstance.getInstance();
	SingletonInstance singletonInstance2 = 
			SingletonInstance.getInstance();
	String checkSingletonInstance = checkSingletonInstance(singletonInstance1, singletonInstance2);
	System.out.println(checkSingletonInstance + ", allora avevi ragione");
}


	private static String checkSingletonInstance(SingletonInstance singletonInstance1, SingletonInstance singletonInstance2) {
	if ( singletonInstance1.equals(singletonInstance2)) {
				log.info("sono le stesse istanze");
				return "sono le stesse istanze";
	}
	else 
			log.info(	"non sono le stesse istanze");
	return "non sono le stesse istanze" ;
}

}
