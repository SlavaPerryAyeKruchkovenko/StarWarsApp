package com.example.starwarsapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapp.R
import com.example.starwarsapp.adapters.StarWarsAdapter
import com.example.starwarsapp.data.models.OutputOf
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.data.utils.RequestType
import com.example.starwarsapp.databinding.FragmentHomeBinding
import com.example.starwarsapp.interfaces.listener.StarWarsObjectListener

class HomeFragment : Fragment(), StarWarsObjectListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private val starWarsAdapter = StarWarsAdapter(this)
    private var searchView: SearchView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        searchView = binding.searchAppbar.searchBar
        init()
        viewModel.init()
        return binding.root
    }
    private fun init() {
        initStarWarsRecycle()
        initSearchView()
        initFavouriteBtn()
    }
    private fun initStarWarsRecycle() {
        binding.objects.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        binding.objects.adapter = starWarsAdapter
        val observer = Observer<OutputOf<List<StarWarsObject>>> { newValue ->
            when (newValue) {
                is OutputOf.Success -> {
                    starWarsAdapter.submitList(newValue.value)
                    binding.objects.visibility = View.VISIBLE
                    binding.loader.root.visibility = View.GONE
                    binding.emptyText.visibility = View.GONE
                    binding.notFound.visibility = View.GONE
                }
                is OutputOf.Nothing -> {
                    binding.objects.visibility = View.GONE
                    binding.loader.root.visibility = View.GONE
                    binding.emptyText.visibility = View.VISIBLE
                    binding.notFound.visibility = View.GONE
                }
                is OutputOf.Loader -> {
                    binding.objects.visibility = View.GONE
                    binding.loader.root.visibility = View.VISIBLE
                    binding.emptyText.visibility = View.GONE
                    binding.notFound.visibility = View.GONE
                }
                is OutputOf.Error -> {
                    binding.objects.visibility = View.GONE
                    binding.loader.root.visibility = View.GONE
                    binding.emptyText.visibility = View.GONE
                    binding.notFound.visibility = View.VISIBLE
                }
                else -> {
                    throw Exception("incorrect type of result")
                }
            }
        }
        viewModel.liveData.observe(viewLifecycleOwner, observer)
    }

    private fun initSearchView() {
        this.searchView?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(value: String): Boolean {
                val requestType = binding.requestType.text.toString()
                try {
                    val type = RequestType.valueOf(requestType.uppercase())
                    viewModel.findByName(value,type)
                } catch (_: Exception) {

                }
                return false
            }

            override fun onQueryTextChange(value: String): Boolean {
                return false
            }
        })
    }

    private fun initFavouriteBtn() {
        binding.searchAppbar.favouriteBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favouriteFragment)
        }
    }

    override fun onDestroy() {
        searchView?.clearFocus()
        super.onDestroy()
        _binding = null
        searchView = null
    }

    override fun onClick(SWObject: StarWarsObject) {
        if (SWObject.isLike) {
            SWObject.dislike()
        } else {
            SWObject.like()
        }
        viewModel.updateObject(SWObject)
    }
}