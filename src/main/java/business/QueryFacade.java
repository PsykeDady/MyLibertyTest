package business;

import dto.ChiaveValore;
import java.util.List;

/**
 * QueryFacade
 */
public interface QueryFacade {

    public ChiaveValore insert(String table, List<ChiaveValore> values);
    public List<Object> select(String table, List<ChiaveValore> values);
    public void remove(String table, List<ChiaveValore> values);
    
}