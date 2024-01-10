package com.chow.cleanmvvmhiltmultimodule.base

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.chow.cleanmvvmhiltmultimodule.utils.KeyboardUtils

abstract class BaseActivity<VB : ViewBinding, vm : ViewModel> : AppCompatActivity() {
    private var startClickTime: Long = 0
    private var isKeyboardShown = false
    protected lateinit var binding: VB
    abstract val bindingInflater: ((LayoutInflater) -> VB)
    abstract val viewModel: vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
        //Use this code to detect keyboard appearance to scroll recyclerview to bottom
//        root.viewTreeObserver.addOnGlobalLayoutListener(object :
//            ViewTreeObserver.OnGlobalLayoutListener {
//            private val EstimatedKeyboardDP = 148
//            private val rect = Rect()
//            override fun onGlobalLayout() {
//                root.getWindowVisibleDisplayFrame(rect)
//                val isShown = root.rootView.height - (rect.bottom - rect.top) >=
//                        TypedValue.applyDimension(
//                            TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP.toFloat(),
//                            root.resources.displayMetrics
//                        ).toInt()
//                if (isShown == isKeyboardShown) return
//                isKeyboardShown = isShown
//                if (isShown && isBottom) rvChat.apply {
//                    scrollToPosition((adapter as ChatAdapter).itemCount - 1)
//                }
//            }
//        })
        initData()
        bindComponent()
        bindEvent()
        bindData()
        observeData()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> startClickTime = System.currentTimeMillis()
            MotionEvent.ACTION_UP -> {
                if (System.currentTimeMillis() - startClickTime < ViewConfiguration.getTapTimeout()) {
                    val view = currentFocus as? EditText ?: return super.dispatchTouchEvent(ev)
                    val outRect = Rect()
                    view.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(ev.x.toInt(), ev.y.toInt())) {
                        KeyboardUtils.hideKeyboard(view)
                        view.clearFocus()
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
    abstract fun initData()
    abstract fun bindData()
    abstract fun bindComponent()
    abstract fun bindEvent()

    abstract fun observeData()

    abstract fun setUpNavigation()
}