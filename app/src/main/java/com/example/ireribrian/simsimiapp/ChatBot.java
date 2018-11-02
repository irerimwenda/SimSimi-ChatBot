package com.example.ireribrian.simsimiapp;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ireribrian.simsimiapp.Adapter.CustomAdapter;
import com.example.ireribrian.simsimiapp.Helper.HttpDataHandler;
import com.example.ireribrian.simsimiapp.Model.ChatModel;
import com.example.ireribrian.simsimiapp.Model.SimSimiModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class ChatBot extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<ChatModel> list_chat = new ArrayList<>();
    FloatingActionButton btn_send_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        listView = (ListView)findViewById(R.id.list_of_message);
        editText = (EditText) findViewById(R.id.user_message);
        btn_send_message = (FloatingActionButton)findViewById(R.id.fab);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();

                //User sends Message
                ChatModel model = new ChatModel(text, true);

                list_chat.add(model);
                new SimiSimiAPI().execute(list_chat);

                //Remove User Message
                editText.setText("");
            }
        });
    }

    private class SimiSimiAPI extends AsyncTask<List<ChatModel>, Void, String>{

        String stream = null;
        List<ChatModel> models;
        String text = editText.getText().toString();

        @Override
        protected String doInBackground(List<ChatModel>... params) {
            String url = String.format("http://sandbox.api.simsimi.com/request.p?key=%s&lc=en&ft=1.0&text=%s", getString(R.string.simsimi_api), text);
            models = params[0];
            HttpDataHandler httpDataHandler = new HttpDataHandler();
            stream = httpDataHandler.GetHTTPData(url);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            SimSimiModel response = gson.fromJson(s,SimSimiModel.class);

            //Get Response from Simsimi
            ChatModel chatModel = new ChatModel(response.getResponse(), false);
            models.add(chatModel);

            CustomAdapter adapter = new CustomAdapter(models, getApplicationContext());
            listView.setAdapter(adapter);
        }
    }
}
