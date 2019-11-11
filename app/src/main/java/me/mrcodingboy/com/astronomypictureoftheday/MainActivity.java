package me.mrcodingboy.com.astronomypictureoftheday;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;

public class MainActivity extends Activity {

    Button button,get;
    String getdatadate = "";
    String url="";

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue= Volley.newRequestQueue(this);

        button = (Button) findViewById(R.id.btn);


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        getdatadate = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
                        url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date="+getdatadate;
                        parseData();
                    }
                }, year, month, dayOfMonth);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog.show();

            }
        });

    }

    public void parseData()
    {
       // Toast.makeText(MainActivity.this, "url is :"+url, Toast.LENGTH_SHORT).show();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    Intent i = new Intent(getApplicationContext(),Data.class);
                    i.putExtra("url",response.getString("url"));
                    i.putExtra("explanation",response.getString("explanation"));
                    startActivity(i);

                   // Toast.makeText(MainActivity.this, "a:" + response.getString("url"), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Ex:"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                }
            });
       requestQueue.add(jsonObjectRequest);
    }

}
