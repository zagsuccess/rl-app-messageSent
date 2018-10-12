package com.uhope.template.web;

import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.template.domain.SurfaceWaterGrade;
import com.uhope.template.service.SurfaceWaterGradeService;
import com.uhope.template.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 描述:
 * 条目控制类
 *
 * @author a4994
 * @create 2018-09-28 9:12
 */
@RestController
@RequestMapping("/v1/SurfaceWaterGrade")
public class SurfaceWaterGradeController {
    @Autowired
    private SurfaceWaterGradeService surfaceWaterGradeService;

    @PostMapping("/add")
    public Result<SurfaceWaterGrade> add(@RequestParam String popedom,
                                         @RequestParam String grade,
                                         @RequestParam String parentid) {
        SurfaceWaterGrade surfaceWaterGrade = new SurfaceWaterGrade();
        surfaceWaterGrade.setPopedom(popedom);
        surfaceWaterGrade.setGrade(grade);
        surfaceWaterGrade.setParentid(parentid);
        surfaceWaterGradeService.insert(surfaceWaterGrade);
        return ResponseMsgUtil.success(surfaceWaterGrade);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        surfaceWaterGradeService.remove(id);
        return ResponseMsgUtil.success();
    }

    @DeleteMapping("/deletelist")
    public Result deletelist(@RequestParam String parentid) {
        surfaceWaterGradeService.deletelist(parentid);
        return ResponseMsgUtil.success();
    }



    @PutMapping("/update")
    public Result<SurfaceWaterGrade> update(@RequestParam("id") String id,
                                            @RequestParam("popedom") String popedom,
                                            @RequestParam("grade") String grade) {
        SurfaceWaterGrade surfaceWaterGrade = new SurfaceWaterGrade();
        surfaceWaterGrade.setId(id);
        surfaceWaterGrade.setPopedom(popedom);
        surfaceWaterGrade.setGrade(grade);
        surfaceWaterGradeService.update(surfaceWaterGrade);
        return ResponseMsgUtil.success(surfaceWaterGrade);
    }

    @GetMapping("/detail")
    public Result<SurfaceWaterGrade> detail(@RequestParam String id) {
        SurfaceWaterGrade surfaceWaterGrade = surfaceWaterGradeService.get(id);
        return ResponseMsgUtil.success(surfaceWaterGrade);
    }

    /*@GetMapping("/list")
    public Result<PageInfo<SurfaceWaterGrade>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                    @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                    @RequestParam String parentid) {
        return ResponseMsgUtil.success(surfaceWaterGradeService.list(pageNumber,pageSize,parentid));
    }*/

    @GetMapping("/list")
    public Result<List<SurfaceWaterGrade>> list(@RequestParam String parentid){
        return ResponseMsgUtil.success(surfaceWaterGradeService.list(parentid));
    }

    @PostMapping("/upload")
    public Result<Integer> upload(@RequestParam MultipartFile file,
                                      @RequestParam String parentid,
                                      HttpServletRequest req) throws IOException {
        int code=100;
        SurfaceWaterGrade surfaceWaterGrade = new SurfaceWaterGrade();
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
            String id=null;
            String popedom = sheetAt.getRow(i).getCell(1).getStringCellValue();
            String grade=null;
            Cell cell = sheetAt.getRow(i).getCell(2);
            cell.setCellType(CellType.STRING);
            grade=cell.getStringCellValue();
            surfaceWaterGrade.setParentid(parentid);
            surfaceWaterGrade.setPopedom(popedom);
            surfaceWaterGrade.setGrade(grade);
            surfaceWaterGrade.setId(UUID.randomUUID().toString().replace("-",""));
            surfaceWaterGradeService.insert(surfaceWaterGrade);
            code=200;
        }
        return ResponseMsgUtil.success(code);
    }

    //   查询所有的区
    @GetMapping("/districtlist")
    public Result<List<String>> districtlist(){
        return ResponseMsgUtil.success(surfaceWaterGradeService.districtlist());
    }

    //   查询以增加的区
    @GetMapping("/haveDistrict")
    public Result<Integer> haveDistrict(@RequestParam String popedom,
                                        @RequestParam String parentid){
        // 10表示列表中没有这个区  可以增加     11表示有这个区 不能增加
        int sta=10;
        List<SurfaceWaterGrade> list = surfaceWaterGradeService.list(parentid);
        for (SurfaceWaterGrade s:list ) {
            if (popedom.equals(s.getPopedom())){
                sta=11;
            }
        }
        return ResponseMsgUtil.success(sta);
    }
}
