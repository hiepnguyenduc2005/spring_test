package com.example.runner.run;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(int id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    Run create(Run run) {
        Run newRun = new Run(run.id(),
                run.title(),
                run.startedOn(),
                run.completedOn(),
                run.miles(),
                run.location());

        runs.add(newRun);
        return newRun;
    }

    void update(Run run, int id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete(int id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.remove(existingRun.get());
        }
    }


    @PostConstruct
    private void init() {
        runs.add(new Run(
                1,
                "Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                5,
                Location.OUTDOOR));

        runs.add(new Run(
                2,
                "Evening Treadmill",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                4,
                Location.INDOOR));
    }
}
