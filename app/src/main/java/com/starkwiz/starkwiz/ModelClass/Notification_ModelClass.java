package com.starkwiz.starkwiz.ModelClass;

public class Notification_ModelClass {

    String id,notification_text;

    public Notification_ModelClass(String id, String notification_text) {
        this.id = id;
        this.notification_text = notification_text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotification_text() {
        return notification_text;
    }

    public void setNotification_text(String notification_text) {
        this.notification_text = notification_text;
    }
}
