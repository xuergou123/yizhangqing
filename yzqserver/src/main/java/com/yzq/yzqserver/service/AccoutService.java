package com.yzq.yzqserver.service;

import com.yzq.yzqserver.utils.PageUtils;

import java.util.Map;

public interface AccoutService {

    PageUtils queryAccout(String accoutType, String userId);

    PageUtils addCost(String accoutType, String userId, String costType, String costValue);

    PageUtils queryBillByDate(String date, String userId, String dateType,int page, int size);

    Map<String,Object> configBudget(String userId, String budgetConfig);

    Map<String, Object> queryBudget(String userId, String month);

    PageUtils queryCostType(String costBigType, String userId);

    Map<String,Object>  addCostType(String costBigType, String userId, String COST_TYPE, String COST_NAME);

    Map<String, Object>  configCost(String accoutType, String userId, String accoutValue, String accoutName);

    PageUtils queryCost(String userId, String accoutType);
}
