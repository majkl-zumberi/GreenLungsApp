package com.majkl.greenlungs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val introSliderAdapter=IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Una nuova avventura",
                "intraprendi questa avventura, inizia a respirare meglio",
                R.drawable.undraw_ethereum_fb7n
            ),
            IntroSlide(
                "Ci prendiamo cura dei tuoi polmoni",
                "I tuoi polmoni potranno solo che ringraziarti",
                R.drawable.undraw_a_whole_year_vnfm
            ),
            IntroSlide(
                "Il tuo medico personale",
                "potrai consultare la cronologia dei tuoi polmoni",
                R.drawable.undraw_doctor_kw5l
            )

        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        introSliderViewPager.adapter=introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        buttonNext.setOnClickListener{
            if(introSliderViewPager.currentItem+1 < introSliderAdapter.itemCount){
                introSliderViewPager.currentItem+=1
            }
            else{
                Intent(applicationContext,LoginActivity::class.java).also{
                    startActivity(it)
                }

            }
        }
        textSkipIntro.setOnClickListener{
            Intent(applicationContext,LoginActivity::class.java).also{
                startActivity(it)}
        }

    }

     private fun setupIndicators(){
        val indicators= arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
         val layoutParams: LinearLayout.LayoutParams=
             LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
             layoutParams.setMargins(8,0,8,0)

         for(i in indicators.indices){
                indicators[i]= ImageView(applicationContext)
                indicators[i].apply {
                    this?.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.indicator_inactive
                        )
                    )
                    this?.layoutParams=layoutParams
                }
             indicatorsContainer.addView(indicators[i])
         }
    }

    private fun setCurrentIndicator(index :Int){
        val childCount=indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView=indicatorsContainer[i] as ImageView
            if(i== index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }
            else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

}
