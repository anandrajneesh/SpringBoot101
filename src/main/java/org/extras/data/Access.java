package org.extras.data;

import org.extras.exceptions.AccessDbException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anand_Rajneesh on 3/3/2017.
 */
@Component
@ConfigurationProperties(prefix = "access")
public class Access extends Data implements Readable {

    private ConnectionPool pool;

    public Access() {
    }

    @PostConstruct
    public void buildPool() {
        if (configuration != null) {
            pool = new ConnectionPool(configuration);
        } else {
            throw new IllegalArgumentException("Properties have not been loaded yet");
        }
    }

    @PreDestroy
    public void destroy() {
        pool.close();
    }

    public Map<String, Object> search(Map<String, Object> parameters) throws AccessDbException {
        Connection connection = null;
        try {
            connection = pool.get();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(buildQuery(parameters));
            Map<String, Object> result = getResult(rs);
            rs.close();
            return result;
        } catch (Exception e) {
            throw new AccessDbException("Problem occured during accessing accessdb",e);
        }finally{
            if(connection != null){
                pool.release(connection);
            }
        }
    }

    private String buildQuery(Map<String, Object> parameters) {
        StringBuilder stb = new StringBuilder("select * from [");
        stb.append(configuration.get("table"))
                .append("] where ");
        boolean first = true;
        for (Map.Entry<String, String> entry : searchColumns.entrySet()) {
            if (first) {
                stb.append(entry.getKey()).append("=").append("'").append(parameters.get(entry.getValue())).append("' ");
            } else {
                stb.append("and ").append(entry.getKey()).append("=").append("'").append(parameters.get(entry.getValue())).append("' ");
            }
            first = false;
        }
        return stb.toString();
    }

    private Map<String, Object> getResult(ResultSet resultSet) throws SQLException {
        Map<String, Object> result = new HashMap<String, Object>();
        if (resultSet != null) {
            try {
                resultSet.next();
                for (String resultColumn : resultColumns) {
                    int index = resultSet.findColumn(resultColumn);
                    result.put(resultColumn, resultSet.getObject(index));
                }
            } catch (SQLException e) {
                throw e;
            }
        }
        return result;
    }

}
