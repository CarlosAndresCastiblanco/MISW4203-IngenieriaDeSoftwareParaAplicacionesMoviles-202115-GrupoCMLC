package com.uniandes.vinilos.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uniandes.vinilos.databinding.FragmentAlbumDetailBinding
import com.uniandes.vinilos.databinding.FragmentAlbumsBinding
import kotlinx.android.synthetic.main.fragment_albums.*

class AlbumDetailFragment: Fragment() {
    private lateinit var albumDetailViewModel: AlbumDetailViewModel
    private var _binding: FragmentAlbumDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        albumDetailViewModel =
            ViewModelProvider(this).get(AlbumDetailViewModel::class.java)

        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textAlbums*/
        /*albumsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        albumDetailViewModel.album.observe(viewLifecycleOwner, {
            init(it)
        })
        albumDetailViewModel.onCreate()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}