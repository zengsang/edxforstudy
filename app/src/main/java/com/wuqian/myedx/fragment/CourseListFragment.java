package com.wuqian.myedx.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wuqian.myedx.R;
import com.wuqian.myedx.activity.VideoPlayActivity;
import com.wuqian.myedx.bean.Course;
import com.wuqian.myedx.util.ToastUtils;

import java.util.List;

/**
 * Created by wuqian on 2016/6/3.
 * 课程章节列表
 */
public class CourseListFragment extends Fragment {

    private boolean hasVideo;
    private List<Course.Chapter> datas;

    private ListView lv;
    private MyAdapter adapter;
    public CourseListFragment(Course course) {
        hasVideo=course.getVideo()==1;
        datas=course.getResults();
    }

    public CourseListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_course_list,null);
        lv= (ListView) view.findViewById(R.id.lv);
        lv.setAdapter(adapter=new MyAdapter());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(hasVideo){
                    Intent intent=new Intent(getActivity(), VideoPlayActivity.class);
                    intent.putExtra("title",datas.get(position).getCh_name());
                    intent.putExtra("url",datas.get(position).getUrl());
                    startActivity(intent);
                }else{
                    ToastUtils.showToast("无视频源");
                }
            }
        });
        return view;
    }



    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Course.Chapter getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView=View.inflate(getActivity(), R.layout.item_course_list,null);
                holder=new ViewHolder();
                holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
                holder.tv_desc= (TextView) convertView.findViewById(R.id.tv_desc);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.tv_title.setText(datas.get(position).getCh_name());
            if(hasVideo){
                holder.tv_desc.setText("视频");
            }else{
                holder.tv_desc.setText("无视频");
            }
            return convertView;
        }
    }

    private class ViewHolder{
        TextView tv_title;
        TextView tv_desc;
    }
}
