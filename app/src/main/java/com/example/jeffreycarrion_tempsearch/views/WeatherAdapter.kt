package com.example.jeffreycarrion_tempsearch.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jeffreycarrion_tempsearch.R
import com.example.jeffreycarrion_tempsearch.databinding.CityTempItemBinding
import com.example.jeffreycarrion_tempsearch.model.CityTemp

class WeatherAdapter(
    private val list: MutableList<CityTemp> = mutableListOf(),
    private val openTempDetail: (CityTemp) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    fun setTempList(newList: List<CityTemp>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(private val binding: CityTempItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CityTemp) {
            binding.apply {
                tvCityTemp.text = item.main.temp.toString()
                tvCityWeatherMain.text = item.weather[0].main
                tvFeelsLike.text = item.main.feels_like.toString()

                when(item.weather[0].main){
                    "Rain" -> ivWeatherIc.setBackgroundResource(R.drawable.ic_rain_foreground)
                    "Clouds" -> ivWeatherIc.setBackgroundResource(R.drawable.ic_cloudy_foreground)
                    "Clear" -> ivWeatherIc.setBackgroundResource(R.drawable.ic_sun_foreground)
                    "Snow" -> ivWeatherIc.setBackgroundResource(R.drawable.ic_sun_foreground)
                }
            }


            binding.root.setOnClickListener{
                openTempDetail(item)
            }


        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherAdapter.WeatherViewHolder {
        return WeatherViewHolder(
            CityTempItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}
