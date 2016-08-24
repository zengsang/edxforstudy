package com.wuqian.myedx.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuqian.myedx.R;
import com.wuqian.myedx.activity.CateGoryActivity;

/**
 * Created by wuqian on 2016/5/10.
 * 发现课程中的 课程分类页面
 * 显示总的分类条目
 */
public class CategoryFragment extends BaseFragment {
    private GridView gv;
    private int[] imgsId;
    private String[] titles;
    @Override
    protected View createEmptyView() {
        View view=View.inflate(getActivity(), R.layout.empty_view,null);
        TextView tv_empty= (TextView) view.findViewById(R.id.tv_empty);
        tv_empty.setText("没有分类信息");
        return view;
    }

    @Override
    protected View createSuccessView() {
        View view=View.inflate(getActivity(),R.layout.frag_category,null);
        gv= (GridView) view.findViewById(R.id.gv);

        MyAdapter adapter=new MyAdapter();
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), CateGoryActivity.class);
                intent.putExtra("position",position);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    protected int initDatas() {
        imgsId=new int[]{
                R.drawable.ic_ca_computer_sel,R.drawable.ic_ca_manage_sel,R.drawable.ic_ca_data_sel,
                R.drawable.ic_ca_engineeing_sel,R.drawable.ic_ca_law_sel,R.drawable.ic_ca_literature_sel,
                R.drawable.ic_ca_history_sel,R.drawable.ic_ca_art_sel,R.drawable.ic_ca_thinking_sel,
                R.drawable.ic_ca_english_sel,R.drawable.ic_ca_philosophy_sel,R.drawable.ic_ca_earth_sel,
                R.drawable.ic_ca_environmental_sel,R.drawable.ic_ca_life_sel,R.drawable.ic_ca_math_sel,
                R.drawable.ic_ca_phsical_sel,R.drawable.ic_ca_chemistry_sel,R.drawable.ic_ca_internationalrelations_sel,
                R.drawable.ic_ca_others_sel
        };

        titles=new String[]{
                "计算机","经济管理","创业","工程","社科法律","文学",
                "历史","艺术设计","哲学","外语","教育","地球环境",
                "生命科学","医学健康","数学","物理","化学","电子",
                "其他"
        };


        return BaseFragment.STATE_GET_DATA_SUCCESS;
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return imgsId.length;
        }

        @Override
        public Object getItem(int position) {
            return imgsId[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                convertView=View.inflate(getActivity(),R.layout.item_category,null);
                holder=new ViewHolder();
                holder.iv_item= (ImageView) convertView.findViewById(R.id.iv_category);
                holder.tv_item= (TextView) convertView.findViewById(R.id.tv_category);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            int imgId=imgsId[position];
            String title=titles[position];
            holder.iv_item.setImageResource(imgId);
            holder.tv_item.setText(title);
            return convertView;
        }
    }

    private class ViewHolder{
        ImageView iv_item;
        TextView tv_item;
    }
}