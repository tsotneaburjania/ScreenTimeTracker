package com.example.screentimetracker.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.screentimetracker.fragments.FragmentOne
import com.example.screentimetracker.fragments.FragmentThree
import com.example.screentimetracker.fragments.FragmentTwo

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                FragmentOne()
            }
            1 -> {
                FragmentTwo()
            }
            2 -> {
                FragmentThree()
            }
            else -> {
                FragmentOne()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Total"
            }
            1 -> {
                return "WOtD"
            }
            2 -> {
                return "Tab 3"
            }
        }
        return super.getPageTitle(position)
    }

}