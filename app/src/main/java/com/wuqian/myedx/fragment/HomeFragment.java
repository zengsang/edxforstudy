package com.wuqian.myedx.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wuqian.myedx.R;
import com.wuqian.myedx.activity.CourseDetailActivity;
import com.wuqian.myedx.bean.AllCourse;
import com.wuqian.myedx.http.CourseHttp;

import java.util.ArrayList;

/**
 * Created by wuqian on 2016/5/10.
 * 发现课程中的   推荐课程
 */
public class HomeFragment extends BaseFragment {
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private GridView gv;
    private SwipeRefreshLayout refresh_layout;

    private ArrayList<AllCourse.CourseAbstract> datas;

    @Override
    protected View createEmptyView() {
        View view=View.inflate(getActivity(), R.layout.empty_view,null);
        TextView tv_empty= (TextView) view.findViewById(R.id.tv_empty);
        tv_empty.setText("没有课程信息");
        return view;
    }

    @Override
    protected View createSuccessView() {
        View view=View.inflate(getActivity(),R.layout.frag_home,null);
        gv= (GridView) view.findViewById(R.id.lv);
        refresh_layout= (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        initView();
        return view;
    }


    private void initView(){
        MyAdapter adapter=new MyAdapter();
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), CourseDetailActivity.class);
                intent.putExtra("file_name",datas.get(position).getFile_name());
                startActivity(intent);
            }
        });

       refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               refresh_layout.setRefreshing(true);
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       refresh_layout.setRefreshing(false);
                   }
               }, 3000);
           }
       });
    }

    private Handler handler=new Handler();

    @Override
    protected int initDatas() {
        AllCourse allCourse= CourseHttp.getData(AllCourse.class,CourseHttp.FILENAME_ALL_COURSE);
        if(allCourse==null){
            return BaseFragment.STATE_LOADING_ERROR;
        }
        datas=allCourse.getResult();

        return BaseFragment.STATE_GET_DATA_SUCCESS;
    }


    private class  MyAdapter extends BaseAdapter{
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
                convertView=View.inflate(getActivity(),R.layout.item_home_course,null);
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
