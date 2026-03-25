package com.freightconnect.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.freightconnect.R
import com.freightconnect.databinding.FragmentProfileBinding
import com.freightconnect.model.AppLanguage
import com.freightconnect.ui.activities.AuthActivity
import com.freightconnect.utils.LanguageHelper
import com.freightconnect.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProfile()
        setupLanguageSelector()
        setupLogout()
    }

    private fun setupProfile() {
        viewModel.currentUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.tvUserName.text = it.name
                binding.tvUserEmail.text = it.email
                binding.tvUserPhone.text = it.phone
                binding.tvUserCompany.text = it.companyName.ifBlank { "-" }
                binding.tvUserRole.text = when (it.role) {
                    com.freightconnect.model.UserRole.BUSINESS_OWNER -> getString(R.string.business_owner)
                    com.freightconnect.model.UserRole.FLEET_OWNER -> getString(R.string.fleet_owner)
                }
            }
        }
    }

    private fun setupLanguageSelector() {
        val currentLang = LanguageHelper.getCurrentLanguage(requireContext())
        val languages = AppLanguage.values().map { it.displayName }
        
        binding.actvLanguage.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, languages)
        )
        
        val currentLanguageIndex = AppLanguage.values().indexOfFirst { it.code == currentLang }
        if (currentLanguageIndex >= 0) {
            binding.actvLanguage.setText(AppLanguage.values()[currentLanguageIndex].displayName, false)
        }

        binding.actvLanguage.setOnItemClickListener { _, _, position, _ ->
            val selectedLang = AppLanguage.values()[position].code
            LanguageHelper.setLocale(requireContext(), selectedLang)
            
            // Restart activity to apply language
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.change_language)
                .setMessage("Restart app to apply language change?")
                .setPositiveButton("Restart") { _, _ ->
                    requireActivity().recreate()
                }
                .setNegativeButton(R.string.cancel, null)
                .show()
        }
    }

    private fun setupLogout() {
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.logout)
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton(R.string.logout) { _, _ ->
                    viewModel.logout()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton(R.string.cancel, null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
