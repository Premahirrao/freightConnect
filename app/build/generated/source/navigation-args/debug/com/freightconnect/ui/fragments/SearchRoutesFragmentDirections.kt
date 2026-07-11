package com.freightconnect.ui.fragments

import android.os.Bundle
import androidx.navigation.NavDirections
import com.freightconnect.R
import kotlin.Int
import kotlin.String

public class SearchRoutesFragmentDirections private constructor() {
  private data class ActionSearchToRouteDetail(
    public val routeId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_search_to_routeDetail

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("routeId", this.routeId)
        return result
      }
  }

  public companion object {
    public fun actionSearchToRouteDetail(routeId: String): NavDirections =
        ActionSearchToRouteDetail(routeId)
  }
}
