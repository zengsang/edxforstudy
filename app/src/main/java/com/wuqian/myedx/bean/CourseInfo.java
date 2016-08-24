package com.wuqian.myedx.bean;

import java.util.ArrayList;

/**
 * Created by wuqian on 2016/5/26.
 */
public class CourseInfo {
    private ArrayList<Course> results;

    public ArrayList<Course> getResults() {
        return results;
    }

    public void setResults(ArrayList<Course> results) {
        this.results = results;
    }


    public class Course{
        private String course_id;
        private String end;
        private String start_display;
        private String short_description;
        private String name;
        private String number;
        private Media media;

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getStart_display() {
            return start_display;
        }

        public void setStart_display(String start_display) {
            this.start_display = start_display;
        }

        public String getShort_description() {
            return short_description;
        }

        public void setShort_description(String short_description) {
            this.short_description = short_description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public Media getMedia() {
            return media;
        }

        public void setMedia(Media media) {
            this.media = media;
        }
    }

    public class Media{
        private Course_image course_image;

        public Course_image getCourse_image() {
            return course_image;
        }

        public void setCourse_image(Course_image course_image) {
            this.course_image = course_image;
        }
    }

    public class Course_image{
        private String uri;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }
}
