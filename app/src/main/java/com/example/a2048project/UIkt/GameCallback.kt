package com.example.a2048project.UIkt

import com.example.a2048project.Game.Step

interface GameCallback {
    fun onSwip(direction: Step.Action)
    fun clickTile()
}