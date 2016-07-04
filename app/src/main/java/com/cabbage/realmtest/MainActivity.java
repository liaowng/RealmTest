package com.cabbage.realmtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    PersonAdapter mAdatper;

    @BindView(R.id.et_person_name) EditText etPersonName;
    @BindView(R.id.rv_persons) RecyclerView rvPersons;

    @SuppressWarnings("unused")
    @OnClick(R.id.btn_add)
    void addPerson() {
        String input = etPersonName.getText().toString();
        if (!TextUtils.isEmpty(input)) {
            Person newPerson = new Person();
            newPerson.setFirstName(input);
            mAdatper.addPerson(newPerson);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        rvPersons.setHasFixedSize(false);
        rvPersons.setLayoutManager(new LinearLayoutManager(this));
        mAdatper = new PersonAdapter();
        rvPersons.setAdapter(mAdatper);
    }

    public static class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

        private List<Person> mData = new ArrayList<>();

        public void addPerson(Person person) {
            mData.add(person);
            notifyItemChanged(getItemCount() - 1);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_person, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Person person = mData.get(position);
            String displayName = person.getFirstName();
            holder.tvPersonName.setText(displayName);
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_person_name) TextView tvPersonName;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}