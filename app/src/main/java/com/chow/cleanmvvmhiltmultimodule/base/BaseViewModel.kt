package com.chow.cleanmvvmhiltmultimodule.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseViewModel<T: BaseEvent> @Inject constructor() : ViewModel() {
    private val _event = Channel<T>()
    val event get() = _event.receiveAsFlow()

    protected fun sendEvent(event: T) {
        viewModelScope.launch {
            _event.send(event)
        }
    }
}

open class BaseEvent

