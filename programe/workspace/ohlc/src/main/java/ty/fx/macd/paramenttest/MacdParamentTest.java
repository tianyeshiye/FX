package ty.fx.macd.paramenttest;

import ty.fx.macd.bean.MacdDataBean;

public class MacdParamentTest extends MacdParamentTestParent {

	public MacdParamentTest(int decimalPointPara, int zhiyingPoint) {

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
	
	// 止盈， 出现金叉死叉后止盈
	@Override
	protected boolean isTargetProfit(MacdDataBean currentBean, MacdDataBean before1Bean, MacdDataBean before2Bean) {

		switch (this.jinChangType) {
		case HOLD_NULL:
			// 空仓
			break;
		case JIN_Duo:
			// 进 - 买

			// 出现死叉
			if (currentBean.getDif() < currentBean.getDea()) {
				return true;
			}

			break;
		case JIN_Kong:
			// 进 - 卖

			// 出现金叉
			if (currentBean.getDif() > currentBean.getDea()) {
				return true;
			}

			break;
		}

		return false;
	}

	@Override
	protected boolean isStopLoss(MacdDataBean currentBean, MacdDataBean before1Bean, MacdDataBean before2Bean) {
		// TODO Auto-generated method stub
		return false;
	}

}
