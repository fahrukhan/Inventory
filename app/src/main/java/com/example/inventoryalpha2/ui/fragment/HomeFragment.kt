package com.example.inventoryalpha2.ui.fragment

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.inventoryalpha2.R
import com.example.inventoryalpha2.helper.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toast = Toast(this.requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

//        with(activity as AppCompatActivity){
//            supportActionBar?.setBackgroundDrawable(
//                ColorDrawable(
//                    ContextCompat.getColor(context, R.color.colorPrimary)
//                )
//            )
//            window.statusBarColor = ContextCompat.getColor(context, R.color.colorPrimary)
//
//        }

    }

}