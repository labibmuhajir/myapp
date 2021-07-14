package com.github.labibmuhajir.myapplication.ui.artisan

import android.os.Bundle
import android.view.*
import androidx.annotation.CheckResult
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.labibmuhajir.myapplication.R
import com.github.labibmuhajir.myapplication.common.snackbar
import com.github.labibmuhajir.myapplication.data.model.ViewState
import com.github.labibmuhajir.myapplication.databinding.FragmentArtisanBinding
import com.github.labibmuhajir.myapplication.ui.artisandetail.ArtisanDetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtisanFragment : Fragment() {
    private lateinit var binding: FragmentArtisanBinding
    private val viewModel by viewModel<ArtisanViewModel>()

    private val adapter by lazy { ArtisanAdapter {
        startActivity(ArtisanDetailActivity.intent(requireContext(), it.id))
    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArtisanBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeData()
        viewModel.getArtisan()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar, menu)
        menu.findItem(R.id.action_search)?.let { searchItem ->
            val searchView = searchItem.actionView as SearchView
            searchView.search()
                .distinctUntilChanged()
                .debounce(200)
                .onEach { viewModel.search(it) }
                .launchIn(lifecycleScope)
        }
    }

    @ExperimentalCoroutinesApi
    @CheckResult
    private fun SearchView.search(): Flow<String?> {
        return callbackFlow {
            val listener = object : android.widget.SearchView.OnQueryTextListener,
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    offer(p0)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    offer(p0)
                    return false
                }

            }
            setOnQueryTextListener(listener)
            awaitClose()
        }
    }

    private fun setupView() {
        binding.rvArtisan.adapter = adapter
    }

    private fun observeData() {
        viewModel.artisan.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Loading -> {
                }
                is ViewState.Success -> adapter.data = it.data.toMutableList()
                is ViewState.Error -> binding.root.snackbar(it.message).show()
            }
        }
    }
}