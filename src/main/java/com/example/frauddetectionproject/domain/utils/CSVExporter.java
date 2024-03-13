package com.example.frauddetectionproject.domain.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVExporter {

    private static final Logger logger = LoggerFactory.getLogger(CSVExporter.class);

    public static <T> void exportToCSVFile(List<T> dataList, String fileName) {
        saveCSVToFile(dataList, fileName);
    }

    public static <T> void exportToCSVHttpResponse(List<T> dataList, HttpServletResponse response, String fileName) {
        sendCSVToHttpResponse(dataList, response, fileName);
    }

    private static <T> void saveCSVToFile(List<T> dataList, String fileName) {
        if (dataList == null || dataList.isEmpty()) {
            logger.error("No data to export to CSV.");
            return;
        }

        try (Writer writer = Files.newBufferedWriter(Paths.get(fileName + ".csv"))) {
            writeDataToCsv(dataList, writer);
            logger.info("CSV file successfully created: " + fileName + ".csv");
        } catch (IOException e) {
            logger.error("An error occurred while exporting data to CSV: {}", e.getMessage(), e);
        }
    }

    private static <T> void sendCSVToHttpResponse(List<T> dataList, HttpServletResponse response, String fileName) {
        if (dataList == null || dataList.isEmpty()) {
            logger.error("No data to export to CSV.");
            return;
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".csv\"");

        try (Writer writer = response.getWriter()) {
            writeDataToCsv(dataList, writer);
            logger.info("CSV response successfully created.");
        } catch (IOException e) {
            logger.error("An error occurred while exporting data to CSV response: {}", e.getMessage(), e);
        }
    }

    private static <T> void writeDataToCsv(List<T> dataList, Writer writer) throws IOException {
        T firstItem = dataList.get(0);
        String[] header = generateHeader(firstItem);
        writer.write(String.join(",", header) + "\n");

        for (T item : dataList) {
            String[] data = generateData(item);
            writer.write(String.join(",", data) + "\n");
        }
    }

    private static <T> String generateCSVFileName(List<T> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return "default";
        }

        T firstItem = dataList.get(0);
        String className = firstItem.getClass().getSimpleName();
        long timestamp = System.currentTimeMillis();

        return className + "_" + timestamp;
    }

    private static <T> String[] generateHeader(T item) {
        Field[] fields = item.getClass().getDeclaredFields();
        String[] header = new String[fields.length];
        int index = 0;
        for (Field field : fields) {
            header[index++] = field.getName();
        }
        return header;
    }

    private static <T> String[] generateData(T item) {
        Field[] fields = item.getClass().getDeclaredFields();
        String[] data = new String[fields.length];
        int index = 0;
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(item);
                data[index++] = value != null ? value.toString() : "";
            } catch (IllegalAccessException e) {
                logger.error("An error occurred while accessing object fields: {}", e.getMessage(), e);
            }
        }
        return data;
    }
}
