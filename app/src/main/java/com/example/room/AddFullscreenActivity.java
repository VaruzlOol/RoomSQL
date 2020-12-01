package com.example.room;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.room.Room.Alumn;
import com.example.room.Room.AlumnLab;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class AddFullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = (view, motionEvent) -> {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (AUTO_HIDE) {
                    delayedHide(AUTO_HIDE_DELAY_MILLIS);
                }
                break;
            case MotionEvent.ACTION_UP:
                view.performClick();
                break;
            default:
                break;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_fullscreen);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);

        // Set up the user interaction to manually show or hide the system UI.
        //mContentView.setOnClickListener(view -> toggle());

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        //METHODS
        exit();
        addAlum();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private void exit(){
        ImageButton IBExits = (ImageButton) findViewById(R.id.IBExit);
        IBExits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addAlum(){
        Button btAdd = (Button) findViewById(R.id.btAdd);
        btAdd.setOnClickListener(v -> insertAlum());
    }

    protected void insertAlum(){
        try {
            EditText edFName = (EditText) findViewById(R.id.edFName);
            EditText edLName = (EditText) findViewById(R.id.edLName);
            EditText edAge = (EditText) findViewById(R.id.edAge);
            EditText edTel = (EditText) findViewById(R.id.edTel);

            if (edFName.length() > 0 && edLName.length() > 0 && edAge.length() > 0 && edTel.length() > 0) {
                AlumnLab alumnLab = AlumnLab.get(this);
                Alumn alumn = new Alumn();
                alumn.setFirstName(edFName.getText().toString());
                alumn.setLastName(edLName.getText().toString());
                alumn.setAge(edAge.getText().toString().length() < 1 ? 0 : Integer.parseInt(edAge.getText().toString()));
                alumn.setTel(edTel.getText().toString());
                alumnLab.addAlumn(alumn);

                updateAdapter();

                Toast.makeText(this, "Alumno insertado", Toast.LENGTH_LONG).show();
                clearFields();
            }else{
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error al insertar alumno: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    protected void clearFields(){
        EditText edFName = (EditText) findViewById(R.id.edFName);
        EditText edLName = (EditText) findViewById(R.id.edLName);
        EditText edAge = (EditText) findViewById(R.id.edAge);
        EditText edTel = (EditText) findViewById(R.id.edTel);

        edFName.setText("");
        edLName.setText("");
        edAge.setText("");
        edTel.setText("");
    }

    protected void updateAdapter(){
        try {
            //12607
            AlumnLab alumnLab = AlumnLab.get(this);
            List<Alumn> alumns = alumnLab.getAlumns();

            MyItemRecyclerViewAdapter myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(this, alumns);


            RecyclerView recyclerView = findViewById(R.id.list);
            recyclerView.setAdapter(myItemRecyclerViewAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(dividerItemDecoration);
            //recyclerView.setLayoutManager(new LinearLayoutManager(this));



            myItemRecyclerViewAdapter.notifyDataSetChanged();
        }catch (Exception ex){
            Log.d("", ex.getMessage());
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
