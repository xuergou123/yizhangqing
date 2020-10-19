import axios from './$http'
import base from './base'

const demo = {
    eggDemo() {
        return axios.get(`${base.egg}/accout/hello`);
    },
    addCost(userid, costType, costValue, accoutType) {
        return axios.post(`${base.url}/accout/addCost`,
            { userId: userid, costType: costType, costValue: costValue, accoutType: accoutType });
    },
    configBudget(userid, budgetConfig) {
        return axios.post(`${base.url}/accout/configBudget`,
            { userId: userid, budgetConfig: budgetConfig });
    },
    queryBillByDate(userId, date, dateType, page, size) {
        return axios.get(`${base.url}/accout/queryBillByDate`, {
            params: { userId: userId, date: date, dateType: dateType, page: page, size: size }
        })
    },
    addCostType(userId, costBigType, costType, costName) {
        return axios.post(`${base.url}/accout/addCostType`,
            { userId: userId, costBigType: costBigType, costType: costType, costName: costName });
    },
    queryCostType(userId, costBigType) {
        return axios.get(`${base.url}/accout/queryCostType`, {
            params: { userId: userId, costBigType: costBigType }
        })
    },
    configCost(userId,accoutType, accoutValue, accoutName) {
        return axios.post(`${base.url}/accout/configCost`,
            { userId: userId, accoutType: accoutType, accoutValue: accoutValue, accoutName: accoutName });
    },
    
}

export default demo;