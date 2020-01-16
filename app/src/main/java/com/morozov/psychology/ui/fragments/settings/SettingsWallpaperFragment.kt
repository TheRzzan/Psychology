package com.morozov.psychology.ui.fragments.settings

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.settings.SettingsWallpaperPresenter
import com.morozov.psychology.mvp.views.settings.SettingsWallpaperView
import com.morozov.psychology.ui.adapters.listeners.OnItemClickListener
import com.morozov.psychology.ui.adapters.settings.wallpaper.StgWallpaperAdapter
import com.morozov.psychology.ui.adapters.settings.wallpaper.StgWallpaperViewHolder
import com.morozov.psychology.utility.AppConstants
import com.morozov.psychology.utility.MySharedPreferences
import com.yarolegovich.discretescrollview.DiscreteScrollView
import kotlinx.android.synthetic.main.settings_wallpaper_layout.*

class SettingsWallpaperFragment: MvpAppCompatFragment(), SettingsWallpaperView,
    DiscreteScrollView.OnItemChangedListener<StgWallpaperViewHolder>, OnItemClickListener{

    @InjectPresenter
    lateinit var mPresenter: SettingsWallpaperPresenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: StgWallpaperAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.settings_wallpaper_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = StgWallpaperAdapter(this)
        recyclerStylesWallpaper.setSlideOnFling(true)
        recyclerStylesWallpaper.adapter = adapter
        recyclerStylesWallpaper.addOnItemChangedListener(this)

        linearBack.setOnClickListener {
            activity?.onBackPressed()
        }

        buttonStyleWallpaperSave.setOnClickListener {
            when (mPresenter.selectedPos) {
                0 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_WALLPAPER, AppConstants.PREF_WALLP_DEF) }
                1 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_WALLPAPER, AppConstants.PREF_WALLP_1) }
                2 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_WALLPAPER, AppConstants.PREF_WALLP_2) }
                3 -> context?.let { it1 -> MySharedPreferences.setPreference(it1, AppConstants.PREF_WALLPAPER, AppConstants.PREF_WALLP_3) }
            }
            mActivityPresenter.setBackground(mPresenter.data[mPresenter.selectedPos])
            activity?.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()

        context?.let { mPresenter.loadImages(it) }
    }

    /*
    * OnItemClickListener implementation
    *
    * */
    override fun onItemClick(view: View, position: Int) {
        recyclerStylesWallpaper.smoothScrollToPosition(position)
    }

    /*
    * SettingsWallpaperView implementation
    *
    * */
    override fun showMainImage(drawable: Drawable, position: Int) {
        imageStyleWallpaper.setImageDrawable(drawable)
        adapter.selectedPosition.value = position
    }

    override fun showImages(data: List<Drawable>) {
        adapter.setData(data)
        adapter.notifyDataSetChanged()
    }

    /*
    * DiscreteScrollView implementation
    *
    * */
    override fun onCurrentItemChanged(viewHolder: StgWallpaperViewHolder?, adapterPosition: Int) {
        mPresenter.showImage(adapterPosition)
    }
}