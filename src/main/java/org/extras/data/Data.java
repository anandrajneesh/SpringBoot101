package org.extras.data;

import java.util.List;
import java.util.Map;

/**
 * Created by Anand_Rajneesh on 3/3/2017.
 */
public abstract class Data {
    //Key is the result of internal search column, value is the external data key
    protected Map<String, String> searchColumns;
    protected List<String> resultColumns;
    protected Map<String, String> configuration;

    public Data() {
    }


    public List<String> getResultColumns() {
        return resultColumns;
    }

    public void setResultColumns(List<String> resultColumns) {
        this.resultColumns = resultColumns;
    }

    public void setConfiguration(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    public Map<String, String> getSearchColumns() {
        return searchColumns;
    }

    public void setSearchColumns(Map<String, String> searchColumns) {
        this.searchColumns = searchColumns;
    }

    public Map<String, String> getConfiguration() {
        return configuration;
    }

}
