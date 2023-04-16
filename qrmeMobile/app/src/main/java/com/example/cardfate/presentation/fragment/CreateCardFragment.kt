package com.example.cardfate.presentation.fragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.cardfate.CardFateApp
import com.example.cardfate.R
import com.example.cardfate.databinding.FragmentCreateCardBinding
import com.example.cardfate.domain.entity.Card
import com.example.cardfate.presentation.activity.MainActivity
import com.example.cardfate.presentation.state.*
import com.example.cardfate.presentation.viewmodel.CreateCardViewModel
import com.example.cardfate.presentation.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject


class CreateCardFragment : Fragment() {

    private var _binding: FragmentCreateCardBinding? = null
    private val binding: FragmentCreateCardBinding
        get() = _binding ?: throw RuntimeException("FragmentCreateCardBinding == null")


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val createCardViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CreateCardViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as CardFateApp).component
    }

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var requestLauncher: ActivityResultLauncher<String>

    private var imageUrl: String? = null

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        registerActivityForResultPickImage()
        registerActivityForResultRequestPermission()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupImageView()
        observeViewModel()
        bindClickListeners()
    }

    private fun setupImageView() {
        binding.ivUserImage.apply {
            clipToOutline = true
        }
    }

    private fun bindClickListeners() {
        with(binding) {
            btCreateCard.setOnClickListener {
                val cardName = etCardName.text.toString()
                val fullName = etFullName.text.toString()
                val phone = etPhone.text.toString()
                val degree = etDegree.text.toString()
                val bio = etBio.text.toString()
                val skills = etSkills.text.toString()
                val linkVk = etLinkVk.text.toString()
                val linkTg = etLinkTg.text.toString()
                val mail = etMail.text.toString()
                val userId = getUserId()
                val card =
                    Card(
                        cardName,
                        fullName,
                        phone,
                        imageUrl,
                        degree,
                        bio,
                        skills,
                        linkVk,
                        linkTg,
                        mail,
                        userId
                    )
                createCardViewModel.uploadCard(card)
            }
            ivUserImage.setOnClickListener {
                storagePermissionRequest()
            }
        }
    }

    private fun getUserId(): String? {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        return pref.getString(MainActivity.LOGIN, null)
    }

    private fun observeViewModel() {
        createCardViewModel.uploadCardState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            binding.btCreateCard.isEnabled = true

            when (it) {
                is UploadSuccess -> {
                    navigateToFragment(MainFragment())
                }
                is UploadProgress -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btCreateCard.isEnabled = false
                }
                is UploadError -> {
                    Toast.makeText(
                        requireContext(),
                        "Что-то пошло не так. Проверьте интернет-подключение",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }


    private fun registerActivityForResultPickImage() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.data?.let {
                        uploadProfileImage(it.toString())

                        imageUrl = it.toString()
                    }
                }
            }
    }

    private fun registerActivityForResultRequestPermission() {
        requestLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                resultLauncher.launch(intent)
            }
        }
    }

    private fun storagePermissionRequest() {
        requestLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private fun uploadProfileImage(imageUrl: String) {
        Picasso.get().load(imageUrl)
            .into(binding.ivUserImage)
    }
}