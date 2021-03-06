package cn.edu.zucc.graduationproject.Service;

import cn.edu.zucc.graduationproject.Dao.IngredientDao;
import cn.edu.zucc.graduationproject.JavaBean.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    IngredientDao ingredientDao;
    public List<Ingredient> getalligd(){
        return ingredientDao.getAllIngredient();
    }

    public void createingredient(String ingredientid, String ingredientname,int ingredientnum, int ingredientmaxnum){
//        ingredientDao.createingredient(ingredientid,ingredientname,ingredientnum,ingredientmaxnum);
        Ingredient ingredient=new Ingredient();
        ingredient.setIngredientid(ingredientid);
        ingredient.setIngredientname(ingredientname);
        ingredient.setIngredientnum(ingredientnum);
        ingredient.setIngredientmaxnum(ingredientmaxnum);
        ingredientDao.save(ingredient);
    }

    public void updateingredient(String ingredientid, String ingredientname,int ingredientnum, int ingredientmaxnum){
        Ingredient ingredient=new Ingredient();
        ingredient.setIngredientid(ingredientid);
        ingredient.setIngredientname(ingredientname);
        ingredient.setIngredientnum(ingredientnum);
        ingredient.setIngredientmaxnum(ingredientmaxnum);
        ingredientDao.save(ingredient);
    }

    public void deleteingredient(String ingredientid){
        ingredientDao.deleteingredient(ingredientid);
    }
}
