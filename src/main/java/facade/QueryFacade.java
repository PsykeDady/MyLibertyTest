package facade;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * QueryFacade
 */
public interface QueryFacade {

	public Entry<String,String> insert(String table, Map<String,String> values);
	public List<Object> select(String table, Map<String,String> values);
	public void remove        (String table, Map<String,String> values);
    
}