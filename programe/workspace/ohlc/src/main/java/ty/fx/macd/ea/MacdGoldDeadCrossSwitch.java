package ty.fx.macd.ea;

import ty.fx.bean.MacdCrossType;
import ty.fx.macd.bean.MacdDataBean;

public class MacdGoldDeadCrossSwitch extends MacdParaent {

	public MacdGoldDeadCrossSwitch(int decimalPointPara) {
		DECIMAL_POINT = decimalPointPara;
	}

	public void initData() {
		super.initData();
	}

	private boolean isGoldCrossOfCurrent(MacdCrossType gold) {

		if (gold.equals(MacdCrossType.GOLD) || gold.equals(MacdCrossType.NULL)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isDeadCrossOfCurrent(MacdCrossType dead) {

		if (dead.equals(MacdCrossType.DEAD) || dead.equals(MacdCrossType.NULL)) {
			return true;
		} else {
			return false;
		}
	}

	private MacdCrossType currentMacdCrossType = MacdCrossType.NULL;
	
	// 金叉 牛
	@Override
	public boolean isBull(MacdDataBean currentBean, MacdDataBean before1Bean, MacdDataBean before2Bean) {

		if (currentBean.getDif() > currentBean.getDea() && isDeadCrossOfCurrent(currentMacdCrossType)) {

			currentMacdCrossType = MacdCrossType.GOLD;
			return true;
		} else {
			return false;
		}
	}

	// 死叉 熊
	@Override
	public boolean isBear(MacdDataBean currentBean, MacdDataBean before1Bean, MacdDataBean before2Bean) {

		if (currentBean.getDif() < currentBean.getDea() && isGoldCrossOfCurrent(currentMacdCrossType)) {

			currentMacdCrossType = MacdCrossType.DEAD;
			return true;
		} else {
			return false;
		}
	}

//	public List<String> ea(List<MacdDataBean> beanList, List<MacdResultBean> resulList) {
//
//		initData();
//
//		List<String> logList = new ArrayList<String>();
//
//		for (int i = 0; i < beanList.size(); i++) {
//
//			this.timesInterval++;
//
//			MacdDataBean before1Bean;
//			MacdDataBean before2Bean;
//
//			if (i == 0) {
//				before1Bean = Macds.getNullBean();
//				before2Bean = Macds.getNullBean();
//			} else if (i == 1) {
//
//				before1Bean = beanList.get(0);
//				before2Bean = Macds.getNullBean();
//			} else {
//				before1Bean = beanList.get(i - 1);
//				before2Bean = beanList.get(i - 2);
//			}
//
//			MacdDataBean currentBean = beanList.get(i);
//
//			// 金叉
//			if (currentBean.getDif() > currentBean.getDea() && isDeadCrossOfCurrent(currentMacdCrossType)) {
//
//				currentMacdCrossType = MacdCrossType.GOLD;
//
//				if (jinChangBean != null) {
//					// 已持有
//					// 出场 - 买
//					chuChang(currentBean, TradeType.CHU_Duo, logList, resulList);
//				}
//				// 进场 - 买
//				jinChang(currentBean, TradeType.JIN_Duo, logList);
//			}
//
//			// 死叉
//			if (currentBean.getDif() < currentBean.getDea() && isGoldCrossOfCurrent(currentMacdCrossType)) {
//				currentMacdCrossType = MacdCrossType.DEAD;
//
//				if (jinChangBean != null) {
//					// 已持有
//					// 出场 - 卖
//					chuChang(currentBean, TradeType.CHU_Kong, logList, resulList);
//				}
//				// 进场 - 卖
//				jinChang(currentBean, TradeType.JIN_Kong, logList);
//			}
//		}
//
//		print(beanList, resulList, logList);
//
//		return logList;
//	}

}
