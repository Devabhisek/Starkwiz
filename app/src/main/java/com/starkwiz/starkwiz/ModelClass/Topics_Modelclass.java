package com.starkwiz.starkwiz.ModelClass;

public class Topics_Modelclass {
    String id,category,topic,cls;

    public Topics_Modelclass(String id, String category, String topic, String cls) {
        this.id = id;
        this.category = category;
        this.topic = topic;
        this.cls = cls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }
}
