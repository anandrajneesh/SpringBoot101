package org.gluecoders.learn.rest;

import org.gluecoders.learn.dao.EmployeeDao;
import org.gluecoders.learn.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Anand_Rajneesh on 2/20/2017.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/employee")
public class EmployeeService {

    @Autowired
    private EmployeeDao dao;

    @PostMapping
    @ResponseBody
    String home() {
        Employee o = new Employee();
        o.setFirstName("Anand");
        o.setLastName("Rajneesh");
        dao.save(o);
        return String.valueOf(o.getId());
    }

}
