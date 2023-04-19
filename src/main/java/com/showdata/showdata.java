package com.showdata;

import com.DB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import com.csvreader.CsvReader;
import java.nio.charset.Charset;
@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class showdata {

    @PostMapping ("/index/showdata")
    public HashMap<String, Object> showdata(@RequestBody String jsonstring) throws SQLException, IOException {
        System.out.println(jsonstring);
        JSONObject jsonObject = JSONObject.parseObject(jsonstring);
        String id=jsonObject.getString("id");
        System.out.println("需要展示原始数据的id是"+id);
        Connection conn = DB.getConnection();//获取连接对象
        Statement state = conn.createStatement();//创建sql语句
        ResultSet rs = state.executeQuery("SELECT * FROM filetable where id='"+id+"'");
        String csvfile="";
        while (rs.next()){
            csvfile=rs.getString("part_hash");
        }

        JSONArray axreturn = new JSONArray();
        JSONArray ayreturn = new JSONArray();
        JSONArray azreturn = new JSONArray();
        JSONArray wxreturn = new JSONArray();
        JSONArray wyreturn = new JSONArray();
        JSONArray wzreturn = new JSONArray();
        JSONArray anglexreturn = new JSONArray();
        JSONArray angleyreturn = new JSONArray();
        JSONArray anglezreturn = new JSONArray();
        JSONArray q0return = new JSONArray();
        JSONArray q1return = new JSONArray();
        JSONArray q2return = new JSONArray();
        JSONArray q3return = new JSONArray();

        CsvReader csvReader = new CsvReader(csvfile, ',', Charset.forName("utf-8"));
        csvReader.readHeaders();
        while (csvReader.readRecord()){
            JSONObject axo = new JSONObject();
            JSONArray axo1 = new JSONArray();
            JSONObject ayo = new JSONObject();
            JSONArray ayo1 = new JSONArray();
            JSONObject azo = new JSONObject();
            JSONArray azo1 = new JSONArray();
            JSONObject wxo = new JSONObject();
            JSONArray wxo1 = new JSONArray();
            JSONObject wyo = new JSONObject();
            JSONArray wyo1 = new JSONArray();
            JSONObject wzo = new JSONObject();
            JSONArray wzo1 = new JSONArray();
            JSONObject anglexo = new JSONObject();
            JSONArray anglexo1 = new JSONArray();
            JSONObject angleyo = new JSONObject();
            JSONArray angleyo1 = new JSONArray();
            JSONObject anglezo = new JSONObject();
            JSONArray anglezo1 = new JSONArray();
            JSONObject q0o = new JSONObject();
            JSONArray q0o1 = new JSONArray();
            JSONObject q1o = new JSONObject();
            JSONArray q1o1 = new JSONArray();
            JSONObject q2o = new JSONObject();
            JSONArray q2o1 = new JSONArray();
            JSONObject q3o = new JSONObject();
            JSONArray q3o1 = new JSONArray();

            String time = csvReader.get(0);
            String[] times=time.split("[:.]");
            int a,b,c,d;
            a=Integer.parseInt(times[0]);
            b=Integer.parseInt(times[1]);
            c=Integer.parseInt(times[2]);
            d=Integer.parseInt(times[3]);
            int timesint[]={a,b,c,d};

            String ax = csvReader.get(1);
            String ay = csvReader.get(2);
            String az = csvReader.get(3);
            String wx = csvReader.get(4);
            String wy = csvReader.get(5);
            String wz = csvReader.get(6);
            String anglex = csvReader.get(7);
            String angley = csvReader.get(8);
            String anglez = csvReader.get(9);
            String q0 = csvReader.get(10);
            String q1 = csvReader.get(11);
            String q2 = csvReader.get(12);
            String q3 = csvReader.get(13);

            axo.put("name",timesint);
            axo1.add(time);
            BigDecimal axint= new BigDecimal(ax);
            axo1.add(axint);
            axo.put("value",axo1);
            axreturn.add(axo);

            ayo.put("name",timesint);
            ayo1.add(time);
            BigDecimal ayint= new BigDecimal(ay);
            ayo1.add(ayint);
            ayo.put("value",ayo1);
            ayreturn.add(ayo);

            azo.put("name",timesint);
            azo1.add(time);
            BigDecimal azint= new BigDecimal(az);
            azo1.add(azint);
            azo.put("value",azo1);
            azreturn.add(azo);

            wxo.put("name",timesint);
            wxo1.add(time);
            BigDecimal wxint= new BigDecimal(wx);
            wxo1.add(wxint);
            wxo.put("value",wxo1);
            wxreturn.add(wxo);

            wyo.put("name",timesint);
            wyo1.add(time);
            BigDecimal wyint= new BigDecimal(wy);
            wyo1.add(wyint);
            wyo.put("value",wyo1);
            wyreturn.add(wyo);

            wzo.put("name",timesint);
            wzo1.add(time);
            BigDecimal wzint= new BigDecimal(wz);
            wzo1.add(wzint);
            wzo.put("value",wzo1);
            wzreturn.add(wzo);

            anglexo.put("name",timesint);
            anglexo1.add(time);
            BigDecimal anglexint= new BigDecimal(anglex);
            anglexo1.add(anglexint);
            anglexo.put("value",anglexo1);
            anglexreturn.add(anglexo);

            angleyo.put("name",timesint);
            angleyo1.add(time);
            BigDecimal angleyint= new BigDecimal(angley);
            angleyo1.add(angleyint);
            angleyo.put("value",angleyo1);
            angleyreturn.add(angleyo);

            anglezo.put("name",timesint);
            anglezo1.add(time);
            BigDecimal anglezint= new BigDecimal(anglez);
            anglezo1.add(anglezint);
            anglezo.put("value",anglezo1);
            anglezreturn.add(anglezo);

            q0o.put("name",timesint);
            q0o1.add(time);
            BigDecimal q0int= new BigDecimal(q0);
            q0o1.add(q0int);
            q0o.put("value",q0o1);
            q0return.add(q0o);

            q1o.put("name",timesint);
            q1o1.add(time);
            BigDecimal q1int= new BigDecimal(q1);
            q1o1.add(q1int);
            q1o.put("value",q1o1);
            q1return.add(q1o);

            q2o.put("name",timesint);
            q2o1.add(time);
            BigDecimal q2int= new BigDecimal(q2);
            q2o1.add(q2int);
            q2o.put("value",q2o1);
            q2return.add(q2o);

            q3o.put("name",timesint);
            q3o1.add(time);
            BigDecimal q3int= new BigDecimal(q3);
            q3o1.add(q3int);
            q3o.put("value",q3o1);
            q3return.add(q3o);


        }
        return new HashMap<String, Object>() {{
            put("state", 200);
            put("ax", axreturn);
            put("ay", ayreturn);
            put("az", azreturn);
            put("wx", wxreturn);
            put("wy", wyreturn);
            put("wz", wzreturn);
            put("anglex", anglexreturn);
            put("angley", angleyreturn);
            put("anglez", anglezreturn);
            put("q0", q0return);
            put("q1", q1return);
            put("q2", q2return);
            put("q3", q3return);
            put("msg", "success");
        }};



    }
}
