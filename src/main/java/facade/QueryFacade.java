package facade;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * QueryFacade
 */
public interface QueryFacade {

	public void					insert		(Object   o);
	public List<Object>			selectList	(Object   o);
	public Map<String,Object>[]	selectMap	(Object   o);
	public void 				remove		(Object   o);
	public void 				update		(Object   o, String primary);

	public Entry<String,String> getResp();
    
}