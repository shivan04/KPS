package sr.unasat.kpsfinetracker.fragments;

import sr.unasat.kpsfinetracker.LoginActivity;
import sr.unasat.kpsfinetracker.R;
import sr.unasat.kpsfinetracker.RegisterActivity;
import sr.unasat.kpsfinetracker.activity.LicensePlateDetailsActivity;
import sr.unasat.kpsfinetracker.databases.DatabaseHelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class LicensePlateFragment extends Fragment {

    Button addDataBtn, searchDataBtn;
    EditText addDataTxt;
    ListView userListView;
    ArrayList<String> arrayList;
    DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_license_plate, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getActivity());

        arrayList = new ArrayList<>();
        addDataBtn = (Button) getView().findViewById(R.id.add_data);
        searchDataBtn = (Button) getView().findViewById(R.id.search_data);
        addDataTxt = (EditText) getView().findViewById(R.id.add_name);
        userListView = (ListView) getView().findViewById(R.id.users_list);

        getLicensePlates();

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = userListView.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), ""+text, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), LicensePlateDetailsActivity.class);
                intent.putExtra("LICENSE_PLATE_NUMBER", text);
                startActivity(intent);
            }
        });

        addDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String license_plate_number = addDataTxt.getText().toString();
                if (!license_plate_number.equals("")) {
                    ContentValues content = new ContentValues();
                    content.put("license_plate_number", license_plate_number);
                    long results = dbHelper.insertOneRecord("vehicles", content);
                    if (results >= 1){
                        Toast.makeText(getActivity(), "License plate inserted succesfully", Toast.LENGTH_SHORT).show();
                        arrayList.clear();
                        addDataTxt.setText("");
                        getLicensePlates();
                    }
                    else{
                        Toast.makeText(getActivity(), "License plate not inserted", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Please add data", Toast.LENGTH_SHORT).show();
                }
               }
        });

        searchDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String license_plate_number = addDataTxt.getText().toString();
                arrayList.clear();
                if (!license_plate_number.equals("")) {
                    searchLicensePlates(license_plate_number);
                } else {
                    getLicensePlates();
                    Toast.makeText(getActivity(), "Data not added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void searchLicensePlates(String search_data){
        Cursor cursor = dbHelper.searchTable("vehicles", "license_plate_number", search_data);

        if (cursor.getCount() == 0){
            Toast.makeText(getActivity(), "No data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                arrayList.add(cursor.getString(1));
            }
            final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
            userListView.setAdapter(adapter);
        }
    }

    private void getLicensePlates(){
        Cursor cursor = dbHelper.getLicensePlates();

        if (cursor.getCount() == 0){
            Toast.makeText(getActivity(), "No data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                arrayList.add(cursor.getString(1));
            }
            final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
            userListView.setAdapter(adapter);
        }
    }

}










