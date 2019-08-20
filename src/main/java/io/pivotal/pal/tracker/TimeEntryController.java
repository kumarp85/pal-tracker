package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private final TimeEntryRepository repository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        repository = timeEntryRepository;
    }


    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry response = repository.create(timeEntry);
        return new ResponseEntity<TimeEntry>(response, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry response = repository.find(id);
        if(response != null) {
            return new ResponseEntity<TimeEntry>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<TimeEntry>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryResponse = repository.list();
        return new ResponseEntity<List<TimeEntry>>(timeEntryResponse, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry response = repository.update(id, timeEntry);
        if(response != null) {
            return new ResponseEntity<TimeEntry>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        repository.delete(id);
        TimeEntry responseEntry = repository.find(id);
        if(responseEntry == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
