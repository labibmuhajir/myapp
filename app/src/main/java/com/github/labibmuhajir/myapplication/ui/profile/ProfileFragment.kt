package com.github.labibmuhajir.myapplication.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.labibmuhajir.myapplication.R
import com.github.labibmuhajir.myapplication.data.model.User
import com.github.labibmuhajir.myapplication.data.model.ViewState
import com.github.labibmuhajir.myapplication.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModel<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userState.observe(viewLifecycleOwner) {
            when(it) {
                is ViewState.Loading -> {}
                is ViewState.Success -> bind(it.data)
                is ViewState.Error -> {}
            }
        }
    }

    private fun bind(user: User) {
        with(binding) {
            tvName.text = user.username
        }
    }
}