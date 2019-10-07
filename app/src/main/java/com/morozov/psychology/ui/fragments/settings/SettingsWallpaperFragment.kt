package com.morozov.psychology.ui.fragments.settings

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.presenters.settings.SettingsWallpaperPresenter
import com.morozov.psychology.mvp.views.settings.SettingsWallpaperView
import com.morozov.psychology.ui.adapters.settings.wallpaper.StgWallpaperAdapter
import com.morozov.psychology.ui.adapters.settings.wallpaper.StgWallpaperViewHolder
import com.yarolegovich.discretescrollview.DiscreteScrollView
import kotlinx.android.synthetic.main.settings_wallpaper_layout.*

class SettingsWallpaperFragment: MvpAppCompatFragment(), SettingsWallpaperView,
    DiscreteScrollView.OnItemChangedListener<StgWallpaperViewHolder>{

    @InjectPresenter
    lateinit var mPresenter: SettingsWallpaperPresenter
    lateinit var mActivityPresenter: MainPresenter

    lateinit var adapter: StgWallpaperAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.settings_wallpaper_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = StgWallpaperAdapter()
        recyclerStylesWallpaper.setSlideOnFling(true)
        recyclerStylesWallpaper.addOnItemChangedListener(this)
        recyclerStylesWallpaper.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        context?.let { mPresenter.loadImages(it) }
    }

    /*
    * SettingsWallpaperView implementation
    *
    * */
    override fun showMainImage(drawable: Drawable, position: Int) {
        relativeStyleWallpaper.background = drawable
        adapter.selectedPosition.value = position
        recyclerStylesWallpaper.post {
            adapter.notifyDataSetChanged()
        }
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