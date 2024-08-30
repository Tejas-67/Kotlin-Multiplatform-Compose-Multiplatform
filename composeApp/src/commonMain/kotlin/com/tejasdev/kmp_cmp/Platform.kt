package com.tejasdev.kmp_cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform