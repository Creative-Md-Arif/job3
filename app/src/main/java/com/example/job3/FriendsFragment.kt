package com.example.job3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.job3.adapter.UserAdapter
import com.example.job3.databinding.FragmentFriendsBinding
import com.example.job3.viewmodel.AuthenticationViewModel
import com.example.job3.viewmodel.FirestoreViewModel


class FriendsFragment : Fragment() {

    lateinit var binding: FragmentFriendsBinding
    private lateinit var firestoreViewModel: FirestoreViewModel
    private lateinit var authenticationViewModel: AuthenticationViewModel
    private lateinit var userAdapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendsBinding.inflate(inflater,container, false)



        return binding.root
    }


}