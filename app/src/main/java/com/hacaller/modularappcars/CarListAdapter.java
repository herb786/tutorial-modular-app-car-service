package com.hacaller.modularappcars;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.hacaller.abusiness.CarCaseHermes;
import com.hacaller.business.Car;
import com.hacaller.business.CarUseCase;
import com.hacaller.modularappcars.databinding.CarItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Herbert Caller on 05/11/2018.
 */
public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarViewHolder> {

    List<Car> carList = new ArrayList<>();
    CarCaseHermes carCaseExecutor;

    public void setCarList(List<Car> cars) {
        carList.clear();
        carList.addAll(cars);
        notifyDataSetChanged();
    }

    public void setCarCaseExecutor(CarCaseHermes carCaseExecutor) {
        this.carCaseExecutor = carCaseExecutor;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =  LayoutInflater.from(viewGroup.getContext());
        CarItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.car_item,
                viewGroup, false);
        return new CarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder carViewHolder, int i) {
        carViewHolder.bind(carList.get(i));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder implements RatingBar.OnRatingBarChangeListener {

        Car car;
        CarItemBinding binding;

        public CarViewHolder(CarItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.carRating.setOnRatingBarChangeListener(this);
        }

        public void bind(Car car){
            this.car = car;
            binding.txtName.setText(car.getBrand());
            binding.txtWebsite.setText(car.getWebsite());
            binding.carRating.setRating(car.getRating());
            if (car.getLogo().length() > 0) Picasso.get().load(car.getLogo()).into(binding.imgCar);
            binding.executePendingBindings();
        }

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            if (carCaseExecutor == null) return;
            car.setRating((int) rating);
            carCaseExecutor.setCarUseCase(CarUseCase.SetCarRating, car);
            carCaseExecutor.setUseCaseObserver(null);
            carCaseExecutor.execute();
        }
    }


}
