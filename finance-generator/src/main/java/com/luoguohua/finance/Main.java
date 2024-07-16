package com.luoguohua.finance;

import com.luoguohua.finance.config.GeneratorConfig;
import com.luoguohua.finance.generator.CodeGenerator;
import freemarker.template.TemplateException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @Version 1.0
 * @Author: luoguohua
 * @Date: 2024/7/10 16:16
 * Content:
 */
public class Main {
    public static void main(String[] args) throws SQLException, TemplateException, IOException {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GeneratorConfig.class)) {
            JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
            CodeGenerator generator = context.getBean(CodeGenerator.class);

            String[] tableNames = {"t_sys_user"}; // replace with your table names
            String outputDir = "E:\\genrater"; // replace with your output directory
            String tablePrefix = "t_";

            generator.generate(tablePrefix,tableNames, "com.luoguohua.finance.admin","luogh",outputDir);
            openGeneratedFolder(outputDir);
        }
    }

    private static void openGeneratedFolder(String folderPath) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Runtime rt = Runtime.getRuntime();
            if (os.contains("win")) {
                rt.exec("explorer " + folderPath);
            } else if (os.contains("mac")) {
                rt.exec("open " + folderPath);
            } else if (os.contains("nix") || os.contains("nux")) {
                rt.exec("xdg-open " + folderPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}