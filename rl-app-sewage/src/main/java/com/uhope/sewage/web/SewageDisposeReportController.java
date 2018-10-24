package com.uhope.sewage.web;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.sewage.domain.SewageDisposeReport;
import com.uhope.sewage.service.SewageDisposeReportService;
import com.uhope.sewage.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * 描述:
 * 条目控制类
 *
 * @author a4994
 * @create 2018-09-28 9:12
 */
@RestController
@RequestMapping("/v1/SewageDisposeReport")
public class SewageDisposeReportController {
    @Autowired
    private SewageDisposeReportService sewageDisposeReportService;

    @PostMapping("/add")
    public Result<SewageDisposeReport> add(@RequestParam String name,
                                           @RequestParam String region,
                                           @RequestParam String assess,
                                           @RequestParam Integer status,
                                           @RequestParam String reason,
                                           @RequestParam String parentid) {
        SewageDisposeReport sewageDisposeReport = new SewageDisposeReport();
        sewageDisposeReport.setId(UUID.randomUUID().toString().replace("-",""));
        sewageDisposeReport.setCode(sewageDisposeReport.getId());
        sewageDisposeReport.setName(name);
        sewageDisposeReport.setRegion(region);
        sewageDisposeReport.setAssess(assess);
        sewageDisposeReport.setStatus(status);
        sewageDisposeReport.setReason(reason);
        sewageDisposeReport.setParentid(parentid);
        sewageDisposeReportService.insert(sewageDisposeReport);
        return ResponseMsgUtil.success(sewageDisposeReport);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        sewageDisposeReportService.remove(id);
        return ResponseMsgUtil.success();
    }

    @DeleteMapping("/deletelist")
    public Result deletelist(@RequestParam String parentid) {
        sewageDisposeReportService.deletelist(parentid);
        return ResponseMsgUtil.success();
    }



    @PutMapping("/update")
    public Result<SewageDisposeReport> update(@RequestParam String id,
                                              @RequestParam String name,
                                              @RequestParam String region,
                                              @RequestParam String assess,
                                              @RequestParam Integer status,
                                              @RequestParam String reason) {
        SewageDisposeReport sewageDisposeReport = new SewageDisposeReport();
        sewageDisposeReport.setId(id);
        sewageDisposeReport.setName(name);
        sewageDisposeReport.setRegion(region);
        sewageDisposeReport.setAssess(assess);
        sewageDisposeReport.setStatus(status);
        sewageDisposeReport.setReason(reason);
        sewageDisposeReportService.update(sewageDisposeReport);
        return ResponseMsgUtil.success(sewageDisposeReport);
    }

    @GetMapping("/detail")
    public Result<SewageDisposeReport> detail(@RequestParam String id) {
        SewageDisposeReport surfaceWaterGrade = sewageDisposeReportService.get(id);
        return ResponseMsgUtil.success(surfaceWaterGrade);
    }

    @GetMapping("/list")
    public Result<PageInfo<SewageDisposeReport>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                      @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                      @RequestParam String parentid) {
        return ResponseMsgUtil.success(sewageDisposeReportService.list(pageNumber,pageSize,parentid));
    }


    @PostMapping("/upload")
    public Result<Integer> upload(@RequestParam MultipartFile file,
                                      @RequestParam String parentid,
                                      HttpServletRequest req) throws IOException {
        int code=100;
        SewageDisposeReport sewageDisposeReport = new SewageDisposeReport();
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
            String name = sheetAt.getRow(i).getCell(1).getStringCellValue();
            String region=sheetAt.getRow(i).getCell(2).getStringCellValue();
            String assess=sheetAt.getRow(i).getCell(3).getStringCellValue();
            String sta=sheetAt.getRow(i).getCell(4).getStringCellValue();
            if ("是".equals(sta)){
                sewageDisposeReport.setStatus(0);
            }else{
                sewageDisposeReport.setStatus(1);
            }
            String reason=sheetAt.getRow(i).getCell(5).getStringCellValue();
            sewageDisposeReport.setId(UUID.randomUUID().toString().replace("-",""));
            sewageDisposeReport.setCode(sewageDisposeReport.getId());
            sewageDisposeReport.setName(name);
            sewageDisposeReport.setRegion(region);
            sewageDisposeReport.setAssess(assess);
            sewageDisposeReport.setReason(reason);
            sewageDisposeReport.setParentid(parentid);
            sewageDisposeReportService.insert(sewageDisposeReport);
            code=200;
        }
        return ResponseMsgUtil.success(code);
    }

    //   查询所有的污水处理厂
    @GetMapping("/selectWorks")
    public Result<List<String>> selectWorks(){
        return ResponseMsgUtil.success(sewageDisposeReportService.selectWorks());
    }


    //   查询所有的区
    @GetMapping("/districtlist")
    public Result<List<String>> districtlist(){
        return ResponseMsgUtil.success(sewageDisposeReportService.districtlist());
    }

    //   查询能否添加指定处理厂
    @GetMapping("/haveSewage")
    public Result<Integer> haveDistrict(@RequestParam String name,
                                        @RequestParam String parentid){
        // 10表示列表中没有这个区  可以增加     11表示有这个区 不能增加
        int sta=10;
        List<SewageDisposeReport> list = sewageDisposeReportService.haveSewage(parentid);
        for (SewageDisposeReport s:list ) {
            if (name.equals(s.getName())){
                sta=11;
            }
        }
        return ResponseMsgUtil.success(sta);
    }
}
