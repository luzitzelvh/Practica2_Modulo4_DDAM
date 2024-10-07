package com.valdeolivar.practica2livh.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.valdeolivar.practica2livh.R
import com.valdeolivar.practica2livh.application.TandaApp
import com.valdeolivar.practica2livh.data.BorrowerRepository
import com.valdeolivar.practica2livh.data.remote.model.BorrowerDto
import com.valdeolivar.practica2livh.databinding.FragmentBorrowersListBinding
import com.valdeolivar.practica2livh.ui.adapters.BorrowersAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class BorrowersListFragment : Fragment() {

    private var _binding: FragmentBorrowersListBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: BorrowerRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBorrowersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = (requireActivity().application as TandaApp).repository

        // Llamada inicial para cargar datos
        loadData()
    }

    private fun loadData() {
        val call: retrofit2.Call<MutableList<BorrowerDto>> = repository.getBorrowers("borrower/borrower_list")

        call.enqueue(object : Callback<MutableList<BorrowerDto>> {
            override fun onResponse(
                p0: retrofit2.Call<MutableList<BorrowerDto>>,
                response: Response<MutableList<BorrowerDto>>
            ) {
                binding.pbLoading.visibility = View.GONE

                response.body()?.let { borrowers ->
                    binding.rvBorrowers.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = BorrowersAdapter(borrowers) { borrower ->
                            // Acción para ir a ver los detalles de los usuarios
                            borrower.id?.let{ id ->
                                requireActivity().supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, BorrowerDetailFragment.newInstance(id))
                                    .addToBackStack(null)
                                    .commit()
                            }

                        }
                    }
                }
            }

            override fun onFailure(p0: retrofit2.Call<MutableList<BorrowerDto>>, p1: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Error: No hay conexión",
                    Toast.LENGTH_SHORT
                ).show()

                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.fail_conection))
                    .setMessage(getString(R.string.check_conection))
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                        // Reintentar la carga de datos al presionar OK
                        loadData()
                    }
                    .create()
                    .show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}