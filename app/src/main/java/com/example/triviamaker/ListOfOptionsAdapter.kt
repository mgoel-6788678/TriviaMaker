package com.example.triviamaker

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class ListOfOptionsAdapter(activity: Activity) : RecyclerView.Adapter<ListOfOptionsAdapter.Items>() {
    private val gameViewModel: GameViewModel = GameViewModel(activity.application)

    inner class Items(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Items {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Items(view)
    }

    override fun onBindViewHolder(holder: Items, position: Int) {
        // failing because the data is not changing even after the request is successfully made
        val netList: MutableList<String> = mutableListOf()
        if (gameViewModel.incorrect != null) {
            for (i in 0 until gameViewModel.incorrect!!.length()) {
                netList.add(gameViewModel.incorrect!!.getString(i))
                Log.d("netlist", netList[i])
            }
        }
        gameViewModel.correctAns?.let { netList.add(it) }
        if (netList.size != 0)
            holder.textView.text = netList[position]
        else
            holder.textView.text = "Default List Item"
    }

    override fun getItemCount(): Int {
//        return gameViewModel.incorrect!!.size + 1
//        if (gameViewModel.incorrect != null) return gameViewModel.incorrect!!.size + 1
//        return 1
        return gameViewModel.incorrect?.length()?.plus(1) ?: 1
    }

}