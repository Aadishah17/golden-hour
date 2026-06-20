package com.goldenhour.utils

import android.media.AudioManager
import android.media.ToneGenerator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SoundEffects @Inject constructor() {
    private val toneGenerator = runCatching {
        ToneGenerator(AudioManager.STREAM_NOTIFICATION, 55)
    }.getOrNull()

    fun selection() {
        runCatching { toneGenerator?.startTone(ToneGenerator.TONE_PROP_ACK, 70) }
    }

    fun alert() {
        runCatching { toneGenerator?.startTone(ToneGenerator.TONE_SUP_ERROR, 220) }
    }

    fun success() {
        runCatching { toneGenerator?.startTone(ToneGenerator.TONE_PROP_PROMPT, 180) }
    }
}
