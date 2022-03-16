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

        with(binding) {
            button.setOnClickListener {
                val address = etAddress.text.toString()
                val age = try {
                    etAge.text.toString().toInt()
                } catch (e: Exception) {
                    0
                }
                val job = etJob.text.toString()

                val bundle = bundleOf(
                    ThirdFragment.EXTRA_PERSON to Person(
                        name = args.name,
                        age = age,
                        address = address,
                        job = job,
                    )
                )

                findNavController().navigate(
                    R.id.action_fourthFragment_to_thirdFragment,
                    bundle
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}