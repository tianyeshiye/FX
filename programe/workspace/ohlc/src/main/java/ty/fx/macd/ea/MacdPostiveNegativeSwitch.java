package ty.fx.macd.ea;

import ty.fx.macd.bean.MacdDataBean;

public class MacdPostiveNegativeSwitch extends MacdParent {

	public MacdPostiveNegativeSwitch(int decimalPointPara, int zhiyingPoint) {

		super.initConstructor(decimalPointPara, zhiyingPoint);
	}

	public void initData() {
		super.initData();
	}

	// 牛
	@Override
	public boolean isBull(MacdDataBean currentBean, MacdDataBean before1Bean, MacdDataBean before2Bean) {

		// 由 负 -> 正
		if ((currentBean.getDif() > 0 && before1Bean.getDif() == 0 && before2Bean.getDif() < 0)
				|| (currentBean.getDif() > 0 && before1Bean.getDif() < 0)) {
			return true;
		} else {
			return false;
		}
	}

	// 熊
	@Override
	public boolean isBear(MacdDataBean currentBean, MacdDataBean before1Bean, MacdDataBean before2Bean) {

		// 由 正 -> 负
		if ((currentBean.getDif() < 0 && before1Bean.getDif() == 0 && before2Bean.getDif() > 0)
				|| (currentBean.getDif() < 0 && before1Bean.getDif() > 0)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected boolean isStopLoss(MacdDataBean currentBean, MacdDataBean before1Bean, MacdDataBean before2Bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isTargetProfit(MacdDataBean currentBean, MacdDataBean before1Bean, MacdDataBean before2Bean) {
		// TODO Auto-generated method stub
		return false;
	}

	// public List<String> ea(List<MacdDataBean> beanList, List<MacdResultBean>
	// resulList) {
	//
	// initData();
	//
	// List<String> logList = new ArrayList<String>();
	//
	// for (int i = 0; i < beanList.size(); i++) {
	//
	// this.timesInterval++;
	//
	// MacdDataBean before1Bean;
	// MacdDataBean before2Bean;
	//
	// if (i == 0) {
	// before1Bean = Macds.getNullBean();
	// before2Bean = Macds.getNullBean();
	// } else if (i == 1) {
	//
	// before1Bean = beanList.get(0);
	// before2Bean = Macds.getNullBean();
	// } else {
	// before1Bean = beanList.get(i - 1);
	// before2Bean = beanList.get(i - 2);
	// }
	//
	// MacdDataBean currentBean = beanList.get(i);
	//
	// // 由 负 -> 正
	// if (isBull(currentBean, before1Bean, before2Bean)) {
	//
	// if (jinChangBean != null) {
	// // 已持有
	// // 出场 - 买
	// chuChang(currentBean, TradeType.CHU_Duo, logList, resulList);
	// }
	// // 进场 - 买
	// jinChang(currentBean, TradeType.JIN_Duo, logList);
	// }
	// // 由 正 -> 负
	// if (isBear(currentBean, before1Bean, before2Bean)) {
	//
	// if (jinChangBean != null) {
	// // 已持有
	// // 出场 - 卖
	// chuChang(currentBean, TradeType.CHU_Kong, logList, resulList);
	// }
	// // 进场 - 卖
	// jinChang(currentBean, TradeType.JIN_Kong, logList);
	// }
	// }
	//
	// print(beanList, resulList, logList);
	//
	// return logList;
	// }

}
