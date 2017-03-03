package org.extras.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Anand_Rajneesh on 3/3/2017.
 */
@Component
@ConfigurationProperties(prefix = "word")
public class Word extends Data implements Writeable<Object> {

    public Word(){}


    public Object write(Map<String, Object> parameters) {
        return resultColumns;
    }
}
