package com.freightconnect.ui.fragments

import android.os.Bundle
import androidx.navigation.NavDirections
import com.freightconnect.R
import kotlin.Int
import kotlin.String

public class SearchCargosFragmentDirections private constructor() {
  private data class ActionSearchToCargoDetail(
    public val cargoId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_search_to_cargoDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("cargoId", this.cargoId)
        return result
      }
  }

  public companion object {
    public fun actionSearchToCargoDetail(cargoId: String): NavDirections =
        ActionSearchToCargoDetail(cargoId)
  }
}
