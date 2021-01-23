package models.infosessions;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public enum ResponseState{
	KO,
	WARNING,
	OK
	;

	public static String toStringReponse(ResponseState state, String msg){
		return state.name()+" : "+msg;
	}

	public static Entry<String,String> toEntryReponse(ResponseState state, String msg){
		return new SimpleEntry<String,String>(state.name(),msg);
	}
}