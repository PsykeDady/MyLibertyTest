package facade;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * QueryFacade
 */
public interface QueryFacade {

	public void			insert(Object o);
	public List<Object>	select(Object o);
	public void 		remove(Object o);
	public void 		update(Object o);

	public Entry<String,String> getResp();
    
}