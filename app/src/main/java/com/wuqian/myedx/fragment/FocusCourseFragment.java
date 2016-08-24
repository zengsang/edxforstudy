package com.wuqian.myedx.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wuqian.myedx.R;
import com.wuqian.myedx.activity.CourseDetailActivity;
import com.wuqian.myedx.bean.AllCourse;
import com.wuqian.myedx.http.CourseHttp;
import com.wuqian.myedx.util.SPFUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuqian on 2016/5/10.
 */
public class FocusCourseFragment extends BaseFragment {
    private ImageLoader imageLoader;
    private ArrayList<AllCourse.CourseAbstract> datas;
    private ListView lv;
    @Override
    protected View createEmptyView() {
        View view=View.inflate(getActivity(), R.layout.empty_view,null);
        TextView tv_empty= (TextView) view.findViewById(R.id.tv_empty);
        tv_empty.setText("你没有关注任何课程");
        return view;
    }

    @Override
    protected View createSuccessView() {
       View view=View.inflate(getActivity(),R.layout.frag_focus_course,null);
        imageLoader=ImageLoader.getInstance();
        lv= (ListView) view.findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), CourseDetailActivity.class);
                intent.putExtra("file_name",datas.get(position).getFile_name());
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    protected int initDatas() {
        if(!SPFUtils.getBoolean(SPFUtils.USER_IS_LOGIN,false)){
            return BaseFragment.STATE_NO_DATA;
        }

        List<AllCourse.CourseAbstract> allCourse= CourseHttp.getData(AllCourse.class,CourseHttp.FILENAME_ALL_COURSE).getResult();
        String username= SPFUtils.getString(SPFUtils.TOKEN_USERNAME,"无");
        AVQuery<AVObject> query=new AVQuery<>("Collect");
        query.whereEqualTo("username",username);
        try {
            List<AVObject> list=query.find();
            if(list!=null&&list.size()>0){
                datas=new ArrayList<>();
                for(AVObject object:list){
                    String file_name="course"+object.getInt("courseId");
                    for(AllCourse.CourseAbstract courseAbstract:allCourse){
                        if(file_name.equals(courseAbstract.getFile_name())){
                            datas.add(courseAbstract);
                        }
                    }
                }
                return BaseFragment.STATE_GET_DATA_SUCCESS;
            }
        } catch (AVException e) {
            e.printStackTrace();
        }
        return BaseFragment.STATE_NO_DATA;
    }



    private class  MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
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
                convertView=View.inflate(getActivity(),R.layout.item_focus_course,null);
                holder=new ViewHolder();
                holder.iv_img= (ImageView) convertView.findViewById(R.id.iv_img);
                holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            AllCourse.CourseAbstract course=datas.get(position);
            imageLoader.displayImage(course.getImg_course(),holder.iv_img);
            holder.tv_title.setText(course.getName());
            return convertView;
        }
    }

    private class ViewHolder{
        ImageView iv_img;
        TextView tv_title;
    }
}
