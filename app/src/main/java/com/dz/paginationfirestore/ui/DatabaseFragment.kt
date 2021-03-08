package com.dz.paginationfirestore.ui

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dz.paginationfirestore.adapters.DatabaseAdapter
import com.dz.paginationfirestore.databinding.DatabaselayoutBinding
import com.dz.paginationfirestore.models.DatabaseModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class DatabaseFragment : Fragment() {
    private lateinit var dataList : MutableList<DatabaseModel>
    private lateinit var databaseReference : DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseAdapter: DatabaseAdapter
    private  var _binding: DatabaselayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       _binding  = DatabaselayoutBinding.inflate(inflater,container,false)

        binding.databaseRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("food")

        dataList = mutableListOf()

        pushData()
        getData()

        return binding.root
    }

    private fun pushData(){
        getFoodDetails().map {
            databaseReference
                    .child(UUID.randomUUID().toString())
                    .setValue(it)
                    .addOnSuccessListener {

                    }
                    .addOnFailureListener {

                    }
        }
    }
    private fun getData(){
        databaseReference
                .get()
                .addOnFailureListener {
                }
                .addOnCompleteListener {
                    val data =it.result!!.getValue(DatabaseModel::class.java)
                    dataList.add(data!!)
                    databaseAdapter = DatabaseAdapter(dataList)
                    binding.databaseRecycler.adapter = databaseAdapter
                }
    }

    private fun getFoodDetails() : MutableList<DatabaseModel>{
        val mutableList = mutableListOf<DatabaseModel>()
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food2","Price2"))
        mutableList.add(DatabaseModel("Food3","Price3"))
        mutableList.add(DatabaseModel("Food4","Price4"))
        mutableList.add(DatabaseModel("Food5","Price5"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        mutableList.add(DatabaseModel("Food1","Price1"))
        return mutableList

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}