package com.example.reggie_takeout.dto;


import com.example.reggie_takeout.entity.Setmeal;
import com.example.reggie_takeout.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
