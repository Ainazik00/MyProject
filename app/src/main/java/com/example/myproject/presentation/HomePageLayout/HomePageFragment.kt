package com.example.myproject.presentation.HomePageLayout

import android.graphics.Outline
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.Window
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.add
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myproject.R
import com.example.myproject.databinding.FragmentHomePageBinding
import com.example.myproject.presentation.HomePageLayout.Calls.CallsFragment
import com.example.myproject.presentation.HomePageLayout.Chat.ChatFragment
import com.example.myproject.presentation.HomePageLayout.Contacts.ContactsFragment
import com.example.myproject.presentation.HomePageLayout.Status.StatusFragment
import com.example.myproject.util.OutlineProvider
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var toolbar: MaterialToolbar
    private lateinit var toolbarTitle: TextView
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var fabShowContacts: FloatingActionButton


    val fragmentList = arrayListOf(
        ChatFragment(),
        CallsFragment(),
        StatusFragment()
    )

    private var requestReadContactsLauncher: ActivityResultLauncher<String?> =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                activity?.let {
                    it.supportFragmentManager.beginTransaction()
                        .add(R.id.fragmentContainer, ContactsFragment(), "contacts_fragment")
                        .addToBackStack("contacts_fragment").commit()
                }
            } else {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_CONTACTS)) {
                    launchContactsLauncherOnceAgain()
                }
            }
        }

    private fun launchContactsLauncherOnceAgain() {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle("Permission needed")
                .setMessage("This app needs access to your contacts to function properly")
                .setPositiveButton("OK") { _, _ ->
                    requestReadContactsLauncher.launch(android.Manifest.permission.READ_CONTACTS)
                }
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(layoutInflater, container, false)
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        toolbar = binding.toolbar
        toolbarTitle = binding.toolbarTitle
        fabShowContacts = binding.fabShowContacts
        fabShowContacts.outlineProvider = OutlineProvider()
        fabShowContacts.clipToOutline = true
        val window: Window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.Green1)
        fabShowContacts.setOnClickListener {
            requestReadContactsLauncher.launch(android.Manifest.permission.READ_CONTACTS)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPagerAndTabLayout()
    }

    private fun setUpViewPagerAndTabLayout() {

        viewPager.adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
            override fun getItemCount(): Int = fragmentList.size
            override fun createFragment(position: Int): Fragment = fragmentList[position]
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Chat"
                1 -> tab.text = "Call"
                2 -> tab.text = "Status"
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                // Handle search action
                true
            }

            R.id.menu_camera -> {
                // Handle camera action
                true
            }

            R.id.create_new_group -> {
                // Handle more item 1 action
                true
            }

            R.id.create_new_broadcast -> {
                // Handle more item 2 action
                true
            }

            R.id.payments -> {
                // Handle more item 2 action
                true
            }

            R.id.settings -> {
                // Handle more item 2 action
                true
            }

            R.id.starred_message -> {
                // Handle more item 2 action
                true
            }

            R.id.linked_devices -> {
                // Handle more item 2 action
                true
            }

            else -> {
                false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requestReadContactsLauncher.unregister()
    }

}