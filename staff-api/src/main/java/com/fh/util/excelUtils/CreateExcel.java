package com.fh.util.excelUtils;

import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CreateExcel {


    public static String createExcel(List<?> data,ExcelBean excelBean) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            //声明工作簿
        XSSFWorkbook workbook=new XSSFWorkbook();
        //新建sheet页

        XSSFSheet sheet=workbook.createSheet(excelBean.getSheetName());
        //创建标题行
        XSSFRow titleRow=sheet.createRow(0);
        XSSFCellStyle titleStyle= PoiCellStyle.titleStyle(workbook);
        //创建单元格并赋值
        XSSFCell titleRowCell = titleRow.createCell(0);
        titleRowCell.setCellValue(excelBean.getTitle());
        titleRowCell.setCellStyle(titleStyle);

        //合并单元格
        CellRangeAddress rangeAddress=new CellRangeAddress(0,0,0,excelBean.getColumns().size()-1);
        sheet.addMergedRegion(rangeAddress);

        //创建列头
        XSSFRow columnRow=sheet.createRow(1);
        List<String> columnNames=excelBean.getColumnNames();
        XSSFCellStyle columnStyle=PoiCellStyle.colunmStyle(workbook);
        for(int i=0;i<columnNames.size();i++){
            String columnName=columnNames.get(i);
            XSSFCell cell = columnRow.createCell(i);
            cell.setCellValue(columnName);
            cell.setCellStyle(columnStyle);
        }

        //通过java的反射机制获取值
        List<String> columns = excelBean.getColumns();
        for(int i=0;i<data.size();i++){
            Object obj=data.get(i);

            //穿件数据行
            XSSFRow dataRow=sheet.createRow(i+2);
            //创建单元格
            for(int j=0;j<columns.size();j++){
                XSSFCell dataCell=dataRow.createCell(j);

                //如何给单元格的赋值
                //获取一个get方法名
                String methodName=getMethodName(columns.get(j));
                //获取一个方法
                Method method=obj.getClass().getMethod(methodName);
                //执行这个方法
                Object dataObj = method.invoke(obj);
                //判断返回值类型
                XSSFCellStyle dataStyle=PoiCellStyle.dataStyle(workbook);
                if(dataObj instanceof String){
                    dataCell.setCellValue((String) dataObj);
                    dataCell.setCellStyle(dataStyle);
                }else if(dataObj instanceof Date){
                    XSSFDataFormat dataFormat= workbook.createDataFormat();
                    dataStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd"));
                    dataCell.setCellValue((Date) dataObj);
                    dataCell.setCellStyle(dataStyle);
                }else if (dataObj instanceof Integer){
                    dataCell.setCellValue((Integer)dataObj);
                    dataCell.setCellStyle(dataStyle);
                }else if (dataObj instanceof BigDecimal){
                    dataCell.setCellValue(String.valueOf((BigDecimal)dataObj));
                    dataCell.setCellStyle(dataStyle);
                }
            }
        }
        for(int i=0;i<columns.size();i++){
            sheet.autoSizeColumn((short) i);
            // 解决自动设置列宽中文失效的问题
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }
        return uploadExcel(workbook,excelBean);
    }

    /**
     * 上传到指定的文件家中
     * @param workbook
     * @param bean
     * @return
     */
    public static String uploadExcel(XSSFWorkbook workbook,ExcelBean bean){
        //创建文件目录
        String realPath=createMkdir(bean);
        //获取新的文件名
        String newFileName=createFileName();
        //把一个workbook变成一个输入流
        ByteArrayOutputStream bos=null;
        // 输出流
        FileOutputStream fos = null;
        try {
            fos=new FileOutputStream(realPath+ "/" + newFileName);
            bos=new ByteArrayOutputStream();
            workbook.write(bos);
            byte[] bytes=bos.toByteArray();
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "commons/"+bean.getMkdir()+ "/" + newFileName;
    }

    /**
     * 生成新的文件名
     * @return
     */
    public static String createFileName(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid+".xlsx";
    }
    /**
     * 检查文件目录是否存在
     * 不存创建文件目录
     * @param bean
     */
    public static  String createMkdir(ExcelBean bean){
        HttpServletRequest request=((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
        //获取项目在你电脑硬盘的绝对路径
        //d://tomcat/ssm/
        String realPath=request.getServletContext().getRealPath("/");
        realPath= realPath+"commons/"+bean.getMkdir();

        //检查文件路径是否存在不存在进行创建
        File file=new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        return realPath;
    }

    public static  String getMethodName(String column){

        return "get"+column.substring(0,1).toUpperCase()+column.substring(1);
    }
}
