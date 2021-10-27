package com.uniandes.vinilos.ui.awards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uniandes.vinilos.databinding.FragmentAwardsBinding

class AwardsFragment : Fragment() {

    private lateinit var awardsViewModel: AwardsViewModel
    private var _binding: FragmentAwardsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        awardsViewModel =
            ViewModelProvider(this).get(AwardsViewModel::class.java)

        _binding = FragmentAwardsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAwards
        awardsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}