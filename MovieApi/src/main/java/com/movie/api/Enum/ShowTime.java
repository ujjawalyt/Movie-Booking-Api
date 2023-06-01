package com.movie.api.Enum;

public enum ShowTime {

	MORNING("09:00-11:30"),
    AFTERNOON("12:00-02:45"),
    EVENING("03:00-05:30"),
    NIGHT("06:00-09:00");

 	private final String displayName;

    ShowTime(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
