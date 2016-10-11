package com.example.john.sunset;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TweenActivity extends AppCompatActivity {

    Button btn;
    EditText edt;
    private List<String> items = new ArrayList<>();

    CalThread mCalThread;

    private ProgressDialog pb1, pb2;
    //记录对话框的完成百分比
    int progress = 0;
    int hasData = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        mCalThread = new CalThread();
        mCalThread.start();

        btn = (Button) findViewById(R.id.btn1);
        edt = (EditText) findViewById(R.id.edt);

        for (int i = 0; i < 3; i++) {
            items.add("asd" + i);
        }

        final String[] itemss = {"123", "233123", "2222"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //        ProgressDialog.show(this,
        //                "标题", "这是ProgressDialog", true, false);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                FragmentManager manager = getSupportFragmentManager();
                //                DialogFragment fragment = new DialogFragment();
                //                fragment.show(manager, "123");

                //                builder.setTitle("标题");
                //简单列表项
                //                builder.setItems(itemss, new DialogInterface.OnClickListener() {
                //                    @Override
                //                    public void onClick(DialogInterface dialog, int which) {
                //                        Toast.makeText(TweenActivity.this, "你选中了" + itemss[which], Toast.LENGTH_SHORT).show();
                //                    }
                //                }).create().show();
                //单选列表项
                //                builder.setSingleChoiceItems(itemss, 1, new DialogInterface.OnClickListener() {
                //                    @Override
                //                    public void onClick(DialogInterface dialog, int which) {
                //                        Toast.makeText(TweenActivity.this, "你选中了" + itemss[which], Toast.LENGTH_SHORT).show();
                //                    }
                //                });
                //多选列表项
                //                builder.setMultiChoiceItems(itemss, null, new DialogInterface.OnMultiChoiceClickListener() {
                //                    @Override
                //                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //
                //                    }
                //                });
                //                builder.create().show();

                //ProgressDialog
                //                progress = 0;
                //                hasData = 0;
                //                pb2 = new ProgressDialog(TweenActivity.this);
                //                pb2.setTitle("标题");
                //                pb2.setMax(100);
                //                pb2.setMessage("百分比");
                //                pb2.setCancelable(false);
                //                pb2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //                pb2.setIndeterminate(false);//设置显示进度值
                //                pb2.show();
                //                new Thread(new Runnable() {
                //                    @Override
                //                    public void run() {
                //                        while (progress < 100) {
                //                            try {
                //                                Thread.sleep(100);
                //                            } catch (InterruptedException e) {
                //                                e.printStackTrace();
                //                            }
                //                            progress++;
                //                            mHandler.sendEmptyMessage(0x123);
                //                            if (progress >= 100) {
                //                                pb2.dismiss();
                //                            }
                //                        }
                //                    }
                //                }).start();
                //
                //            }
                //        });

                //        AlertDialog.Builder builder=new AlertDialog.Builder(this);
                //        AlertDialog alertDialog = builder.setTitle("标题").setIcon(R.mipmap.ic_launcher).setMessage("zheshi")
                //                .setPositiveButton("hha", null).create();
                //        alertDialog.show();
                cal();
            }
        });

    }


    //创建消息发送EditText的点击事件

    private void cal() {
        //创建消息
        Message message = new Message();
        message.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt("INT", Integer.parseInt(edt.getText().toString()));
        message.setData(bundle);
        mCalThread.mHandler.sendMessage(message);
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                pb2.setProgress(progress);
            }
        }
    };

    class CalThread extends Thread {
        public Handler mHandler;

        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        int edt_Int = msg.getData().getInt("INT");
                        List<Integer> nums = new ArrayList<>();
                        for (int i = 2; i < edt_Int; i++) {
                            for (int j = 2; j <= Math.sqrt(i); j++) {
                                if (i != 2 && i % j == 0) {
                                    continue;
                                }
                            }
                            nums.add(i);
                        }
                        Toast.makeText(TweenActivity.this, "" + nums, Toast.LENGTH_SHORT).show();
                    }
                }
            };
            Looper.loop();
        }
    }
}