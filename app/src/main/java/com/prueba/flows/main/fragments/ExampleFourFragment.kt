package com.prueba.flows.main.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.prueba.common.base.BaseFragment
import com.prueba.common.utils.viewBinding
import com.prueba.databinding.FragmentEampleFourBinding
import com.prueba.flows.main.actions.ExampleFourActions
import com.prueba.flows.main.viewmodels.ExampleFourViewModel
import com.prueba.flows.main.views.LocationAdapter
import com.prueba.flows.main.views.models.LocationObj
import javax.inject.Inject

class ExampleFourFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: ExampleFourViewModel

    private val binding by viewBinding {
        FragmentEampleFourBinding.inflate(layoutInflater)
    }

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private lateinit var db : FirebaseFirestore
    private lateinit var adapter: LocationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingViewModel()
        FirebaseApp.initializeApp(requireActivity())
        db = Firebase.firestore
        initView()
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.stopRepeatAction()
    }

    private fun bindingViewModel() {
        viewModel.apply {
            getShowErrorMessage().observe(viewLifecycleOwner, Observer(::genToast))
            getShowProgress().observe(viewLifecycleOwner, Observer(::showLoadDialog))
            getAction().observe(viewLifecycleOwner, Observer(::eventListener))
            checkPermissions(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION, LOCATE_PERMISSION)
        }
    }

    private fun eventListener(actions: ExampleFourActions) {
        when(actions) {
            ExampleFourActions.InitLocation -> sendAndGetData()
            is ExampleFourActions.RequestPermissions -> requestPermissions(
                actions.permissions.toTypedArray(),
                actions.permissionCode
            )
            is ExampleFourActions.GetData -> updateData(actions.list)
        }
    }

    private fun initView() {
        adapter = LocationAdapter(ArrayList())
        binding.recyclerLocation.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ExampleFourFragment.adapter
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        viewModel.onRequestPermissionsResult(requestCode, grantResults)
    }

    private fun sendAndGetData() {
        initGeolocation()
        viewModel.getData(db)
    }

    @SuppressLint("MissingPermission")
    private fun initGeolocation() {
        if (fusedLocationClient == null) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        }
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { location: Location? ->
                location?.let {
                    viewModel.sendData(db, it)
                }
            }
    }

    private fun updateData(list: ArrayList<LocationObj>) {
        adapter.updateAdapter(list)
    }

    private companion object {
        const val LOCATE_PERMISSION = 1000
    }

}