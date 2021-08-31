package com.eachut.cylinder.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.eachut.cylinder.Adapter.ExtraScheduleAdapter
import com.eachut.cylinder.Adapter.NotificationHistoyAdapter
import com.eachut.cylinder.Adapter.WorkScheduleAdapter
import com.eachut.cylinder.AddSchedule
import com.eachut.cylinder.MainActivity
import com.eachut.cylinder.R
import com.eachut.cylinder.databinding.FragmentNotificationsBinding
import com.eachut.cylinder.entity.NotificationHistory
import com.eachut.cylinder.entity.ScheduleExtraWork
import com.eachut.cylinder.entity.ScheduleResellerStock
import com.eachut.cylinder.repository.NotificationRepository
import com.eachut.cylinder.repository.ScheduleExtraWorkRepository
import com.eachut.cylinder.repository.ScheduleResellerStockRepository
import com.eachut.cylinder.ui.profiles.GetResellerProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsFragment : Fragment() {

    private lateinit var recyclerview : RecyclerView
    private lateinit var extrarecyclerview : RecyclerView
    private lateinit var notificationrecyclerview : RecyclerView
    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private var notificationList = mutableListOf<NotificationHistory>()
    private var scheduleList = mutableListOf<ScheduleResellerStock>()
    private var extrascheduleList = mutableListOf<ScheduleExtraWork>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerview = root.findViewById(R.id.recyclerview)
        extrarecyclerview = root.findViewById(R.id.Extrarecyclerview)
        notificationrecyclerview = root.findViewById(R.id.notificationrecyclerview)

        //load notification on notification
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repo3 = NotificationRepository()
                val response3 = repo3.notificationHistoryList()

                if(response3.success!!)
                {
                    notificationList = response3.data!!

                    withContext(Main){
                        notificationrecyclerview.adapter = NotificationHistoyAdapter(requireContext(), notificationList)
                        notificationrecyclerview.layoutManager = LinearLayoutManager(context)
                    }
                }
                else{
                    withContext(Main) {
                        Toast.makeText(context, "Not Success", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            catch (e:Exception){
                withContext(Main){
                    Toast.makeText(context, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //schedule

        binding.Plus.setOnClickListener { view ->


            val intent = Intent(this.context, AddSchedule::class.java)
            startActivity(intent)

        }

//search
        binding.ivSearchProfiles.setOnClickListener { view ->
            binding.flTab3.isVisible = false
            binding.ivSearchProfiles.isVisible = false
            binding.llsearchBox.isVisible = true
            binding.ivCancalSearchProfiles.isVisible = true
        }

        binding.ivCancalSearchProfiles.setOnClickListener { view ->
            binding.flTab3.isVisible = true
            binding.ivSearchProfiles.isVisible = true
            binding.llsearchBox.isVisible = false
            binding.ivCancalSearchProfiles.isVisible = false
        }

// FILTER
        binding.ivFilterProfiles.setOnClickListener { view ->
//            loadPopUpMenu()
        }

//        ALL SCHEDULE CLICK
        binding.tvNotiAll.setOnClickListener { view ->
            binding.svNotificationView.isVisible = true
            binding.svScheduleView.isVisible = false

            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.LEFT
            binding.ivToggleActiveNoti.setLayoutParams(params);

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repo3 = NotificationRepository()
                    val response3 = repo3.notificationHistoryList()

                    if(response3.success!!)
                    {
                        notificationList = response3.data!!

                        withContext(Main){
                            notificationrecyclerview.adapter = NotificationHistoyAdapter(requireContext(), notificationList)
                            notificationrecyclerview.layoutManager = LinearLayoutManager(context)
                        }
                    }
                    else{
                        withContext(Main) {
                            Toast.makeText(context, "Not Success", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                catch (e:Exception){
                    withContext(Main){
                        Toast.makeText(context, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        binding.tvNotiSchedule.setOnClickListener { view ->
            binding.svScheduleView.isVisible = true
            binding.svNotificationView.isVisible = false

            val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.RIGHT
            binding.ivToggleActiveNoti.setLayoutParams(params);

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repo1 = ScheduleResellerStockRepository()
                    val response1 = repo1.getScheduleResellerStock()

                    if(response1.success!!){

                        scheduleList = response1.data!!

                        withContext(Main) {
                            recyclerview.adapter = WorkScheduleAdapter(requireContext(), scheduleList)
                            recyclerview.layoutManager = LinearLayoutManager(context)

                        }
                    }
                    else{
                        withContext(Main) {
                            Toast.makeText(context, "Not Success", Toast.LENGTH_SHORT).show()
                        }
                    }

                }catch (e : Exception){
                    withContext(Main){
                        Toast.makeText(context, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            CoroutineScope(Dispatchers.IO).launch{
                try{
                    val repo = ScheduleExtraWorkRepository()
                    val response = repo.getExtraWorkSchedule()
                    if(response.success!!){
                        extrascheduleList=response.data!!

                        withContext(Main){
                            extrarecyclerview.adapter = ExtraScheduleAdapter(requireContext(), extrascheduleList)
                            extrarecyclerview.layoutManager = LinearLayoutManager(context)
                        }
                    }
                    else{
                        withContext(Main){
                            Toast.makeText(context, "${response.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }catch(e : Exception) {
                    withContext(Main){
                        Toast.makeText(context, "${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

        binding.lltodayToggle.setOnClickListener { view ->
            val maxormin = binding.lltodayToggle.getContentDescription()
            if (maxormin == "max") {
                binding.lltodayToggle.setContentDescription("min")
                binding.recyclerview.isVisible = false
                binding.lltodayToggleUpDown.animate().rotation(0f).start();
            }else{
                binding.lltodayToggle.setContentDescription("max")
                binding.recyclerview.isVisible = true
                binding.lltodayToggleUpDown.animate().rotation(180f).start();
            }
        }

        binding.lllaterToggle.setOnClickListener { view ->
            val maxormin = binding.lllaterToggle.getContentDescription()
            if (maxormin == "max") {
                binding.lllaterToggle.setContentDescription("min")
                binding.Extrarecyclerview.isVisible = false
                binding.lllaterToggleUpDown.animate().rotation(0f).start();
            }else{
                binding.lllaterToggle.setContentDescription("max")
                binding.Extrarecyclerview.isVisible = true
                binding.lllaterToggleUpDown.animate().rotation(180f).start();
            }
        }


//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Load pop up menu image upload
//    private fun loadPopUpMenu() {
//        val popupMenu = PopupMenu(this.activity, binding.ivFilterProfiles)
//        popupMenu.menuInflater.inflate(R.menu.filter_profiles, popupMenu.menu)
//        popupMenu.setOnMenuItemClickListener { item ->
//            when (item.itemId) {
//                R.id.iNameaz ->
//                    iNameaz()
//                R.id.iNameza ->
//                    iNameza()
//                R.id.iAmountDue ->
//                    iAmountDue()
//                R.id.iInactive ->
//                    iInactive()
//                R.id.iMostSold ->
//                    iMostSold()
//            }
//            true
//        }
//        popupMenu.show()
//    }


}