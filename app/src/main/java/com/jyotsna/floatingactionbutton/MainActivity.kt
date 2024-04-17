package com.jyotsna.floatingactionbutton

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var listItems: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        fab = findViewById(R.id.fab)

        listItems = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter

        fab.setOnClickListener { view ->
            addItem()
            showUndoSnackbar(view)
        }
    }

    private fun addItem() {
        listItems.add("Item ${listItems.size + 1}")
        adapter.notifyDataSetChanged()
    }

    private fun showUndoSnackbar(view: View) {
        val snackbar = Snackbar.make(view, "Item added", Snackbar.LENGTH_LONG)
        snackbar.setAction("Undo") {
            undoLastItem()
        }
        snackbar.show()
    }

    private fun undoLastItem() {
        if (listItems.isNotEmpty()) {
            listItems.removeAt(listItems.size - 1)
            adapter.notifyDataSetChanged()
        }
    }
}
