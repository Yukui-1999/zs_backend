package com.login;

import com.DB;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;
import org.json.JSONObject;
@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class Login {

	@PostMapping ("/login")
	public HashMap<String, Object> login(@RequestBody String jsonstring) throws JSONException, SQLException {
		System.out.println(jsonstring);
		JSONObject jsonObject = new JSONObject(jsonstring);
		String name=jsonObject.getString("username");
		String password=jsonObject.getString("password");
		System.out.println("用户名是"+name+"密码是"+password);


		Connection conn = DB.getConnection();//获取连接对象
		Statement state = conn.createStatement();//创建sql语句
		ResultSet rs = state.executeQuery(
                "SELECT username,password,usertype FROM user WHERE username='" +
                        name+"' AND password='"+password+"'");

		if (rs.next() ){
			if(rs.getInt("usertype")==1){
				return new HashMap<String, Object>() {{
					put("state", 200);
					put("data", "success");
					put("msg", "1");
				}};
			}
			else {
				return new HashMap<String, Object>() {{
					put("state", 200);
					put("data", "success");
					put("msg", "0");
				}};
			}

		}
		else{
			return new HashMap<String, Object>() {{
				put("state", 200);
				put("data", "fail");
				put("msg", "");
			}};
		}

	}

	@PostMapping ("/index/changepwd")
	public HashMap<String, Object> changepwd(@RequestBody String jsonstring) throws JSONException, SQLException {
		System.out.println(jsonstring);
		JSONObject jsonObject = new JSONObject(jsonstring);
		String name=jsonObject.getString("username");
		String oldpwd=jsonObject.getString("oldpwd");
		String newpwd=jsonObject.getString("newpwd");
		System.out.println("用户名是"+name+"密码是"+oldpwd+"新密码"+newpwd);


		Connection conn = DB.getConnection();//获取连接对象
		Statement state = conn.createStatement();//创建sql语句
		ResultSet rs = state.executeQuery(
				"SELECT username,password,usertype FROM user WHERE username='" +
						name+"' AND password='"+oldpwd+"'");
		if(rs.next()){
				int rei = state.executeUpdate("UPDATE user SET password ='"+newpwd+"' WHERE username='"+name+"'");
			if(rei>0){
				System.out.println("修改成功");
				return new HashMap<String, Object>() {{
					put("state", 200);
					put("data", "");
					put("msg", "success");
				}};
			}
			else{
				System.out.println("修改失败");
				return new HashMap<String, Object>() {{
					put("state", 200);
					put("data", "");
					put("msg", "fail");
				}};
			}
		}
		else{
			System.out.println("修改密码——原密码错误");
			return new HashMap<String, Object>() {{
				put("state", 200);
				put("data", "");
				put("msg", "oldpwderror");
			}};
		}



	}


}
