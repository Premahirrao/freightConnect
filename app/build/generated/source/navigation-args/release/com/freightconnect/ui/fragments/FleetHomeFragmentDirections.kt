package com.freightconnect.ui.fragments

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.freightconnect.R
import kotlin.Int
import kotlin.String

public class FleetHomeFragmentDirections private constructor() {
  private data class ActionHomeToRouteDetail(
    public val routeId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_home_to_routeDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("routeId", this.routeId)
        return result
      }
  }

  public companion object {
    public fun actionHomeToRouteDetail(routeId: String): NavDirections =
        ActionHomeToRouteDetail(routeId)

    public fun actionHomeToPostRoute(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_postRoute)

    public fun actionHomeToSearchCargos(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_searchCargos)
  }
}
