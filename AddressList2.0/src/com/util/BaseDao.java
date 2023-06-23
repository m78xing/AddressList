package com.util;

import com.util.ConnUtil;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "BaseDao", value = "/BaseDao")
public class BaseDao extends HttpServlet {
    public int modifyData(String sql,Object [] obj) throws SQLException { //修改参数
        Connection conn= ConnUtil.getConn();
        int result=0;
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
            result=ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        conn.close();
        return result;
    }
    public ResultSet getDataByAny(String sql, Object [] obj) throws SQLException { //查询参数
        Connection conn=ConnUtil.getConn();
        ResultSet rs=null;
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i+1,obj[i]);
            }
            rs=ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
}
