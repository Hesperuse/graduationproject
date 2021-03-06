package cn.edu.zucc.graduationproject.Controller;

import cn.edu.zucc.graduationproject.Service.OrderService;
import cn.edu.zucc.graduationproject.Service.ProductService;
import cn.edu.zucc.graduationproject.util.ElmUtil;
import eleme.openapi.sdk.api.entity.order.OGoodsItem;
import eleme.openapi.sdk.api.entity.order.OOrder;
import eleme.openapi.sdk.api.entity.order.OrderList;
import eleme.openapi.sdk.api.entity.product.OItem;
import eleme.openapi.sdk.api.enumeration.order.OOrderStatus;
import eleme.openapi.sdk.api.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrderManager {
    private final static Logger logger = LoggerFactory.getLogger(OrderManager.class);
    @Autowired
    ElmUtil elmUtil;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/ordermanager")
    public String getallorder(String date, String orderid,ModelMap map){
        if (date==null){
            Date nowdate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date=sdf.format(nowdate);
        }
        map.put("date",date);
        OrderList orderlist=new OrderList();
        try{
            orderlist=orderService.getAllOrder(date);
        }catch (Exception e){
            logger.warn("获取全部订单出错",e);
        }

        List<String> nckorderlist=null;
        try {
            nckorderlist=orderService.getallnotcheckorder();
        } catch (ServiceException e) {
            logger.warn("获取全部未处理订单出错",e);
        }

        if (nckorderlist!=null){
            for (int i=0;i<nckorderlist.size();i++){
                OOrder oOrder=null;
                try {
                    oOrder=orderService.getOrderByid(nckorderlist.get(i));
                } catch (ServiceException e) {
                    logger.warn("获取单个订单详细信息出错");
                }
                if (oOrder!=null){
                    orderlist.getList().add(oOrder);
                }
            }
        }
        List<Map<String,Object>> ordermsglist=new ArrayList<>();
        List<Map<String,Object>> ordermsgmap=new ArrayList<>();
        if(orderlist!=null){
            for (int i=0;i<orderlist.getList().size();i++){
                OOrder oOrder=orderlist.getList().get(i);

                Map<String,Object> ordermsg=new HashMap<>();
                ordermsg.put("orderid",oOrder.getId());

                Map<String,Object> nameandprice=new HashMap<>();
                for (int j=0;j<oOrder.getGroups().get(0).getItems().size();j++){
                    OGoodsItem items=oOrder.getGroups().get(0).getItems().get(j);
                    nameandprice.put(items.getName(),items.getQuantity());
                }

                ordermsg.put("nameandprice",nameandprice);
                ordermsg.put("address",oOrder.getAddress());
                OOrderStatus status=oOrder.getStatus();
//                if (status..equals(status)) {
//                    ordermsg.put("status","未确认订单");
//                }else if ("valid".equals(status)){
//                    ordermsg.put("status","已确认订单");
//                }else if ("invalid".equals(status)){
//                    ordermsg.put("status","已取消订单");
//                }else if ("pending".equals(status)){
//                    ordermsg.put("status","未生效订单");
//                }else if ("settled".equals(status)){
//                    ordermsg.put("status","已完成订单");
//                }
                ordermsg.put("status",status);
                if (oOrder.getDeliverTime()!=null) {
                    ordermsg.put("deliverTime", oOrder.getDeliverTime().toString());
                }else{
                    ordermsg.put("deliverTime", "暂无预估时间");
                }
                ordermsglist.add(ordermsg);
            }

            if (orderid!=null){
                if (!"".equals(orderid)){
                    for (int i=0;i<ordermsglist.size();i++){
                        if (ordermsglist.get(i).get("orderid").equals(orderid)){
                            ordermsgmap.add(ordermsglist.get(i));
                        }
                    }
                    map.put("orderlist",ordermsgmap);
                }else{
                    map.put("orderlist",ordermsglist);
                }
                return "ordermanager";
            }
        }
        map.put("ordererrormsg","dasdadadas");
        map.put("orderlist",ordermsglist);
        return "ordermanager";
    }

    @RequestMapping(value = "/ordermsg")
    public String getordermsgbyid(String orderid,ModelMap map){
        if (orderid!=null){
            OOrder oOrder=new OOrder();
            try {
                oOrder=orderService.getOrderByid(orderid);
            } catch (ServiceException e) {
                logger.warn("获取单个订单详细信息出错");
            }
            Map<String,Object> ordermsg=new HashMap<>();
            if (oOrder!=null){
                ordermsg.put("orderid",oOrder.getId());

                Map<String,Object> nameandprice=new HashMap<>();
                for (int j=0;j<oOrder.getGroups().get(0).getItems().size();j++){
                    OGoodsItem items=oOrder.getGroups().get(0).getItems().get(j);
                    nameandprice.put(items.getName(),items.getQuantity());
                }

                ordermsg.put("nameandprice",nameandprice);
                ordermsg.put("address",oOrder.getAddress());

                Map<String,String> timemap=new HashMap<>();
                if (oOrder.getDeliverTime()!=null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    timemap.put("预估时间", sdf.format(oOrder.getDeliverTime()));
                }else{
                    timemap.put("预估时间", "暂无预估时间");
                }
                if (oOrder.getCreatedAt()!=null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    timemap.put("下单时间", sdf.format(oOrder.getCreatedAt()));
                }else{
                    timemap.put("下单时间", "暂无下单时间");
                }
                if (oOrder.getActiveAt()!=null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    timemap.put("订单生效时间", sdf.format(oOrder.getActiveAt()));
                }else{
                    timemap.put("订单生效时间", "暂无生效时间");
                }
                ordermsg.put("timemap",timemap);
                ordermsg.put("income",oOrder.getIncome());
            }
            map.put("orderlist",ordermsg);
        }
        return "ordermsg";
    }

    @ResponseBody
    @RequestMapping(value = "/sureorder")
    public Map<String,String> sureorder(String orderid){
        Map<String,String> msg=new HashMap<>();
        try {
            orderService.sureOrder(orderid);
            msg.put("ordererrormsg","确认订单成功");
        } catch (ServiceException e) {
            logger.warn("确认订单出错，错误信息",e);
            msg.put("ordererrormsg","确认订单"+orderid+"出错，错误信息:"+e.getMessage());
        }
        if ("确认订单成功".equals(msg.get("ordererrormsg"))){
            OOrder oOrder=new OOrder();
            try {
                oOrder=orderService.getOrderByid(orderid);
            } catch (ServiceException e) {
                logger.warn("获取单个订单详细信息出错");
            }
//            if (oOrder!=null){
//                Map<Long,Object> nameandprice=new HashMap<>();
//                for (int j=0;j<oOrder.getGroups().get(0).getItems().size();j++){
//                    OGoodsItem items=oOrder.getGroups().get(0).getItems().get(j);
//                    nameandprice.put(items.getId(),items.getQuantity());
//                }
//                for (Map.Entry<Long,Object> entry:nameandprice.entrySet()){
//                    OItem oItem=null;
//                    try {
//                       oItem=productService.getproductbyid(entry.getKey());
//                    } catch (ServiceException e) {
//                        logger.warn("获取单个商品出错",e);
//                    }
//                    if (oItem!=null){
//                        List<String> materials=oItem.getAttributes()
//                    }
//                }
//            }
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/cancelorder")
    public void cancelorder(String orderid,ModelMap map){
        try {
            orderService.cancelOrder(orderid);
        } catch (Exception e) {
            logger.warn("取消订单出错，错误信息",e);
            map.put("ordererrormsg","取消订单"+orderid+"出错，错误信息:"+e.getMessage());
        }
    }
}
