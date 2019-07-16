package com.lianglong.threadpool.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {


     String s= "[{nameedPath\":\"mtscvehicleapplydockdata/2017/12/28\",\"title\":\"\",\"viewName\":\"\",\"showName\":\"7221104f2a5c4b2c8647dca0887bc232\",\"name\":\"7221104f2a5c4b2c8647dca0887bc232.jpg\",\"path\":\"/usr/local/MTSC/Download/mtscweb/mtscvehicleapplydockdata/2017/12/28/7221104f2a5c4b2c8647dca0887bc232.jpg\",\"parent\":\"\",\"uuid\":\"\",\"isScan\":\"\",\"fileCount\":\"\",\"size\":\"0.0\"}]\"";

        String nameedPath = s.substring(s.indexOf(":")+1, s.indexOf(","));
        System.out.println(s.indexOf("name\":")+6);
        System.out.println(s.indexOf("path")-2);

        String name = s.substring(s.indexOf("name\":")+6,s.indexOf("path")-2);

        System.out.println("nameedPath:"+nameedPath);

        System.out.println("name:"+name);
    }

}
