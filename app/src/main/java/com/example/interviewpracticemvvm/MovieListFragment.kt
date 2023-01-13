package com.example.interviewpracticemvvm

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.interviewpracticemvvm.adapter.MovieAdapter
import com.example.interviewpracticemvvm.databinding.FragmentMovieListBinding
import com.example.interviewpracticemvvm.viewmodel.MovieViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 * fixed 2 issues via question asked on StackOverflow: https://stackoverflow.com/questions/74968100/migrating-from-using-activity-to-fragment-getting-e-recyclerview-no-adapter-at
 */
class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG,"onCreateView: called")

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_list, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"attaching Adapter")
        prepareRecyclerView()
        Log.d(TAG,"prepareRecyclerView called")

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getPopularMovies()
        Log.d(TAG,"getPopularMovies called")

        viewModel.observeMovieLiveData().observe(viewLifecycleOwner, Observer { movieList ->
            movieAdapter.setMovieList(movieList)
            Log.d(TAG,"movieAdapter.setMovieList(movieList) called")

        })
    }

    private fun prepareRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), 2) //Tried activity; appCompatActivity wouldn't work
            adapter = movieAdapter
        }
        Log.d(TAG,"adapter attached")

    }
}
