package com;

import com.homepage.homepagedata;
import com.login.Login;
import com.machinelist.machinemanage;
import com.peoplemanage.PeopleManage;
import com.showdata.showdata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
@Import({Login.class,PeopleManage.class, machinemanage.class, homepagedata.class, showdata.class})
public class Total {
    public static void main(String[] args) {
        SpringApplication.run(Total.class, args);
    }
}

