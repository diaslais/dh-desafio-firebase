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

        val list = arrayListOf<Game>(
            Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
            Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
            Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
            Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
            Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
            Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
            Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
            Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho")
        )

        val recyclerView = _view.findViewById<RecyclerView>(R.id.homeRecyclerView)

        recyclerView.adapter = HomeAdapter(list) {
            navController.navigate(R.id.action_homeFragment_to_detailFragment)
        }

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
    }


}