package com.wuqian.myedx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuqian.myedx.R;
import com.wuqian.myedx.bean.Course;

/**
 * Created by wuqian on 2016/6/3.
 * 课程简介
 */
public class CourseDescFragment extends Fragment {

    private Course course;

    private TextView tv_location,tv_desc;
    public CourseDescFragment(Course course) {
        this.course=course;
    }

    public CourseDescFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.frag_course_desc,null);
        tv_location= (TextView) view.findViewById(R.id.tv_location);
        tv_desc= (TextView) view.findViewById(R.id.tv_desc);
        if(course!=null){
            tv_location.setText(course.getLocation());
            tv_desc.setText(course.getDesc());
        }
        return view;
    }
}
