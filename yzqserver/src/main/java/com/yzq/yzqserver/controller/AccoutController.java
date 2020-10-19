package com.yzq.yzqserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yzq.yzqserver.service.AccoutService;
import com.yzq.yzqserver.utils.PageUtils;
import com.yzq.yzqserver.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "accout/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccoutController {

    @Autowired
    private AccoutService accoutService;

    @RequestMapping(value = "/hello")
    public String sayHello() {
        return "hello";
    }


    @RequestMapping("/list")
    //@RequiresPermissions("order:orderitem:list")
    public R list(@RequestParam("accouttype") String accoutType, @RequestParam("userId") String userId) {
        PageUtils page = accoutService.queryAccout(accoutType, userId);

        return R.ok().put("page", page);
    }

    @PostMapping("/addCost")
    //@RequiresPermissions("order:orderitem:list")@RequestParam("userId") String userId, @RequestParam("costType") String costType,
    //                  @RequestParam("costVlaue") String costValue, @RequestParam("accoutType") String accoutType
    public R addCost(@RequestBody JSONObject params) {
        String accoutType = params.getString("accoutType");
        String userId = params.getString("userId");
        String costType = params.getString("costType");
        String costValue = params.getString("costValue");
        PageUtils page = accoutService.addCost(accoutType, userId,costType,costValue);

        return R.ok().put("page", page);
    }


    @PostMapping("/configCost")
    //@RequiresPermissions("order:orderitem:list")@RequestParam("userId") String userId, @RequestParam("costType") String costType,
    //                  @RequestParam("costVlaue") String costValue, @RequestParam("accoutType") String accoutType
    public R configCost(@RequestBody JSONObject params) {
        String accoutType = params.getString("accoutType");
        String userId = params.getString("userId");
        String accoutValue = params.getString("accoutValue");
        String accoutName = params.getString("accoutName");
        Map<String, Object>  page = accoutService.configCost(accoutType, userId,accoutValue,accoutName);

        return R.ok().put("page", page);
    }

    @GetMapping("/queryCost")
    public R queryCost(@RequestParam("userId") String userId, @RequestParam("accoutType") String accoutType) {
        PageUtils page = accoutService.queryCost(userId,accoutType);

        return R.ok().put("data", page);
    }



    @PostMapping("/configBudget")
    public R configBudget(@RequestBody JSONObject params) {
        String budgetConfig = params.getString("budgetConfig");
        String userId = params.getString("userId");
        Map<String,Object> page = accoutService.configBudget(userId,budgetConfig);

        return R.ok().put("data", page);
    }

    @GetMapping("/queryBudget")
    public R queryBudget(@RequestParam("month") String month, @RequestParam("userId") String userId) {
        Map<String,Object> page = accoutService.queryBudget(userId,month);

        return R.ok().put("data", page);
    }


    @RequestMapping("/queryBillByDate")
    //@RequiresPermissions("order:orderitem:list")
    public R queryBillByDate(@RequestParam("userId") String userId, @RequestParam("date") String date,
                              @RequestParam("dateType") String dateType,@RequestParam(value = "page",defaultValue = "1") int page,
                              @RequestParam(value = "size",defaultValue = "10") int size ) {
        PageUtils r = accoutService.queryBillByDate(date, userId,dateType,page,size);

        return R.ok().put("page", r);
    }

    @RequestMapping("/queryCostType")
    public R queryCostType(@RequestParam("userId") String userId, @RequestParam("costBigType") String costBigType ) {
        PageUtils r = accoutService.queryCostType(costBigType, userId);
        return R.ok().put("page", r);
    }

    @RequestMapping("/addCostType")
    public R addCostType(@RequestParam("userId") String userId, @RequestParam("costBigType") String costBigType,
            @RequestParam("COST_TYPE") String COST_TYPE, @RequestParam("COST_NAME") String COST_NAME) {
        Map<String,Object>  r = accoutService.addCostType(costBigType, userId, COST_TYPE, COST_NAME);
        return R.ok().put("page", r);
    }

}
