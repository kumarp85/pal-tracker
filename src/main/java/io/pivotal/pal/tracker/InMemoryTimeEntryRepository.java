package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private final Map<Long, TimeEntry> timeEntries = new HashMap<>();
    private Long count = 0L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = ++count;
        timeEntry.setId(id);
        timeEntries.put(id, timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntries.get(id);
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry timeEnteryToUpdate = timeEntries.get(id);
        timeEnteryToUpdate.setProjectId(timeEntry.getId());
        timeEnteryToUpdate.setUserId(timeEntry.getUserId());
        timeEnteryToUpdate.setDate(timeEntry.getDate());
        timeEnteryToUpdate.setHours(timeEntry.getHours());
        return timeEnteryToUpdate;
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntriesList = new ArrayList<>();
        for (Long id:timeEntries.keySet()) {
            timeEntriesList.add(timeEntries.get(id));
        }
        return timeEntriesList;
    }

    @Override
    public void delete(Long id) {
        timeEntries.remove(id);
    }
}
