package com.example.ds_movies.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.example.ds_movies.R
import com.example.ds_movies.databinding.ActivitySplashBinding
import com.example.ds_movies.ui.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }
    override fun generateViewModel(): SplashViewModel {
        return ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vm = viewModel

        Handler().postDelayed({

        }, 2000)

//        viewModel.verifyStoragePermissions(this)
//        viewModel.verifyLocationPermissions(this)
//
//        if (viewModel.verifyStoragePermissions(this) && viewModel.verifyLocationPermissions(this)){
//            Handler().postDelayed({
//           //     val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//                finish()
//            }, 2000)
//        }
    }
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == AppConstant.REQUEST_EXTERNAL_STORAGE && requestCode == AppConstant.REQUEST_FINE_LOCATION) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "user granted", Toast.LENGTH_LONG).show()
//            } else {
//                Toast.makeText(this, "user denied", Toast.LENGTH_LONG).show()
//            }
//        }
//    }


}
