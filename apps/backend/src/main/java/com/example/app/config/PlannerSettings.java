package com.example.app.config;

import java.time.LocalTime;

public class PlannerSettings {
    private static PlannerSettings instance = null;

    public static PlannerSettings getInstance() {
        if (instance == null) {
            instance = new PlannerSettings();
        }
        return instance;
    }

    // parameters used for plan generation
    private int maxCliquesCount = 30;

    private boolean ignoreLectures = false; // if true, all iteractions related to lectures will be ignored (except
                                            // clashes)
    private boolean allowLectureClashes = false; // if true, clashes will ignore lectures.
    private int minLunchDuration = 30; // enforces a break of at least this many minutes between classes during 10:30
                                       // and 13:30

    private LocalTime firstClassTime = LocalTime.of(9, 00); // no classes start before this time
    private LocalTime lastClassTime = LocalTime.of(23, 00); // no classes end before this time
    private boolean needVacancy = false; // if true, the planner will try to find solutions with at least one vacancy

    public int getMaxCliquesCount() {
        return maxCliquesCount;
    }

    public void setMaxCliquesCount(int maxCliquesCount) {
        this.maxCliquesCount = maxCliquesCount;
    }

    public boolean isIgnoreLectures() {
        return ignoreLectures;
    }

    public void setIgnoreLecture(boolean ignoreLectures) {
        this.ignoreLectures = ignoreLectures;
    }

    public boolean isAllowLectureClashes() {
        return allowLectureClashes;
    }

    public void setAllowLectureClashes(boolean allowLectureClashes) {
        this.allowLectureClashes = allowLectureClashes;
    }

    public int getMinLunchDuration() {
        return minLunchDuration;
    }

    public void setMinLunchDuration(int minLunchDuration) {
        this.minLunchDuration = minLunchDuration;
    }

    public LocalTime getFirstClassTime() {
        return firstClassTime;
    }

    public void setFirstClassTime(LocalTime firstClassTime) {
        this.firstClassTime = firstClassTime;
    }

    public LocalTime getLastClassTime() {
        return lastClassTime;
    }

    public void setLastClassTime(LocalTime lastClassTime) {
        this.lastClassTime = lastClassTime;
    }

    public boolean isNeedVacancy() {
        return needVacancy;
    }

    public void setNeedVacancy(boolean needVacancy) {
        this.needVacancy = needVacancy;
    }
}
