package com.luoguohua.finance.generator;

import com.luoguohua.finance.enums.PostgresToJavaType;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CodeGenerator {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Configuration freemarkerConfig;

    @PostConstruct
    public void init() {
        freemarkerConfig = new Configuration(new Version("2.3.31"));
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
        freemarkerConfig.setDefaultEncoding("UTF-8");
    }

    public void generate(String tablePrefix,String[] tableNames,String packageName,String author, String outputDir) throws SQLException, IOException, TemplateException {
        DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
        for (String tableName : tableNames) {
            String tableRemarks = "";
            ResultSet tables = metaData.getTables(null, null, tableName, null);
            if(tables.next()){
                tableRemarks = tables.getString("remarks");
            }
            // 查字段集的时候，不能去除前缀，否则会查不到对应表的字段数据
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            List<Map<String, Object>> columnsList = new ArrayList<>();
            while (columns.next()) {
                Map<String, Object> columnMap = new HashMap<>();
                columnMap.put("columnName", columns.getString(4)); // 替换为实际的列名获取方法
                columnMap.put("columnType", PostgresToJavaType.getJavaType(columns.getInt(5))); // 替换为实际的列类型获取方法
                columnMap.put("columnDesc", columns.getString(12)); // 替换为实际的列描述型获取方法
//                 可以根据需要获取更多列的信息并放入 columnMap 中
                columnsList.add(columnMap);
            }
            Map<String, Object> dataModel = new HashMap<>(6);
            dataModel.put("tableName", tableName);
            dataModel.put("tableRemarks", tableRemarks == null || "".equals(tableRemarks) ? tableName : tableRemarks);
            dataModel.put("className", toCamelCase(tableName, tablePrefix,true));
            dataModel.put("columns", columnsList);
            dataModel.put("packageName",packageName);
            dataModel.put("author",author);

            generateFile(dataModel, "po.ftl", outputDir + "/entity/" + toCamelCase(tableName,tablePrefix, true) + ".java");
            generateFile(dataModel, "repository.ftl", outputDir + "/repository/" + toCamelCase(tableName,tablePrefix, true) + "Repository.java");
            generateFile(dataModel, "service.ftl", outputDir + "/service/" + toCamelCase(tableName,tablePrefix, true) + "Service.java");
        }
    }

    private String removePrefix(String prefix,String tableName) {
        if (tableName.startsWith(prefix)) {
            return tableName.replace(prefix,"");
        }
        return tableName;
    }

    private void generateFile(Map<String, Object> dataModel, String templateName, String filePath) throws IOException, TemplateException {
        File outputFile = new File(filePath);
        outputFile.getParentFile().mkdirs();
        Template template = freemarkerConfig.getTemplate(templateName);
        try (Writer writer = new FileWriter(filePath)) {
            template.process(dataModel, writer);
        }
    }

    private String toCamelCase(String text,String tablePrefix,boolean capitalizeFirst) {
        text = removePrefix(tablePrefix,text);
        StringBuilder result = new StringBuilder();
        boolean capitalize = capitalizeFirst;
        for (char c : text.toCharArray()) {
            if (c == '_') {
                capitalize = true;
            } else {
                result.append(capitalize ? Character.toUpperCase(c) : c);
                capitalize = false;
            }
        }
        return result.toString();
    }
}
