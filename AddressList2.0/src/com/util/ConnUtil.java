package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
    public static Connection getConn(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//注册驱动
            String Url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";//接入数据库路径+当前时区
            String User="root";//mysql用户名
            String Pwd="248651379";//mysql密码
            conn= DriverManager.getConnection(Url,User,Pwd);//从mysql中得到对应数据库
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
