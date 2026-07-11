package com.freightconnect.ui.fragments

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class CargoDetailFragmentArgs(
  public val cargoId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("cargoId", this.cargoId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("cargoId", this.cargoId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): CargoDetailFragmentArgs {
      bundle.setClassLoader(CargoDetailFragmentArgs::class.java.classLoader)
      val __cargoId : String?
      if (bundle.containsKey("cargoId")) {
        __cargoId = bundle.getString("cargoId")
        if (__cargoId == null) {
          throw IllegalArgumentException("Argument \"cargoId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"cargoId\" is missing and does not have an android:defaultValue")
      }
      return CargoDetailFragmentArgs(__cargoId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): CargoDetailFragmentArgs {
      val __cargoId : String?
      if (savedStateHandle.contains("cargoId")) {
        __cargoId = savedStateHandle["cargoId"]
        if (__cargoId == null) {
          throw IllegalArgumentException("Argument \"cargoId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"cargoId\" is missing and does not have an android:defaultValue")
      }
      return CargoDetailFragmentArgs(__cargoId)
    }
  }
}
