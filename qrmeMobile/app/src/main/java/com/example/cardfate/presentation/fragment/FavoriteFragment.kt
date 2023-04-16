package com.example.cardfate.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.cardfate.CardFateApp
import com.example.cardfate.R
import com.example.cardfate.databinding.FragmentFavoriteBinding
import com.example.cardfate.presentation.activity.MainActivity
import com.example.cardfate.presentation.adapters.RvItemImagesAdapter
import com.example.cardfate.presentation.adapters.RvItemsFavoriteAdapter
import com.example.cardfate.presentation.viewmodel.FavoriteViewModel
import com.example.cardfate.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoriteBinding == null")

    private val rvAdapterItemsFavorite by lazy {
        RvItemsFavoriteAdapter()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as CardFateApp).component
    }

    private val favoriteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindClickListeners()
        loadFavoriteCards()
        observeViewModel()
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.rvFavoriteCards.adapter = rvAdapterItemsFavorite
    }

    private fun loadFavoriteCards() {
        favoriteViewModel.getFavoriteCardsByUserId(getUserId()!!)
    }

    private fun observeViewModel() {
        favoriteViewModel.favoriteCards.observe(viewLifecycleOwner) {
            rvAdapterItemsFavorite.cards = it
        }
    }

    private fun getUserId(): String? {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        return pref.getString(MainActivity.LOGIN, null)
    }

    private fun bindClickListeners() {
        binding.btMenu.setOnClickListener {
            navigateToFragment(MainFragment())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}