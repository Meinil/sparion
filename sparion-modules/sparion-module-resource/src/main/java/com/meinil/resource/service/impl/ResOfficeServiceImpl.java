package com.meinil.resource.service.impl;

import com.meinil.resource.service.IResOfficeService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author meinil
 * @date 2025/4/15
 */
@Service
public class ResOfficeServiceImpl implements IResOfficeService {

    public static Long replaceVariable() {
        // 加载模板文件
//        XWPFDocument doc = new XWPFDocument(new FileInputStream("/Users/meinil/Downloads/模板 (1).docx"));
//
//        // 定义替换变量
//        Map<String, String> replacements = new HashMap<>();
//        replacements.put("${name}", "张三");
//        replacements.put("${date}", "2023-11-15");
//        replacements.put("${amount}", "¥5,000.00");
//
//        // 执行替换
//        for (Map.Entry<String, String> entry : replacements.entrySet()) {
//            replaceText(doc, entry.getKey(), entry.getValue());
//        }
//
//        // 保存结果
//        doc.write(new FileOutputStream("output.docx"));
//        doc.close();

        return null;
    }

    public static void main(String[] args) {
        replaceVariable();
    }
}
