package com.example.starwarsapp.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapp.adapters.StarWarsAdapter
import com.example.starwarsapp.data.models.OutputOf
import com.example.starwarsapp.data.models.StarWarsObject
import com.example.starwarsapp.databinding.FragmentFavouriteBinding
import com.example.starwarsapp.interfaces.listener.StarWarsObjectListener

class FavouriteFragment : Fragment(), StarWarsObjectListener {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FavouriteViewModel>()
    private val starWarsAdapter = StarWarsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        init()
        viewModel.init()
        return binding.root
    }

    private fun init() {
        initStarWarsRecycle()
        initHeaderMenu()
        initClearBtn()
    }

    private fun initHeaderMenu() {
        binding.appbar.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun initClearBtn(){
        binding.clear.setOnClickListener{
            viewModel.clear()
        }
    }
    private fun initStarWarsRecycle() {
        binding.likedObjects.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        binding.likedObjects.adapter = starWarsAdapter
        val observer = Observer<OutputOf<List<StarWarsObject>>> { newValue ->
            when (newValue) {
                is OutputOf.Success -> {
                    starWarsAdapter.submitList(newValue.value)
                    binding.favourites.visibility = View.VISIBLE
                    binding.loader.root.visibility = View.GONE
                    binding.emptyText.visibility = View.GONE
                }
                is OutputOf.Nothing -> {
                    binding.favourites.visibility = View.GONE
                    binding.loader.root.visibility = View.GONE
                    binding.emptyText.visibility = View.VISIBLE
                }
                is OutputOf.Loader -> {
                    binding.favourites.visibility = View.GONE
                    binding.loader.root.visibility = View.VISIBLE
                    binding.emptyText.visibility = View.GONE
                }
                else -> {
                    throw Exception("incorrect type of result")
                }
            }

        }
        viewModel.liveData.observe(viewLifecycleOwner, observer)
    }

    override fun onClick(SWObject: StarWarsObject) {
        viewModel.removeElement(SWObject)
    }
}