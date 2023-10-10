package com.juanfaria_jorgeseoane.proyectouf7juanyjorge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.juanfaria_jorgeseoane.proyectouf7juanyjorge.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            if (binding.editTextTextPassword.text.toString() == "admin" && binding.editTextTextPersonName.text.toString() == "admin") {
                findNavController().navigate(R.id.action_FirstFragment_to_itemFragment)
            } else {
                Toast.makeText(view.context, "USUARIO Y CONTRASEÑA INCORRECTO", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}