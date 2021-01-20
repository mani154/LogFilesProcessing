package org.codejudge.sb.service;

import org.codejudge.sb.controller.ExceptionResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class LogProcessingService {

    public ExceptionResponse processLogs(List<String> logUrls, int parallelFileProcessingCount) throws IOException {
        return processSingleLogFile(logUrls.get(0));
    }

    private ExceptionResponse processSingleLogFile(String logFileUrl) throws IOException {
        URL url = new URL(logFileUrl);
        Map<String, Map<String, Integer>> result = new TreeMap<>();
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
            Map<String, Integer> map = result.computeIfAbsent(timeBucket, x -> new TreeMap<>());
            map.put(values[2], map.getOrDefault(values[2], 0) + 1);
        }
        read.close();
        return ExceptionResponse.create(result);
    }

    private String getTimeBucket(int hour, int minutes) {
        int factor = minutes / 15;
        int var = factor * 15;
        int var2 = (factor+1) * 15;
        int hour2 = (var2 == 60) ? hour + 1: hour;

        String from = getHour(hour) + ":" + (var == 0 ? "00" : "" + var);
        String to = getHour(hour2) + ":" + (var2 == 60 ? "00" : "" + var2);
        to = to.equals("24:00") ? "00:00" : to;
        return from + "-" + to;
    }

    private String getHour(int hour) {
        return hour < 10 ? "0" + hour : "" + hour;
    }

}

/*
Multiple Files:
    Check if separate map works
    Merge by time bucket
        Within time bucket, merge by exception
        Add the count
    Union of buckets of both files

    REST APIs, Spring Boot Working
    Difference between APIs and main()

Limitations:
    My code logic omits date of logs created. Only time is considered.
 */