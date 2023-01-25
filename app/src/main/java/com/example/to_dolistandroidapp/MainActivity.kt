package com.example.to_dolistandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing the array lists and the adapter
        var itemList = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemList)


        // Adding the items to the list when the add button is pressed
        // get reference to button
        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val editText = findViewById<EditText>(R.id.editView)
        val listView = findViewById<ListView>(R.id.listView)

        // set on-click listener
        addButton.setOnClickListener {
            // your code to perform when the user clicks on the button
            itemList.add((editText.text.toString()))
            listView.adapter =  adapter
            editText.text.clear()
        }

        deleteButton.setOnClickListener{
            val position: SparseBooleanArray = listView.checkedItemPositions // object with selected items
            val count = listView.count //[].length
            var item = count - 1//index of array
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemList[item])
                }
                item--
            }
            position.clear()
        }
        // Clearing all the items in the list when the clear button is pressed
        clearButton.setOnClickListener {
            itemList.clear()
            adapter.notifyDataSetChanged()
        }

        // Adding the toast message to the list when an item on the list is pressed
        listView.setOnItemClickListener { _, _, index, _ ->
            android.widget.Toast.makeText(this, "You Selected the item --> "+itemList[index], android.widget.Toast.LENGTH_SHORT).show()
        }
    }

}