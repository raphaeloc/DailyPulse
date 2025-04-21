package com.cgs.dailypulse

expect class Platform() {
    val osName: String
    val osVersion: String
    val deviceModel: String
    val density: String
}