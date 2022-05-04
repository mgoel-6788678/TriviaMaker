package com.example.triviamaker

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class ListOfOptionsAdapter(private val activity: Activity) : RecyclerView.Adapter<ListOfOptionsAdapter.Items>() {

    private val gameViewModel: GameViewModel = GameViewModel(activity.application)

    inner class Items(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Items {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Items(view)
    }

    override fun onBindViewHolder(holder: Items, position: Int) {
        var netList: MutableList<String>? = null
        if (gameViewModel.incorrect != null) {
            netList = gameViewModel.incorrect!!
        }
        if (netList != null) {
            gameViewModel.correctAns?.let { netList.add(it) }
        }
        holder.textView.text = netList?.get(position)
    }

    override fun getItemCount(): Int {
//        return gameViewModel.incorrect!!.size + 1
        if (gameViewModel.incorrect != null) return gameViewModel.incorrect!!.size + 1
        return 1
    }
}