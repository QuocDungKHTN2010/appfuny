package com.herosky.hackathon.hackathonedumobileclient;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.herosky.hackathon.hackathonedumobileclient.ws.PBGArrayOfTeacher_Student_Mapping;
import com.herosky.hackathon.hackathonedumobileclient.ws.PBGArrayOfstring;
import com.herosky.hackathon.hackathonedumobileclient.ws.PBGTeacher;
import com.herosky.hackathon.hackathonedumobileclient.ws.PBGTeacher_Student_Mapping;
import com.herosky.hackathon.hackathonedumobileclient.ws.ServiceClient;
import com.herosky.hackathon.hackathonedumobileclient.ws2.QMPArrayOfHistory;
import com.herosky.hackathon.hackathonedumobileclient.ws2.QMPHistory;
import com.herosky.hackathon.hackathonedumobileclient.ws2.ServiceClient2;

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
    TableRow group1,group2;
    LinearLayout loginForm, sendMessageForm,historyForm;
    TableRow rowAdd;
    EditText title, message, time,user,pass;
    TextView people, filterPeople;
    Button addPeople,sendMessage,login;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(currentForm.equals(loginForm))
            return true;
        switch (item.getItemId()) {
            case R.id.call:

                return true;
            case R.id.historyAction:
                goToHistoryForm();
                return true;
            case R.id.message:
                goToMessageForm(true);
                return true;
            case R.id.logoutAction:
                goToLoginForm();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public View currentForm;
    Animation in, out;


    public void goToMessageForm(boolean isSendMessage)
    {
        exam.setChecked(true);

        if(isSendMessage)
        {
            sendMessage.setText("SEND MESSAGE");
            group2.setVisibility(View.VISIBLE);
        }
        else {
            sendMessage.setText("ADD SCHEDULE");
            group2.setVisibility(View.GONE);
        }
        currentForm.startAnimation(out);
        currentForm.setVisibility(View.GONE);
        sendMessageForm.setVisibility(View.VISIBLE);
        sendMessageForm.startAnimation(in);
        currentForm = sendMessageForm;



    }
    Boolean isSendMessage = false;
    public class loadHistoryTask extends  AsyncTask<Void, Void, QMPArrayOfHistory>
    {
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this,ProgressDialog.THEME_HOLO_LIGHT);
            dialog.setTitle("History");
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected QMPArrayOfHistory doInBackground(Void... params) {
            try {
                return new ServiceClient2().getHistoryByTeacherId(currentUser.TeacherId);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(QMPArrayOfHistory res) {
            if(res == null)
            {
                Toast.makeText(MainActivity.this,"Connection problem...",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,"Successful...",Toast.LENGTH_LONG).show();
                histories.clear();
                histories.add(new QMPHistory());
                histories.addAll(res);
                adapterHistory.notifyDataSetChanged();
            }
            dialog.dismiss();
            super.onPostExecute(res);
        }
    }
    ArrayList<QMPHistory> histories = new ArrayList<>();
    public void goToHistoryForm()
    {

        isSendMessage = false;
        currentForm.startAnimation(out);
        currentForm.setVisibility(View.GONE);
        historyForm.setVisibility(View.VISIBLE);
        historyForm.startAnimation(in);
        currentForm = historyForm;
        new loadHistoryTask().execute();
    }
    public void goToLoginForm()
    {
        currentUser = null;
        currentForm.startAnimation(out);
        currentForm.setVisibility(View.GONE);
        loginForm.setVisibility(View.VISIBLE);
        loginForm.startAnimation(in);
        currentForm = loginForm;
    }
    public void initIDLoginForm()
    {
        loginForm = (LinearLayout)findViewById(R.id.first);

        user = (EditText) findViewById(R.id.editTextUserName);
        pass = (EditText) findViewById(R.id.editTextPass);
        login = (Button) findViewById(R.id.buttonLogin);
    }

    public void initIDSendMessageForm()
    {

        group1 = (TableRow)findViewById(R.id.group1);
        group2 = (TableRow)findViewById(R.id.group2);
        sendMessageForm = (LinearLayout)findViewById(R.id.sendMessageForm);
        title = (EditText) findViewById(R.id.editTextEventTitle);
        message = (EditText) findViewById(R.id.editTextMessage);
        time = (EditText) findViewById(R.id.editTextTime);
        people = (TextView) findViewById(R.id.textViewPeople);
        addPeople = (Button) findViewById(R.id.imageButtonAdd);
        sendMessage = (Button) findViewById(R.id.buttonSendMessage);
        rowAdd = (TableRow) findViewById(R.id.rowAdd);

        exam = (RadioButton)findViewById(R.id.radioButtonExam);
        fee = (RadioButton)findViewById(R.id.radioButtonFee);
        comment = (RadioButton)findViewById(R.id.radioButtonComment);
        meeting = (RadioButton)findViewById(R.id.radioButtonMeeting);
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
                listInfos.clear();
                listInfos.addAll(result);
                goToMessageForm(true);
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
                return new ServiceClient().sendMessage(currentUser.TeacherId+"" ,message.getText().toString(), phonesPBG,idPBG, type,isAll, new Date(), currentTime.getTime());
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


    HistoryAdapter adapterHistory;

    public void setUpHistoryForm()
    {
        adapterHistory = new HistoryAdapter(MainActivity.this,histories);
        listViewHistory.setAdapter(adapterHistory);

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
                Button done = (Button) dialog.findViewById(R.id.buttonDone);
                final Button selectAll = (Button) dialog.findViewById(R.id.buttonSelect);
                selectAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < listInfos.size(); i++) {
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
                        int count = 0;
                        listChecked.clear();
                        for (int i = 0; i < listInfos.size(); i++) {
                            if (listInfos.get(i).isCheck) {
                                listChecked.add(listInfos.get(i));
                                count++;
                                if (preview.length() > 40) {
                                    preview += "...";

                                } else {

                                    preview += listInfos.get(i).student.FullName + ", ";
                                }
                            }

                        }

                        if (count == 0)
                            preview = "No student choose";
                        else {
                            if (count == listInfos.size()) {
                                preview = "All student";
                                isSendAll = true;
                            } else
                                isSendAll = false;
                        }
                        filterPeople.setText(preview);
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        addHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMessageForm(false);
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
        filterPeople = (TextView)findViewById(R.id.textViewFilter);
    }
    public void setUpSendMessageForm()
    {
        currentTime = Calendar.getInstance();
        //initData();
        exam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    type ="Exam";
                    title.setText(type);
                    message.setText("Examination: ");
                    meeting.setChecked(false);
                    comment.setChecked(false);
                    fee.setChecked(false);
                }
            }
        });
        fee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    type ="Fee";
                    title.setText(type);
                    message.setText("Fee: ");
                    meeting.setChecked(false);
                    comment.setChecked(false);
                    exam.setChecked(false);
                }
            }
        });
        comment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    type ="Comment";
                    title.setText(type);
                    message.setText("Comment : ");
                    meeting.setChecked(false);
                    fee.setChecked(false);
                    exam.setChecked(false);
                }
            }
        });
        meeting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    type ="Meeting";
                    title.setText(type);
                    message.setText("Meeting :");

                    exam.setChecked(false);
                    comment.setChecked(false);
                    fee.setChecked(false);
                }
            }
        });
        //
        //
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
        in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_right);
        out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_left);
        loginForm.setVisibility(View.VISIBLE);
        historyForm.setVisibility(View.GONE);
        sendMessageForm.setVisibility(View.GONE);
        currentForm = loginForm;
        super.onCreate(savedInstanceState);
    }
}
