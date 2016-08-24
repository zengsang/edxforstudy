package com.wuqian.myedx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuqian.myedx.R;
import com.wuqian.myedx.fragment.CategoryFragment2;

/**
 * 点击一个分类条目时会调转到这，
 * 展示某个分类数据
 */
public class CateGoryActivity extends AppCompatActivity {
    private ImageView iv_back;
    private ImageView iv_category;
    private TextView tv_title;

    private String[] titles=titles=new String[]{
            "计算机","经济管理","创业","工程","社科法律","文学",
            "历史","艺术设计","哲学","外语","教育","地球环境",
            "生命科学","医学健康","数学","物理","化学","电子",
            "其他"
    };
    private int[] imgsId=new int[]{
            R.mipmap.bg_category_compute,R.mipmap.bg_category_management,R.mipmap.bg_category_analysis_data,
            R.mipmap.bg_category_engineering_science,R.mipmap.bg_category_society,R.mipmap.bg_category_history_literature,
            R.mipmap.bg_category_history_literature,R.mipmap.bg_category_art,R.mipmap.bg_category_philosophy,
            R.mipmap.bg_category_english,R.mipmap.bg_category_information,R.mipmap.bg_category_environment,
            R.mipmap.bg_category_bioscience,R.mipmap.bg_category_bioscience,R.mipmap.bg_category_math,
            R.mipmap.bg_category_physics,R.mipmap.bg_category_chemistry,R.mipmap.bg_category_international_relation,
            R.mipmap.bg_category_others

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cate_gory);
        initView();
        int position=getIntent().getIntExtra("position",0);
        tv_title.setText(titles[position]);
        iv_category.setImageResource(imgsId[position]);

        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,new CategoryFragment2()).commit();
    }

    private void initView(){
        iv_back= (ImageView) findViewById(R.id.iv_back);
        tv_title= (TextView) findViewById(R.id.tv_title);
        iv_category= (ImageView) findViewById(R.id.iv_category);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
