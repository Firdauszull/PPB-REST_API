package com.example.pertemuan9.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pertemuan9.R
import com.example.pertemuan9.adapter.HomeAdapter
import com.example.pertemuan9.databinding.HomeFragmentBinding
import com.example.pertemuan9.view.tambah.TambahFragment
import com.example.pertemuan9.viewmodel.ViewModelMahasiswa

class HomeFragment : Fragment() {
    lateinit var binding : HomeFragmentBinding
    lateinit var tambahFragment: TambahFragment
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
            binding.btnAdd.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_tambahFragment)
            }
        }
        viewModel.showDataMahasiswa()
    }


}
