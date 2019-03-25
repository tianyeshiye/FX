package ty.fx.macd.eapoint;

import ty.fx.macd.bean.MacdDataBean;

public class MacdPNSwitchPoint extends MacdPNPointParent {

	public MacdPNSwitchPoint(int decimalPointPara, int zhiyingPoint) {

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
}
