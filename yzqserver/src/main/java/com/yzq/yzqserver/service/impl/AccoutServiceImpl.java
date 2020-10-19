package com.yzq.yzqserver.service.impl;


import com.yzq.yzqserver.dao.AccoutDao;
import com.yzq.yzqserver.service.AccoutService;
import com.yzq.yzqserver.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("AccoutService")
public class AccoutServiceImpl implements AccoutService {

    @Autowired
    private AccoutDao accoutDao;

    @Override
    public PageUtils queryAccout(String accoutType, String userId) {

        return new PageUtils(accoutDao.queryAccout(accoutType, userId), 0, 0, 0);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public PageUtils addCost(String accoutType, String userId, String costType, String costValue) {
        int result = accoutDao.addCost(accoutType, userId, costType, costValue);
        if (result <= 0) {
            return null;
        }

        String month = "2020-9";
        double budget = accoutDao.queryBudget(userId, month);

        budget -= Double.valueOf(costValue);

        int updateBudget = accoutDao.updateBudget(userId, budget, month);

        double accoutBalance = accoutDao.queryAccoutBalance(userId, accoutType);

        accoutBalance -= Double.valueOf(costValue);

        int updateAccoutBalance = accoutDao.updateAccoutBalance(userId, accoutBalance, accoutType);


        Map<String, Object> content = new HashMap<>();
        content.put("result", result);
        List<Map<String, Object>> r = new LinkedList<>();


        return new PageUtils(null, 0, 0, 0);
    }

    @Override
    public PageUtils queryBillByDate(String date, String userId, String dateType, int page, int size) {
        List<Map<String, Object>> r = new LinkedList<>();
        if ("日".equals(dateType)) {
            r = accoutDao.queryBillByDay(date, userId, dateType, (page - 1) * size, size);
        } else if ("月".equals(dateType)) {
            r = accoutDao.queryBillByMonth(date, userId, dateType, (page - 1) * size, size);
        } else if ("季度".equals(dateType)) {
            r = accoutDao.queryBillByQuarter(date, userId, dateType, (page - 1) * size, size);
        } else if ("年".equals(dateType)) {

        }
        return new PageUtils(r, 0, size, page);
    }

    @Override
    public Map<String, Object> configBudget(String userId, String budgetConfig) {
        int r = accoutDao.updateConfig(userId, "budgetConfig", budgetConfig);
        Map<String, Object> data = new HashMap<>();
        data.put("updateResult", r);
        return data;
    }

    @Override
    public Map<String, Object> queryBudget(String userId, String month) {
        double r = 0;
        r = accoutDao.queryBudget(userId, month);
        Map<String, Object> data = new HashMap<>();
        data.put("updateResult", r);
        return data;
    }

    @Override
    public PageUtils queryCostType(String costBigType, String userId) {
        if ("pay".equals(costBigType)) {
            costBigType = "pay" + "%";
        } else if ("income".equals(costBigType)) {
            costBigType = "income" + "%";
        } else {
            return null;
        }
        List<Map<String, Object>> r = accoutDao.queryCostType(costBigType, userId);
        return new PageUtils(r, 0, 0, 0);
    }

    @Override
    public Map<String, Object> addCostType(String costBigType, String userId, String COST_TYPE, String COST_NAME) {
        int r = accoutDao.addCostType(costBigType, userId, COST_TYPE, COST_NAME);
        Map<String, Object> result = new HashMap<>();
        result.put("updateResult", r);
        return result;
    }

    @Override
    public Map<String, Object>  configCost(String accoutType, String userId, String accoutValue, String accoutName) {
        int count = accoutDao.isAccoutExist(userId, accoutType);
        int r = 0;
        if (count <= 0) {
            r = accoutDao.addAccoutType(accoutType, userId, accoutValue, accoutName);
        }else {
            r = accoutDao.updateAccout(accoutType, userId, accoutValue, accoutName);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("updateResult", r);
        return result;
    }

    @Override
    public PageUtils queryCost(String userId, String accoutType) {
        List<Map<String, Object>> r;
        if(accoutType == null || "".equals(accoutType)){
            r = accoutDao.queryCost(accoutType, userId);
        }else{
            r = accoutDao.queryCostByaccoutType(accoutType, userId);
        }
        return new PageUtils(r, 0, 0, 0);
    }
}
