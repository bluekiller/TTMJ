package com.pfwu.ttmj;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pfwu.ttmj.core.datatypes.DownloadEntry;
import com.pfwu.ttmj.core.services.PageParser;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    final Context context = this;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            List<DownloadEntry> entries = (List<DownloadEntry>) data.get("entries");
            if (entries != null) {
                MyAdapter mAdapter = new MyAdapter(entries);
                mRecyclerView.setAdapter(mAdapter);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mLayoutManager = new LinearLayoutManager(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(
                        new Runnable() {

                            @Override
                            public void run() {
                                try

                                {
                                    List<DownloadEntry> entries = PageParser.getDownloadEntries(new URL("http://www.ttmeiju.com/meiju/NCIS.Los.Angeles.html"));
                                    Message message = new Message();
                                    Bundle data = new Bundle();
                                    data.putSerializable("entries", (Serializable) entries);
                                    message.setData(data);

                                    handler.sendMessage(message);
//创建并设置Adapter


                                } catch (
                                        MalformedURLException e
                                        )

                                {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
            }
        });


        mRecyclerView.setLayoutManager(mLayoutManager);

//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
      //  mRecyclerView.setHasFixedSize(true);


        new Thread(
                new Runnable() {

                    @Override
                    public void run() {
                        try

                        {
                            List<DownloadEntry> entries = PageParser.getDownloadEntries(new URL("http://www.ttmeiju.com/meiju/Better.Call.Saul.html"));
                            Message message = new Message();
                            Bundle data = new Bundle();
                            data.putSerializable("entries", (Serializable) entries);
                            message.setData(data);

                            handler.sendMessage(message);
//创建并设置Adapter


                        } catch (
                                MalformedURLException e
                                )

                        {
                            e.printStackTrace();
                        }
                    }
                }).start();
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        public List<DownloadEntry> entries = new ArrayList<>();

        public MyAdapter(List<DownloadEntry> entries) {
            this.entries = entries;
        }

        public void addEntries(List<DownloadEntry> entries) {

        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.mTextView.setText(entries.get(position).getName());
        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return entries.size();
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mTextView = (TextView) view.findViewById(R.id.text_view);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
