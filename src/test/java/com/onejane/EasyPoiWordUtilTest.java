package com.onejane;

import com.onejane.poi.utils.WordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyPoiWordUtilTest {


    @Test
    public void testExportWord(){
        try {
            try (FileOutputStream fos = new FileOutputStream("E:\\opt\\template\\aaa.docx")) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("title", "个人信息");
                Map<String, String> user = new HashMap<>();
                user.put("name", "张三");
                user.put("age", "22");
                user.put("address", "重庆渝北区");
                user.put("other", "篮球");
                dataMap.put("user", user);

                List<Map<String, String>> jobs = new ArrayList<>();
                Map<String, String> job;
                for (int i = 0; i < 5; i++) {
                    job = new HashMap<>();
                    job.put("name", "公司名称-" + i);
                    job.put("address", "公司地址:" + i);
                    jobs.add(job);
                }

                dataMap.put("jobs",jobs);

                byte[] doc = WordUtils.exportWord("E:\\opt\\template\\bbb.docx", dataMap);

                fos.write(doc);

                fos.flush();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSearchAndReplace(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("${test}", "dddddddddddddddddddddd");
        map.put("${tel}", "8886666");
        map.put("${table}", "fesfdsfasdafdsafsdafsdafsdadfsdfsddfsafsdfsfsd");
        String srcPath = "E:\\opt\\template\\ccc.docx";
        String destPath = "E:\\opt\\template\\ddd.docx";
        WordUtils.searchAndReplace(srcPath, destPath, map);
    }
}
