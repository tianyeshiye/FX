package ty.fx.movingaverage.ea;

import ty.fx.bean.CrossType;
import ty.fx.movingaverage.bean.MovingAverBean;

public class MovingAverEa extends  MovingParentEa {

	public MovingAverEa(int decimalPointPara, int zhiyingPoint) {

		super.initConstructor(decimalPointPara, zhiyingPoint);
	}

	public void initData() {
		super.initData();
	}

	private boolean isGoldOfCurrentStatus(CrossType gold) {

		if (gold.equals(CrossType.GOLD) || gold.equals(CrossType.NULL)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isDeadOfCurrentStatus(CrossType dead) {

		if (dead.equals(CrossType.DEAD) || dead.equals(CrossType.NULL)) {
			return true;
		} else {
			return false;
		}
	}

	private CrossType currentCrossType = CrossType.NULL;

	// 金叉 牛
	@Override
	public boolean isBull(MovingAverBean currentBean, MovingAverBean before1Bean, MovingAverBean before2Bean) {

		if (currentBean.getFastMovingAver() > currentBean.getSlowMovingAver() && isDeadOfCurrentStatus(currentCrossType)) {

			currentCrossType = CrossType.GOLD;
			return true;
		} else {
			return false;
		}
	}

	// 死叉 熊
	@Override
	public boolean isBear(MovingAverBean currentBean, MovingAverBean before1Bean, MovingAverBean before2Bean) {

		if (currentBean.getFastMovingAver() < currentBean.getSlowMovingAver() && isGoldOfCurrentStatus(currentCrossType)) {

			currentCrossType = CrossType.DEAD;
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected boolean isStopLoss(MovingAverBean currentBean, MovingAverBean before1Bean, MovingAverBean before2Bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isTargetProfit(MovingAverBean currentBean, MovingAverBean before1Bean, MovingAverBean before2Bean) {
		// TODO Auto-generated method stub
		return false;
	}
}
