package com.freightconnect.ui.fragments

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class RouteDetailFragmentArgs(
  public val routeId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("routeId", this.routeId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("routeId", this.routeId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): RouteDetailFragmentArgs {
      bundle.setClassLoader(RouteDetailFragmentArgs::class.java.classLoader)
      val __routeId : String?
      if (bundle.containsKey("routeId")) {
        __routeId = bundle.getString("routeId")
        if (__routeId == null) {
          throw IllegalArgumentException("Argument \"routeId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"routeId\" is missing and does not have an android:defaultValue")
      }
      return RouteDetailFragmentArgs(__routeId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): RouteDetailFragmentArgs {
      val __routeId : String?
      if (savedStateHandle.contains("routeId")) {
        __routeId = savedStateHandle["routeId"]
        if (__routeId == null) {
          throw IllegalArgumentException("Argument \"routeId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"routeId\" is missing and does not have an android:defaultValue")
      }
      return RouteDetailFragmentArgs(__routeId)
    }
  }
}
