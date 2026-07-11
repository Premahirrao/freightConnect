package com.freightconnect.ui.fragments

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.freightconnect.R
import kotlin.Int
import kotlin.String

public class BusinessHomeFragmentDirections private constructor() {
  private data class ActionHomeToCargoDetail(
    public val cargoId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_home_to_cargoDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("cargoId", this.cargoId)
        return result
      }
  }

  public companion object {
    public fun actionHomeToCargoDetail(cargoId: String): NavDirections =
        ActionHomeToCargoDetail(cargoId)

    public fun actionHomeToPostCargo(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_postCargo)

    public fun actionHomeToSearchRoutes(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_searchRoutes)
  }
}
