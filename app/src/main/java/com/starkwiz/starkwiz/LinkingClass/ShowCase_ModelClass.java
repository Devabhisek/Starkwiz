package com.starkwiz.starkwiz.LinkingClass;

public class ShowCase_ModelClass {

    String id,user_id,file_name,likes,views,topic,video_type,category;

    public ShowCase_ModelClass(String id, String user_id, String file_name,
                               String likes, String views, String topic, String video_type, String category) {
        this.id = id;
        this.user_id = user_id;
        this.file_name = file_name;
        this.likes = likes;
        this.views = views;
        this.topic = topic;
        this.video_type = video_type;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
