package com.onejane.poi.controller;

import com.onejane.poi.utils.DateUtils;
import com.onejane.poi.utils.ExcelUtils;
import com.onejane.poi.vo.ExportPerson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/excel", produces = {"application/json;charset=UTF-8"})
public class ExcelUtilController {
    @RequestMapping("export")
    public void export(HttpServletResponse response){

        //模拟从数据库获取需要导出的数据
        List<ExportPerson> personList = new ArrayList<>();
        ExportPerson person1 = new ExportPerson("路飞","1",new Date());
        ExportPerson person2 = new ExportPerson("娜美","2", DateUtils.addDay(new Date(),3));
        ExportPerson person3 = new ExportPerson("索隆","1", DateUtils.addDay(new Date(),10));
        ExportPerson person4 = new ExportPerson("小狸猫","1", DateUtils.addDay(new Date(),-10));
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        ExcelUtils.exportExcel(personList,"花名册","草帽一伙",ExportPerson.class,"海贼王.xls",response);
    }

    @RequestMapping("importExcel")
    public void importExcel(){
        String filePath = "F:\\海贼王.xls";
        //解析excel，
        List<ExportPerson> personList = ExcelUtils.importExcel(filePath,1,1,ExportPerson.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【"+personList.size()+"】行");

    }
}