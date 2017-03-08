package org.extras;

import org.extras.config.Configuration;
import org.mvel2.MVEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by Anand_Rajneesh on 3/8/2017.
 */
@Component
public class MvelRunner {

    @Autowired
    Configuration configuration;

    public boolean executeExcludeRules(Map<String, Object> parameters){

        Serializable excludedRules = configuration.getCompiledExcludeRules();
        parameters.put("custom", new MvelHelper());
        String response = (String) MVEL.executeExpression(excludedRules,parameters);
        return !response.equals("accepted");
    }

    public Map<String, Object> executeTariffRules(Map<String, Object> parameters){
        Serializable tarrifRules = configuration.getCompiledTarrifRules();
        parameters.put("custom", new MvelHelper());
        Map<String, Object> tarrifMap = (Map<String, Object>) MVEL.executeExpression(tarrifRules, parameters);
        return tarrifMap;
    }

}
