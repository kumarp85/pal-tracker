package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class TimeEntryController {

    private final TimeEntryRepository repository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        repository = timeEntryRepository;
    }


    @RequestMapping(value ="/time-entries", method = RequestMethod.POST)
    public ResponseEntity<TimeEntry> create(TimeEntry timeEntry) {
        TimeEntry response = repository.create(timeEntry);
        return new ResponseEntity<TimeEntry>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value ="/time-entries", method = RequestMethod.GET)
    public ResponseEntity<TimeEntry> read(Long id) {
        TimeEntry response = repository.find(id);
        if(response != null) {
            return new ResponseEntity<TimeEntry>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<TimeEntry>(response, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value ="/time-entries", method = RequestMethod.GET)
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryResponse = repository.list();
        return new ResponseEntity<List<TimeEntry>>(timeEntryResponse, HttpStatus.OK);
    }

    @RequestMapping(value ="/time-entries", method = RequestMethod.PUT)
    public ResponseEntity<TimeEntry> update(long id, TimeEntry timeEntry) {
        TimeEntry response = repository.update(id, timeEntry);
        if(response != null) {
            return new ResponseEntity<TimeEntry>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value ="/time-entries", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(long id) {
        repository.delete(id);
        TimeEntry responseEntry = repository.find(id);
        if(responseEntry == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
