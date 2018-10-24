package com.uhope.quality.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.quality.domain.WaterQualityGrade;

import com.uhope.quality.domain.WaterqualityDaily;
import com.uhope.quality.domain.WaterqualityMonthly;
import com.uhope.quality.domain.WaterqualityRealTime;
import com.uhope.quality.dto.MdSectionDTO;
import com.uhope.quality.service.WaterQualityGradeService;
import com.uhope.quality.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 描述:
 * 条目控制类
 *
 * @author a4994
 * @create 2018-09-28 9:12
 */
@RestController
@RequestMapping("/v1/WaterQualityGrade")
public class WaterQualityGradeController {
    @Autowired
    private WaterQualityGradeService waterQualityGradeService;

    @Autowired
    private MongoTemplate temp;

    @PostMapping("/add")
    public Result<WaterQualityGrade> add(@RequestParam String name,
                                         @RequestParam String riverName,
                                         @RequestParam String samplingTime,
                                         @RequestParam double water_temperature,
                                         @RequestParam double total_phosphorus,
                                         @RequestParam double ammonia_nitrogen,
                                         @RequestParam double permanganate_index,
                                         @RequestParam double DO,
                                         @RequestParam String parentid) throws ParseException {
        WaterQualityGrade waterQualityGrade = new WaterQualityGrade();
        waterQualityGrade.setId(UUID.randomUUID().toString().replace("-",""));
        waterQualityGrade.setCode(waterQualityGrade.getId());
        waterQualityGrade.setName(name);
        waterQualityGrade.setRiverName(riverName);
        waterQualityGrade.setSamplingTime(new SimpleDateFormat("yyyy-MM-dd").parse(samplingTime));
        waterQualityGrade.setWater_temperature(water_temperature);
        waterQualityGrade.setTotal_phosphorus(total_phosphorus);
        waterQualityGrade.setAmmonia_nitrogen(ammonia_nitrogen);
        waterQualityGrade.setPermanganate_index(permanganate_index);
        waterQualityGrade.setDO(DO);
        waterQualityGrade.setParentId(parentid);
        waterQualityGradeService.insert1(waterQualityGrade);
        return ResponseMsgUtil.success(waterQualityGrade);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        waterQualityGradeService.remove(id);
        return ResponseMsgUtil.success();
    }

    @DeleteMapping("/deletelist")
    public Result deletelist(@RequestParam String parentid) {
        waterQualityGradeService.deletelist(parentid);
        return ResponseMsgUtil.success();
    }



    @PutMapping("/update")
    public Result<WaterQualityGrade> update(@RequestParam String id,
                                            @RequestParam String samplingTime,
                                            @RequestParam double water_temperature,
                                            @RequestParam double total_phosphorus,
                                            @RequestParam double ammonia_nitrogen,
                                            @RequestParam double permanganate_index,
                                            @RequestParam double DO) throws ParseException {
        WaterQualityGrade waterQualityGrade = new WaterQualityGrade();
        waterQualityGrade.setId(id);
        waterQualityGrade.setSamplingTime(new SimpleDateFormat("yyyy-MM-dd").parse(samplingTime));
        waterQualityGrade.setWater_temperature(water_temperature);
        waterQualityGrade.setTotal_phosphorus(total_phosphorus);
        waterQualityGrade.setAmmonia_nitrogen(ammonia_nitrogen);
        waterQualityGrade.setPermanganate_index(permanganate_index);
        waterQualityGrade.setDO(DO);
        waterQualityGradeService.update1(waterQualityGrade);
        return ResponseMsgUtil.success(waterQualityGrade);
    }

    @GetMapping("/detail")
    public Result<WaterQualityGrade> detail(@RequestParam String id) {
        WaterQualityGrade waterQualityGrade = waterQualityGradeService.get1(id);
        return ResponseMsgUtil.success(waterQualityGrade);
    }

    @GetMapping("/list")
    public Result<PageInfo<WaterQualityGrade>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                    @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                    @RequestParam String parentid,
                                                    String riverName,String name) {
        return ResponseMsgUtil.success(waterQualityGradeService.list(pageNumber,pageSize,parentid,riverName,name));
    }

    /*@GetMapping("/list")
    public Result<List<WaterQualityGrade>> list(@RequestParam String parentid){
        return ResponseMsgUtil.success(waterQualityGradeService.list(parentid));
    }*/

    @PostMapping("/upload")
    public Result<Integer> upload(@RequestParam MultipartFile file,
                                      @RequestParam String parentid,
                                      HttpServletRequest req) throws IOException {
        WaterQualityGrade waterQualityGrade = new WaterQualityGrade();
        int code=100;
        InputStream in = file.getInputStream();
        Workbook wb = null;
        if (ExcelUtil.isExcel2007(file.getOriginalFilename())) {
            wb = new XSSFWorkbook(in);
        } else {
            wb = new HSSFWorkbook(in);
        }
        Sheet sheetAt = wb.getSheetAt(0);
        //获取所有行
        int hang = sheetAt.getPhysicalNumberOfRows();
        for (int i = 2; i < hang; i++) {
            Cell cell = sheetAt.getRow(i).getCell(0);
            cell.setCellType(CellType.STRING);
            String oldCode=cell.getStringCellValue();
           //=sheetAt.getRow(i).getCell(1).getStringCellValue();;
            String name = sheetAt.getRow(i).getCell(1).getStringCellValue();
            String riverName=sheetAt.getRow(i).getCell(2).getStringCellValue();
            Date samplingTime=null;
            if(sheetAt.getRow(i).getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                short format = sheetAt.getRow(i).getCell(3).getCellStyle().getDataFormat();
                //判断日期个格式是否是 2017/01/01 这样
                /*
                 * 14 yyyy-MM-dd / 2017/01/01
                 * 31 yyyy年m月d日
                 * */
                if (format == 14 || format == 31) {
                    samplingTime = HSSFDateUtil.getJavaDate(sheetAt.getRow(i).getCell(3).getNumericCellValue());

                }
            }
            //Date samplingTime=sheetAt.getRow(i).getCell(3).getDateCellValue();
            Double water_temperature=sheetAt.getRow(i).getCell(4).getNumericCellValue();
            Double total_phosphorus=sheetAt.getRow(i).getCell(5).getNumericCellValue();
            Double ammonia_nitrogen=sheetAt.getRow(i).getCell(6).getNumericCellValue();
            Double permanganate_index=sheetAt.getRow(i).getCell(7).getNumericCellValue();
            Double DO=sheetAt.getRow(i).getCell(8).getNumericCellValue();
            waterQualityGrade.setParentId(parentid);
            waterQualityGrade.setId(UUID.randomUUID().toString().replace("-",""));
            waterQualityGrade.setCode(waterQualityGrade.getId());
            waterQualityGrade.setOldCode(oldCode);
            waterQualityGrade.setName(name);
            waterQualityGrade.setRiverName(riverName);
            waterQualityGrade.setSamplingTime(samplingTime);
            waterQualityGrade.setWater_temperature(water_temperature);
            waterQualityGrade.setTotal_phosphorus(total_phosphorus);
            waterQualityGrade.setAmmonia_nitrogen(ammonia_nitrogen);
            waterQualityGrade.setPermanganate_index(permanganate_index);
            waterQualityGrade.setDO(DO);
            waterQualityGradeService.insert1(waterQualityGrade);
            code=200;
        }
        return ResponseMsgUtil.success(code);
    }


   // 查询级联的断面和河流
    @GetMapping("/selectCascade")
    public Result<List<MdSectionDTO>> selectCascade(){
        return ResponseMsgUtil.success(waterQualityGradeService.selectCascade());
    }

    //   查询以增加的区
    @GetMapping("/haveSection")
    public Result<Integer> haveSection(@RequestParam String name,
                                        @RequestParam String parentid){
        // 10表示列表中没有这个区  可以增加     11表示有这个区 不能增加
        int sta=10;
        List<WaterQualityGrade> list = waterQualityGradeService.haveSection(parentid);
        for (WaterQualityGrade s:list ) {
            if (name.equals(s.getName())){
                sta=11;
            }
        }
        return ResponseMsgUtil.success(sta);
    }


    // 查询mangodb中的月数据
    @GetMapping("/selectMonthly")
    public Result<Map<String,Object>> selectMonthly(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                    @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                    @RequestParam String time) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        Query query = new Query();
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        date.setDate(01);
        date.setHours(00);
        date.setMinutes(00);
        date.setSeconds(00);
        long ts = date.getTime()/1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        date1.setDate(calendar.getActualMaximum(Calendar.DATE));
        date1.setHours(23);
        date1.setMinutes(59);
        date1.setSeconds(59);
        long ts1 = date1.getTime()/1000;
        query.addCriteria(Criteria.where("timestamp").gte(ts).lte(ts1));
        List<WaterqualityMonthly> list = temp.find(query.skip((pageNumber-1)*pageSize).limit(pageSize),WaterqualityMonthly.class);
        for (WaterqualityMonthly w:list) {
            String riverName=waterQualityGradeService.selectRiverName(w.getCode());
            w.setRiverName(riverName);
        }
        Long sum=temp.count(query,WaterqualityMonthly.class);
        map.put("total", sum);
        map.put("rows",list);
        return ResponseMsgUtil.success(map);
    }

    // 查询mangodb中的日数据
    @GetMapping("/selectDaily")
    public Result<Map<String,Object>> selectDaily(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                       @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                           @RequestParam String time  ) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        Query query = new Query();
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        date.setHours(00);
        date.setMinutes(00);
        date.setSeconds(00);
        long ts = date.getTime()/1000;
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        date1.setHours(23);
        date1.setMinutes(59);
        date1.setSeconds(59);
        long ts1 = date1.getTime()/1000;
        query.addCriteria(Criteria.where("timestamp").gte(ts).lte(ts1));
        List<WaterqualityDaily> list = temp.find(query.skip((pageNumber-1)*pageSize).limit(pageSize),WaterqualityDaily.class);
        for (WaterqualityDaily w:list) {
            String riverName=waterQualityGradeService.selectRiverName(w.getCode());
            w.setRiverName(riverName);
        }
        Long sum=temp.count(query,WaterqualityDaily.class);
        map.put("total", sum);
        map.put("rows",list);
        return ResponseMsgUtil.success(map);
}


    // 查询mangodb中的实时数据
    @GetMapping("/selectRealTime")
    public Result<Map<String,Object>> selectRealTime(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                     @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                     @RequestParam String time) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        Query query = new Query();
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        date.setMinutes(00);
        date.setSeconds(00);
        long ts = date.getTime()/1000;
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        date1.setMinutes(59);
        date1.setSeconds(59);
        long ts1 = date1.getTime()/1000;
        query.addCriteria(Criteria.where("timestamp").gte(ts).lte(ts1));
        List<WaterqualityRealTime> list = temp.find(query.skip((pageNumber-1)*pageSize).limit(pageSize),WaterqualityRealTime.class);
        for (WaterqualityRealTime w:list) {
            String riverName=waterQualityGradeService.selectRiverName(w.getCode());
            w.setRiverName(riverName);
        }
        Long sum=temp.count(query,WaterqualityRealTime.class);
        map.put("total", sum);
        map.put("rows",list);
        return ResponseMsgUtil.success(map);
    }

    @GetMapping("/selectRiver")
    public Result<String> selectRiver(@RequestParam String name){
        return ResponseMsgUtil.success(waterQualityGradeService.selectRiver(name));
    }

}
