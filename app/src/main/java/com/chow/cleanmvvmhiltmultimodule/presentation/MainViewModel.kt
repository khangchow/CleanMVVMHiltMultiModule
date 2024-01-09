package com.chow.cleanmvvmhiltmultimodule.presentation

import com.chow.cleanmvvmhiltmultimodule.base.BaseEvent
import com.chow.cleanmvvmhiltmultimodule.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainEvent>() {

}

sealed class MainEvent : BaseEvent() {

}