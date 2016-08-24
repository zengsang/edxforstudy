package com.wuqian.myedx.bean;

import java.util.ArrayList;

/**
 * Created by wuqian on 2016/6/3.
 */
public class AllCourse {
    private int count;
    private ArrayList<CourseAbstract> result;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<CourseAbstract> getResult() {
        return result;
    }

    public void setResult(ArrayList<CourseAbstract> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AllCourse{" +
                "count=" + count +
                ", result=" + result +
                '}';
    }

    public static class CourseAbstract{
        private String file_name;
        private String img_course;
        private String name;

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getImg_course() {
            return img_course;
        }

        public void setImg_course(String img_course) {
            this.img_course = img_course;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "CourseAbstract{" +
                    "file_name='" + file_name + '\'' +
                    ", img_course='" + img_course + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
