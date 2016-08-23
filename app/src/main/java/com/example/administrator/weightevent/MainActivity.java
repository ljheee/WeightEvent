package com.example.administrator.weightevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //声明所需控件
    EditText editTextHeight;//输入框
    TextView textViewShowWeight;//文本显示
    TextView textViewShowProcess;//文本显示

    SeekBar seekBar;//进度条

    CheckBox checkBox;
    Button btnNext;

    //点选组
    RadioGroup radioGroupSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //加载布局，并且实例化布局中声明的视图
        setContentView(R.layout.activity_main);

        //获得要操作的视图，与该类前面的声明做关联
        editTextHeight = (EditText) findViewById(R.id.editText_height);
        textViewShowWeight = (TextView) findViewById(R.id.textView_showWeight);
        textViewShowProcess = (TextView) findViewById(R.id.textView_showProcess);
        seekBar = (SeekBar) findViewById(R.id.seekBar);


        //注册OnSeekBarChangeListener事件监听器
        //Ctrl + P     或 Ctrl +Q
        seekBar.setOnSeekBarChangeListener(new MySeekBarListener());


        checkBox = (CheckBox) findViewById(R.id.checkBox_agree);
        btnNext = (Button) findViewById(R.id.button_next);



        //注册监听事件
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            /**
             *
             * @param buttonView
             * @param isChecked   复选框是否被选中
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnNext.setEnabled(isChecked);
            }
        });


        //获取控件关联
        radioGroupSex = (RadioGroup) findViewById(R.id.rg_sex);
        radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String msg = "";
                switch (checkedId){
                    case R.id.radioButton_male:
                        msg = "male";
                        break;
                    case R.id.radioButton_female:
                        msg = "female";
                        break;
                    case R.id.radioButton_untalk:
                        msg = "untalk";
                        break;
                }
                btnNext.setText(msg);
            }
        });

    }

    /**
     * 内部类实现事件监听
     */
    class MySeekBarListener implements SeekBar.OnSeekBarChangeListener{

        /**
         *
         * @param seekBar   控件
         * @param progress  进度
         * @param fromUser  是否是用户触控
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //进度变化
            textViewShowProcess.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    /**
     * 获得身高，计算体重
     * @param view
     */
    public void countWeight(View view) {

        String input = editTextHeight.getText().toString();
        int height = 0;
        if(input.length()> 0){
            height = Integer.parseInt(input);
            height = (int)Math.ceil((height-70)*0.7);
        }

        //在TextView 显示
        textViewShowWeight.setText(String.valueOf(height));

    }
}
