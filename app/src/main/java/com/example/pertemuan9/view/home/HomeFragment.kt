package com.example.pertemuan9.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pertemuan9.adapter.HomeAdapter
import com.example.pertemuan9.databinding.HomeFragmentBinding
import com.example.pertemuan9.viewmodel.ViewModelMahasiswa

class HomeFragment : Fragment() {
    lateinit var binding : HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
// Inflate the layout for this fragment
        binding = HomeFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState:
    Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(ViewModelMahasiswa::class.java)
            viewModel.getDataMahasiswa().observe(viewLifecycleOwner)
        {
            if (it != null) {
                binding.rvUser.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                val adapter = HomeAdapter(it)
                binding.rvUser.adapter = adapter
            }else{
                binding.rvUser.visibility = View.GONE
            }
        }
        viewModel.showDataMahasiswa()
    }
}
