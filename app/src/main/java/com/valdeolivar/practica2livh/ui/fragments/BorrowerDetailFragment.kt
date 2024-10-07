package com.valdeolivar.practica2livh.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.valdeolivar.practica2livh.R
import com.valdeolivar.practica2livh.application.TandaApp
import com.valdeolivar.practica2livh.data.BorrowerRepository
import com.valdeolivar.practica2livh.data.remote.BorrowerApi
import com.valdeolivar.practica2livh.data.remote.model.BorrowerDetailDto
import com.valdeolivar.practica2livh.databinding.FragmentBorrowerDetailBinding
import com.valdeolivar.practica2livh.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val BORROWER_ID = "borrower_id"

class BorrowerDetailFragment : Fragment() {

    private var borrowerId: String? = null

    private var _binding: FragmentBorrowerDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: BorrowerRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            borrowerId = it.getString(BORROWER_ID)
            Log.d(Constants.LOGTAG, getString(R.string.id_received, borrowerId))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBorrowerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = (requireActivity().application as TandaApp).repository

        // Llamada inicial para cargar los detalles
        loadBorrowerDetails()
    }

    private fun loadBorrowerDetails() {
        borrowerId?.let { id ->
            val call: Call<BorrowerDetailDto> = repository.getBorrowersDetail(id)

            call.enqueue(object : Callback<BorrowerDetailDto> {
                override fun onResponse(
                    p0: Call<BorrowerDetailDto>,
                    response: Response<BorrowerDetailDto>
                ) {
                    binding.pbLoading.visibility = View.GONE
                    response.body()?.let { borrowerDetail ->
                        binding.tvName.text = "${borrowerDetail.name} ${borrowerDetail.surname}"
                        binding.tvState.text = borrowerDetail.state
                        Glide.with(binding.root.context)
                            .load(borrowerDetail.image)
                            .into(binding.ivImage)
                    }
                }

                override fun onFailure(p0: Call<BorrowerDetailDto>, p1: Throwable) {
                    // Manejo del error de conexión
                    binding.pbLoading.visibility = View.GONE
                    showConnectionErrorDialog()
                }
            })
        }
    }

    private fun showConnectionErrorDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.fail_conection))
            .setMessage(getString(R.string.check_conection))
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                // Reintentar la carga de detalles al presionar OK
                loadBorrowerDetails()
            }
            .create()
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance(borrowerId: String) =
            BorrowerDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(BORROWER_ID, borrowerId)
                }
            }
    }
}