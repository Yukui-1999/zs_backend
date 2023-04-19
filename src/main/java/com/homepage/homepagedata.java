package com.homepage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;
import java.sql.*;
import java.util.*;
import com.DB;
@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class homepagedata {
    @PostMapping ("/index/piedata")
    public HashMap<String, Object> piedata(@RequestBody String jsonstring) throws  SQLException {
        System.out.println(jsonstring);
        JSONArray resObject = new JSONArray();
        JSONArray resObject2 = new JSONArray();
        Connection conn = DB.getConnection();//获取连接对象
        Statement state = conn.createStatement();//创建sql语句
        ResultSet rs = state.executeQuery("SELECT * FROM machine where name!='' ");
        int[] value= {0,0,0,0,0,0,0,0,0,0,0,0,0};
        JSONObject object1=new JSONObject();
        JSONObject object2=new JSONObject();
        JSONObject object3=new JSONObject();
        JSONObject object4=new JSONObject();
        JSONObject object5=new JSONObject();
        JSONObject object6=new JSONObject();
        JSONObject object7=new JSONObject();
        JSONObject object8=new JSONObject();
        JSONObject object9=new JSONObject();
        JSONObject object10=new JSONObject();
        JSONObject object11=new JSONObject();
        JSONObject object12=new JSONObject();
        JSONObject object13=new JSONObject();
        while (rs.next()){
            JSONObject object=new JSONObject();
            object.put("name",rs.getString("name"));
            object.put("intro",rs.getString("intro"));
            object.put("imageurl",rs.getString("imageurl"));
            object.put("id",rs.getString("id"));
            resObject2.add(object);
            if(rs.getString("lateststate").equals("0"))
                value[0]++;
            if(rs.getString("lateststate").equals("1"))
                value[1]++;
            if(rs.getString("lateststate").equals("2"))
                value[2]++;
            if(rs.getString("lateststate").equals("3"))
                value[3]++;
            if(rs.getString("lateststate").equals("4"))
                value[4]++;
            if(rs.getString("lateststate").equals("5"))
                value[5]++;
            if(rs.getString("lateststate").equals("6"))
                value[6]++;
            if(rs.getString("lateststate").equals("7"))
                value[7]++;
            if(rs.getString("lateststate").equals("8"))
                value[8]++;
            if(rs.getString("lateststate").equals("9"))
                value[9]++;
            if(rs.getString("lateststate").equals("10"))
                value[10]++;
            if(rs.getString("lateststate").equals("11"))
                value[11]++;
            if(rs.getString("lateststate").equals("12"))
                value[12]++;
        }
        object1.put("value",value[0]);
        object1.put("name","静止，不转，大瓶，空瓶");
        object2.put("value",value[1]);
        object2.put("name","逆时针转，快速，大瓶，空瓶");
        object3.put("value",value[2]);
        object3.put("name","逆时针转，快速，大瓶，空瓶");
        object4.put("value",value[3]);
        object4.put("name","逆时针转，慢速，大瓶，空瓶");
        object5.put("value",value[4]);
        object5.put("name","逆时针转，慢速，大瓶，满水");
        object6.put("value",value[5]);
        object6.put("name","逆时针转，慢速，小瓶，空瓶");
        object7.put("value",value[6]);
        object7.put("name","逆时针转，慢速，小瓶，满水");
        object8.put("value",value[7]);
        object8.put("name","顺时针转，快速，大瓶，空瓶");
        object9.put("value",value[8]);
        object9.put("name","顺时针转，快速，小瓶，空瓶");
        object10.put("value",value[9]);
        object10.put("name","顺时针转，慢速，大瓶，空瓶");
        object11.put("value",value[10]);
        object11.put("name","顺时针转，慢速，大瓶，满水");
        object12.put("value",value[11]);
        object12.put("name","顺时针转，慢速，小瓶，空瓶");
        object13.put("value",value[12]);
        object13.put("name","顺时针转，慢速，小瓶，满水");
        if(!object1.get("value").equals(0))
        resObject.add(object1);
        if(!object2.get("value").equals(0))
        resObject.add(object2);
        if(!object3.get("value").equals(0))
        resObject.add(object3);
        if(!object4.get("value").equals(0))
        resObject.add(object4);
        if(!object5.get("value").equals(0))
        resObject.add(object5);
        if(!object6.get("value").equals(0))
        resObject.add(object6);
        if(!object7.get("value").equals(0))
        resObject.add(object7);
        if(!object8.get("value").equals(0))
        resObject.add(object8);
        if(!object9.get("value").equals(0))
        resObject.add(object9);
        if(!object10.get("value").equals(0))
        resObject.add(object10);
        if(!object11.get("value").equals(0))
        resObject.add(object11);
        if(!object12.get("value").equals(0))
        resObject.add(object12);
        if(!object13.get("value").equals(0))
        resObject.add(object13);
        System.out.println(resObject.toString());
        System.out.println(resObject2.toString());


        ResultSet rs1 = state.executeQuery("SELECT * FROM filetable where id=1001");
        JSONArray time=new JSONArray();
        while (rs1.next()){
            time.add(rs1.getString("time"));
        }
        System.out.println(time.toString());
        Map<String,ArrayList<Integer>> returnvalue=new HashMap<>();

        ArrayList<Integer>[] lists = new ArrayList[13];//目前只有13种状态
        for (int i = 0; i < lists.length; i++) {
            ArrayList<Integer> a=new ArrayList<>();
            for (int j = 0; j < time.size(); j++) {
                a.add(0);
            }
            lists[i]=a;
        }
        for (int i = 0; i < time.size(); i++) {
            ResultSet rs2 = state.executeQuery("SELECT * FROM filetable where time='"+time.get(i)+"'");
            ArrayList<String> list=new ArrayList();

            while (rs2.next()){
                list.add(rs2.getString("result"));
            }

            Map<String, Integer> map = new HashMap<>();
            for (String k: list) {
                Integer count = map.get(k);
                map.put(k, (count == null) ? 1 : count + 1);
            }
            System.out.println(map.toString());
            for (String key : map.keySet()) {
                    int x=lists[Integer.parseInt(key)-1].get(i);
                    lists[Integer.parseInt(key)-1].set(i,x+map.get(key));
                    returnvalue.put(key,lists[Integer.parseInt(key)-1]);
            }
        }

        System.out.println(returnvalue.toString());
        JSONArray resvalue=new JSONArray();
        for(String key:returnvalue.keySet()){
            JSONObject obj=new JSONObject();
            JSONArray arr=new JSONArray();
            obj.put("name",key);
            for (int i = 0; i < returnvalue.get(key).size(); i++)
                arr.add(returnvalue.get(key).get(i));
            obj.put("data",arr);
            resvalue.add(obj);
        }

        System.out.println(resvalue);
        return new HashMap<String, Object>() {{
            put("state", 200);
            put("data", resObject);
            put("data2",resObject2);
            put("linevalue",resvalue);
            put("time",time);
            put("msg", "success");
        }};
    }


}
