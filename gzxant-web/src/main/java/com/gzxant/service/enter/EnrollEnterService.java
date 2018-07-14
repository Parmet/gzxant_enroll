package com.gzxant.service.enter;

import com.baomidou.mybatisplus.mapper.Condition;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.gzxant.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzxant.entity.enroll.enter.EnrollEnter;
import com.gzxant.dao.enroll.enter.EnrollEnterDao;
import com.gzxant.service.enroll.enter.IEnrollEnterService;
import com.gzxant.base.service.impl.BaseService;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 参赛者信息 服务实现类
 * </p>
 *
 * @author tecty
 * @since 2018-07-06
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EnrollEnterService extends BaseService<EnrollEnterDao, EnrollEnter> implements IEnrollEnterService {
    private Sheet sheet=null;
    private Workbook wb = null;
    /**
     * 登录
     *
     * @param personnel_id
     * @return
     */
    @Override
    public EnrollEnter findbyIdEnterdate(String personnel_id) {
        EnrollEnter enrollEnter = selectOne(Condition.create().in("personnel_id",personnel_id));
        return enrollEnter;
    }
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean insertBean(EnrollEnter enrollEnter){
        return insert(enrollEnter);
    }
    /**
     * 导出数据
     *
     * @param fileName
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean batchImport(String fileName) throws Exception {
        File file = new File(fileName);
        InputStream targetStream = new FileInputStream(file);
        List<EnrollEnter> userList = new ArrayList<EnrollEnter>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        //InputStream is = targetStream.getInputStream();

        if (isExcel2003) {
            wb = new HSSFWorkbook(targetStream);
        } else {
            wb = new XSSFWorkbook(targetStream);
        }
        sheet = wb.getSheetAt(0);
        int i=sheet.getLastRowNum();

        EnrollEnter enrollEnter;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            enrollEnter = new EnrollEnter();
            if (row.getCell(0).getCellType() != 1) {
                throw new Exception("导入失败(第" + (r + 1) + "行,姓名请设为文本格式)");
            }
            //获取参赛者id
            String numbers = row.getCell(0).getStringCellValue();

            if (StringUtils.isEmpty(numbers)) {
                throw new Exception("导入失败(第" + (r + 1) + "行,姓名未填写)");
            }

            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            //姓名
            String name = row.getCell(1).getStringCellValue();
            if (StringUtils.isEmpty(name)) {
                throw new Exception("导入失败(第" + (r + 1) + "行,电话未填写)");
            }
            //获取地点
            String palce = row.getCell(4).getStringCellValue();
            if (StringUtils.isEmpty(palce)) {
                throw new Exception("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
            }

            //获取状态
            String state = row.getCell(8).getStringCellValue();
            if (StringUtils.isEmpty(state)) {
                throw new Exception("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
            }

            //获取分数
            row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
            String fraction = row.getCell(9).getStringCellValue()+"";
            if (StringUtils.isEmpty(fraction)) {
                throw new Exception("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
            }
            //获取歌曲
            String song = row.getCell(10).getStringCellValue();
            if (StringUtils.isEmpty(song)) {
                throw new Exception("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
            }
            //获取类型
            String type = row.getCell(11).getStringCellValue();
            if (StringUtils.isEmpty(type)) {
                throw new Exception("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
            }

            enrollEnter.setName(name);
            enrollEnter.setNumbers(numbers+"");
            enrollEnter.setFraction(fraction);
            enrollEnter.setPlace(palce);
            enrollEnter.setSong(song);
            enrollEnter.setType(type);
            if (state.equals("成功")) {
                enrollEnter.setState("Y");
            } else {
                enrollEnter.setState("N");
            }

            userList.add(enrollEnter);
        }
        sheet=null;
        wb=null;
        for (EnrollEnter userResord : userList) {
            insert(userResord);

        }
        return true;
    }

}
