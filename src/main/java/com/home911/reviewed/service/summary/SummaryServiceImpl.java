package com.home911.reviewed.service.summary;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import com.home911.reviewed.model.Summary;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class SummaryServiceImpl implements SummaryService {
    private static final Logger LOGGER = Logger.getLogger(SummaryServiceImpl.class.getCanonicalName());

    public SummaryServiceImpl() {
        ObjectifyService.register(Summary.class);
    }

    @Override
    public Summary getSummary(String id) {
        LOGGER.info("Getting summary for id:[" + id + "]");
        Summary summary = ofy().load().type(Summary.class).id(id).now();
        if (summary == null) {
            throw new NotFoundException();
        }
        return summary;
    }

    @Override
    public String saveSummary(Summary summary) {
        LOGGER.info("Saving summary: " + summary);
        String id = null;
        if (StringUtils.isEmpty(summary.getId()))
        {
            id = String.valueOf(System.currentTimeMillis());
            summary.setId(id);
        }
        ofy().save().entity(summary).now();
        return id;
    }

    @Override
    public List<Summary> getSummaries(int offset, int limit) {
        LOGGER.info("Getting summaries");
        List<Summary> summaries = new ArrayList<Summary>();
        Query<Summary> query = ofy().load().type(Summary.class).offset(offset).limit(limit);
        QueryResultIterator<Summary> iterator = query.iterator();
        while (iterator.hasNext()) {
            summaries.add(iterator.next());
        }

        return summaries;
    }

    @Override
    public List<Summary> searchSummaries(String field, String value, int offset, int limit) {
        LOGGER.info("Searching summaries for field[" + field + "] and value[" + value + "]");
        List<Summary> summaries = new ArrayList<Summary>();
        Query<Summary> query = ofy().load().type(Summary.class).filter(field, value).offset(offset).limit(limit);
        QueryResultIterator<Summary> iterator = query.iterator();
        while (iterator.hasNext()) {
            summaries.add(iterator.next());
        }

        return summaries;
    }
}
