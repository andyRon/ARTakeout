package com.andyron.takeout.dto;

import com.andyron.takeout.entity.Setmeal;
import com.andyron.takeout.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
