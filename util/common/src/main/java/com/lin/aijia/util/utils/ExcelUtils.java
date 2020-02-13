package com.lin.aijia.util.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.yyfly.common.exception.GlobalException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * excel工具类
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class ExcelUtils {

    /**
     * Export excel.
     *
     * @param list           the list
     * @param title          the title
     * @param sheetName      the sheet name
     * @param pojoClass      the pojo class
     * @param fileName       the file name
     * @param isCreateHeader the is create header
     * @param response       the response
     * @author : yangjunqing / 2019-02-28
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }

    /**
     * Export excel.
     *
     * @param list      the list
     * @param title     the title
     * @param pojoClass the pojo class
     * @param fileName  the file name
     * @param response  the response
     * @author : yangjunqing / 2019-02-28
     */
    public static void exportExcel(List<?> list, String title, Class<?> pojoClass, String fileName, HttpServletResponse response){
        ExportParams exportParams = new ExportParams();
        exportParams.setTitle(title);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    /**
     * Export excel.
     *
     * @param list      the list
     * @param pojoClass the pojo class
     * @param fileName  the file name
     * @param response  the response
     * @author : yangjunqing / 2019-02-28
     */
    public static void exportExcel(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams());
    }

    /**
     * Export excel.
     *
     * @param list      the list
     * @param title     the title
     * @param sheetName the sheet name
     * @param pojoClass the pojo class
     * @param fileName  the file name
     * @param response  the response
     * @author : yangjunqing / 2019-02-28
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    /**
     * Export excel.
     *
     * @param list     the list
     * @param fileName the file name
     * @param response the response
     * @author : yangjunqing / 2019-02-28
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }

    /**
     * Default export.
     *
     * @param list         the list
     * @param pojoClass    the pojo class
     * @param fileName     the file name
     * @param response     the response
     * @param exportParams the export params
     * @author : yangjunqing / 2019-02-28
     */
    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null){
            downLoadExcel(fileName, response, workbook);
        }
    }

    /**
     * Down load excel.
     *
     * @param fileName the file name
     * @param response the response
     * @param workbook the workbook
     * @author : yangjunqing / 2019-02-28
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * Default export.
     *
     * @param list     the list
     * @param fileName the file name
     * @param response the response
     * @author : yangjunqing / 2019-02-28
     */
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null){
            downLoadExcel(fileName, response, workbook);
        }
    }

    /**
     * Import excel list.
     *
     * @param <T>        the type parameter
     * @param filePath   the file path
     * @param titleRows  the title rows
     * @param headerRows the header rows
     * @param pojoClass  the pojo class
     * @return the list
     * @author : yangjunqing / 2019-02-28
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new GlobalException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return list;
    }

    /**
     * Import excel list.
     *
     * @param <T>        the type parameter
     * @param file       the file
     * @param titleRows  the title rows
     * @param headerRows the header rows
     * @param pojoClass  the pojo class
     * @return the list
     * @author : yangjunqing / 2019-02-28
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new GlobalException("excel文件不能为空");
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
        return list;
    }
}
