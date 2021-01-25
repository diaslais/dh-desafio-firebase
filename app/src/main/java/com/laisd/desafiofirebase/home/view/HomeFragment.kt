package com.laisd.desafiofirebase.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.home.model.Game

class HomeFragment : Fragment() {
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_home, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
    }

    private fun makeRecyclerview(games: List<Game>) {
        val planetsRecyclerView = _view.findViewById<RecyclerView>(R.id.homeRecyclerView)

        planetsRecyclerView.adapter = HomeAdapter(games)

        planetsRecyclerView.setHasFixedSize(true)
        planetsRecyclerView.layoutManager = GridLayoutManager(context, 2)
    }

}