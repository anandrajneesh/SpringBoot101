package org.extras.rest;

import org.extras.data.Access;
import org.extras.data.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anand_Rajneesh on 2/20/2017.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeService {

    @Autowired
    Access access;
    @Autowired
    Word word;

    @GetMapping
    @ResponseBody
    String home() {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("company", "foobar");
            params = access.search(params);
            System.out.println(params);
            word.write(Collections.<String, Object>emptyMap());
            return "done";
        }catch (Exception e){
            return "error";
        }
    }

}
