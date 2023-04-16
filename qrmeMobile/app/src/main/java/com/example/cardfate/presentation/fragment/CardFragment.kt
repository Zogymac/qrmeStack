package com.example.cardfate.presentation.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.cardfate.CardFateApp
import com.example.cardfate.R
import com.example.cardfate.databinding.FragmentCardBinding
import com.example.cardfate.domain.entity.Card
import com.example.cardfate.presentation.activity.MainActivity
import com.example.cardfate.presentation.viewmodel.CardViewModel
import com.example.cardfate.presentation.viewmodel.ViewModelFactory
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.squareup.picasso.Picasso
import java.util.*
import javax.inject.Inject


class CardFragment : Fragment() {

    private var cardId: String = "null"

    private var _binding: FragmentCardBinding? = null
    private val binding: FragmentCardBinding
        get() = _binding ?: throw RuntimeException("FragmentCardBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val cardViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CardViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as CardFateApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cardId = it.getString(CARD_ID)?:"null"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCardById()
        observeViewModel()
        bindClickListeners()
    }

    private fun bindClickListeners() {
        binding.btQr.setOnClickListener {
            generateQRCode()
        }
        binding.btCloseQr.setOnClickListener {
            binding.layoutQr.visibility = View.GONE
        }
        binding.btFavorite.setOnClickListener {
            cardViewModel.addCardToFavorite(getUserId()!!,cardId)
        }
    }

    private fun getUserId(): String? {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        return pref.getString(MainActivity.LOGIN, null)
    }

    private fun observeViewModel() {
        cardViewModel.card.observe(viewLifecycleOwner){
            with(binding){
                Picasso.get().load(it.imageUrl).into(ivCardImage)
                tvFullName.text = it.fullName
                tvProfession.text = it.skills
                tvBio.text = it.bio
            }
        }
        cardViewModel.cardAdded.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "Добавлено в избранное", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCardById() {
        cardViewModel.getCardById(cardId)
    }

    private fun generateQRCode() {
        val writer = QRCodeWriter()
        val hints = Hashtable<EncodeHintType, Any>()
        hints[EncodeHintType.CHARACTER_SET] = "UTF-8"

        val link = "http://www.cardfate.com/card?=$cardId"
        // Generate QR code bitmap
        val qrCode = writer.encode(link, BarcodeFormat.QR_CODE, 512, 512, hints)
        val width = qrCode.width
        val height = qrCode.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        // Set QR code pixels to the bitmap
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (qrCode.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }

        // Set QR code bitmap to the image view
        binding.layoutQr.visibility = View.VISIBLE
        binding.ivQr.setImageBitmap(bitmap)

    }

    companion object {

        private const val CARD_ID = "card_id"

        fun newInstance(cardId: String) =
            CardFragment().apply {
                arguments = Bundle().apply {
                    putString(CARD_ID, cardId)
                }
            }
    }
}