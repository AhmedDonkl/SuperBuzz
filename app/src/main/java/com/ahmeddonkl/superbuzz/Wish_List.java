package com.ahmeddonkl.superbuzz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ahmeddonkl.superbuzz.Adpaters.Wish_List_adapter;
import com.ahmeddonkl.superbuzz.Miscellaneous.SwipeDismissListViewTouchListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Wish_List extends ActionBarActivity
{

    //views in wish list
    android.widget.Spinner category_spin;
    EditText remind_text;
    ListView list;

    //To add all Buzzes On it
    ArrayList<String> reminds = new ArrayList<String>();
    ArrayList<String> category = new ArrayList<String>();

    //Custom array adapter for spin
    ArrayAdapter<String> spin_adapter;

    //Custom Wish List Adapter
    Wish_List_adapter wish_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        //prevent keyboard from appear when activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //links views
        category_spin=(android.widget.Spinner)findViewById(R.id.category_spinner);
        remind_text = (EditText)findViewById(R.id.remind_text);
        list = (ListView)findViewById(R.id.wish_list);

        //Spinner Item
        String my_category[]={"Food","Coffe","Pharmacy","Clothes","Labtops"};

        //set spinner
        spin_adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,my_category);
        spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spin.setAdapter(spin_adapter);

        //declare  SharedPreferences To save BUZZes
        SharedPreferences prefs = this.getSharedPreferences("com.ahmeddonkl.superbuzz", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        //load prev element
        Map<String, String> result;
        result= (Map<String, String>) prefs.getAll();

        //get element from map
        if(result != null)
        {
            Iterator myVeryOwnIterator = result.keySet().iterator();
            while (myVeryOwnIterator.hasNext())
            {
                String key = (String) myVeryOwnIterator.next();
                String value = (String) result.get(key);
                category.add(key);
                reminds.add(value);
            }
        }

        //set adapter
        wish_list_adapter = new Wish_List_adapter(this,category,reminds);
        list.setAdapter(wish_list_adapter);

        //add when press button
        Button button_buzz=(Button)findViewById(R.id.add_wish_button);
        button_buzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0)
            {
                //take Remind Text
                remind_text=(EditText)findViewById(R.id.remind_text);
                String remember_text=remind_text.getText().toString();

                //check if remind is empty
                if(remember_text.equals(""))
                {
                    remind_text.setError("Enter your Remind");
                }
                else
                {
                    //get selected value of category
                    String category_selected = category_spin.getSelectedItem().toString();

                    //check if element exist so append only remind text
                    if (category.contains(category_selected)) {
                        //get index of selected category
                        int index = category.indexOf(category_selected);
                        //save in shred preference
                        editor.putString(category_selected, reminds.get(index) + " \n" + remember_text);
                        editor.commit();

                        //update
                        reminds.set(index, reminds.get(index) + " \n" + remember_text);
                    } else {
                        //save in shred preference
                        editor.putString(category_selected, remember_text);
                        editor.commit();

                        //add to list
                        category.add(category_selected);
                        reminds.add(remember_text);
                    }
                    //update list view
                    wish_list_adapter.notifyDataSetChanged();

                    remind_text.setText("");
                }
            }

        });

        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        list,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    //remove from shared preference
                                    editor.remove(category.get(position));
                                    editor.commit();

                                    //remove from list
                                    reminds.remove(position);
                                    category.remove(position);

                                }
                                wish_list_adapter.notifyDataSetChanged();
                            }
                        });
        list.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        list.setOnScrollListener(touchListener.makeScrollListener());
        Get_Saved_Category();
    }

    Map<String, String> Get_Saved_Category()
    {
        //array list to save data on it
        Map<String, String> Category = new HashMap<>();
        //open shared prefrence
        SharedPreferences prefs = this.getSharedPreferences("com.ahmeddonkl.superbuzz", Context.MODE_PRIVATE);
        //load prev element
        Map<String, String> result;
        result= (Map<String, String>) prefs.getAll();

        //get element from map
        if(result != null)
        {
            Iterator myVeryOwnIterator = result.keySet().iterator();
            while (myVeryOwnIterator.hasNext())
            {
                String category = (String) myVeryOwnIterator.next();
                String Remind = (String) result.get(category);
                Category.put(category,Remind);
                Log.d("key", category);
                Log.d("value",Remind);
            }
        }
        return Category;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
