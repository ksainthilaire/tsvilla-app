package com.tsvilla.optimus.presentation.fragment


import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import fr.airweb.news.R
import fr.airweb.news.databinding.FragmentSettingsBinding
import fr.airweb.news.domain.model.NewsPreview
import fr.airweb.news.domain.model.TypeNews
import fr.airweb.news.presentation.adapters.SettingsAdapter
import fr.airweb.news.presentation.model.SettingsState
import fr.airweb.news.presentation.model.SortBy
import fr.airweb.news.presentation.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment :
    BaseFragment<
            SettingsState,
            SettingsViewModel,
            FragmentSettingsBinding>(R.layout.fragment_news_list) {


    override val viewModel: SettingsViewModel by viewModel()


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.by_title -> {}
            R.id.by_date -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun filterSettings(state: SettingsState): List<NewsPreview> {

        var Settings = if (state.searchedTitle == null) state.Settings!! else
            state.Settings!!
                .filter { newsPreview ->
                    newsPreview.title.toString().lowercase().trim().contains(state.searchedTitle) }


        Settings = when (state.sortBy) {
            SortBy.DATE_ASC -> Settings.sortedBy { it.date }
            SortBy.DATE_DESC -> Settings.sortedByDescending { it.date }
            SortBy.TITLE_ASC -> Settings.sortedBy { it.title }
            SortBy.TITLE_DESC -> Settings.sortedByDescending { it.title }
        }

        return Settings.filter { newsPreview ->
            newsPreview.type == state.sortByType
        }
    }

    override fun updateView(state: SettingsState) {

        if (state.Settings == null) return

        with(binding) {

            progressBar.visibility =  if (state.isLoading) View.VISIBLE else View.GONE

            val Settings = filterSettings(state)

            title.text = when (state.sortByType) {
                TypeNews.ACTUALITY -> resources.getString(R.string.news_type_actuality)
                TypeNews.NEWS -> resources.getString(R.string.news_type_default)
                TypeNews.HOT -> resources.getString(R.string.news_type_hot)
                else -> resources.getString(R.string.news_type_default)
            }


            if (fragmentMainRecyclerView.adapter == null) {
                fragmentMainRecyclerView.layoutManager = LinearLayoutManager(context)
                fragmentMainRecyclerView.adapter = SettingsAdapter(Settings, this@SettingsFragment)
            } else {
                (fragmentMainRecyclerView.adapter as SettingsAdapter).updateData(Settings)
            }
        }
    }


    override fun initView() {


        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_sort_date_asc -> viewModel.sortBy(SortBy.DATE_ASC)
                R.id.action_sort_date_desc -> viewModel.sortBy(SortBy.DATE_DESC)
                R.id.action_sort_title_asc -> viewModel.sortBy(SortBy.TITLE_ASC)
                R.id.action_sort_title_desc -> viewModel.sortBy(SortBy.TITLE_DESC)
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
            true
        }
        val searchView = binding.toolbar.menu.findItem(R.id.action_search).actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(text: String): Boolean {
                viewModel.searchArticleByTitle(text.lowercase().trim())
                return false
            }

            override fun onQueryTextSubmit(text: String): Boolean {
                return false
            }

        })


        with(binding) {

            tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    when (tab.position) {
                        0 -> viewModel.sortByType(TypeNews.NEWS)
                        1 -> viewModel.sortByType(TypeNews.ACTUALITY)
                        2 -> viewModel.sortByType(TypeNews.HOT)
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

    }


}