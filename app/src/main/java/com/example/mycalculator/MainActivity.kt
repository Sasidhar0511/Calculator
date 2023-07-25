package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(),FragmentActionListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var firstFragment: FirstFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        fragmentManager = supportFragmentManager

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null){
   //     Toast.makeText(this,"From onSaved",Toast.LENGTH_SHORT).show()

        }else{
            showDefaultFragment()
        }
    }

    private fun showDefaultFragment(){
            firstFragment = FirstFragment()
            fragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentContainer,firstFragment,"firstFragment").commit()
            }
    }

    override fun passData(bundle: Bundle) {

        val secondFragment = SecondFragment()
        secondFragment.arguments = bundle

        fragmentManager.beginTransaction().apply {
            replace(R.id.flFragmentContainer, secondFragment, "secondFragment")
            addToBackStack(null)
            commit()
        }
    }

    override fun passCallBackData(bundle: Bundle) {

        val fragment = fragmentManager.findFragmentByTag("firstFragment")
        if(fragment!= null) {
            fragment.arguments = bundle
        }
    }


    override fun onBackPressed() {

        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


}