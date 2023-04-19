package com.peoplemanage;

import com.DB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;
import java.sql.*;
import java.util.*;
@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")

public class PeopleManage {

    @PostMapping ("/index/peoplemanage")
    public HashMap<String, Object> manage(@RequestBody String jsonstring) throws  SQLException {
        System.out.println(jsonstring);
        JSONArray resObject = new JSONArray();
        Connection conn = DB.getConnection();//获取连接对象
        Statement state = conn.createStatement();//创建sql语句
        ResultSet rs = state.executeQuery("SELECT * FROM user ");
        JSONArray op1=new JSONArray();
        JSONArray op2=new JSONArray();
        op1.add("重置密码");
        op1.add("删除用户");
        op2.add("重置密码");
        while (rs.next()){
            JSONObject object=new JSONObject();
            object.put("username",rs.getString("username"));
            object.put("usertype",rs.getString("usertype"));
            if(rs.getString("usertype").equals("0"))
                object.put("op",op1);
            else
                object.put("op",op2);
            resObject.add(object);
        }
        System.out.println(resObject.toString());
        return new HashMap<String, Object>() {{
            put("state", 200);
            put("data", resObject);
            put("msg", "");
        }};
    }
    @PostMapping ("/index/resetpwd")
    public HashMap<String, Object> resetpwd(@RequestBody String jsonstring) throws  SQLException {
        System.out.println(jsonstring);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String name=jsonObject.getString("username");
        System.out.println("需要重置的用户名是"+name);
        Connection conn = DB.getConnection();//获取连接对象
        Statement state = conn.createStatement();//创建sql语句
        int rs = state.executeUpdate("UPDATE user SET password =123456 WHERE username='"+name+"'");
        if(rs>0){
            System.out.println("重置密码成功");
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", "");
                put("msg", "success");
            }};
        }
        else{
            System.out.println("重置密码失败");
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", "");
                put("msg", "fail");
            }};
        }
    }
    @PostMapping ("/index/deleteuser")
    public HashMap<String, Object> deleteuser(@RequestBody String jsonstring) throws  SQLException{
        System.out.println(jsonstring);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String name=jsonObject.getString("username");
        System.out.println("需要删除的用户名是"+name);
        Connection conn = DB.getConnection();//获取连接对象
        Statement state = conn.createStatement();//创建sql语句
        int rs = state.executeUpdate("DELETE from user WHERE username='"+name+"'");
        if(rs>0){
            System.out.println("删除成功");
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", "");
                put("msg", "success");
            }};
        }
        else{
            System.out.println("删除失败");
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", "");
                put("msg", "fail");
            }};
        }
    }

    @PostMapping ("/index/createuser")
    public HashMap<String, Object> createuser(@RequestBody String jsonstring) throws  SQLException{
        System.out.println(jsonstring);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String name=jsonObject.getString("username");
        String pwd=jsonObject.getString("password");
        String type=jsonObject.getString("type");
        System.out.println("username,pwd,type are "+name+" "+pwd+" "+type);
        Connection conn = DB.getConnection();//获取连接对象


        Statement state = conn.createStatement();
        ResultSet rs = state.executeQuery(
                "SELECT * FROM user WHERE username='"+name+"'");
        //如果搜到有这个username的信息，就返回注册失败
        if(rs.next()&&rs.getString("username")!=null) {
            System.out.println("用户名已存在");
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", "");
                put("msg", "reuse");
            }};
        }

        String sql = "insert into user values(?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        // 传参
        ptmt.setString(1, name);
        ptmt.setString(2, pwd);
        ptmt.setString(3, type);
        if(!ptmt.execute()){
            System.out.println("创建成功");
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", "");
                put("msg", "success");
            }};
        }
        else{
            System.out.println("创建失败");
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", "");
                put("msg", "fail");
            }};
        }
    }

}
