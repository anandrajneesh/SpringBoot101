package org.extras.data;

import java.util.Map;

/**
 * Created by Anand_Rajneesh on 3/3/2017.
 */
public interface Writeable<T> {

    T write(Map<String, Object> parameters);
}
