package com.fystock.bigdata.cloud.utils;

import com.fystock.bigdata.cloud.constants.CityCache;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 文件操作类
 *
 * @author He.Yong
 * @since 2021-03-11 14:15:18
 */
@Slf4j
public class FileUtil {

    public static String readResourceFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = CityCache.class.getClassLoader().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            log.error("******读取文件失败******* ", e);
        }
        return sb.toString();
    }
}