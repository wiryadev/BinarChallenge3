package com.wiryadev.binarchallenge3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wiryadev.binarchallenge3.databinding.FragmentFourthBinding

class FourthFragment : Fragment() {

    private var _binding: FragmentFourthBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<FourthFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            validateInput()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validateInput() {
        with(binding) {
            when {
                etAge.text.isNullOrEmpty() -> {
                    etAge.error = "Umur tidak boleh kosong"
                }
                etAddress.text.isNullOrEmpty() -> {
                    etAddress.error = "Alamat tidak boleh kosong"
                }
                etJob.text.isNullOrEmpty() -> {
                    etJob.error = "Pekerjaan tidak boleh kosong"
                }
                etMass.text.isNullOrEmpty() -> {
                    etMass.error = "Massa tidak boleh kosong"
                }
                etGravity.text.isNullOrEmpty() -> {
                    etGravity.error = "Gravitasi tidak boleh kosong"
                }
                etHeight.text.isNullOrEmpty() -> {
                    etHeight.error = "Tinggi tidak boleh kosong"
                }
                else -> {
                    moveToThirdFragment()
                }
            }
        }
    }

    private fun moveToThirdFragment() {
        with(binding) {
            // Person
            val address = etAddress.text.toString()
            val age = try {
                etAge.text.toString().toInt()
            } catch (e: Exception) {
                0
            }
            val job = etJob.text.toString()

            // Potential energy
            val mass = try {
                etMass.text.toString().toDouble()
            } catch (e: Exception) {
                0.0
            }
            val gravity = try {
                etGravity.text.toString().toDouble()
            } catch (e: Exception) {
                0.0
            }
            val height = try {
                etHeight.text.toString().toInt()
            } catch (e: Exception) {
                0
            }

            val person = Person(
                name = args.name,
                age = age,
                address = address,
                job = job,
            )

            val energy = PotentialEnergy(
                mass = mass,
                gravity = gravity,
                height = height,
            )

            val bundle = Bundle().apply {
                putParcelableArray(
                    ThirdFragment.EXTRA_ARRAY,
                    arrayOf(person, energy),
                )
            }

            findNavController().navigate(
                R.id.action_fourthFragment_to_thirdFragment,
                bundle
            )
        }
    }

}