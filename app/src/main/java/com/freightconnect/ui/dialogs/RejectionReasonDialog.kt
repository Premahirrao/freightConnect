package com.freightconnect.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.freightconnect.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Task 10: RejectionReasonDialog - Show dropdown of rejection reasons
 * 
 * When user rejects an interest, show this dialog to capture the reason
 * Reasons: Already matched, Pricing, Will contact, Not suitable, Other
 */
class RejectionReasonDialog(
    private val onReasonSelected: (String) -> Unit
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Dialog is shown directly, no view needed
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showReasonDialog()
    }

    private fun showReasonDialog() {
        // Define rejection reasons
        val reasons = listOf(
            getString(R.string.reason_already_matched),
            getString(R.string.reason_pricing),
            getString(R.string.reason_will_contact),
            getString(R.string.reason_not_suitable),
            getString(R.string.reason_other)
        )

        var selectedReason = reasons[0]

        // Show as radio button list
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.reject_interest_title)
            .setSingleChoiceItems(
                reasons.toTypedArray(),
                0  // Default selection
            ) { _, which ->
                selectedReason = reasons[which]
            }
            .setPositiveButton(android.R.string.ok) { _, _ ->
                onReasonSelected(selectedReason)
                dismiss()
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                dismiss()
            }
            .show()
    }

    companion object {
        fun show(
            fragmentManager: androidx.fragment.app.FragmentManager,
            onReasonSelected: (String) -> Unit
        ) {
            RejectionReasonDialog(onReasonSelected).show(
                fragmentManager,
                "RejectionReasonDialog"
            )
        }
    }
}

