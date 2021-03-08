package com.dz.paginationfirestore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dz.paginationfirestore.R
import com.dz.paginationfirestore.databinding.DatabaseRowsLayoutBinding
import com.dz.paginationfirestore.models.DatabaseModel

class DatabaseAdapter(var list : List<DatabaseModel>) : RecyclerView.Adapter<DatabaseAdapter.ViewHolder>(){

    class  ViewHolder(var databaseRowsLayoutBinding: DatabaseRowsLayoutBinding)
        : RecyclerView.ViewHolder(databaseRowsLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<DatabaseRowsLayoutBinding>(
                LayoutInflater.from(parent.context),
                R.layout.database_rows_layout,
                parent,false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.databaseRowsLayoutBinding.model = data
    }

    override fun getItemCount(): Int {
        return list.size
    }
}