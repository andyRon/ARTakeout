package com.andyron.takeout.dto;

import com.andyron.takeout.entity.Setmeal;
import com.andyron.takeout.entity.SetmealDish;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
@ApiModel("套餐传输数据")
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    @ApiModelProperty("分类名")
    private String categoryName;
}
