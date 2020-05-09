package com.example.recyclerview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set toolbar to be the ActionBar of the Activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize words list
        initializeList(mWordList);

        //Initialize mRecyclerView
        mRecyclerView = findViewById(R.id.recycler);

        //Create WordListAdapter
        mAdapter = new WordListAdapter(this, mWordList);

        //Link mRecyclerView with the adapter
        mRecyclerView.setAdapter(mAdapter);

        //Set the LayoutManager to be LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set an OnClickListener for the Floating Action Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int WordListSize = mWordList.size();

                //Add a word to the list
                mWordList.addLast("Word " + WordListSize);

                //Notify the adapter of the change
                mRecyclerView.getAdapter().notifyItemInserted(WordListSize);

                //Automatic scroll to the added word
                mRecyclerView.smoothScrollToPosition(WordListSize);

            }
        });
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
        if (id == R.id.action_reset) {
            reset(mWordList);
            initializeList(mWordList);
            mRecyclerView.getAdapter().notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void reset(LinkedList<String> mLinkedList) {
        //Reset the list
        //This is done using a loop
        //that remove the first element of the list
        //till it becomes empty
        while (!mLinkedList.isEmpty()) {
            mLinkedList.removeFirst();
        }
    }

    private void initializeList(LinkedList<String> mLinkedList) {
        //Initialize list with 20 words using a for loop
        for (int i = 0; i < 20; i++) {
            mLinkedList.add(i, "Word " + i);
        }
    }
}
