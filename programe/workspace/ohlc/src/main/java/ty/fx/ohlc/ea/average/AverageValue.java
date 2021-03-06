package ty.fx.ohlc.ea.average;

import java.math.BigDecimal;
import java.util.List;

import ty.fx.bean.TimeDataBaseBean;

public class AverageValue {

	public AverageValue(int decimalPointPara) {
		DECIMAL_POINT = decimalPointPara;
	}

	public static int DECIMAL_POINT = 0;

	public float ea(List<TimeDataBaseBean> beanList) {

		BigDecimal sum = new BigDecimal(0).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);

		for (int i = 0; i < beanList.size(); i++) {

			TimeDataBaseBean bean = beanList.get(i);

			BigDecimal high = new BigDecimal(bean.getHigh()).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);
			BigDecimal low = new BigDecimal(bean.getLow()).setScale(DECIMAL_POINT, BigDecimal.ROUND_DOWN);

			BigDecimal differ = high.subtract(low);

			sum = sum.add(differ);
		}

		BigDecimal count = new BigDecimal(beanList.size()).setScale(0, BigDecimal.ROUND_DOWN);

		float average = sum.divide(count, DECIMAL_POINT, BigDecimal.ROUND_DOWN).floatValue();

		return average;
	}

}
