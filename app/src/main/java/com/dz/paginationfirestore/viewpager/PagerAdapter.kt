package com.dz.paginationfirestore.viewpager

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dz.paginationfirestore.ui.DatabaseFragment
import com.dz.paginationfirestore.ui.FirestoreFragment

class PagerAdapter(fm : FragmentActivity) : FragmentStateAdapter(fm) {
    private val tabs = 2
    override fun getItemCount(): Int {
       return tabs
    }

    override fun createFragment(position: Int): Fragment {
        if(position == 0)
            return   FirestoreFragment()
        else
            return  DatabaseFragment()
    }

}