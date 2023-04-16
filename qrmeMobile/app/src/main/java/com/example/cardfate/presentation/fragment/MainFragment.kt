package com.example.cardfate.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.viewpager2.widget.ViewPager2
import com.example.cardfate.CardFateApp
import com.example.cardfate.R
import com.example.cardfate.databinding.FragmentMainBinding
import com.example.cardfate.presentation.activity.MainActivity
import com.example.cardfate.presentation.adapters.RvItemImagesAdapter
import com.example.cardfate.presentation.adapters.VpItemImagesAdapter
import com.example.cardfate.presentation.viewmodel.LogInViewModel
import com.example.cardfate.presentation.viewmodel.MainViewModel
import com.example.cardfate.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private val vpAdapterItemImages by lazy {
        VpItemImagesAdapter()
    }
    private val rvAdapterItemImages by lazy {
        RvItemImagesAdapter()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as CardFateApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindClickListeners()
        loadCards()
        setupImages()
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.cards.observe(viewLifecycleOwner) {
            binding.vpCards.visibility = View.VISIBLE
            vpAdapterItemImages.cards = it
            rvAdapterItemImages.images = it.map { it.imageUrl }
        }
    }

    private fun loadCards() {
        val id = getUserId()
        id?.let {
            mainViewModel.getCardsByUserId(it)
        }
    }

    private fun getUserId(): String? {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        return pref.getString(MainActivity.LOGIN, null)
    }

    private fun bindClickListeners() {
        vpAdapterItemImages.onItemClickListener = {
            navigateToFragment(CardFragment.newInstance(it.id!!))
        }
        binding.btCreateCart.setOnClickListener {
            navigateToFragment(CreateCardFragment())
        }
        binding.btMenu.setOnClickListener {
            navigateToFragment(MenuFragment())
        }
    }

    private fun setupImages() {
        val viewPager = binding.vpCards
        val photoList = binding.rvCards
        viewPager.adapter = vpAdapterItemImages
        photoList.adapter = rvAdapterItemImages
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(photoList)
        rvAdapterItemImages.setSelectedPosition(0)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                rvAdapterItemImages.setSelectedPosition(position)
                photoList.smoothScrollToPosition(position)
            }
        })
        rvAdapterItemImages.onItemClickListener = {
            viewPager.currentItem = it
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
