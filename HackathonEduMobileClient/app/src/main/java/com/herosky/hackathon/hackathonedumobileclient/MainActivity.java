package com.herosky.hackathon.hackathonedumobileclient;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by DELL on 8/1/2015.
 */
public class MainActivity extends Activity{
    public class Person
    {
        String name;
        Boolean isCheck = false;
        public Person(String n)
        {
            name = n;
        }
    }
    TableRow rowAdd;
    EditText title, message, time;
    TextView people;
    Button addPeople,sendMessage;
    ArrayList<Person> persons = new ArrayList<>();

    public void initID()
    {
        title = (EditText) findViewById(R.id.editTextEventTitle);
        message = (EditText) findViewById(R.id.editTextMessage);
        time = (EditText) findViewById(R.id.editTextTime);
        people = (TextView) findViewById(R.id.textView);
        addPeople = (Button) findViewById(R.id.imageButtonAdd);
        sendMessage = (Button) findViewById(R.id.buttonSendMessage);
        rowAdd = (TableRow) findViewById(R.id.rowAdd);
    }

    public void initData()
    {
        persons.add(new Person("Duy"));
        persons.add(new Person("John"));
        persons.add(new Person("Dave"));
        persons.add(new Person("Cuong"));
        persons.add(new Person("Dung"));
        persons.add(new Person("Micheal"));

        persons.add(new Person("Duy"));
        persons.add(new Person("John"));
        persons.add(new Person("Dave"));
        persons.add(new Person("Cuong"));
        persons.add(new Person("Dung"));
        persons.add(new Person("Micheal"));

        persons.add(new Person("Duy"));
        persons.add(new Person("John"));
        persons.add(new Person("Dave"));
        persons.add(new Person("Cuong"));
        persons.add(new Person("Dung"));
        persons.add(new Person("Micheal"));

        persons.add(new Person("Duy"));
        persons.add(new Person("John"));
        persons.add(new Person("Dave"));
        persons.add(new Person("Cuong"));
        persons.add(new Person("Dung"));
        persons.add(new Person("Micheal"));
    }

    public void setUp()
    {
        initData();

        rowAdd.setClickable(true);
        rowAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "safsadf", Toast.LENGTH_LONG).show();
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_choose_people);
                dialog.setTitle("Add student");
                GridView gridView = (GridView) dialog.findViewById(R.id.gridView);
                AdapterGridView adapter = new AdapterGridView(MainActivity.this, persons);
                gridView.setAdapter(adapter);
                Button done = (Button)dialog.findViewById(R.id.buttonDone);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String preview = "";
                        for(int i= 0; i < persons.size();i++)
                        {

                            if(preview.length() > 40) {
                                preview += "...";
                                break;
                            }
                            preview+= persons.get(i).name+", ";

                        }
                        people.setText(preview);
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        addPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button", Toast.LENGTH_LONG).show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog timePickDialog = new Dialog(MainActivity.this);
                timePickDialog.setContentView(R.layout.dialog_date_pick);
                timePickDialog.setTitle("Pick time");
                Button pick = (Button) timePickDialog.findViewById(R.id.buttonPick);
                final TimePicker timePicker = (TimePicker) timePickDialog.findViewById(R.id.timePicker);
                final DatePicker datePicker = (DatePicker) timePickDialog.findViewById(R.id.datePicker);
                pick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String timeSet = datePicker.toString() + "--" +timePicker.toString();
                        time.setText(timeSet);
                        timePickDialog.dismiss();
                    }
                });
                timePickDialog.show();
            }
        });
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "message", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.mainlayout);
        initID();
        setUp();
        super.onCreate(savedInstanceState);
    }
}
