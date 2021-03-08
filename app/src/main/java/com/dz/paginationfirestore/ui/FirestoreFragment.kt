package com.dz.paginationfirestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.dz.paginationfirestore.adapters.FirestoreAdapter
import com.dz.paginationfirestore.databinding.FirestorelayoutBinding
import com.dz.paginationfirestore.models.FirestoreModel
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.api.Distribution
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FirestoreFragment : Fragment() {

    private lateinit var firestoreAdapter: FirestoreAdapter
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseFirestore : CollectionReference
    private var _binding : FirestorelayoutBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       _binding = FirestorelayoutBinding.inflate(inflater,container,false)


        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance().collection("car")


        binding.firestoreRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        pushData()
        getData()
        return binding.root
    }

    private fun pushData(){
        getCardDetails().map {
            firebaseFirestore
                    .add(it)
                .addOnCompleteListener {
                     // success
                }
                .addOnFailureListener {
                    // failure
                }
        }
    }
    private fun getData(){

        val config = PagedList.Config.Builder()
                .setPageSize(5)
                .setPrefetchDistance(3)
                .build()
        val query  = firebaseFirestore.orderBy("cardPrice",Query.Direction.ASCENDING)

        val pagingOptions = FirestorePagingOptions.Builder<FirestoreModel>()
                .setQuery(query,config,FirestoreModel::class.java)
                .setLifecycleOwner(this)
                .build()

        firestoreAdapter = FirestoreAdapter(pagingOptions)
        binding.firestoreRecycler.adapter  = firestoreAdapter


    }


    private fun getCardDetails() : MutableList<FirestoreModel>{
        val mutableList = mutableListOf<FirestoreModel>()
        mutableList.add(FirestoreModel("Toyota","15000"))
        mutableList.add(FirestoreModel("Mercedez","25000"))
        mutableList.add(FirestoreModel("Renault","17000"))
        mutableList.add(FirestoreModel("Kia","25000"))
        mutableList.add(FirestoreModel("Ferrari","13500"))
        mutableList.add(FirestoreModel("Lamborghini","13000"))
        mutableList.add(FirestoreModel("Peugeot","12000"))
        mutableList.add(FirestoreModel("Dacia","75841"))
        mutableList.add(FirestoreModel("Golf","10000"))
        mutableList.add(FirestoreModel("BWM","25000"))
        mutableList.add(FirestoreModel("GMC","25000"))
        mutableList.add(FirestoreModel("Chevrolet","25000"))
        mutableList.add(FirestoreModel("Opel","25000"))
        mutableList.add(FirestoreModel("Lada","25000"))
        mutableList.add(FirestoreModel("Citroen","25000"))
        mutableList.add(FirestoreModel("Ford","25000"))
        mutableList.add(FirestoreModel("TATA","25000"))
        mutableList.add(FirestoreModel("IVECO","25000"))
        mutableList.add(FirestoreModel("GOOGLE","25000"))
        mutableList.add(FirestoreModel("Micosoft","30000"))
        mutableList.add(FirestoreModel("Tesla","23000"))
        mutableList.add(FirestoreModel("Nissan","411111"))

        return mutableList
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}