package com.chow.cleanmvvmhiltmultimodule.presentation.ui.test

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(): ViewModel() {
}