package com.landisgyr.e4s.omcore;

import java.util.Timer;
import java.util.TimerTask;

/** Each OMTimer runs on its own thread: used for T1, T2 timers in OM FSM */
// Uses java.util.Timer class as basis to implement this class
public class OMTimer {
    public enum TimerState { INACTIVE, RUNNING, EXPIRED};

    public Timer timer;
    public String name;
    public TimerState timerState;
    public long intervalmS;
    public TimerTask task;

    /** Constructor for a new timer (without known interval) */
    public OMTimer(String name, TimerTask task) {
        this.name = name;
        timer = new Timer(name);
        this.task = task;
        timerState = TimerState.INACTIVE;
    }

    /** Constructor for a new timer (with known interval) */
    public OMTimer(String name, long intervalmS, TimerTask task) {
        this(name, task);
        this.intervalmS = intervalmS;
    }


    /** Sets the desired timer interval (in milliseconds) */
    public void setInterval(long intervalmS) {
        this.intervalmS = intervalmS;
    }

    /** Resets the timer back to INACTIVE state */
    public void reset() {
        timerState = TimerState.INACTIVE;
    }


    /** Starts the timer */
    public void start() {
        timer.schedule(task, intervalmS);
        timerState = TimerState.RUNNING;
    }


    /** Cancels the timer */
    public void cancel() {
        timer.cancel();
        timerState = TimerState.INACTIVE;
    }

}
