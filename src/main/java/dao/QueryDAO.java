package dao;

import dto.ChiaveValore;
import java.util.List;

/**
 * QueryDAO
 */
public interface QueryDAO {

    public ChiaveValore insert(String table, List<ChiaveValore> values);
    public List<Object> select(String table, List<ChiaveValore> values);
    public void remove(String table, List<ChiaveValore> values);

}