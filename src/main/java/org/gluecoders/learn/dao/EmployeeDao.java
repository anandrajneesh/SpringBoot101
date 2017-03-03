package org.gluecoders.learn.dao;

import org.gluecoders.learn.models.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Anand_Rajneesh on 2/20/2017.
 */
@Service
@Transactional
public class EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Employee save(Employee o){
        sessionFactory.getCurrentSession().save(o);
        return o;
    }
}
