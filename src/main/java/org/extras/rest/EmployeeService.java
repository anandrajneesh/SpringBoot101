package org.extras.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.extras.DocPdfUtil;
import org.extras.MvelRunner;
import org.extras.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Anand_Rajneesh on 2/20/2017.
 */
@Controller
@RequestMapping("/insurance")
public class EmployeeService {

    @Autowired
    private Configuration configuration;

    @Autowired
    private MvelRunner mvelRunner;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String insurance(@RequestBody String request){
        String pdfPath = null;
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(request, JsonObject.class);
            System.out.println(jsonObject);
            Map<String, Object> flatMap = flatMapJson(jsonObject);
            boolean excludeFromTarrif = mvelRunner.executeExcludeRules(flatMap);
            if(excludeFromTarrif){
                //TODO not accepted document
                pdfPath = newFilePath();
                DocPdfUtil.createPdfFromDoc(configuration.getNotAcceptedDoc(), flatMap, pdfPath);
            }else{
                //TODO accepted document calculate tarrif
                Map<String, Object> tarrifMap = mvelRunner.executeTariffRules(flatMap);
                flatMap.putAll(tarrifMap);
                pdfPath = newFilePath();
                DocPdfUtil.createPdfFromDoc(configuration.getAcceptedDoc(), flatMap, pdfPath);
            }
        }catch (Exception e){
            e.printStackTrace();
            pdfPath = null;
        }
        return pdfPath;

    }

    private String newFilePath(){
        return configuration.getOutputDir()+"/"+UUID.randomUUID()+".pdf";
    }

    private Map<String,Object> flatMapJson(JsonObject jsonObject) throws ParseException {
        Map<String, Object> flatMapped = new HashMap<String, Object>();
        for(Map.Entry<String,JsonElement> entry:  jsonObject.entrySet()){
            JsonElement element = entry.getValue();
            if(element.isJsonObject()){
                flatMapped.putAll(flatMapJson(element.getAsJsonObject()));
            }else if(element.isJsonPrimitive()){
                if(configuration.getDateParameters().contains(entry.getKey())){
                    flatMapped.put(entry.getKey()+"_date",getDate(element.getAsString()));
                }
                flatMapped.put(entry.getKey(), element.getAsString());
            }
        }
        return flatMapped;
    }

    private Date getDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        return formatter.parse(date);
    }



}
