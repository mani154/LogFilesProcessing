package org.codejudge.sb.controller.model;

import lombok.ToString;
import org.codejudge.sb.controller.ErrorResponse;
import java.util.List;

@ToString
public class LogRequest {
    List<String> logFiles;
    int parallelFileProcessingCount;

    public List<String> getLogFiles() {
        return logFiles;
    }

    public int getParallelFileProcessingCount() {
        return parallelFileProcessingCount;
    }

    public void setLogFiles(List<String> logFiles) {
        this.logFiles = logFiles;
    }

    public void setParallelFileProcessingCount(int parallelFileProcessingCount) {
        this.parallelFileProcessingCount = parallelFileProcessingCount;
    }

    public ErrorResponse validate() {
        if (parallelFileProcessingCount <= 0) {
            return new ErrorResponse("failure", "Parallel File Processing count must be greater than zero!");
        } else if (logFiles == null || logFiles.size() < parallelFileProcessingCount) {
            return new ErrorResponse("failure", "Log files cannot be empty or less than parallelFileProcessingCount");
        } else {
            return null;
        }
    }
}