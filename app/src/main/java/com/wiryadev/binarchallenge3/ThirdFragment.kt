package com.wiryadev.binarchallenge3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wiryadev.binarchallenge3.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ThirdFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments?.getParcelableArray(EXTRA_ARRAY)
        val name: String

        if (bundle != null) {
            val person = bundle[0] as Person
            binding.biodata.run {
                tvName.text = getString(R.string.your_name, person.name)
                tvAge.text = person.age.isEvenOrOdd()
                tvAddress.text = getString(R.string.your_address, person.address)
                tvJob.text = getString(R.string.your_job, person.job)
            }
            binding.tvEnergy.apply {
                visibility = View.VISIBLE
                text = getString(
                    R.string.potential_energy,
                    calculatePotentialEnergy(bundle[1] as PotentialEnergy)
                )
            }
            binding.button.visibility = View.GONE
            name = person.name
        } else {
            binding.biodata.run {
                tvName.text = getString(R.string.your_name, args.name)
                tvAge.visibility = View.GONE
                tvAddress.visibility = View.GONE
                tvJob.visibility = View.GONE
            }
            binding.tvEnergy.visibility = View.GONE
            binding.button.visibility = View.VISIBLE
            name = args.name
        }

        with(binding) {
            button.setOnClickListener {
                findNavController().navigate(
                    ThirdFragmentDirections.actionThirdFragmentToFourthFragment(
                        name = name
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun Int.isEvenOrOdd(): String {
        return getString(
            R.string.your_age, if (this % 2 == 0) {
                "Genap"
            } else {
                "Ganjil"
            }
        )
    }

    private fun calculatePotentialEnergy(energy: PotentialEnergy): String {
        return (energy.gravity * energy.height * energy.mass).toString()
    }

    companion object {
        const val EXTRA_ARRAY = "extra_array"
    }

}