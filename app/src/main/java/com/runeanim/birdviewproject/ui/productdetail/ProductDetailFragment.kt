package com.runeanim.birdviewproject.ui.productdetail

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.runeanim.birdviewproject.R
import com.runeanim.birdviewproject.databinding.DetailFragmentBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class ProductDetailFragment : BottomSheetDialogFragment(), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ProductDetailViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: DetailFragmentBinding

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            setOnShowListener {
                val bottomSheet =
                    findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                with(BottomSheetBehavior.from(bottomSheet)) {
                    state = BottomSheetBehavior.STATE_EXPANDED
                    skipCollapsed = true
                    isHideable = true
                }

                window?.let {
                    it.findViewById<View>(R.id.touch_outside)
                        ?.setOnClickListener(null)
                    (it.findViewById<View>(R.id.design_bottom_sheet)
                        ?.layoutParams as CoordinatorLayout.LayoutParams).behavior = null
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DetailFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.start(arguments?.getInt(EXTRA_PRODUCT_ID))

        setupCloseButton()
    }

    private fun setupCloseButton() {
        viewDataBinding.closeButton.setOnClickListener {
            dismiss()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        const val EXTRA_PRODUCT_ID = "extra_product_id"
    }
}
