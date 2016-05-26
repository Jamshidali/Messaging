package com.example.jamshi.messaging;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class ReceiveSmsActivity extends Activity implements AdapterView.OnItemClickListener {

    ArrayList smsMessagesList;
    int position=0;
    ListView lv;
    ArrayAdapter arrayAdapter;
    Intent i;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_sms);

       lv = (ListView) findViewById(R.id.lv);
       lv.setOnItemClickListener(this);
        i = getIntent();
        b = i.getExtras();
        smsMessagesList =(ArrayList) b.getParcelableArrayList("messageList");
        position = b.getInt("pos",0);
        try {
            String[] smsMessages = ((String) smsMessagesList.get(position)).split("\n");
            String address = smsMessages[0];
            String smsMessage = "";
            for (int i = 1; i < smsMessages.length; ++i) {
                smsMessage += smsMessages[i];
            }

            String smsMessageStr = address + "\n";
            smsMessageStr += smsMessage;
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsMessages);
            lv.setAdapter(arrayAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
