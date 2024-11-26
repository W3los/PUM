package com.example.lista2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lista2.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegister.setOnClickListener {
            val newUsername = binding.editTextNewUsername.text.toString()
            val newPassword = binding.editTextNewPassword.text.toString()

            if (newUsername.isNotBlank() && newPassword.isNotBlank()) {
                defaultUsers.add(User(newUsername, newPassword))
                Toast.makeText(requireContext(), "User registered", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_register_to_login)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonBackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
