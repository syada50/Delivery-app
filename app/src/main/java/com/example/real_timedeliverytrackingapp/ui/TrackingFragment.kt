package com.example.real_timedeliverytrackingapp.ui



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.real_timedeliverytrackingapp.viewModels.DeliveryViewModel
import com.example.real_timedeliverytrackingapp.databinding.FragmentTrackingBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class TrackingFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: DeliveryViewModel by viewModels()
    private var _binding: FragmentTrackingBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTrackingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.trackingFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        arguments?.getString("deliveryId")?.let { deliveryId ->
            viewModel.trackDelivery(deliveryId)
        }

        viewModel.deliveryLocation.observe(viewLifecycleOwner) { location ->
            updateMapLocation(location)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        // You can customize the map here if needed
        map.uiSettings.isZoomControlsEnabled = true
    }

    private fun updateMapLocation(location: LatLng) {
        val markerOptions = MarkerOptions().position(location).title(getString(R.string.delivery_vehicle))
        map.clear()
        map.addMarker(markerOptions)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
