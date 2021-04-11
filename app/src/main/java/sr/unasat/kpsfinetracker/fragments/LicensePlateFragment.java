package sr.unasat.kpsfinetracker.fragments;

import sr.unasat.kpsfinetracker.MainActivity;
import sr.unasat.kpsfinetracker.R;
import sr.unasat.kpsfinetracker.databases.DatabaseHelperSearch;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class LicensePlateFragment extends Fragment {

    DatabaseHelperSearch db;

    Button add_data;
    EditText add_name;

    ListView userlist;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_license_plate, container, false);


    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        db = new DatabaseHelperSearch(getActivity());

        listItem = new ArrayList<>();
        add_data = (Button) getView().findViewById(R.id.add_data);
        add_name = (EditText) getView().findViewById(R.id.add_name);
        userlist = (ListView) getView().findViewById(R.id.users_list);


        viewData();

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = userlist.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), ""+text, Toast.LENGTH_SHORT).show();
            }
        });

        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = add_name.getText().toString();
                if (!name.equals("") && db.insertData(name)){
                    Toast.makeText(getActivity(), "Data added", Toast.LENGTH_SHORT).show();
                    add_name.setText("");
                    listItem.clear();
                    viewData();
                }else{
                    Toast.makeText(getActivity(), "Data not added", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void viewData() {
        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0){
            Toast.makeText(getActivity(), "No data to show", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1)); //index 1 is name, index 0 is Id
            }
            final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                    listItem);
            userlist.setAdapter(adapter);

        }
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> userslist = new ArrayList<>();

                for (String user : listItem){
                    if (user.toLowerCase().contains(newText.toLowerCase())){
                        userslist.add(user);
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, userslist);
                userlist.setAdapter(adapter);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    }










