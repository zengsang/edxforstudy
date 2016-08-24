package com.wuqian.myedx.bean;

import java.util.ArrayList;

/**
 * Created by wuqian on 2016/6/2.
 */
public class Course {
    private String desc;
    private String img_course;
    private String location;
    private String name;
    private int video;//1代表有视屏
    private int id;//课程id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    private ArrayList<Chapter> results;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg_course() {
        return img_course;
    }

    public void setImg_course(String img_course) {
        this.img_course = img_course;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Chapter> getResults() {
        return results;
    }

    public void setResults(ArrayList<Chapter> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Course{" +
                "desc='" + desc + '\'' +
                ", img_course='" + img_course + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", results=" + results +
                '}';
    }

    public static class Chapter{
        private String ch_name;

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCh_name() {
            return ch_name;
        }

        public void setCh_name(String ch_name) {
            this.ch_name = ch_name;
        }

        @Override
        public String toString() {
            return "Chapter{" +
                    "ch_name='" + ch_name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
