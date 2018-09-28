package com.uhope.template.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.uhope.template.domain.SurfaceWaterGrade;
import com.uhope.template.service.SurfaceWaterGradeService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @PutMapping("/update")
    public Result<SurfaceWaterGrade> update(@RequestParam("id") String id,
                                            @RequestParam("popedom") String popedom,
                                            @RequestParam("grade") String grade,
                                            @RequestParam("parentid") String parentid) {
        SurfaceWaterGrade surfaceWaterGrade = new SurfaceWaterGrade();
        surfaceWaterGrade.setId(id);
        surfaceWaterGrade.setPopedom(popedom);
        surfaceWaterGrade.setGrade(grade);
        surfaceWaterGrade.setParentid(parentid);
        surfaceWaterGradeService.update(surfaceWaterGrade);
        return ResponseMsgUtil.success(surfaceWaterGrade);
    }

    @GetMapping("/detail")
    public Result<SurfaceWaterGrade> detail(@RequestParam String id) {
        SurfaceWaterGrade surfaceWaterGrade = surfaceWaterGradeService.get(id);
        return ResponseMsgUtil.success(surfaceWaterGrade);
    }

    @GetMapping("/list")
    public Result<PageInfo<SurfaceWaterGrade>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                    @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<SurfaceWaterGrade> list = surfaceWaterGradeService.find();
        PageInfo<SurfaceWaterGrade> pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }

    @PostMapping("/upload")
    public Map<String, String> upload(@RequestParam MultipartFile file,
                                      @RequestParam String parentid,
                                      HttpServletRequest req) throws IOException {
        SurfaceWaterGrade surfaceWaterGrade = new SurfaceWaterGrade();
        Map<String, String> map = new HashMap<>();
        InputStream in = file.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        //获取所有行
        int hang = sheetAt.getPhysicalNumberOfRows();
        for (int i = 2; i < hang; i++) {
            String id=null;
            String popedom = sheetAt.getRow(i).getCell(1).getStringCellValue();
            String grade=null;
            XSSFCell cell = sheetAt.getRow(i).getCell(2);
            cell.setCellType(CellType.STRING);
            grade=cell.getStringCellValue();
            surfaceWaterGrade.setParentid(parentid);
            surfaceWaterGrade.setPopedom(popedom);
            surfaceWaterGrade.setGrade(grade);
            surfaceWaterGrade.setId(UUID.randomUUID().toString().replace("-",""));
            surfaceWaterGradeService.insert(surfaceWaterGrade);
            map.put("code", "200");
        }
        return map;
    }

}
