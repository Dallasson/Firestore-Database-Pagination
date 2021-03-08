package com.dz.paginationfirestore.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.R
import androidx.recyclerview.widget.RecyclerView
import com.dz.paginationfirestore.databinding.FirestoreRowsLayoutBinding
import com.dz.paginationfirestore.models.FirestoreModel
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.firebase.ui.firestore.paging.LoadingState


class FirestoreAdapter(options : FirestorePagingOptions<FirestoreModel>)
    : FirestorePagingAdapter<FirestoreModel,FirestoreAdapter.ViewHolder>(options) {

    class ViewHolder(var firestoreRowsLayoutBinding: FirestoreRowsLayoutBinding) :
            RecyclerView.ViewHolder(firestoreRowsLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<FirestoreRowsLayoutBinding>(
            LayoutInflater.from(parent.context),
            com.dz.paginationfirestore.R.layout.firestore_rows_layout,
            parent,
                false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: FirestoreModel) {
        val data = model
        holder.firestoreRowsLayoutBinding.model = data

    }

    override fun onLoadingStateChanged(state: LoadingState) {
        super.onLoadingStateChanged(state)
        when(state){
            LoadingState.LOADING_INITIAL -> {
                Log.d("TAG FIRESTORE","Loading Initial Data...")
            }
            LoadingState.LOADING_MORE -> {
                Log.d("TAG FIRESTORE","Loading More...")
            }
            LoadingState.ERROR -> {
                Log.d("TAG FIRESTORE","Error Loading..")
            }
            LoadingState.FINISHED -> {
                Log.d("TAG FIRESTORE","Loading Finished..")
            }
            LoadingState.LOADED -> {
                Log.d("TAG FIRESTORE","Loaded Fired...")
            }
        }
    }
}