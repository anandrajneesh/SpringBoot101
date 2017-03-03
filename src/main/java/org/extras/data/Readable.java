package org.extras.data;

import org.extras.exceptions.AccessDbException;
import org.extras.exceptions.ReadableException;

import java.util.Map;

/**
 * Created by Anand_Rajneesh on 3/3/2017.
 */
public interface Readable {

    Map<String, Object> search(Map<String, Object> parameters) throws ReadableException;

}
