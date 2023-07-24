package com.example.starwarsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapp.adapters.StarWarsAdapter
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private val starWarsAdapter = StarWarsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }
    private fun init() {
        initStarWarsRecycle()
    }
    private fun initStarWarsRecycle() {
        binding.objects.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        binding.objects.adapter = starWarsAdapter
        val observer = Observer<List<StarWarsObject>> { newValue ->
            starWarsAdapter.submitList(newValue)
        }
        viewModel.liveData.observe(viewLifecycleOwner, observer)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}