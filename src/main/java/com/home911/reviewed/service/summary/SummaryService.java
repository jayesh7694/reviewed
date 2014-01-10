package com.home911.reviewed.service.summary;

import com.home911.reviewed.model.Summary;

import java.util.List;

public interface SummaryService {
    public List<Summary> getSummaries(int offset, int limit);
    public List<Summary> searchSummaries(String field, String value, int offset, int limit);
    public Summary getSummary(String id);
    public String saveSummary(Summary summary);
}
