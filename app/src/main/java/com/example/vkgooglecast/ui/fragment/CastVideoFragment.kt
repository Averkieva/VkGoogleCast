package com.example.vkgooglecast.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.mediarouter.media.MediaRouter
import androidx.mediarouter.media.MediaRouterParams
import com.example.vkgooglecast.R
import com.example.vkgooglecast.databinding.FragmentCastVideoBinding
import com.example.vkgooglecast.ui.viewmodel.CastVideoViewModel
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class CastVideoFragment : Fragment(R.layout.fragment_cast_video) {

    private val viewModel: CastVideoViewModel by viewModel()
    private var _binding: FragmentCastVideoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCastVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeMediaRouter()
        initializeCastButton()
        setupClickListeners()
    }

    private fun initializeMediaRouter() {
        val mediaRouter = MediaRouter.getInstance(requireContext())
        mediaRouter.setRouterParams(
            MediaRouterParams.Builder()
                .setOutputSwitcherEnabled(true)
                .build()
        )
    }

    private fun initializeCastButton() {
        CastContext.getSharedInstance(requireContext())
        CastButtonFactory.setUpMediaRouteButton(
            requireContext(),
            binding.mediaRouteButton
        )
    }

    private fun setupClickListeners() {
        binding.castButton.setOnClickListener {
            viewModel.playVideo()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
