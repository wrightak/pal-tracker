package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private List<TimeEntry> timeEntries = new ArrayList<>();
    private long autoId = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry constructedTimeEntry = new TimeEntry(
                autoId,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntries.add(constructedTimeEntry);
        autoId++;
        return constructedTimeEntry;
    }

    public TimeEntry find(long timeEntryId) {
        return timeEntries.stream()
                .filter(timeEntry -> timeEntry.getId() == timeEntryId)
                .findFirst()
                .orElse(null);
    }

    public List<TimeEntry> list() {
        return timeEntries;
    }

    public TimeEntry update(long id, TimeEntry updatedEntry) {
        boolean found = timeEntries.removeIf(timeEntry -> timeEntry.getId() == id);
        if (!found) {
            return null;
        }
        TimeEntry timeEntry = new TimeEntry(
                id,
                updatedEntry.getProjectId(),
                updatedEntry.getUserId(),
                updatedEntry.getDate(),
                updatedEntry.getHours()
        );
        timeEntries.add(timeEntry);
        return timeEntry;
    }

    public void delete(long id) {
        timeEntries.removeIf(timeEntry -> timeEntry.getId() == id);
    }
}
