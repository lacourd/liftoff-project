package org.launchcode.liftoffproject.models;

public enum DayOfTheWeek {
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday")
    
private final String displayName;

EventType(String displayName) {
   this.displayName = displayName;
}

public String getDisplayName() {
   return displayName;
}
}
