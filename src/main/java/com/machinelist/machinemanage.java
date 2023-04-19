package com.machinelist;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import com.DB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class machinemanage {

    @PostMapping ("/index/machinesearch")
    public HashMap<String, Object> machinesearch(@RequestBody String jsonstring) throws  SQLException {
        System.out.println(jsonstring);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String id=jsonObject.getString("id");
        JSONArray resObject = new JSONArray();
        Connection conn = DB.getConnection();//获取连接对象
        Statement state = conn.createStatement();//创建sql语句
        ResultSet rs = state.executeQuery("SELECT * from machine WHERE id='"+id+"'");

        if (rs.next()){
            JSONObject object=new JSONObject();
            object.put("id",rs.getString("id"));
            object.put("name",rs.getString("name"));
            object.put("state",rs.getString("lateststate"));

            resObject.add(object);
            System.out.println(resObject.toString());
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", resObject);
                put("msg", "");
            }};
        }
        else{
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", "notfind");
                put("msg", "");
            }};
        }

    }
    @PostMapping ("/index/machineadd")
    public HashMap<String, Object> machineadd(@RequestBody String jsonstring) throws  SQLException {
        System.out.println(jsonstring);
        JSONArray resObject = new JSONArray();
        Connection conn = DB.getConnection();//获取连接对象
        Statement state = conn.createStatement();//创建sql语句
        ResultSet rs = state.executeQuery("SELECT * FROM machine where name=''");

        while (rs.next()){
            JSONObject object=new JSONObject();
            object.put("id",rs.getString("id"));
            object.put("state",rs.getString("lateststate"));

            resObject.add(object);
        }
        System.out.println(resObject.toString());
        return new HashMap<String, Object>() {{
            put("state", 200);
            put("data", resObject);
            put("msg", "");
        }};
    }

    @PostMapping ("/index/machinemanage")
    public HashMap<String, Object> machinemanage(@RequestBody String jsonstring) throws  SQLException {
        System.out.println(jsonstring);
        JSONArray resObject = new JSONArray();
        Connection conn = DB.getConnection();//获取连接对象
        Statement state = conn.createStatement();//创建sql语句
        ResultSet rs = state.executeQuery("SELECT * FROM machine where name!=''");

        while (rs.next()){
            JSONObject object=new JSONObject();
            object.put("id",rs.getString("id"));
            object.put("name",rs.getString("name"));
            object.put("state",rs.getString("lateststate"));

            resObject.add(object);
        }
        System.out.println(resObject.toString());
        return new HashMap<String, Object>() {{
            put("state", 200);
            put("data", resObject);
            put("msg", "");
        }};
    }
    @PostMapping ("/index/addmachine")
    public HashMap<String, Object> addmachine(@RequestBody String jsonstring) throws  SQLException {
        System.out.println(jsonstring);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String id=jsonObject.getString("id");
        String name=jsonObject.getString("name");
        String sensor=jsonObject.getString("sensor");
        String intro=jsonObject.getString("intro");
        String url=jsonObject.getString("url");
        System.out.println("id name sensor intro url are "+id+" "+name+" "+sensor+" "+intro+" "+url);
        Connection conn = DB.getConnection();//获取连接对象
//        Statement state = conn.createStatement();
//        ResultSet rs = state.executeQuery(
//                "UPDATE machine SET name = '"+name+"'"+
//                        "SET sensortype = '"+sensor+"'"+
//                        "SET intro = '"+intro+"'"+
//                        "SET url = '"+url+"'"+
//                        " WHERE id='"+id+"'");
//        //如果搜到有这个id的信息，就返回注册失败
//        if(rs.next()&&rs.getString("id")!=null) {
//            System.out.println("该工程机械id已存在");
//            return new HashMap<String, Object>() {{
//                put("state", 200);
//                put("data", "");
//                put("msg", "reuse");
//            }};
//        }

        String sql = "UPDATE machine SET name = '"+name+"'"+
                ", sensortype = '"+sensor+"'"+
                ", intro = '"+intro+"'"+
                ", imageurl = '"+url+"'"+
                " WHERE id='"+id+"'";
        System.out.println(sql);
        PreparedStatement ptmt = conn.prepareStatement(sql);
//        // 传参
//        ptmt.setString(1, id);
//        ptmt.setString(2, name);
//        ptmt.setString(3, "normal");
//        ptmt.setString(4, sensor);
//        ptmt.setString(5, intro);
//        ptmt.setString(6, url);
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
    @PostMapping ("/index/deletemachine")
    public HashMap<String, Object> deletemachine(@RequestBody String jsonstring) throws  SQLException{
        System.out.println(jsonstring);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String id=jsonObject.getString("id");
        System.out.println("需要删除的机械id是"+id);
        Connection conn = DB.getConnection();//获取连接对象
        Statement state = conn.createStatement();//创建sql语句
        int rs = state.executeUpdate("DELETE from machine WHERE id='"+id+"'");
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

    @GetMapping ("/index/load")
    public void fileload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String filepath="/Users/dingzhengyao/Downloads/zs_backend/src/main/java/com/data/result.csv";
        File f = new File(filepath);
        FileInputStream ips = new FileInputStream(f);
        OutputStream ops = response.getOutputStream();
        response.setContentType("application/octet-stream; charset=UTF-8");
        byte[] a = new byte[100000000];
        ips.read(a);
        ops.write(a);
    }

    @PostMapping ("/index/machinedetail")
    public HashMap<String, Object> machinedetail(@RequestBody String jsonstring) throws  SQLException{

        System.out.println(jsonstring);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String id=jsonObject.getString("id");
        System.out.println("需要获取信息的机械id是"+id);
        Connection conn = DB.getConnection();//获取连接对象
        Statement state = conn.createStatement();//创建sql语句

        JSONObject object=new JSONObject();

        JSONArray time=new JSONArray();
        ArrayList<Integer>[] lists=new ArrayList[13];//目前共13种状态
        ResultSet rs2 = state.executeQuery("SELECT * from filetable WHERE id='"+id+"'");
        while (rs2.next()){
            time.add(rs2.getString("time"));
        }
        for (int i = 0; i < lists.length; i++) {
            ArrayList<Integer> a=new ArrayList<>();
            for (int j = 0; j < time.size(); j++) {
                a.add(0);
            }
            lists[i]=a;
        }
        ResultSet rs1 = state.executeQuery("SELECT * from filetable WHERE id='"+id+"'");
        int count=0;
        while (rs1.next()){
            lists[rs1.getInt("result")-1].set(count,lists[rs1.getInt("result")-1].get(count)+1);
            count++;
        }
        JSONArray resvalue=new JSONArray();
        for (int i = 0; i < lists.length; i++) {
            JSONObject obj=new JSONObject();
            JSONArray jar=new JSONArray();
            int flag=0;
            for (int j = 0; j <lists[i].size(); j++) {
                jar.add(lists[i].get(j));
                if(lists[i].get(j)!=0){
                    flag=1;
                }
            }
            if(flag==1){
                obj.put("name",i+1);
                obj.put("data",jar);
                resvalue.add(obj);
            }
        }
        System.out.println(resvalue.toString());
        ResultSet rs = state.executeQuery("SELECT * from machine WHERE id='"+id+"'");
        if (rs.next()){
            object.put("intro",rs.getString("intro"));
            object.put("sensor",rs.getString("sensortype"));
            object.put("imgurl",rs.getString("imageurl"));
            object.put("name",rs.getString("name"));
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", object);
                put("line",resvalue);
                put("time",time);
                put("msg", "success");
            }};
        }
        else{
            return new HashMap<String, Object>() {{
                put("state", 200);
                put("data", object);
                put("msg", "fail");
            }};
        }

    }
}
