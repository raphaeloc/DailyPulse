package com.cgs.dailypulse

data class AboutInformation(
    val title: String,
    val subtitle: String
)

object AboutData {
    private val platform = Platform()

    fun informations(): List<AboutInformation> = listOf(
        AboutInformation(title = "Operating System", subtitle = "${platform.osName} ${platform.osVersion}"),
        AboutInformation(title = "Device", subtitle = platform.deviceModel),
        AboutInformation(title = "Density", subtitle = platform.density),
    )
}
