package org.codejudge.sb.service;

import org.codejudge.sb.controller.model.ExceptionResponse;
import org.codejudge.sb.controller.model.LogResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class LogProcessingService {

    public LogResponse processLogs(List<String> logUrls, int parallelFileProcessingCount) throws IOException {
        for (String url : logUrls) {
            ExceptionResponse exceptionResponse = processSingleLogFile(url);
        }
        return null;
    }

    private ExceptionResponse processSingleLogFile(String logFileUrl) throws IOException {
        URL url = new URL(logFileUrl);
        BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = read.readLine()) != null) {
            System.out.println(line);
            String[] values = line.trim().split("\\s+");
            // 2nd string is the timestamp
            Instant timestamp = Instant.ofEpochSecond(Long.parseLong(values[1]));
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(timestamp, ZoneOffset.UTC);
            String timeBucket = getTimeBucket(zonedDateTime.getHour(), zonedDateTime.getMinute());
            // 3rd string is the exception
            System.out.println(timeBucket + "-" + values[2]);
        }
        read.close();
        return null;
    }

    private String getTimeBucket(int hour, int minutes) {
        int offset = minutes % 10;
        int factor = minutes / 10;
        return offset <= 5 ? hour + ":" + (factor * 10) : hour + ":" + (factor * 10 + 5);
    }
}
