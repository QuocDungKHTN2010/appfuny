package com.herosky.hackathon.hackathonedumobileclient;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.herosky.hackathon.hackathonedumobileclient.ws.PBGArrayOfTeacher_Student_Mapping;
import com.herosky.hackathon.hackathonedumobileclient.ws.PBGArrayOfstring;
import com.herosky.hackathon.hackathonedumobileclient.ws.PBGTeacher;
import com.herosky.hackathon.hackathonedumobileclient.ws.PBGTeacher_Student_Mapping;
import com.herosky.hackathon.hackathonedumobileclient.ws.ServiceClient;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    RadioButton meeting, exam, comment, fee;
    String type="";
    LinearLayout loginForm, sendMessageForm,historyForm;
    TableRow rowAdd;
    EditText title, message, time,user,pass;
    TextView people;
    Button addPeople,sendMessage,login;

    public void initIDLoginForm()
    {
        loginForm = (LinearLayout)findViewById(R.id.first);

        user = (EditText) findViewById(R.id.editTextUserName);
        pass = (EditText) findViewById(R.id.editTextPass);
        login = (Button) findViewById(R.id.buttonLogin);
    }

    public void initIDSendMessageForm()
    {
        sendMessageForm = (LinearLayout)findViewById(R.id.sendMessageForm);
        title = (EditText) findViewById(R.id.editTextEventTitle);
        message = (EditText) findViewById(R.id.editTextMessage);
        time = (EditText) findViewById(R.id.editTextTime);
        people = (TextView) findViewById(R.id.textView);
        addPeople = (Button) findViewById(R.id.imageButtonAdd);
        sendMessage = (Button) findViewById(R.id.buttonSendMessage);
        rowAdd = (TableRow) findViewById(R.id.rowAdd);
    }
    public void initID()
    {
        initIDHistoryForm();
        initIDLoginForm();
        initIDSendMessageForm();
    }
    ProgressDialog dialog;
    public void initData()
    {
        /*persons.add(new Person("Duy"));
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
        persons.add(new Person("Micheal"));*/
    }


    public boolean validateForm()
    {
        boolean result = true;
        if(user.getText().toString().trim().equals(""))
        {
            result = false;
            user.setError("This field can't be empty");
        }
        if(pass.getText().toString().trim().equals(""))
        {
            result = false;
            pass.setError("This field can't be empty");
        }
        return result;
    }
    String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
            "Friday", "Saturday" };
    PBGTeacher currentUser;
    public class loginSync extends AsyncTask<Void, Void, PBGTeacher>
    {
        @Override
        protected void onPostExecute(PBGTeacher result) {
            if(result == null)
            {
                Toast.makeText(MainActivity.this,"Wrong info",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
            else
            {
                currentUser = result;
                new getInfoSync().execute();
            }
            super.onPostExecute(result);
        }

        @Override
        protected PBGTeacher doInBackground(Void... params) {


            try {
                return new ServiceClient().checkLoginTeacher(user.getText().toString().trim(), pass.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this,ProgressDialog.THEME_HOLO_LIGHT);
            dialog.setTitle("Login");
            dialog.setMessage("Checking user accout");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            super.onPreExecute();
        }
    }
    ArrayList<PBGTeacher_Student_Mapping> listInfos = new ArrayList<>();
    public class getInfoSync extends AsyncTask<Void, Void, PBGArrayOfTeacher_Student_Mapping>
    {
        @Override
        protected void onPostExecute(PBGArrayOfTeacher_Student_Mapping result) {
            if(result == null)
            {
                Toast.makeText(MainActivity.this,"Internet connection problem...",Toast.LENGTH_LONG).show();

            }
            else
            {
                listInfos.addAll(result);
                Animation in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_right);
                Animation out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_left);

                loginForm.startAnimation(out);
                loginForm.setVisibility(View.GONE);
                sendMessageForm.setVisibility(View.VISIBLE);
                sendMessageForm.startAnimation(in);
            }
            dialog.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected PBGArrayOfTeacher_Student_Mapping doInBackground(Void... params) {
            try {
                return new ServiceClient().getStudent_Parent_ByTeacherId(currentUser.TeacherId);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPreExecute() {

            dialog.setMessage("Getting user information...");

            super.onPreExecute();
        }
    }
    boolean isSendAll = false;
    ArrayList<PBGTeacher_Student_Mapping> listChecked = new ArrayList<>();
    public class sendMessageAsync extends AsyncTask<Void, Void, PBGArrayOfstring>
    {
        @Override
        protected void onPostExecute(PBGArrayOfstring result) {
            if(result == null)
            {
                Toast.makeText(MainActivity.this,"Internet connection problem...",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG);
            }
            dialog.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected PBGArrayOfstring doInBackground(Void... params) {
            try {
                ArrayList<String> phones = new ArrayList<>();
                ArrayList<String> names = new ArrayList<>();
                for(int i=0; i< listChecked.size();i++)
                {
                    phones.add(listChecked.get(i).parent.PhoneNumber);
                    names.add(listChecked.get(i).parent.ParentId+"");
                }
                PBGArrayOfstring phonesPBG =  new PBGArrayOfstring(), idPBG = new PBGArrayOfstring();
                phonesPBG.addAll(phones);
                idPBG.addAll(names);

                Integer isAll;
                if(isSendAll)
                    isAll =1;
                else isAll = 2;
                return new ServiceClient().sendMessage(currentUser.TeacherId+"" ,message.getText().toString(), phonesPBG,idPBG, "",isAll, new Date(), currentTime.getTime());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(MainActivity.this,ProgressDialog.THEME_HOLO_LIGHT);
            dialog.setTitle("Send message");
            dialog.setMessage("Sending...");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            super.onPreExecute();
        }
    }

    public String getTime(Calendar cal)
    {

        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());



        String ap ="";
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12)
            ap ="AM";
        else
            ap = "PM";

        String day =strDays[cal.get(Calendar.DAY_OF_WEEK) - 1];
         return day+", "+month+" "+cal.get(Calendar.DAY_OF_MONTH)+", "+cal.get(Calendar.YEAR) +" "+cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+ " "+ap;
    }
    Calendar currentTime;
    boolean checkAll = false;

    public void setUpHistoryForm()
    {
        rowFilter.setClickable(true);
        rowFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAll = true;
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_choose_people);
                dialog.setTitle("FILTER STUDENT");
                GridView gridView = (GridView) dialog.findViewById(R.id.gridView);
                final AdapterGridView adapter = new AdapterGridView(MainActivity.this, listInfos);
                gridView.setAdapter(adapter);
                Button done = (Button)dialog.findViewById(R.id.buttonDone);
                final Button selectAll = (Button)dialog.findViewById(R.id.buttonSelect);
                selectAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i = 0; i< listInfos.size();i++)
                        {
                            listInfos.get(i).isCheck = checkAll;
                        }
                        checkAll = !checkAll;
                        adapter.notifyDataSetChanged();
                    }
                });
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String preview = "";
                        int count= 0;
                        listChecked.clear();
                        for(int i= 0; i < listInfos.size();i++)
                        {
                            if(listInfos.get(i).isCheck) {
                                listChecked.add(listInfos.get(i));
                                count++;
                                if (preview.length() > 40) {
                                    preview += "...";

                                }
                                else {

                                    preview += listInfos.get(i).student.FullName + ", ";
                                }
                            }

                        }

                        if(count == 0)
                            preview = "No student choose";
                        else {
                            if (count == listInfos.size()) {
                                preview = "All student";
                                isSendAll = true;
                            } else
                                isSendAll = false;
                        }
                        people.setText(preview);
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

    }

    Button addHistory, deleteHistory;
    ListView listViewHistory;
    TableRow rowFilter;
    public void initIDHistoryForm()
    {
        historyForm = (LinearLayout)findViewById(R.id.historyForm);
        addHistory = (Button)findViewById(R.id.buttonAdd);
        deleteHistory = (Button)findViewById(R.id.buttonDel);
        listViewHistory = (ListView)findViewById(R.id.listViewHistory);
        rowFilter = (TableRow) findViewById(R.id.rowFilter);
    }
    public void setUpSendMessageForm()
    {
        currentTime = Calendar.getInstance();
        //initData();
        rowAdd.setClickable(true);
        rowAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "safsadf", Toast.LENGTH_LONG).show();
                checkAll = true;
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_choose_people);
                dialog.setTitle("ADD STUDENT");
                GridView gridView = (GridView) dialog.findViewById(R.id.gridView);
                final AdapterGridView adapter = new AdapterGridView(MainActivity.this, listInfos);
                gridView.setAdapter(adapter);
                Button done = (Button)dialog.findViewById(R.id.buttonDone);
                final Button selectAll = (Button)dialog.findViewById(R.id.buttonSelect);
                selectAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i = 0; i< listInfos.size();i++)
                        {
                            listInfos.get(i).isCheck = checkAll;
                        }
                        checkAll = !checkAll;

                        adapter.notifyDataSetChanged();
                    }
                });
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String preview = "";
                        int count= 0;
                        listChecked.clear();
                        for(int i= 0; i < listInfos.size();i++)
                        {
                            if(listInfos.get(i).isCheck) {
                                listChecked.add(listInfos.get(i));
                                count++;
                                if (preview.length() > 40) {
                                    preview += "...";

                                }
                                else {

                                    preview += listInfos.get(i).student.FullName + ", ";
                                }
                            }

                        }

                        if(count == 0)
                            preview = "No student choose";
                        else {
                            if (count == listInfos.size()) {
                                preview = "All student";
                                isSendAll = true;
                            } else
                                isSendAll = false;
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
                Log.e("Time","Start");
                final Dialog timePickDialog = new Dialog(MainActivity.this);
                timePickDialog.setContentView(R.layout.dialog_date_pick);
                timePickDialog.setTitle("Pick time");
                Button pick = (Button) timePickDialog.findViewById(R.id.buttonPick);
                Button cancel = (Button) timePickDialog.findViewById(R.id.buttonCancel);
                final TimePicker timePicker = (TimePicker) timePickDialog.findViewById(R.id.timePicker);
                final DatePicker datePicker = (DatePicker) timePickDialog.findViewById(R.id.datePicker);
                timePicker.setCurrentHour(currentTime.get(Calendar.HOUR));
                timePicker.setCurrentMinute(currentTime.get(Calendar.MINUTE));
                datePicker.updateDate(currentTime.get(Calendar.YEAR), currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH));
                Log.e("Time", "Prepare");
                pick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                        time.setText(getTime(cal));
                        currentTime = cal;
                        timePickDialog.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timePickDialog.dismiss();
                    }
                });
                Log.e("Time", "Mid");
                timePickDialog.show();
                Log.e("Time", "End");
            }
        });
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new sendMessageAsync().execute();
            }
        });
    }

    public void setUpFormLogin()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()) {
                    new loginSync().execute();
                }

            }
        });
    }
    public void setUp()
    {
        setUpFormLogin();
        setUpSendMessageForm();
        setUpHistoryForm();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.mainlayout);
        initID();
        setUp();
        super.onCreate(savedInstanceState);
    }
}
