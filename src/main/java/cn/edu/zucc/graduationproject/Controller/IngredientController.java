package cn.edu.zucc.graduationproject.Controller;

import cn.edu.zucc.graduationproject.Service.IngredientService;
import cn.edu.zucc.graduationproject.util.GsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IngredientController {
    private final static Logger logger = LoggerFactory.getLogger(IngredientController.class);
    @Autowired
    IngredientService ingredientService;
    @RequestMapping(value = "/ingredientmanage")
    public String getallIngredient(ModelMap map){
        map.put("ingredientlist", ingredientService.getalligd());
        return "ingredientmanager";
    }

    @RequestMapping(value = "/ingredientupdate")
    public String toupdateingredient(String igdid,ModelMap map){
        if (igdid!=null){
            map.put("igdid",igdid);
        }
        return "ingredientupdate";
    }

    @RequestMapping(value = "/ingredientupdate/update")
    public String createingredient(String igdid,String igdname,String stock,String maxstock,ModelMap map){
        int stocknum=Integer.parseInt(stock);
        int maxstocknum=Integer.parseInt(maxstock);
        ingredientService.createingredient(igdid,igdname,stocknum,maxstocknum);
        return toupdateingredient(null,map);
    }


    @ResponseBody
    @RequestMapping(value = "/ingredientdelete")
    public void deleteingredient(String igdid,ModelMap map){
        try {
            ingredientService.deleteingredient(igdid);
        }catch (Exception e){
            logger.warn("删除配料出错，配料id：{}",igdid,e);
        }
    }
}
