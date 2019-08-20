package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;

import java.util.List;

public interface TimeEntryRepository {
    TimeEntry create(TimeEntry timeEntry);
    TimeEntry find(Long id);
    TimeEntry update(Long id, TimeEntry timeEntry);
    public List<TimeEntry> list();
    public void delete(Long id);


}
