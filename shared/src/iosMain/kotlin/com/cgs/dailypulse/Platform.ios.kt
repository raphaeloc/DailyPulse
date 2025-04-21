package com.cgs.dailypulse

import platform.Foundation.NSLog
import platform.UIKit.UIDevice
import platform.UIKit.UIScreen

actual class Platform actual constructor() {
    private val currentDevice = UIDevice.currentDevice

    actual val osName: String
        get() = currentDevice.systemName
    actual val osVersion: String
        get() = currentDevice.systemVersion
    actual val deviceModel: String
        get() = currentDevice.model
    actual val density: String
        get() = "@${UIScreen.mainScreen.scale}"
}