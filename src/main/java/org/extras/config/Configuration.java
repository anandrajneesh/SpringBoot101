package org.extras.config;

import org.extras.data.Access;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

/**
 * Created by Anand_Rajneesh on 3/3/2017.
 */
public class Configuration {

   /* public static void main(String[] args) {
        Connection conn = null;
        try{
            conn= DriverManager.getConnection("jdbc:ucanaccess://D:/extraz/Database11.accdb");
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from [Contacts] where company='foobar'");
            while(rs.next()){
                System.out.println(rs.getString(5));
            }



        }catch(Exception e){
            e.printStackTrace();
            try {
                if(conn!=null) {
                    conn.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
*/


}
