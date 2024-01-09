package com.chow.cleanmvvmhiltmultimodule.presentation.ui.test

import com.chow.cleanmvvmhiltmultimodule.base.BaseEvent
import com.chow.cleanmvvmhiltmultimodule.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(): BaseViewModel<TestEvent>() {
    fun navigate() {
        sendEvent(TestEvent.NavigateToHomeScreen("ABC"))
    }
}

sealed class TestEvent : BaseEvent() {
    class NavigateToHomeScreen(val data: String) : TestEvent()
}